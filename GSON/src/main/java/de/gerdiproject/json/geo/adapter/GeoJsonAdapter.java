package de.gerdiproject.json.geo.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.geo.LineString;
import de.gerdiproject.json.geo.MultiLineString;
import de.gerdiproject.json.geo.MultiPoint;
import de.gerdiproject.json.geo.MultiPolygon;
import de.gerdiproject.json.geo.Point;
import de.gerdiproject.json.geo.Polygon;

/**
 * This adapter defines the (de-)serialization behavior of GeoJson objects.
 * @author Robin Weiss
 *
 */
@SuppressWarnings("rawtypes")
public class GeoJsonAdapter implements JsonDeserializer<GeoJson>
{

    @Override
    public GeoJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        GeoJson<?> geoJson;

        JsonObject geoJsonRaw = json.getAsJsonObject();
        String type = geoJsonRaw.get("type").getAsString().toLowerCase();
        JsonArray coords = geoJsonRaw.get("coordinates").getAsJsonArray();

        switch (type) {
            case "point":
                geoJson = new GeoJson<Point>(new Point(coords));
                break;

            case "multipoint":
                geoJson = new GeoJson<MultiPoint>(new MultiPoint(coords));
                break;

            case "linestring":
                geoJson = new GeoJson<LineString>(new LineString(coords));
                break;

            case "multilinestring":
                geoJson = new GeoJson<MultiLineString>(new MultiLineString(coords));
                break;

            case "polygon":
                geoJson = new GeoJson<Polygon>(new Polygon(coords));
                break;

            case "multipolygon":
                geoJson = new GeoJson<MultiPolygon>(new MultiPolygon(coords));
                break;

            default:
                geoJson = null;
        }

        return geoJson;
    }
}