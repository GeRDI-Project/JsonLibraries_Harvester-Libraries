package de.gerdiproject.json.geo.adapter;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.gerdiproject.json.geo.MultiPoint;

/**
 * This adapter defines the (de-)serialization behavior of MultiPoint coordinate objects.
 * @author Robin Weiss
 *
 */
public class MultiPointAdapter implements JsonDeserializer<MultiPoint>
{
    @Override
    public MultiPoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        return new MultiPoint(json.getAsJsonArray());
    }
}