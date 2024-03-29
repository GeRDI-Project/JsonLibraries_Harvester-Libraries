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
package de.gerdiproject.json.datacite.extension.generic.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.gerdiproject.json.datacite.constants.DataCiteResearchConstants;
import de.gerdiproject.json.datacite.extension.generic.AbstractResearch;
import de.gerdiproject.json.datacite.extension.generic.constants.ResearchAreaConstants;
import de.gerdiproject.json.datacite.extension.generic.constants.ResearchDisciplineConstants;

/**
 * This adapter can convert {@linkplain AbstractResearch} objects to JSON and vice-versa.
 *
 * @author Robin Weiss
 */
public class ResearchAdapter implements JsonDeserializer<AbstractResearch>, JsonSerializer<AbstractResearch>
{
    @Override
    public AbstractResearch deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException
    {
        final String rnbrString = json.getAsJsonObject().get(DataCiteResearchConstants.RNBR_JSON).getAsString();
        AbstractResearch output;

        if (rnbrString.indexOf('-') == -1)
            output = ResearchAreaConstants.getByRnbrString(rnbrString);
        else
            output = ResearchDisciplineConstants.getByRnbrString(rnbrString);

        return output;
    }


    @Override
    public JsonElement serialize(final AbstractResearch src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        final JsonObject rdObject = new JsonObject();
        rdObject.addProperty(DataCiteResearchConstants.AREA_JSON, src.getAreaName());
        rdObject.addProperty(DataCiteResearchConstants.CATEGORY_JSON, src.getCategoryName());
        rdObject.addProperty(DataCiteResearchConstants.RNBR_JSON, src.getRnbrAsString());

        // discipline is optional
        final String disciString = src.getDisciplineName();

        if (disciString != null)
            rdObject.addProperty(DataCiteResearchConstants.DISCIPLINE_JSON, disciString);

        return rdObject;
    }
}