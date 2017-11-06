package de.gerdiproject.json.datacite.adapter;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.gerdiproject.json.datacite.Date;
import de.gerdiproject.json.datacite.DateRange;
import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.enums.DateType;

/**
 * This adapter defines the (de-)serialization behavior of {@linkplain Configuration} objects.
 *
 * @author Robin Weiss
 */
public class DateAdapter implements JsonDeserializer<AbstractDate>, JsonSerializer<AbstractDate>
{
    @Override
    public AbstractDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        AbstractDate returnDate;

        JsonObject dateJsonObj = json.getAsJsonObject();

        // get date type
        DateType dateType = DateType.valueOf(dateJsonObj.get("dateType").getAsString());

        // get raw date value
        String value = dateJsonObj.get("value").getAsString();

        // is date-range?
        if (value.indexOf(DateRange.DATE_RANGE_SPLITTER) != -1)
            returnDate = new DateRange(value, dateType);
        else
            returnDate = new Date(value, dateType);

        // get date information
        JsonElement rawDateInfo = dateJsonObj.get("dateInformation");

        if (rawDateInfo != null)
            returnDate.setDateInformation(rawDateInfo.getAsString());

        return returnDate;
    }


    @Override
    public JsonElement serialize(AbstractDate src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject dateJson = new JsonObject();

        // add date or date-range
        dateJson.addProperty("value", src.getValue());

        // add dateType
        dateJson.addProperty("dateType", src.getType().toString());

        // optionally add dateInformation
        String dateInfo = src.getDateInformation();

        if (dateInfo != null)
            dateJson.addProperty("dateInformation", dateInfo);

        return dateJson;
    }
}