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
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.gerdiproject.json.geo.Point;

/**
 * This adapter defines the (de-)serialization behavior of GeoJson coordinates.
 *
 * @author Robin Weiss
 */
public class PointAdapter implements JsonSerializer<Point>, JsonDeserializer<Point>
{
    /**
     * Converts a GeoJson coordinate to an array with either
     * [longitude, latitude] or
     * [longitude, latitude, elevation] if elevation is defined.
     */
    @Override
    public JsonElement serialize(Point src, Type srcType, JsonSerializationContext context)
    {
        boolean hasElevation = Double.isFinite(src.getElevation());
        JsonArray dest = new JsonArray(hasElevation ? 3 : 2);

        dest.add(src.getLongitude());
        dest.add(src.getLatitude());

        if (hasElevation)
            dest.add(src.getElevation());

        return dest;
    }


    @Override
    public Point deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        return new Point(json.getAsJsonArray());
    }
}