package de.gerdiproject.json.impl;


import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;


/**
 * This is a static utility class that provides methods that are commonly used
 * by this GSON implementation.
 *
 * @author Robin Weiss
 *
 */
public final class GsonUtils
{
    /**
     * Converts a GSON element to the Java object representation.
     *
     * @param ele
     *            the GSON element that is to be converted
     * @return a Java object that is represented by the GSON element
     */
    public static Object gsonToObject(JsonElement ele)
    {
        if (ele != null && !ele.isJsonNull()) {

            if (ele.isJsonPrimitive()) {
                JsonPrimitive primitive = ele.getAsJsonPrimitive();

                if (primitive.isString())
                    return primitive.getAsString();

                if (primitive.isBoolean())
                    return primitive.getAsBoolean();

                if (primitive.isNumber())
                    return primitive.getAsNumber();

            } else {
                if (ele.isJsonArray())
                    return new GsonArray(ele.getAsJsonArray());

                if (ele.isJsonObject())
                    return new GsonObject(ele.getAsJsonObject());
            }
        }

        return null;
    }


    /**
     * Empty constructor, because this is a static class.
     */
    private GsonUtils()
    {
    }
}
