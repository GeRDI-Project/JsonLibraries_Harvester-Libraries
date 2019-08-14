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

import de.gerdiproject.json.datacite.enums.IdentifierType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The primary Identifier of the resource being registered.
 * The Identifier is a unique string that identifies a resource.
 * For software, determine whether the identifier is for a specific
 * version of a piece of software, (per the Force11 Software Citation Principles11),
 * or for all versions.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor
public class Identifier
{
    /**
     * -- GETTER --
     * Retrieves the unique string that identifies a resource.
     * <br>e.g. 10.1234/foo
     * @return the unique string that identifies a resource
     *
     * -- SETTER --
     * Sets the unique string that identifies a resource.
     * <br>e.g. 10.1234/foo
     * @param value the unique string that identifies a resource
     */
    private final String value;


    /**
     * -- GETTER --
     * Retrieves the type of the identifier.
     * @return the type of the identifier
     *
     * -- SETTER --
     * Sets the type of the identifier.
     * @param type the type of the identifier
     */
    @SerializedName("identifierType")
    private final IdentifierType type;


    /**
     * Constructs a DOI identifier.
     *
     * @param value a DOI identifier string of the format "10.1234/foo"
     */
    public Identifier(final String value)
    {
        this.value = value;
        this.type = IdentifierType.DOI;
    }
}
