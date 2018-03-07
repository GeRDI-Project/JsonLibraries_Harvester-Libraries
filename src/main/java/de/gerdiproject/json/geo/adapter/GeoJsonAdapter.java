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
package de.gerdiproject.json.geo.adapter;

import java.lang.reflect.Type;

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

    @Override
    public GeoJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        JsonObject geoJsonRaw = json.getAsJsonObject();
        String type = geoJsonRaw.get("type").getAsString().toLowerCase();
        JsonArray coordinatesRaw = geoJsonRaw.get("coordinates").getAsJsonArray();

        IGeoCoordinates coordinates;

        switch (type) {
            case "point":
                coordinates = new Point(coordinatesRaw);
                break;

            case "multipoint":
                coordinates = new MultiPoint(coordinatesRaw);
                break;

            case "linestring":
                coordinates = new LineString(coordinatesRaw);
                break;

            case "multilinestring":
                coordinates = new MultiLineString(coordinatesRaw);
                break;

            case "polygon":
                coordinates =  new Polygon(coordinatesRaw);
                break;

            case "multipolygon":
                coordinates = new MultiPolygon(coordinatesRaw);
                break;

            default:
                throw new JsonParseException(String.format("Unknown GeoJson type '%s'!", type));
        }

        return new GeoJson(coordinates);
    }
}