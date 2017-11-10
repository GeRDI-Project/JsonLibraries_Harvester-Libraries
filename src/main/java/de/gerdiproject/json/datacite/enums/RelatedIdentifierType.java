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
package de.gerdiproject.json.datacite.enums;

import de.gerdiproject.json.datacite.RelatedIdentifier;

/**
 * This enumeration represents the related identifier type of a {@link RelatedIdentifier}.
 *
 * @author Robin Weiss
 */
public enum RelatedIdentifierType {
    /**
     * Archival Resource Key;
     * URL designed to support long-term access to information objects. In general, ARK syntax is of the form (brackets indicate [optional] elements: [http://NMA/]ark:/NAAN/Name [Qualifier]
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
     * <br>See http://info-uri.info/registry/OAIHandler?verb=GetRecord&amp;metadataPrefix=reg&amp;identifier=info:bibcode/
     */
    bibcode,

    /**
     * Digital Object Identifier;
     * a character string used to uniquely identify an object. A DOI name is divided into two parts, a prefix and a suffix, separated by a slash.
     */
    DOI,

    /**
     * European Article Number, now renamed International Article Number, but retaining the original acronym, is a 13-digit barcoding standard which is a superset of the original 12-digit Universal Product Code (UPC) system.
     */
    EAN13,

    /**
     * Electronic International Standard Serial Number;
     * ISSN used to identify periodicals in electronic form (eISSN or e- ISSN).
     */
    EISSN,

    /**
     * A handle is an abstract reference to a resource.
     */
    Handle,

    /**
     * International Geo Sample Number;
     * a 9-digit alphanumeric code that uniquely identifies samples from our natural environment and related sampling features.
     */
    IGSN,

    /**
     * International Standard Book Number;
     * a unique numeric book identifier. There are 2 formats: a 10-digit ISBN format and a 13- digit ISBN.
     */
    ISBN,

    /**
     * International Standard Serial Number;
     * a unique 8-digit number used to identify a print or electronic periodical publication.
     */
    ISSN,

    /**
     * International Standard Text Code;
     * a unique "number" assigned to a textual work. An ISTC consists of 16 numbers and/or letters.
     */
    ISTC,

    /**
     * The linking ISSN or ISSN-L enables collocation or linking among different media versions of a continuing resource.
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
     * Universal Product Code is a barcode symbology used for tracking trade items in stores. Its most common form, the UPC-A, consists of 12 numerical digits.
     */
    UPC,

    /**
     * Uniform Resource Locator, also known as web address, is a specific character string that constitutes a reference to a resource.
     * The syntax is: scheme://domain:port/path?query_string#fragment_id
     */
    URL,

    /**
     * Uniform Resource Name;
     * is a unique and persistent identifier of an electronic document. The syntax is: urn:&lt;NID&gt;:&lt;NSS&gt;
     * the leading urn:sequence is case-insensitive, &lt;NID&gt; is the namespace identifier, &lt;NSS&gt; is the namespace-specific string.
     */
    URN
}
