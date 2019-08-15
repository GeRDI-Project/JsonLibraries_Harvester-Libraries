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
package de.gerdiproject.json.datacite;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.utils.StringUtils;
import de.gerdiproject.json.datacite.enums.TitleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A name or title by which a resource is known.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class Title implements ICleanable
{
    /**
     * -- GETTER --
     * Retrieves the free text title or name.
     * <br>e.g. Crops, Catch Value in the Atlantic Ocean
     * @return the free text title or name
     *
     * -- SETTER --
     * Sets the free text title or name.
     * <br>e.g. Crops, Catch Value in the Atlantic Ocean
     * @param value a free text title or name
     */
    @NonNull
    private String value;


    /**
     * -- GETTER --
     * Retrieves the type of Title.
     * @return the type of Title
     *
     * -- SETTER --
     * Sets the type of Title.
     * @param type the type of Title
     */
    @SerializedName("titleType")
    private TitleType type;


    /**
     * -- GETTER --
     * Retrieves the IETF language tag describing the language of the title text.
     * <br>e.g. de, en-US
     * @return the IETF language tag describing the language of the title text
     *
     * -- SETTER --
     * Sets the IETF language tag describing the language of the title text.
     * <br>e.g. de, en-US
     * @param lang the IETF language tag describing the language of the title text
     */
    private String lang;


    @Override
    public boolean clean()
    {
        setValue(StringUtils.clean(value));
        return true;
    }
}
