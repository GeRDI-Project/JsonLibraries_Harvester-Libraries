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
package de.gerdiproject.json.datacite.abstr;

import java.util.Collection;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.utils.CollectionUtils;
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
     * Unique identifiers for an individual or legal entity, according to various schemes.
     * <br>e.g. Orcid ID
     */
    private Set<NameIdentifier> nameIdentifiers;

    /**
     * The organisational or institutional affiliations of the person.
     * <br>e.g. Council of Ricks
     */
    @SerializedName("affiliation")
    private Set<String> affiliations;


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
    public Set<NameIdentifier> getNameIdentifiers()
    {
        return nameIdentifiers;
    }


    /**
     * Changes unique identifiers of an individual or legal entity, according to various schemes.
     * <br>e.g. orcid id number
     *
     * @param nameIdentifiers unique identifiers of an individual or legal entity
     */
    public void addNameIdentifiers(Collection<NameIdentifier> nameIdentifiers)
    {
        this.nameIdentifiers = CollectionUtils.addToSet(this.nameIdentifiers, nameIdentifiers);
    }


    /**
     * Returns the organisational or institutional affiliations of the person.
     *
     * @return the organisational or institutional affiliations of the person
     */
    public Set<String> getAffiliations()
    {
        return affiliations;
    }


    /**
     * Changes the organisational or institutional affiliations of the person.
     * <br>e.g. Council of Ricks
     *
     * @param affiliations the organisational or institutional affiliations of the person
     */
    public void addAffiliations(Collection<String> affiliations)
    {
        this.affiliations = CollectionUtils.addToSet(this.affiliations, affiliations);
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((affiliations == null) ? 0 : affiliations.hashCode());
        result = prime * result + ((familyName == null) ? 0 : familyName.hashCode());
        result = prime * result + ((givenName == null) ? 0 : givenName.hashCode());
        result = prime * result + ((nameIdentifiers == null) ? 0 : nameIdentifiers.hashCode());
        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof AbstractPerson))
            return false;

        AbstractPerson other = (AbstractPerson) obj;

        if (affiliations == null) {
            if (other.affiliations != null)
                return false;
        } else if (!affiliations.equals(other.affiliations))
            return false;

        if (familyName == null) {
            if (other.familyName != null)
                return false;
        } else if (!familyName.equals(other.familyName))
            return false;

        if (givenName == null) {
            if (other.givenName != null)
                return false;
        } else if (!givenName.equals(other.givenName))
            return false;

        if (nameIdentifiers == null) {
            if (other.nameIdentifiers != null)
                return false;
        } else if (!nameIdentifiers.equals(other.nameIdentifiers))
            return false;

        return true;
    }
}
