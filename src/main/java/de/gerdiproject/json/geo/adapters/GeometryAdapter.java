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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;

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

/**
 * This class offers generic JSON serialization logic for {@linkplain Geometry}s.
 * It is required in order to fix an issue that caused serialized {@linkplain Geometry} objects
 * to disregard their respective {@linkplain PrecisionModel}.
 *
 * @author Robin Weiss
 */
public class GeometryAdapter implements JsonSerializer<Geometry>, JsonDeserializer<Geometry>
{
    private final Locale locale = Locale.ENGLISH;
    private final Map<String, BiFunction<JsonSerializationContext, Geometry, JsonElement>> serializationMap;
    private final Map<String, BiFunction<JsonDeserializationContext, JsonElement, Geometry>> deserializationMap;


    /**
     * Simple Constructor that initializes {@linkplain HashMap}s
     * of (de-)serialization functions.
     */
    public GeometryAdapter()
    {
        this.serializationMap = new HashMap<>();
        addSerializationFunction(Point.class, serializationMap);
        addSerializationFunction(MultiPoint.class, serializationMap);
        addSerializationFunction(LineString.class, serializationMap);
        addSerializationFunction(MultiLineString.class, serializationMap);
        addSerializationFunction(Polygon.class, serializationMap);
        addSerializationFunction(MultiPolygon.class, serializationMap);

        this.deserializationMap = new HashMap<>();
        addDeserializationFunction(Point.class, deserializationMap);
        addDeserializationFunction(MultiPoint.class, deserializationMap);
        addDeserializationFunction(LineString.class, deserializationMap);
        addDeserializationFunction(MultiLineString.class, deserializationMap);
        addDeserializationFunction(Polygon.class, deserializationMap);
        addDeserializationFunction(MultiPolygon.class, deserializationMap);
    }


    private void addSerializationFunction(final Class<?> geoClass, final Map<String, BiFunction<JsonSerializationContext, Geometry, JsonElement>> map)
    {
        map.put(geoClass.getSimpleName().toLowerCase(locale),
                (final JsonSerializationContext context, final Geometry geo)
                -> context.serialize(geo, geoClass));
    }


    private void addDeserializationFunction(final Class<?> geoClass, final Map<String, BiFunction<JsonDeserializationContext, JsonElement, Geometry>> map)
    {
        map.put(geoClass.getSimpleName().toLowerCase(locale),
                (final JsonDeserializationContext context, final JsonElement geo)
                -> context.deserialize(geo, geoClass));
    }


    @Override
    public JsonElement serialize(final Geometry src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        final BiFunction<JsonSerializationContext, Geometry, JsonElement> serializationFunction =
            serializationMap.get(src.getGeometryType().toLowerCase(locale));

        if (serializationFunction == null)
            throw new JsonParseException(String.format(GeometryConstants.UNKNOWN_GEOMETRY_TYPE_ERROR, src.getGeometryType()));
        else
            return serializationFunction.apply(context, src);
    }


    @Override
    public Geometry deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException
    {
        final String geometryType = json.getAsJsonObject().get(GeometryConstants.TYPE_JSON_FIELD).getAsString().toLowerCase(locale);
        final BiFunction<JsonDeserializationContext, JsonElement, Geometry> deserializationFunction =
            deserializationMap.get(geometryType);

        if (deserializationFunction == null)
            throw new JsonParseException(String.format(GeometryConstants.UNKNOWN_GEOMETRY_TYPE_ERROR, geometryType));
        else
            return deserializationFunction.apply(context, json);
    }
}
