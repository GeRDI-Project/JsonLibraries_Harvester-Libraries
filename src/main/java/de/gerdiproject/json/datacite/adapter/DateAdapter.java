/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import de.gerdiproject.json.datacite.constants.DataCiteDateConstants;
import de.gerdiproject.json.datacite.enums.DateType;

/**
 * This adapter defines the (de-)serialization behavior of {@linkplain AbstractDate} objects.
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
        DateType dateType = DateType.valueOf(dateJsonObj.get(DataCiteDateConstants.DATE_TYPE_JSON).getAsString());

        // get raw date value
        String value = dateJsonObj.get(DataCiteDateConstants.VALUE_JSON).getAsString();

        // is date-range?
        if (value.indexOf(DataCiteDateConstants.DATE_RANGE_SPLITTER) != -1)
            returnDate = new DateRange(value, dateType);
        else
            returnDate = new Date(value, dateType);

        // get date information
        JsonElement rawDateInfo = dateJsonObj.get(DataCiteDateConstants.DATE_INFO_JSON);

        if (rawDateInfo != null)
            returnDate.setDateInformation(rawDateInfo.getAsString());

        return returnDate;
    }


    @Override
    public JsonElement serialize(AbstractDate src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject dateJson = new JsonObject();

        // add date or date-range
        dateJson.addProperty(DataCiteDateConstants.VALUE_JSON, src.getValue());

        // add dateType
        dateJson.addProperty(DataCiteDateConstants.DATE_TYPE_JSON, src.getType().toString());

        // optionally add dateInformation
        String dateInfo = src.getDateInformation();

        if (dateInfo != null)
            dateJson.addProperty(DataCiteDateConstants.DATE_INFO_JSON, dateInfo);

        return dateJson;
    }
}