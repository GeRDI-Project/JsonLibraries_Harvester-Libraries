/**
 * Copyright © 2019 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
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
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.gerdiproject.json.datacite.enums.FunderIdentifierType;

/**
 * This adapter defines the (de-)serialization behavior of {@linkplain FunderIdentifierType} enumerations.
 *
 * @author Robin Weiss
 */
public class FunderIdentifierTypeAdapter implements JsonDeserializer<FunderIdentifierType>, JsonSerializer<FunderIdentifierType>
{
    private static final String SPACE = " ";
    private static final String ESCAPED_SPACE = "_";


    @Override
    public FunderIdentifierType deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException
    {
        final String rawText = json.getAsString().replaceAll(SPACE, ESCAPED_SPACE);

        try {
            return FunderIdentifierType.valueOf(rawText);
        } catch (final IllegalArgumentException e) {
            return null;
        }
    }


    @Override
    public JsonElement serialize(final FunderIdentifierType src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        return context.serialize(src.toString().replaceAll(ESCAPED_SPACE, SPACE));
    }
}