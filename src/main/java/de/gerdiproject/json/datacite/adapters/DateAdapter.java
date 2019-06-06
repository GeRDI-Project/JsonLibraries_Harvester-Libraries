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
package de.gerdiproject.json.datacite.adapters;

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
    public AbstractDate deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException
    {
        AbstractDate returnDate;

        final JsonObject dateJsonObj = json.getAsJsonObject();

        // get date type
        final DateType dateType = DateType.valueOf(dateJsonObj.get(DataCiteDateConstants.DATE_TYPE_JSON).getAsString());

        // get raw date value
        final String value = dateJsonObj.get(DataCiteDateConstants.VALUE_JSON).getAsString();

        // is date-range?
        if (value.indexOf(DataCiteDateConstants.DATE_RANGE_SPLITTER) == -1)
            returnDate = new Date(value, dateType);
        else
            returnDate = new DateRange(value, dateType);

        // get date information
        final JsonElement rawDateInfo = dateJsonObj.get(DataCiteDateConstants.DATE_INFO_JSON);

        if (rawDateInfo != null)
            returnDate.setDateInformation(rawDateInfo.getAsString());

        return returnDate;
    }


    @Override
    public JsonElement serialize(final AbstractDate src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        final JsonObject dateJson = new JsonObject();

        // add date or date-range
        dateJson.addProperty(DataCiteDateConstants.VALUE_JSON, src.getValue());

        // add dateType
        final DateType dateType = src.getType();

        if (dateType != null)
            dateJson.addProperty(DataCiteDateConstants.DATE_TYPE_JSON, dateType.toString());

        // optionally add dateInformation
        final String dateInfo = src.getDateInformation();

        if (dateInfo != null)
            dateJson.addProperty(DataCiteDateConstants.DATE_INFO_JSON, dateInfo);

        return dateJson;
    }
}