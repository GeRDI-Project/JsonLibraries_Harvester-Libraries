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
package de.gerdiproject.json.datacite.abstr;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.json.datacite.Contributor;
import de.gerdiproject.json.datacite.Creator;
import de.gerdiproject.json.datacite.nested.NameIdentifier;
import de.gerdiproject.json.datacite.nested.PersonName;

/**
 * Both the {@linkplain Creator} and {@linkplain Contributor} share so many similarities, that it made sense
 * to include them in an abstract class. Behold the magnificence of this class!
 *
 * @author Mathis Neumann, Robin Weiss
 */
public abstract class AbstractPerson
{
    /**
     * The personal or first name of the person.
     * <br>e.g. Rick
     */
    private String givenName;

    /**
     * The surname or last name of the person.
     * <br>e.g. Sanchez
     */
    private String familyName;

    /**
     * A unique identifier for an individual or legal entity, according to various schemes.
     * <br>e.g. Orcid ID
     */
    private List<NameIdentifier> nameIdentifiers;

    /**
     * The organisational or institutional affiliation of the person.
     * <br>e.g. Council of Ricks
     */
    @SerializedName("affiliation")
    private List<String> affiliations;


    /**
     * Constructor that creates a {@linkplain PersonName} out of a String.
     *
     * @param name the name of the person
     */
    public AbstractPerson(String name)
    {
        setName(new PersonName(name));
    }


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param nameObject the name and name type of the person
     */
    public AbstractPerson(PersonName nameObject)
    {
        setName(nameObject);
    }


    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    abstract public PersonName getName();


    /**
     * Changes the name of the person.
     * <br>e.g. Sanchez, Rick
     *
     * @param name the name of the person
     */
    abstract public void setName(PersonName name);


    /**
     * Returns the personal or first name of the person.
     *
     * @return the personal or first name of the person
     */
    public String getGivenName()
    {
        return givenName;
    }


    /**
     * Changes the personal or first name of the person.
     * <br>e.g. Rick
     *
     * @param givenName the personal or first name of the person
     */
    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }


    /**
     * Returns the surname or last name of the person.
     *
     * @return the surname or last name of the person
     */
    public String getFamilyName()
    {
        return familyName;
    }


    /**
     * Changes the surname or last name of the person.
     * <br>e.g. Sanchez
     *
     * @param familyName the surname or last name of the person
     */
    public void setFamilyName(String familyName)
    {
        this.familyName = familyName;
    }


    /**
     * Returns unique identifiers of an individual or legal entity, according to various schemes.
     *
     * @return unique identifiers of an individual or legal entity
     */
    public List<NameIdentifier> getNameIdentifiers()
    {
        return nameIdentifiers;
    }


    /**
     * Changes unique identifiers of an individual or legal entity, according to various schemes.
     * <br>e.g. orcid id number
     *
     * @param nameIdentifiers unique identifiers of an individual or legal entity
     */
    public void setNameIdentifiers(List<NameIdentifier> nameIdentifiers)
    {
        this.nameIdentifiers = nameIdentifiers;
    }


    /**
     * Returns the organisational or institutional affiliations of the person.
     *
     * @return the organisational or institutional affiliations of the person
     */
    public List<String> getAffiliations()
    {
        return affiliations;
    }


    /**
     * Changes the organisational or institutional affiliations of the person.
     * <br>e.g. Council of Ricks
     *
     * @param affiliations the organisational or institutional affiliations of the person
     */
    public void setAffiliations(List<String> affiliations)
    {
        this.affiliations = affiliations;
    }
}
