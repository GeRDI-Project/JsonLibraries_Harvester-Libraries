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
package de.gerdiproject.json.geo.adapters;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.geo.IGeoCoordinates;
import de.gerdiproject.json.geo.LineString;
import de.gerdiproject.json.geo.MultiLineString;
import de.gerdiproject.json.geo.MultiPoint;
import de.gerdiproject.json.geo.MultiPolygon;
import de.gerdiproject.json.geo.Point;
import de.gerdiproject.json.geo.Polygon;

/**
 * This adapter defines the (de-)serialization behavior of GeoJson objects.
 *
 * @author Robin Weiss
 */
public class GeoJsonAdapter implements JsonDeserializer<GeoJson>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJson.class);

    @Override
    public GeoJson deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException
    {
        final JsonObject geoJsonRaw = json.getAsJsonObject();
        final String type = geoJsonRaw.get("type").getAsString().toLowerCase();
        final JsonArray coordinatesRaw = geoJsonRaw.get("coordinates").getAsJsonArray();

        IGeoCoordinates coordinates;

        try {
            switch (type) {
                case "point":
                    coordinates = context.deserialize(coordinatesRaw, Point.class);
                    break;

                case "multipoint":
                    coordinates = context.deserialize(coordinatesRaw, MultiPoint.class);
                    break;

                case "linestring":
                    coordinates = context.deserialize(coordinatesRaw, LineString.class);
                    break;

                case "multilinestring":
                    coordinates = context.deserialize(coordinatesRaw, MultiLineString.class);
                    break;

                case "polygon":
                    coordinates = context.deserialize(coordinatesRaw, Polygon.class);
                    break;

                case "multipolygon":
                    coordinates = context.deserialize(coordinatesRaw, MultiPolygon.class);
                    break;

                default:
                    throw new JsonParseException(String.format("Unknown GeoJson type '%s'!", type));
            }
        } catch (final RuntimeException e) { // NOPMD for edge case scenarios, all kinds of exceptions should be caught to be able to continue the harvest
            LOGGER.error("Could not parse GeoJson!", e);
            return null;
        }

        return new GeoJson(coordinates);
    }
}