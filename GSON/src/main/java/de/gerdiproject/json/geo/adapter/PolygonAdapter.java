package de.gerdiproject.json.geo.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import de.gerdiproject.json.geo.Polygon;

/**
 * This adapter defines the (de-)serialization behavior of Polygon coordinate objects.
 * @author Robin Weiss
 *
 */
public class PolygonAdapter implements JsonDeserializer<Polygon>
{
    @Override
    public Polygon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        return new Polygon(json.getAsJsonArray());
    }
}