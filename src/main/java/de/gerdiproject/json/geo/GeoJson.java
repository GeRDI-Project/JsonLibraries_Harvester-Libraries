/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package de.gerdiproject.json.geo;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.core.geometry.ogc.OGCGeometry;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.GsonUtils;

/**
 * GeoJSON is a format for encoding a variety of geographic data structures.
 * @author Robin Weiss
 */
public class GeoJson implements ICleanable
{
    private static final String PARSE_FAILED = "Could not simplify GeoJson:\n";
    private static final String INVALID_GEO = "Invalid GeoJson:\n";
    private static final String INVALID_TYPE = "Invalid";
    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJson.class);

    // exclude this field from serialization, it's only used for performance reasons
    private transient boolean isClean;

    private String type;
    private IGeoCoordinates coordinates;

    /**
     * Constructor that sets the coordinates.
     * @param coordinates a IGeoCoordinate implementing object that represents valid GeoJson coordinates
     */
    public GeoJson(IGeoCoordinates coordinates)
    {
        setCoordinates(coordinates);
    }


    /**
     * Changes the coordinates of the GeoJson. The type is adjusted accordingly.
     * @param coordinates a IGeoCoordinate implementing object that represents valid GeoJson coordinates
     */
    public void setCoordinates(IGeoCoordinates coordinates)
    {
        if (coordinates == null)
            this.type = INVALID_TYPE;
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
    public void clean()
    {
        if (!isClean && coordinates != null && (coordinates instanceof Polygon  || coordinates instanceof MultiPolygon)) {
            Gson gson = GsonUtils.getGson();

            String geoJsonString = gson.toJson(this);

            try {
                // map our polygon implementation to the ESRI implementation
                OGCGeometry polygon = OGCGeometry.fromGeoJson(geoJsonString);

                // simplify ESRI polygon and convert it to JSON string
                String simpleGeoString = polygon.makeSimple().asGeoJson();

                // parse JSON string to a new GeoJson object
                GeoJson  cleanedGeo = gson.fromJson(simpleGeoString, GeoJson.class);

                // copy the simplified coordinates
                this.coordinates = cleanedGeo.coordinates;
                this.type = cleanedGeo.type;
                isClean = true;

            } catch (JSONException e) {
                LOGGER.warn(PARSE_FAILED + geoJsonString);
                setCoordinates(null);
            } catch (JsonSyntaxException e) {
                LOGGER.warn(INVALID_GEO + geoJsonString);
                setCoordinates(null);
            }
        }
    }
}
