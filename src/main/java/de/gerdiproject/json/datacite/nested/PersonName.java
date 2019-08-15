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
package de.gerdiproject.json.datacite.nested;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.json.datacite.enums.NameType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The full name of a person.
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
@Data @RequiredArgsConstructor
public class PersonName
{
    /**
     * -- GETTER --
     * Retrieves the free text full name of the person or entity.
     * In XML, this is the text between the name-tags.
     * @return the free text full name of the person or entity
     *
     * -- SETTER --
     * Sets the free text full name of the person or entity.
     * In XML, this is the text between the name-tags.
     * @param value the free text full name of the person or entity
     */
    private final String value;


    /**
     * -- GETTER --
     * Retrieves the type of name.
     * @return the type of name
     *
     * -- SETTER --
     * Sets the type of name.
     * @param type the type of name
     */
    @SerializedName("nameType")
    private final NameType type;


    /**
     * -- GETTER --
     * Retrieves the IETF language tag.
     * <br>e.g. de, en-us
     * @return the IETF language tag
     *
     * -- SETTER --
     * Sets the IETF language tag.
     * <br>e.g. de, en-us
     * @param lang the IETF language tag
     */
    private String lang;


    /**
     * Simple constructor that does not initialize the nameType.
     *
     * @param value the free text full name of the person or entity
     */
    public PersonName(final String value)
    {
        this.value = value;
        this.type = null;
    }
}
