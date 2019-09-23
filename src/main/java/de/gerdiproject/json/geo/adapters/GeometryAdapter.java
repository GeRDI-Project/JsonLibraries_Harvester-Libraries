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

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
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
public class GeometryAdapter implements JsonSerializer<Geometry>, JsonDeserializer<Geometry>
{
    @Override
    public JsonElement serialize(final Geometry src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        final String geometryType = src.getGeometryType();

        switch (geometryType) {
            case GeometryConstants.POINT_TYPE:
                return context.serialize(src, Point.class);

            case GeometryConstants.MULTI_POINT_TYPE:
                return context.serialize(src, MultiPoint.class);

            case GeometryConstants.LINE_STRING_TYPE:
                return context.serialize(src, LineString.class);

            case GeometryConstants.MULTI_LINE_STRING_TYPE:
                return context.serialize(src, MultiLineString.class);

            case GeometryConstants.POLYGON_TYPE:
                return context.serialize(src, Polygon.class);

            case GeometryConstants.MULTI_POLYGON_TYPE:
                return context.serialize(src, MultiPolygon.class);

            default:
                throw new JsonParseException(String.format(GeometryConstants.UNKNOWN_GEOMETRY_TYPE_ERROR, geometryType));
        }
    }


    @Override
    public Geometry deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException
    {
        final String geometryType = json.getAsJsonObject().get(GeometryConstants.TYPE_JSON_FIELD).getAsString();

        switch (geometryType) {
            case GeometryConstants.POINT_TYPE:
                return context.deserialize(json, Point.class);

            case GeometryConstants.MULTI_POINT_TYPE:
                return context.deserialize(json, MultiPoint.class);

            case GeometryConstants.LINE_STRING_TYPE:
                return context.deserialize(json, LineString.class);

            case GeometryConstants.MULTI_LINE_STRING_TYPE:
                return context.deserialize(json, MultiLineString.class);

            case GeometryConstants.POLYGON_TYPE:
                return context.deserialize(json, Polygon.class);

            case GeometryConstants.MULTI_POLYGON_TYPE:
                return context.deserialize(json, MultiPolygon.class);

            default:
                throw new JsonParseException(String.format(GeometryConstants.UNKNOWN_GEOMETRY_TYPE_ERROR, geometryType));
        }
    }
}
