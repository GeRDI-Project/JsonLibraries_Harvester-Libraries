package de.gerdiproject.json.geo.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import de.gerdiproject.json.geo.MultiPolygon;

/**
 * This adapter defines the (de-)serialization behavior of MultiPolygon coordinate objects.
 * @author Robin Weiss
 *
 */
public class MultiPolygonAdapter implements JsonDeserializer<MultiPolygon>
{
    @Override
    public MultiPolygon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        return new MultiPolygon(json.getAsJsonArray());
    }
}