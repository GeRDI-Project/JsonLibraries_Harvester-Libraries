/*
 *  Copyright © 2019 Robin Weiss (http://www.gerdi-project.de/)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package de.gerdiproject.json.datacite.nested;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * An organisational or institutional affiliation of a person.
 * <br>e.g. Council of Ricks
 *
 * @author Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class Affiliation
{
    /**
     * -- GETTER --
     * Retrieves the name of the organisational or institutional affiliation.
     * @return the name of the affiliation
     *
     * -- SETTER --
     * Sets the name of the organisational or institutional affiliation.
     * @param value the name of the affiliation
     */
    private final String value;


    // TODO wait for official documentation: https://schema.datacite.org/meta/kernel-4.3/doc/DataCite-MetadataKernel_v4.3.pdf
    @SerializedName("affiliationIdentifier")
    private String identifier;


    // TODO wait for official documentation: https://schema.datacite.org/meta/kernel-4.3/doc/DataCite-MetadataKernel_v4.3.pdf
    @SerializedName("affiliationIdentifierScheme")
    private String identifierScheme;


    /**
     * -- GETTER --
     * Retrieves the scheme URI of the affiliationIdentifierScheme.
     * @return the scheme URI of the affiliationIdentifierScheme
     *
     * -- SETTER --
     * Sets the scheme URI of the affiliationIdentifierScheme.
     * @param schemeURI the scheme URI of the affiliationIdentifierScheme
     */
    private String schemeURI;
}
