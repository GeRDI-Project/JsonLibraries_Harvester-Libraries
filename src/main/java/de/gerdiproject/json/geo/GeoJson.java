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
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * GeoJSON is a format for encoding a variety of geographic data structures.
 *
 * @author Robin Weiss
 */
@EqualsAndHashCode
public class GeoJson implements ICleanable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJson.class);
    private static final Gson GSON = GsonUtils.createGeoJsonGsonBuilder().create();

    @Getter
    private IGeoCoordinates coordinates;

    // exclude this field from serialization, it's only used for performance reasons
    @EqualsAndHashCode.Exclude
    private transient boolean isClean;

    @Getter private String type;


    /**
     * Constructor that sets the coordinates.
     *
     * @param coordinates a IGeoCoordinate implementing object that represents valid GeoJson coordinates
     */
    public GeoJson(final IGeoCoordinates coordinates)
    {
        setCoordinates(coordinates);
    }


    /**
     * Changes the coordinates of the GeoJson. The type is adjusted accordingly.
     *
     * @param coordinates a IGeoCoordinate implementing object that represents valid GeoJson coordinates
     */
    public void setCoordinates(final IGeoCoordinates coordinates)
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
            } catch (final JsonSyntaxException e) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug(String.format(GeoJsonConstants.INVALID_GEOJSON_ERROR, geoJsonString));

                setCoordinates(null);
            }
        }

        return isValid();
    }
}
