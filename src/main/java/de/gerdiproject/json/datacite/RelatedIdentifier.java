/**
 * Copyright © 2017 Robin Weiss (http://www.gerdi-project.de)
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

import de.gerdiproject.json.datacite.enums.RelatedIdentifierType;
import de.gerdiproject.json.datacite.enums.RelationType;
import de.gerdiproject.json.datacite.enums.ResourceTypeGeneral;

/**
 * Identifiers of related resources.
 * These must be globally unique identifiers.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class RelatedIdentifier
{
    /**
     * Free text identifier of related resources.
     * In XML, this is the value between the relatedIdentifier-tags.
     * Use this property to indicate subsets of properties, as appropriate.
     */
    private String value;

    /**
     * The type of the related identifier.
     */
    private RelatedIdentifierType relatedIdentifierType;

    /**
     * The general type of the related resource (B).
     */
    private ResourceTypeGeneral resourceTypeGeneral;


    /**
     * Description of the relationship of the resource being registered (A)
     * and the related resource (B).
     */
    private RelationType relationType;

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

    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param value free text identifier of related resources
     * @param type type of the RelatedIdentifier
     * @param relationType description of the relationship of the resource being registered and the related resource
     */
    public RelatedIdentifier(String value, RelatedIdentifierType type, RelationType relationType)
    {
        this.value = value;
        this.relatedIdentifierType = type;
        this.relationType = relationType;
    }


    /**
     * Returns an identifier of related resources.
     * In XML, this is the value between the relatedIdentifier-tags.
     *
     * @return an identifier of related resources
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the free text identifier of related resources.
     * In XML, this is the value between the relatedIdentifier-tags.
     * Use this property to indicate subsets of properties, as appropriate.
     *
     * @param value a free text identifier of related resources
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the type of the related identifier.
     *
     * @return the type of the related identifier
     */
    public String getRelatedMetadataScheme()
    {
        return relatedMetadataScheme;
    }


    /**
     * Changes the type of the related identifier.
     *
     * @param relatedMetadataScheme a type of a related identifier
     */
    public void setRelatedMetadataScheme(String relatedMetadataScheme)
    {
        this.relatedMetadataScheme = relatedMetadataScheme;
    }


    /**
     * Returns the URI of the relatedMetadataScheme.
     * @return the URI of the relatedMetadataScheme
     */
    public String getSchemeURI()
    {
        return schemeURI;
    }


    /**
     * Changes the URI of the relatedMetadataScheme.
     * <br>e.g. https://github.com/citation-style-language/schema/raw/master/csl-data.json
     *
     * @param schemeURI the URI of the relatedMetadataScheme
     */
    public void setSchemeURI(String schemeURI)
    {
        this.schemeURI = schemeURI;
    }


    /**
     * Returns the type of the relatedMetadataScheme, linked with the schemeURI.
     *
     * @return the type of the relatedMetadataScheme
     */
    public String getSchemeType()
    {
        return schemeType;
    }


    /**
     * Changes the type of the relatedMetadataScheme, linked with the schemeURI.
     *
     * @param schemeType the type of the relatedMetadataScheme
     */
    public void setSchemeType(String schemeType)
    {
        this.schemeType = schemeType;
    }


    /**
     * Returns the type of the related identifier.
     *
     * @return the type of the related identifier
     */
    public RelatedIdentifierType getType()
    {
        return relatedIdentifierType;
    }


    /**
     * Changes the type of the related identifier.
     *
     * @param type the type of the related identifier
     */
    public void setType(RelatedIdentifierType type)
    {
        this.relatedIdentifierType = type;
    }


    /**
     * Retrieves the general type of the related resource (B).
     *
     * @return the general type of the related resource
     */
    public ResourceTypeGeneral getResourceTypeGeneral()
    {
        return resourceTypeGeneral;
    }


    /**
     * Changes the general type of the related resource (B).
     *
     * @param resourceTypeGeneral the general type of the related resource
     */
    public void setResourceTypeGeneral(ResourceTypeGeneral resourceTypeGeneral)
    {
        this.resourceTypeGeneral = resourceTypeGeneral;
    }


    /**
     * Returns the description of the relationship of the resource being registered (A)
     * and the related resource (B).
     *
     * @return the description of the relationship of the resource and the related resource
     */
    public RelationType getRelationType()
    {
        return relationType;
    }


    /**
     * Changes the description of the relationship of the resource being registered (A)
     * and the related resource (B).
     *
     * @param relationType the description of the relationship of the resource and the related resource
     */
    public void setRelationType(RelationType relationType)
    {
        this.relationType = relationType;
    }
}
