package de.gerdiproject.json.controlledvocab.adapter;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.gerdiproject.json.controlledvocab.IResearchDiscipline;

/**
 * TODO: HOW SHOULD A RESEARCH DISCIPLINE LOOK IN JSON AS PART OF A DOCUMENT?
 *
 * @author Robin Weiss
 */
public class ResearchDisciplineAdapter implements JsonDeserializer<IResearchDiscipline>, JsonSerializer<IResearchDiscipline>
{
    @Override
    public IResearchDiscipline deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
    {
    	// TODO

        return null;
    }


    @Override
    public JsonElement serialize(IResearchDiscipline src, Type typeOfSrc, JsonSerializationContext context)
    {
        // TODO

        return null;
    }
}