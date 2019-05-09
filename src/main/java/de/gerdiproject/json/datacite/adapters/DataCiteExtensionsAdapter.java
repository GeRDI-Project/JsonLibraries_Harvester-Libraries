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
package de.gerdiproject.json.datacite.adapters;

import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.gerdiproject.json.datacite.extension.DataCiteExtensions;
import de.gerdiproject.json.datacite.extension.IDataCiteExtension;
import de.gerdiproject.json.datacite.extension.soep.SoepDataCiteExtension;

/**
 * This adapter defines the (de-) serialization behavior of {@linkplain DataCiteExtensions} objects.
 *
 * @author Robin Weiss
 */
public class DataCiteExtensionsAdapter implements JsonDeserializer<DataCiteExtensions>, JsonSerializer<DataCiteExtensions>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DataCiteExtensionsAdapter.class);

    @Override
    public DataCiteExtensions deserialize(final JsonElement src, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException
    {
        final JsonObject sourceObject = src.getAsJsonObject();
        final Set<String> keys = sourceObject.keySet();
        final DataCiteExtensions deserializedObject = new DataCiteExtensions();

        // parse each extension individually
        for (final String key : keys) {
            final IDataCiteExtension ex = jsonToExtension(key, sourceObject.get(key), context);
            deserializedObject.add(ex);
        }

        // do not return the object if it contains no extensions
        return deserializedObject.getExtensions() == null ? null : deserializedObject;
    }


    @Override
    public JsonElement serialize(final DataCiteExtensions src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        if (src == null || src.getExtensions() == null || src.getExtensions().isEmpty())
            return JsonNull.INSTANCE;

        final JsonObject serializedObject = new JsonObject();

        for (final Entry<String, IDataCiteExtension> extension : src.getExtensions().entrySet()) {
            final JsonElement exJson = context.serialize(extension.getValue());

            // do not add empty extensions
            if (!exJson.isJsonNull())
                serializedObject.add(extension.getKey(), exJson);
        }

        return serializedObject.size() == 0 ? JsonNull.INSTANCE : serializedObject;
    }


    /**
     * Converts a {@linkplain JsonElement} to an {@link IDataCiteExtension} implementation.
     *
     * @param key the key of the extension
     * @param json the raw json object to be deserialized
     * @param context the deserialization context
     *
     * @return a deserialized {@link IDataCiteExtension}
     */
    private IDataCiteExtension jsonToExtension(final String key, final JsonElement json, final JsonDeserializationContext context)
    {
        final IDataCiteExtension extension;

        switch (key) {
            case SoepDataCiteExtension.KEY:
                extension = context.deserialize(json, SoepDataCiteExtension.class);
                break;

            default:
                LOGGER.error("Unknown GeRDI extension: " + key); // NOPMD this case is erroneous and must be logged
                extension = null;
        }

        return extension;
    }
}