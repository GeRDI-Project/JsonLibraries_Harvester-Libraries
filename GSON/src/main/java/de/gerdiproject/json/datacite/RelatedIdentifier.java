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
 * An Identifier of related resources. Use this property to indicate subsets of properties, as appropriate.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class RelatedIdentifier
{
    /**
     * relatedIdentifier in DataCite schema
     */
    public String value;

    /**
     * relatedIdentifierType in DataCite schema
     */
    public RelatedIdType type;

    /**
     * optional, e.g. citeproc+json
     */
    public String relatedMetadataScheme;

    /**
     *  e.g. https://github.com/citation-style-language/schema/raw/master/csl-data.json
     */
    public String schemeURI;

    /**
     * the type of the metaData scheme
     */
    public String schemeType;

    /**
     * Simple constructor that requires all mandatory fields.
     * @param value the identifier value
     * @param type tag of the identifier
     */
    public RelatedIdentifier(String value, RelatedIdType type)
    {
        this.value = value;
        this.type = type;
    }

    /**
     * This enumeration represents the type of a {@link RelatedIdentifier}.
     * @author Robin Weiss
     *
     */
    public enum RelatedIdType {
        ARK,
        arXiv,
        bibcode,
        DOI,
        EAN13,
        EISSN,
        Handle,
        IGSN,
        ISBN,
        ISSN,
        ISTC,
        LISSN,
        LSID,
        PMID,
        PURL,
        UPC,
        URL,
        URN
    }

}
