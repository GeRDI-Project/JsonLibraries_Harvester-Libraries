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
 * @author Robin Weiss
 *
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