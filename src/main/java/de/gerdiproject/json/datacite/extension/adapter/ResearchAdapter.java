package de.gerdiproject.json.datacite.extension.adapter;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.gerdiproject.json.datacite.constants.DataCiteResearchConstants;
import de.gerdiproject.json.datacite.extension.ResearchDiscipline;
import de.gerdiproject.json.datacite.extension.abstr.AbstractResearch;
import de.gerdiproject.json.datacite.extension.constants.ResearchAreaConstants;
import de.gerdiproject.json.datacite.extension.constants.ResearchDisciplineConstants;

/**
 * This adapter can convert {@linkplain AbstractResearch} objects to JSON and vice-versa.
 *
 * @author Robin Weiss
 */
public class ResearchAdapter implements JsonDeserializer<AbstractResearch>, JsonSerializer<AbstractResearch>
{
    @Override
    public AbstractResearch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        String rnbrString = json.getAsJsonObject().get(DataCiteResearchConstants.RNBR_JSON).getAsString();
        AbstractResearch output;

        if (rnbrString.indexOf('-') == -1)
            output = ResearchAreaConstants.getByRnbrString(rnbrString);
        else
            output = ResearchDisciplineConstants.getByRnbrString(rnbrString);

        return output;
    }


    @Override
    public JsonElement serialize(AbstractResearch src, Type typeOfSrc, JsonSerializationContext context)
    {
        String rnbrString = "";

        if (src instanceof ResearchDiscipline)
            rnbrString = String.format(DataCiteResearchConstants.DISCIPLINE_RNBR_FORMAT,
                                       ((ResearchDiscipline) src).getArea().getRbnr(),
                                       src.getRbnr());
        else
            rnbrString = String.format(DataCiteResearchConstants.AREA_RNBR_FORMAT, src.getRbnr());


        JsonObject rdObject = new JsonObject();
        rdObject.addProperty(DataCiteResearchConstants.NAME_JSON, src.getName());
        rdObject.addProperty(DataCiteResearchConstants.RNBR_JSON, rnbrString);
        return rdObject;
    }
}