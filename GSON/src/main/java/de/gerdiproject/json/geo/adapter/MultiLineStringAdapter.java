package de.gerdiproject.json.geo.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import de.gerdiproject.json.geo.MultiLineString;

public class MultiLineStringAdapter implements JsonDeserializer<MultiLineString>
{
    @Override
    public MultiLineString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        return new MultiLineString(json.getAsJsonArray());
    }
}