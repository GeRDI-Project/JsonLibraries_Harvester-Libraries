/*
 *  Copyright © 2019 Robin Weiss (http://www.gerdi-project.de/)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
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
package de.gerdiproject.json.geo.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;

import de.gerdiproject.json.geo.constants.GeometryConstants;
import lombok.RequiredArgsConstructor;

/**
 * This class offers generic JSON serialization logic for {@linkplain Geometry}s.
 * It is required in order to fix an issue that caused serialized {@linkplain Geometry} objects
 * to disregard their respective {@linkplain PrecisionModel}.
 *
 * @author Robin Weiss
 */
@RequiredArgsConstructor
public class GeometryAdapter <T extends Geometry> implements JsonSerializer<T>
{
    private final double decimalFactor;


    @Override
    public JsonElement serialize(final T src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        final  JsonObject out = new JsonObject();
        out.addProperty(GeometryConstants.TYPE_JSON_FIELD, src.getClass().getSimpleName());
        out.add(GeometryConstants.COORDINATES_JSON_FIELD, serializeCoordinates(src));
        return out;
    }


    /**
     * Serializes the {@linkplain Coordinate}s of a {@linkplain Geometry} to a {@linkplain JsonArray}.
     *
     * @param src a {@linkplain Geometry} the {@linkplain Coordinate}s of which are to be serialized
     *
     * @return a {@linkplain JsonArray} of {@linkplain Coordinate}s
     */
    private JsonArray serializeCoordinates(final Geometry src)
    {
        final JsonArray out;
        final int subGeometryCount = src.getNumGeometries();

        if (subGeometryCount == 1) {
            if (src.getGeometryType().equalsIgnoreCase(GeometryConstants.POINT_TYPE)) {
                // handle Points that only have a single coordinate
                out = coordinateToJsonArray(src.getCoordinate());
            }

            else if (src.getGeometryType().equalsIgnoreCase(GeometryConstants.POLYGON_TYPE)) {
                out = new JsonArray();

                // handle the hull of the Polygon
                final Polygon srcPoly = (Polygon) src;
                out.add(coordinatesToJsonArray(srcPoly.getExteriorRing().getCoordinates()));

                // handle hull holes of the Polygon
                final int holeCount = srcPoly.getNumInteriorRing();

                for (int i = 0; i < holeCount; i++)
                    out.add(coordinatesToJsonArray(srcPoly.getInteriorRingN(i).getCoordinates()));

            } else {
                // handle Geometries that have a consecutive list of coordinates
                out = coordinatesToJsonArray(src.getCoordinates());
            }

        } else {
            // handle composite Geometries
            out = new JsonArray();

            for (int i = 0; i < subGeometryCount; i++)
                out.add(serializeCoordinates(src.getGeometryN(i)));
        }

        return out;
    }


    /**
     * Converts an array of {@linkplain Coordinate}s to a {@linkplain JsonArray}.
     *
     * @param coordinates the {@linkplain Coordinate}s which are to be serialized
     *
     * @return a {@linkplain JsonArray} of {@linkplain Coordinate}s
     */
    private JsonArray coordinatesToJsonArray(final Coordinate... coordinates)
    {
        final JsonArray jsonArray = new JsonArray();

        for (final Coordinate c : coordinates)
            jsonArray.add(coordinateToJsonArray(c));

        return jsonArray;
    }



    /**
     * Converts a single {@linkplain Coordinate} to a {@linkplain JsonArray}.
     *
     * @param coordinate the {@linkplain Coordinate} which is to be serialized
     *
     * @return a {@linkplain JsonArray} that contains {@linkplain Double} values
     */
    private JsonArray coordinateToJsonArray(final Coordinate coordinate)
    {
        final JsonArray jsonArray = new JsonArray();

        if (decimalFactor == Double.POSITIVE_INFINITY) {
            jsonArray.add(coordinate.x);
            jsonArray.add(coordinate.y);

            if (Double.isFinite(coordinate.z))
                jsonArray.add(coordinate.z);
        } else {
            jsonArray.add(Math.round(coordinate.x * decimalFactor) / decimalFactor);
            jsonArray.add(Math.round(coordinate.y * decimalFactor) / decimalFactor);

            if (Double.isFinite(coordinate.z))
                jsonArray.add(Math.round(coordinate.z * decimalFactor) / decimalFactor);
        }

        return jsonArray;
    }
}
