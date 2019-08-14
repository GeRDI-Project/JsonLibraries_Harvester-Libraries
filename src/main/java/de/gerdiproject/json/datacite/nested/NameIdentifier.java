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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A unique identifier for an individual or legal entity, according to various schemes.
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class NameIdentifier
{
    /**
     * -- GETTER --
     * Retrieves an identifier of an individual or legal entity, according to various schemes.
     * <br>e.g. orcid id number
     * @return the identifier of an individual or legal entity
     *
     * -- SETTER --
     * Sets the identifier of an individual or legal entity.
     * <br>e.g. orcid id number
     * @param value the identifier of an individual or legal entity
     */
    private final String value;


    /**
     * -- GETTER --
     * Retrieves the name of the name identifier scheme.
     * <br>e.g. ORCID
     * @return the name of the name identifier scheme
     *
     * -- SETTER --
     * Sets the name of the name identifier scheme.
     * <br>e.g. ORCID
     * @param scheme the name of the name identifier scheme
     */
    @SerializedName("nameIdentifierScheme")
    private final String scheme;


    /**
     * -- GETTER --
     * Retrieves the URI of the name identifier scheme.
     * <br>e.g. http://orcid.org/
     * @return the URI of the name identifier scheme
     *
     * -- SETTER --
     * Sets the URI of the name identifier scheme.
     * <br>e.g. http://orcid.org/
     * @param schemeURI the URI of the name identifier scheme
     */
    private String schemeURI;
}
