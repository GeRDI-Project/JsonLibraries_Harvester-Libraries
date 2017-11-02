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
package de.gerdiproject.json.datacite.nested;

import de.gerdiproject.json.datacite.Contributor;
import de.gerdiproject.json.datacite.Creator;

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
    private NameIdentifier nameIdentifier;

    /**
     * The organisational or institutional affiliation of the person.
     * <br>e.g. Council of Ricks
     */
    private String affiliation;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param name the name of the person
     */
    public AbstractPerson(String name)
    {
        setName(new PersonName(name));
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
     * Returns a unique identifier of an individual or legal entity, according to various schemes.
     *
     * @return a unique identifier of an individual or legal entity
     */
    public NameIdentifier getNameIdentifier()
    {
        return nameIdentifier;
    }


    /**
     * Changes a unique identifier of an individual or legal entity, according to various schemes.
     * <br>e.g. orcid id number
     *
     * @param nameIdentifier a unique identifier of an individual or legal entity
     */
    public void setNameIdentifier(NameIdentifier nameIdentifier)
    {
        this.nameIdentifier = nameIdentifier;
    }


    /**
     * Returns the organisational or institutional affiliation of the person.
     *
     * @return the organisational or institutional affiliation of the person
     */
    public String getAffiliation()
    {
        return affiliation;
    }


    /**
     * Changes the organisational or institutional affiliation of the person.
     * <br>e.g. Council of Ricks
     *
     * @param affiliation the organisational or institutional affiliation of the person
     */
    public void setAffiliation(String affiliation)
    {
        this.affiliation = affiliation;
    }
}
