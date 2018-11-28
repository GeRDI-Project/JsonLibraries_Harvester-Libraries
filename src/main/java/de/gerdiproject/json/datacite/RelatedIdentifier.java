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
import lombok.Data;

/**
 * Identifiers of related resources.
 * These must be globally unique identifiers.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data
public class RelatedIdentifier
{
    /**
     * Free text identifier of related resources.
     * In XML, this is the value between the relatedIdentifier-tags.
     * Use this property to indicate subsets of properties, as appropriate.
     */
    private final String value;

    /**
     * The type of the related identifier.
     */
    @SerializedName("relatedIdentifierType")
    private final RelatedIdentifierType type;

    /**
     * Description of the relationship of the resource being registered (A)
     * and the related resource (B).
     */
    private final RelationType relationType;

    /**
     * The general type of the related resource (B).
     */
    private ResourceTypeGeneral resourceTypeGeneral;

    /**
     * The name of the related scheme.
     * <br>e.g. citeproc+json
     */
    private String relatedMetadataScheme;

    /**
     * The URI of the relatedMetadataScheme.
     * <br>e.g. https://github.com/citation-style-language/schema/raw/master/csl-data.json
     */
    private String schemeURI;

    /**
     * The type of the relatedMetadataScheme, linked with the schemeURI.
     */
    private String schemeType;
}
