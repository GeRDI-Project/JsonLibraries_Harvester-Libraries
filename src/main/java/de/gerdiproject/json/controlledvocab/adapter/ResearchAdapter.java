package de.gerdiproject.json.controlledvocab.adapter;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.gerdiproject.json.controlledvocab.ResearchDiscipline;
import de.gerdiproject.json.controlledvocab.abstr.AbstractResearch;
import de.gerdiproject.json.controlledvocab.constants.ResearchConstants;

/**
 * TODO: HOW SHOULD A RESEARCH DISCIPLINE LOOK IN JSON AS PART OF A DOCUMENT?
 *
 * @author Robin Weiss
 */
public class ResearchAdapter implements JsonDeserializer<AbstractResearch>, JsonSerializer<AbstractResearch>
{
    @Override
    public AbstractResearch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
        String rnbrString = json.getAsJsonObject().get(ResearchConstants.RNBR_JSON).getAsString();
        AbstractResearch output;

        if (rnbrString.indexOf('-') == -1)
            output = ResearchConstants.getCategoryByRnbr(Integer.parseInt(rnbrString));
        else {
            String[] splitRnbr = rnbrString.split("-");
            int categoryRnbr = Integer.parseInt(splitRnbr[0]);
            int disciplineRnbr = Integer.parseInt(splitRnbr[1]);
            System.out.println(rnbrString + " => " + categoryRnbr + ", " + disciplineRnbr);

            output = ResearchConstants.getDisciplineByRnbr(categoryRnbr, disciplineRnbr);
        }

        return output;
    }


    @Override
    public JsonElement serialize(AbstractResearch src, Type typeOfSrc, JsonSerializationContext context)
    {
        String rnbrString = "";

        if (src instanceof ResearchDiscipline)
            rnbrString = String.format(ResearchConstants.DISCIPLINE_FORMAT,
                                       ((ResearchDiscipline) src).getCategory().getRbnr(),
                                       src.getRbnr());

        else
            rnbrString = String.format(ResearchConstants.CATEGORY_FORMAT, src.getRbnr());


        JsonObject rdObject = new JsonObject();
        rdObject.addProperty(ResearchConstants.NAME_JSON, src.getName());
        rdObject.addProperty(ResearchConstants.RNBR_JSON, rnbrString);
        return rdObject;
    }
}