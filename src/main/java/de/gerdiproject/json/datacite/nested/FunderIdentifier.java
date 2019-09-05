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

import de.gerdiproject.json.datacite.enums.FunderIdentifierType;
import lombok.Data;

/**
 * Uniquely identifies a funding entity, according to various types.
 * <br>This is not indexed!
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
@Data
public class FunderIdentifier
{
    /**
     * -- GETTER --
     * Retrieves the value of the FunderIdentifier.
     * @return the value of the FunderIdentifier
     *
     * -- SETTER --
     * Sets the value of the FunderIdentifier.
     * @param value the value of the FunderIdentifier
     */
    private final String value;


    /**
     * -- GETTER --
     * Retrieves the type of the funder identifier.
     * @return the type of the funder identifier
     *
     * -- SETTER --
     * Sets the type of the funder identifier.
     * @param type the type of the funder identifier
     */
    @SerializedName("funderIdentifierType")
    private final FunderIdentifierType type;


    /**
     * -- GETTER --
     * Retrieves the URI of the funder identifier schema.
     * <br> e.g. https://www.crossref.org/services/funder-registry/
     * @return the scheme URI of the {@linkplain FunderIdentifierType}
     *
     * -- SETTER --
     * Sets the URI of the funder identifier schema.
     * <br> e.g. https://www.crossref.org/services/funder-registry/
     * @param schemeURI the scheme URI of the {@linkplain FunderIdentifierType}
     */
    private String schemeURI;
}
