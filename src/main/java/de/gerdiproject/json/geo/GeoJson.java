/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gerdiproject.json.geo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.core.geometry.ogc.OGCGeometry;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.GsonUtils;
import de.gerdiproject.json.geo.constants.GeoJsonConstants;

/**
 * GeoJSON is a format for encoding a variety of geographic data structures.
 *
 * @author Robin Weiss
 */
public class GeoJson implements ICleanable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJson.class);
    private static final Gson GSON = GsonUtils.createGeoJsonGsonBuilder().create();

    // exclude this field from serialization, it's only used for performance reasons
    private transient boolean isClean;

    private String type;
    private IGeoCoordinates coordinates;


    /**
     * Constructor that sets the coordinates.
     *
     * @param coordinates a IGeoCoordinate implementing object that represents valid GeoJson coordinates
     */
    public GeoJson(IGeoCoordinates coordinates)
    {
        setCoordinates(coordinates);
    }


    /**
     * Changes the coordinates of the GeoJson. The type is adjusted accordingly.
     *
     * @param coordinates a IGeoCoordinate implementing object that represents valid GeoJson coordinates
     */
    public void setCoordinates(IGeoCoordinates coordinates)
    {
        if (coordinates == null)
            this.type = GeoJsonConstants.INVALID_TYPE;
        else
            this.type = coordinates.getClass().getSimpleName();

        this.coordinates = coordinates;
        this.isClean = false;
    }


    /**
     * Returns true if the GeoJson has coordinates.
     *
     * @return true, if the geo json has coordinates
     */
    public boolean isValid()
    {
        return coordinates != null;
    }


    public String getType()
    {
        return type;
    }


    public IGeoCoordinates getCoordinates()
    {
        return coordinates;
    }


    /**
     * Attempts to detect and remove errors in a geoJson object, such as
     * self-intersecting (multi-)polygons. Additionally, MultiPolygons that
     * can be simplified, may become regular Polygons.
     *
     * If the coordinates are broken beyond all repair, they will become null,
     * rendering the GeoJson invalid.
     */
    @Override
    public boolean clean()
    {
        if (!isClean && coordinates != null && (coordinates instanceof Polygon  || coordinates instanceof MultiPolygon)) {

            final String geoJsonString = GSON.toJson(this);

            try {
                // map our polygon implementation to the ESRI implementation
                final OGCGeometry polygon = OGCGeometry.fromGeoJson(geoJsonString);

                // simplify ESRI polygon and convert it to JSON string
                final String simpleGeoString = polygon.makeSimple().asGeoJson();

                // parse JSON string to a new GeoJson object
                final GeoJson  cleanedGeo = GSON.fromJson(simpleGeoString, GeoJson.class);

                // copy the simplified coordinates
                setCoordinates(cleanedGeo.coordinates);
                this.isClean = true;
            } catch (JsonSyntaxException e) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug(String.format(GeoJsonConstants.INVALID_GEOJSON_ERROR, geoJsonString));

                setCoordinates(null);
            }
        }

        return isValid();
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof GeoJson))
            return false;

        GeoJson other = (GeoJson) obj;

        if (coordinates == null) {
            if (other.coordinates != null)
                return false;
        } else if (!coordinates.equals(other.coordinates))
            return false;

        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;

        return true;
    }
}
