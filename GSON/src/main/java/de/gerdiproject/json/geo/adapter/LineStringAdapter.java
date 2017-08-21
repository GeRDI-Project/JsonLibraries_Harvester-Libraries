package de.gerdiproject.json.geo.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import de.gerdiproject.json.geo.LineString;

/**
 * This adapter defines the (de-)serialization behavior of LineString coordinate objects.
 * @author Robin Weiss
 *
 */
public class LineStringAdapter implements JsonDeserializer<LineString>
{
    @Override
    public LineString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        return new LineString(json.getAsJsonArray());
    }
}