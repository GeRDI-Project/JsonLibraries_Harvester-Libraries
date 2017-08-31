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
 * The main researcher involved in producing the data, or an author of the publication.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Creator
{
    /**
     * The name of the creator.
     * e.g. Sanchez, Rick
     */
    private String name;

    /**
     * The personal or first name of the creator.
     * e.g. Rick
     */
    private String givenName;

    /**
     * The surname or last name of the creator.
     * e.g. Sanchez
     */
    private String familyName;

    /**
     * Uniquely identifies an individual or legal entity, according to various schemes.
     * e.g. orcid id number
     */
    private String nameIdentifier;

    /**
     * The name of the name identifier scheme.
     * e.g. ORCID
     */
    private String nameIdentifierScheme;

    /**
     * The URI of the name identifier scheme.
     * e.g. http://orcid.org/
     */
    private String schemeURI;

    /**
     * The organisational or institutional affiliation of the creator.
     * e.g. Council of Ricks
     */
    private String affiliation;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param name the name of the creator
     */
    public Creator(String name)
    {
        this.name = name;
    }


    /**
     * Returns the name of the creator.
     *
     * @return the name of the creator
     */
    public String getName()
    {
        return name;
    }


    /**
     * Changes the name of the creator.
     * e.g. Sanchez, Rick
     *
     * @param name the name of the creator
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Returns the personal or first name of the creator.
     *
     * @return the personal or first name of the creator
     */
    public String getGivenName()
    {
        return givenName;
    }


    /**
     * Changes the personal or first name of the creator.
     * e.g. Rick
     *
     * @param givenName the personal or first name of the creator
     */
    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }


    /**
     * Returns the surname or last name of the creator.
     *
     * @return the surname or last name of the creator
     */
    public String getFamilyName()
    {
        return familyName;
    }


    /**
     * Changes the surname or last name of the creator.
     * e.g. Sanchez
     *
     * @param familyName the surname or last name of the creator
     */
    public void setFamilyName(String familyName)
    {
        this.familyName = familyName;
    }


    /**
     * Returns a unique identifier of an individual or legal entity, according to various schemes.
     *
     * @return a unique identifier of an individual or legal entity
     */
    public String getNameIdentifier()
    {
        return nameIdentifier;
    }


    /**
     * Changes a unique identifier of an individual or legal entity, according to various schemes.
     * e.g. orcid id number
     *
     * @param nameIdentifier a unique identifier of an individual or legal entity
     */
    public void setNameIdentifier(String nameIdentifier)
    {
        this.nameIdentifier = nameIdentifier;
    }


    /**
     * Returns the name of the name identifier scheme.
     *
     * @return the name of the name identifier scheme
     */
    public String getNameIdentifierScheme()
    {
        return nameIdentifierScheme;
    }


    /**
     * Changes the name of the name identifier scheme.
     * e.g. ORCID
     *
     * @param nameIdentifierSchema the name of the name identifier scheme
     */
    public void setNameIdentifierScheme(String nameIdentifierSchema)
    {
        this.nameIdentifierScheme = nameIdentifierSchema;
    }


    /**
     * Returns the URI of the name identifier scheme.
     *
     * @return the URI of the name identifier scheme
     */
    public String getSchemeURI()
    {
        return schemeURI;
    }


    /**
     * Changes the URI of the name identifier scheme.
     * e.g. http://orcid.org/
     *
     * @param nameIdentifierSchemaURI the URI of the name identifier scheme
     */
    public void setSchemeURI(String schemeURI)
    {
        this.schemeURI = schemeURI;
    }


    /**
     * Returns the organisational or institutional affiliation of the creator.
     *
     * @return the organisational or institutional affiliation of the creator
     */
    public String getAffiliation()
    {
        return affiliation;
    }


    /**
     * Changes the organisational or institutional affiliation of the creator.
     * e.g. Council of Ricks
     *
     * @param affiliation the organisational or institutional affiliation of the creator
     */
    public void setAffiliation(String affiliation)
    {
        this.affiliation = affiliation;
    }
}
