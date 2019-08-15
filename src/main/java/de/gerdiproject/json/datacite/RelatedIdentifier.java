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

import de.gerdiproject.json.datacite.enums.RelatedIdentifierType;
import de.gerdiproject.json.datacite.enums.RelationType;
import de.gerdiproject.json.datacite.enums.ResourceTypeGeneral;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Identifiers of related resources.
 * These must be globally unique identifiers.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class RelatedIdentifier
{
    /**
     * -- GETTER --
     * Retrieves the free text identifier of related resources.
     * Use this property to indicate subsets of properties, as appropriate.
     * @return the free text identifier of related resources
     *
     * -- SETTER --
     * Sets the free text identifier of related resources.
     * Use this property to indicate subsets of properties, as appropriate.
     * @param value the free text identifier of related resources
     */
    private final String value;


    /**
     * -- GETTER --
     * Retrieves the type of the related identifier.
     * @return the type of the related identifier
     *
     * -- SETTER --
     * Sets the type of the related identifier.
     * @param type the type of the related identifier
     */
    @SerializedName("relatedIdentifierType")
    private final RelatedIdentifierType type;


    /**
     * -- GETTER --
     * Retrieves the description of the relationship of the resource being registered (A)
     * and the related resource (B).
     * @return the description of the relationship
     *
     * -- SETTER --
     * Sets the description of the relationship of the resource being registered (A)
     * and the related resource (B).
     * @param relationType the description of the relationship
     */
    private final RelationType relationType;


    /**
     * -- GETTER --
     * Retrieves the general type of the related resource (B).
     * @return the general type of the related resource (B)
     *
     * -- SETTER --
     * Sets the general type of the related resource (B).
     * @param resourceTypeGeneral the general type of the related resource (B)
     */
    private ResourceTypeGeneral resourceTypeGeneral;


    /**
     * -- GETTER --
     * Retrieves the name of the related scheme.
     * <br>e.g. citeproc+json
     *
     * @return the name of the related scheme
     *
     * -- SETTER --
     * Sets the name of the related scheme.
     *
     * @param relatedMetadataScheme the new name of the related scheme
     */
    private String relatedMetadataScheme;


    /**
     * -- GETTER --
     * Retrieves the URI of the relatedMetadataScheme.
     * <br>e.g. https://github.com/citation-style-language/schema/raw/master/csl-data.json
     * @return the URI of the relatedMetadataScheme
     *
     * -- SETTER --
     * Sets the URI of the relatedMetadataScheme.
     * <br>e.g. https://github.com/citation-style-language/schema/raw/master/csl-data.json
     * @param schemeURI the URI of the relatedMetadataScheme
     */
    private String schemeURI;


    /**
     * -- GETTER --
     * Retrieves the type of the relatedMetadataScheme, linked with the schemeURI.
     * @return the type of the relatedMetadataScheme
     *
     * -- SETTER --
     * Sets the type of the relatedMetadataScheme, linked with the schemeURI.
     * @param schemeType the type of the relatedMetadataScheme
     */
    private String schemeType;
}
