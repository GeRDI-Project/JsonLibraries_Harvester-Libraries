/**
 * Copyright © 2019 Robin Weiss (http://www.gerdi-project.de)
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
package de.gerdiproject.json.datacite.extension.adapters;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import de.gerdiproject.json.datacite.extension.soep.SoepDataCiteExtension;
import de.gerdiproject.json.datacite.extension.soep.SoepVariable;

/**
 * This adapter defines the (de-)serialization behavior of {@linkplain SoepDataCiteExtension} objects.
 *
 * @author Robin Weiss
 */
public class SoepDataCiteExtensionAdapter implements JsonDeserializer<SoepDataCiteExtension>, JsonSerializer<SoepDataCiteExtension>
{
    private static final Type LIST_TYPE = new TypeToken<List<SoepVariable>>() {} .getType();

    @Override
    public SoepDataCiteExtension deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException
    {
        final SoepDataCiteExtension deserializedObject = new SoepDataCiteExtension();

        final List<SoepVariable> soepVariables = context.deserialize(json.getAsJsonArray(), LIST_TYPE);
        deserializedObject.addSoepDatasetVariables(soepVariables);

        return deserializedObject.getDatasetVariables() == null
               ? null
               : deserializedObject;
    }


    @Override
    public JsonElement serialize(final SoepDataCiteExtension src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        if (src.getDatasetVariables() == null || src.getDatasetVariables().isEmpty())
            return JsonNull.INSTANCE;

        final JsonArray serializedObject = new JsonArray();

        for (final SoepVariable soepVar : src.getDatasetVariables())
            serializedObject.add(context.serialize(soepVar));

        return serializedObject;
    }
}