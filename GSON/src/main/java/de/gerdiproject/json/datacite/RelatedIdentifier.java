/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
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
package de.gerdiproject.json.datacite;

/**
 * Identifiers of related resources.
 * These must be globally unique identifiers.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class RelatedIdentifier
{
    /**
     * Free text identifier of related resources.
     * Use this property to indicate subsets of properties, as appropriate.
     */
    private String value;

    /**
     * The type of the related identifier.
     */
    private RelatedIdentifierType type;

    /**
     * Description of the relationship of the resource being registered (A)
     * and the related resource (B).
     */
    private RelationType relationType;

    /**
     * The name of the related scheme.
     * e.g. citeproc+json
     */
    private String relatedMetadataScheme;

    /**
     * The URI of the relatedMetadataScheme.
     * e.g. https://github.com/citation-style-language/schema/raw/master/csl-data.json
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
        this.type = type;
        this.relationType = relationType;
    }


    /**
     * Returns an identifier of related resources.
     *
     * @return an identifier of related resources
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the free text identifier of related resources.
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
     * e.g. https://github.com/citation-style-language/schema/raw/master/csl-data.json
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
        return type;
    }


    /**
     * Changes the type of the related identifier.
     *
     * @param type the type of the related identifier
     */
    public void setType(RelatedIdentifierType type)
    {
        this.type = type;
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


    /**
     * This enumeration represents the related identifier type of a {@link RelatedIdentifier}.
     *
     * @author Robin Weiss
     */
    public enum RelatedIdentifierType {
        /**
         * Archival Resource Key;
         * URL designed to support long‐term access to information objects. In general, ARK syntax is of the form (brackets indicate [optional] elements: [http://NMA/]ark:/NAAN/Name [Qualifier]
         */
        ARK,

        /**
         * arXiv identifier;
         * arXiv.org is a repository of preprints of scientific papers in the fields of mathematics, physics, astronomy, computer science, quantitative biology, statistics, and quantitative finance.
         */
        arXiv,

        /**
         * Astrophysics Data System bibliographic codes;
         * a standardized 19 character identifier according to the syntax yyyyjjjjjvvvvmppppa.
         * See http://info‐ uri.info/registry/OAIHandler?ve rb=GetRecord&metadataPrefix= reg&identifier=info:bibcode/
         */
        bibcode,

        /**
         * Digital Object Identifier;
         * a character string used to uniquely identify an object. A DOI name is divided into two parts, a prefix and a suffix, separated by a slash.
         */
        DOI,

        /**
         * European Article Number, now renamed International Article Number, but retaining the original acronym, is a 13‐digit barcoding standard which is a superset of the original 12‐digit Universal Product Code (UPC) system.
         */
        EAN13,

        /**
         * Electronic International Standard Serial Number;
         * ISSN used to identify periodicals in electronic form (eISSN or e‐ ISSN).
         */
        EISSN,

        /**
         * A handle is an abstract reference to a resource.
         */
        Handle,

        /**
         * International Geo Sample Number;
         * a 9‐digit alphanumeric code that uniquely identifies samples from our natural environment and related sampling features.
         */
        IGSN,

        /**
         * International Standard Book Number;
         * a unique numeric book identifier. There are 2 formats: a 10‐digit ISBN format and a 13‐ digit ISBN.
         */
        ISBN,

        /**
         * International Standard Serial Number;
         * a unique 8‐digit number used to identify a print or electronic periodical publication.
         */
        ISSN,

        /**
         * International Standard Text Code;
         * a unique "number" assigned to a textual work. An ISTC consists of 16 numbers and/or letters.
         */
        ISTC,

        /**
         * The linking ISSN or ISSN‐L enables collocation or linking among different media versions of a continuing resource.
         */
        LISSN,

        /**
         * Life Science Identifiers;
         * a unique identifier for data in the Life Science domain. Format: urn:lsid:authority:namespace:id entifier:revision
         */
        LSID,

        /**
         * PubMed identifier;
         * a unique number assigned to each PubMed record.
         */
        PMID,

        /**
         * Persistent Uniform Resource Locator. A PURL has three parts: (1) a protocol, (2) a resolver address, and (3) a name.
         */
        PURL,

        /**
         * Universal Product Code is a barcode symbology used for tracking trade items in stores. Its most common form, the UPC‐A, consists of 12 numerical digits.
         */
        UPC,

        /**
         * Uniform Resource Locator, also known as web address, is a specific character string that constitutes a reference to a resource. The syntax is: scheme://domain:port/path?qu ery_string#fragment_id
         */
        URL,

        /**
         * Uniform Resource Name;
         * is a unique and persistent identifier of an electronic document. The syntax is: urn:< NID>:<NSS>             The leading urn: sequence is case‐insensitive, <NID> is the namespace identifier, <NSS> is the namespace‐specific string.
         */
        URN
    }


    /**
     * This enumeration represents the description of the relationship of the resource being registered (A)
     * and the related resource (B).
     *
     * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
     * @author Robin Weiss
     */
    public enum RelationType {
        /**
         * Indicates that B includes A in a citation.
         */
        IsCitedBy,

        /**
         * Indicates that A includes B in a citation.
         */
        Cites,

        /**
         * Indicates that A is a supplement to B.
         */
        IsSupplementTo,

        /**
         * Indicates that B is a supplement to A.
         */
        IsSupplementedBy,

        /**
         * Indicates A is continued by the work B.
         */
        IsContinuedBy,

        /**
         * Indicates A is a continuation of the work B.
         */
        Continues,

        /**
         * Indicates resource A has additional metadata B.
         */
        HasMetadata,

        /**
         * Indicates additional metadata A for a   resource B.
         */
        IsMetadataFor,

        /**
         * Indicates A is a new edition of B, where the new edition has been modified or updated.
         */
        IsNewVersionOf,

        /**
         * Indicates A is a previous edition of B.
         */
        IsPreviousVersionOf,

        /**
         * Indicates A is a portion of B; may be used for elements of a series.
         */
        IsPartOf,

        /**
         * Indicates A includes the part B.
         */
        HasPart,

        /**
         * Indicates A is used as a source of information by B.
         */
        IsReferencedBy,

        /**
         * Indicates B is used as a source of information for A.
         */
        References,

        /**
         * Indicates B is documentatio n about/ explaining A.
         */
        IsDocumentedBy,

        /**
         * Indicates A is documentatio n about/B.
         */
        Documents,

        /**
         * Indicates B is used to compile or create A.
         */
        IsCompiledBy,

        /**
         * Indicates B is the result of a compile or creation event using A.
         */
        Compiles,

        /**
         * Indicates A is a variant or different form of B, e.g. calculated or calibrated form or different packaging.
         */
        IsVariantFormOf,

        /**
         * Indicates A is the original form of B.
         */
        IsOriginalFormOf,

        /**
         * Indicates that A is identical to B, for use when there is a need to register two separate instances of the same resource.
         */
        IsIdenticalTo,

        /**
         * Indicates that A is reviewed by B.
         */
        IsReviewedBy,

        /**
         * Indicates that A is a review of B.
         */
        Reviews,

        /**
         * Indicates B is a source upon which A is based.
         */
        IsDerivedFrom,

        /**
         * Indicates A is a source upon which B is based.
         */
        IsSourceOf
    }

}
