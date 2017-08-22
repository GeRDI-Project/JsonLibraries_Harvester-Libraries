package de.gerdiproject.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.geo.LineString;
import de.gerdiproject.json.geo.MultiLineString;
import de.gerdiproject.json.geo.MultiPoint;
import de.gerdiproject.json.geo.MultiPolygon;
import de.gerdiproject.json.geo.Point;
import de.gerdiproject.json.geo.Polygon;
import de.gerdiproject.json.geo.adapter.GeoJsonAdapter;
import de.gerdiproject.json.geo.adapter.LineStringAdapter;
import de.gerdiproject.json.geo.adapter.MultiLineStringAdapter;
import de.gerdiproject.json.geo.adapter.MultiPointAdapter;
import de.gerdiproject.json.geo.adapter.MultiPolygonAdapter;
import de.gerdiproject.json.geo.adapter.PointAdapter;
import de.gerdiproject.json.geo.adapter.PolygonAdapter;
import de.gerdiproject.json.impl.GsonArray;
import de.gerdiproject.json.impl.GsonObject;


/**
 * This is a static utility class that provides methods that are commonly used
 * by this GSON implementation.
 *
 * @author Robin Weiss
 *
 */
public final class GsonUtils
{
    private static final Gson GSON = createBuilder().create();
    private static final Gson PRETTY_GSON = createBuilder().setPrettyPrinting().create();


    /**
     * Creates a GsonBuilder with customized (de-)serialization adapters.
     * @return a GsonBuilder with customized (de-)serialization adapters
     */
    private static GsonBuilder createBuilder()
    {
        return new GsonBuilder()
               .registerTypeAdapter(Point.class, new PointAdapter())
               .registerTypeAdapter(MultiPoint.class, new MultiPointAdapter())
               .registerTypeAdapter(LineString.class, new LineStringAdapter())
               .registerTypeAdapter(MultiLineString.class, new MultiLineStringAdapter())
               .registerTypeAdapter(Polygon.class, new PolygonAdapter())
               .registerTypeAdapter(MultiPolygon.class, new MultiPolygonAdapter())
               .registerTypeAdapter(GeoJson.class, new GeoJsonAdapter());
    }

    /**
     * Converts an Object to a JSON string.
     * @param obj the object that is to be converted
     * @param pretty if true
     * @return a JSON string, representing the object
     */
    public static String objectToJsonString(Object obj, boolean pretty)
    {
        if (pretty)
            return PRETTY_GSON.toJson(obj);
        else
            return GSON.toJson(obj);
    }

    /**
     * Converts a GSON element to the Java object representation.
     *
     * @param ele the GSON element that is to be converted
     * @param objClass the class of the target object
     * @param <T> the type of the target object
     * @return a Java object, parsed from the JSON element
     */
    public static <T> T jsonToObject(JsonElement ele, Class<T> objClass)
    {
        return GSON.fromJson(ele, objClass);
    }

    /**
     * Converts a JSON string to the Java object representation.
     *
     * @param jsonString the json string that is to be converted
     * @param objClass the class of the target object
     * @param <T> the type of the target object
     * @return a Java object, parsed from the JSON string
     */
    public static <T> T jsonStringToObject(String jsonString, Class<T> objClass)
    {
        return GSON.fromJson(jsonString, objClass);
    }
    
    /**
     * Converts a Java object to a generic JSON object or array.
     * @param obj the source Java object
     * @param <T> the type of the source object
     * @return a GSON element
     */
    public static <T> JsonElement objectToJson(T obj)
    {
        return GSON.toJsonTree( obj, new TypeToken<T>(){}.getType() );
    }
    
    /**
     * Converts a GSON element to the Java object representation.
     *
     * @param ele the GSON element that is to be converted
     * @return a Java object that is represented by the GSON element
     */
    public static Object jsonToObject(JsonElement ele)
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
