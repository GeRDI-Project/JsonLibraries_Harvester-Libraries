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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

import de.gerdiproject.json.geo.constants.GeometryConstants;
import lombok.RequiredArgsConstructor;

/**
 * This class offers generic JSON serialization logic for {@linkplain Geometry}s.
 * It is required in order to fix issues that cause serialized {@linkplain Geometry} objects
 * to disregard their respective {@linkplain PrecisionModel}, and
 * deserialized {@linkplain Geometry} to throw an exception if the coordinate arrays are empty.
 *
 * @author Robin Weiss
 */
@RequiredArgsConstructor
public abstract class AbstractGeometryAdapter <T extends Geometry> implements JsonSerializer<T>, JsonDeserializer<T>
{
    private final String geometryType;
    private final double decimalFactor;
    private final GeometryFactory geoFactory;


    /**
     * Simple Constructor.
     *
     * @param geoFactory the {@linkplain GeometryFactory} used to create deserialized objects
     */
    @SuppressWarnings("unchecked")
    public AbstractGeometryAdapter(final GeometryFactory geoFactory)
    {
        this.geoFactory = geoFactory;
        this.geometryType = ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();

        if (geoFactory.getPrecisionModel().getType() == PrecisionModel.FIXED)
            decimalFactor = geoFactory.getPrecisionModel().getScale() * 10.0;
        else
            decimalFactor = Double.POSITIVE_INFINITY;
    }


    @Override
    public JsonElement serialize(final T src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        final JsonArray coordinates = serializeCoordinates(src);

        if (coordinates == null)
            return JsonNull.INSTANCE;
        else {
            final JsonObject out = new JsonObject();
            out.addProperty(GeometryConstants.TYPE_JSON_FIELD, geometryType);
            out.add(GeometryConstants.COORDINATES_JSON_FIELD, coordinates);
            return out;
        }
    }


    @Override
    public T deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException
    {
        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonElement coordinatesEle = jsonObject.get(GeometryConstants.COORDINATES_JSON_FIELD);
        final JsonArray coordinates = coordinatesEle.isJsonNull() ? null : coordinatesEle.getAsJsonArray();

        if (coordinates == null || coordinates.size() == 0)
            return null;
        else
            return deserializeGeometry(coordinates, geoFactory);
    }


    /**
     * Creates a {@linkplain Geometry} object by deserializing its {@linkplain Coordinate}s {@linkplain JsonArray}.
     *
     * @param coordinates a {@linkplain JsonArray} of {@linkplain Coordinate}s
     * @param factory the {@linkplain GeometryFactory} used for building the output
     *
     * @return a {@linkplain Geometry} object represented by its JSON representation
     */
    protected abstract T deserializeGeometry(final JsonArray coordinates, final GeometryFactory factory);


    /**
     * Serializes the {@linkplain Coordinate}s of a {@linkplain Geometry} to a {@linkplain JsonArray}.
     *
     * @param src a {@linkplain Geometry} the {@linkplain Coordinate}s of which are to be serialized
     *
     * @return a {@linkplain JsonArray} of {@linkplain Coordinate}s
     */
    protected abstract JsonArray serializeCoordinates(final T src);


    /**
     * Converts an array of {@linkplain Coordinate}s to a {@linkplain JsonArray}.
     *
     * @param coordinates the {@linkplain Coordinate}s which are to be serialized
     *
     * @return a {@linkplain JsonArray} of {@linkplain Coordinate}s
     */
    protected JsonArray coordinatesToJsonArray(final Coordinate... coordinates)
    {
        if (coordinates == null)
            return null;

        final JsonArray jsonArray = new JsonArray();

        for (final Coordinate c : coordinates) {
            final JsonArray coordinateJson = coordinateToJsonArray(c);

            if (coordinateJson != null)
                jsonArray.add(coordinateToJsonArray(c));
        }

        return jsonArray;
    }


    /**
     * Converts a single {@linkplain Coordinate} to a {@linkplain JsonArray}.
     *
     * @param coordinate the {@linkplain Coordinate} which is to be serialized
     *
     * @return a {@linkplain JsonArray} that contains {@linkplain Double} values
     */
    protected JsonArray coordinateToJsonArray(final Coordinate coordinate)
    {
        if (coordinate == null)
            return null;

        final JsonArray jsonArray = new JsonArray();

        // check if coordinates must be rounded or not
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


    /**
     * Creates a single {@linkplain Coordinate} out of a {@linkplain JsonArray}.
     *
     * @param jsonArray the {@linkplain JsonArray} that is to be parsed
     *
     * @return a single {@linkplain Coordinate}
     */
    protected Coordinate jsonArrayToCoordinate(final JsonArray jsonArray)
    {
        if (jsonArray == null || jsonArray.size() < 2)
            return null;

        // get longitude and latitude
        double x = jsonArray.get(0).getAsDouble();
        double y = jsonArray.get(1).getAsDouble();

        // round longitude and latitude to the specified precision
        if (decimalFactor != Double.POSITIVE_INFINITY) {
            x = Math.round(x * decimalFactor) / decimalFactor;
            y = Math.round(y * decimalFactor) / decimalFactor;
        }

        if (jsonArray.size() >= 3) {
            // get elevation
            double z = jsonArray.get(2).getAsDouble();

            // round elevation to the specified precision
            if (decimalFactor != Double.POSITIVE_INFINITY)
                z = Math.round(z * decimalFactor) / decimalFactor;

            return new Coordinate(x, y, z);
        } else
            return new Coordinate(x, y);
    }


    /**
     * Creates an array of {@linkplain Coordinate}s out of a {@linkplain JsonArray}.
     *
     * @param jsonArray the {@linkplain JsonArray} that is to be parsed
     *
     * @return an array of {@linkplain Coordinate}s
     */
    protected Coordinate[] jsonArrayToCoordinates(final JsonArray jsonArray)
    {
        if (jsonArray == null || jsonArray.size() == 0)
            return null;

        final int size = jsonArray.size();
        final Coordinate[] coordinates = new Coordinate[size];

        int i = 0;

        for (final JsonElement ele : jsonArray)
            coordinates[i++] = jsonArrayToCoordinate(ele.getAsJsonArray());

        return coordinates;
    }
}
