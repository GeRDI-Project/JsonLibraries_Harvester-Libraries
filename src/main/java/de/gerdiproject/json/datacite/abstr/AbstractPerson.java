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
import java.util.LinkedList;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.utils.CollectionUtils;
import de.gerdiproject.json.datacite.Contributor;
import de.gerdiproject.json.datacite.Creator;
import de.gerdiproject.json.datacite.nested.Affiliation;
import de.gerdiproject.json.datacite.nested.NameIdentifier;
import de.gerdiproject.json.datacite.nested.PersonName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Both the {@linkplain Creator} and {@linkplain Contributor} share so many similarities, that it made sense
 * to include them in an abstract class. Behold the magnificence of this class!
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data
public abstract class AbstractPerson
{
    /**
     * -- GETTER --
     * Retrieves the personal or first name of the person.
     * <br>e.g. Rick
     * @return the personal or first name of the person
     *
     * -- SETTER --
     * Sets the personal or first name of the person.
     * <br>e.g. Rick
     * @param givenName the personal or first name of the person
     */
    private String givenName;


    /**
     * -- GETTER --
     * Retrieves the surname or last name of the person.
     * <br>e.g. Sanchez
     * @return the urname or last name of the person
     *
     * -- SETTER --
     * Sets the surname or last name of the person.
     * <br>e.g. Sanchez
     * @param familyName the urname or last name of the person
     */
    private String familyName;


    /**
     * -- GETTER --
     * Retrieves unique identifiers for an individual or legal entity, according to various schemes.
     * <br>e.g. Orcid ID
     * @return unique identifiers for an individual or legal entity
     *
     * -- SETTER --
     * Sets unique identifiers for an individual or legal entity, according to various schemes.
     * <br>e.g. Orcid ID
     * @param nameIdentifiers unique identifiers for an individual or legal entity
     */
    private Set<NameIdentifier> nameIdentifiers;


    /**
     * -- GETTER --
     * Retrieves the organisational or institutional affiliations of the person.
     * <br>e.g. Council of Ricks
     * @return the organisational or institutional affiliations of the person
     *
     * -- SETTER --
     * Sets the organisational or institutional affiliations of the person.
     * <br>e.g. Council of Ricks
     * @param affiliations the organisational or institutional affiliations of the person
     */
    @SerializedName("affiliation") @Setter(AccessLevel.NONE)
    private Set<Affiliation> affiliations;


    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    abstract public PersonName getName();


    /**
     * Changes unique identifiers of an individual or legal entity, according to various schemes.
     * <br>e.g. orcid id number
     *
     * @param nameIdentifiers unique identifiers of an individual or legal entity
     */
    public void addNameIdentifiers(final Collection<NameIdentifier> nameIdentifiers)
    {
        this.nameIdentifiers = CollectionUtils.addToSet(this.nameIdentifiers, nameIdentifiers);
    }


    /**
     * Changes the organisational or institutional affiliations of the person.
     * <br>e.g. Council of Ricks
     *
     * @param affiliationValues the organisational or institutional affiliations of the person
     *
     * @deprecated Use {@linkplain AbstractPerson#addAffiliations(Collection)} instead
     */
    @Deprecated
    public void addAffiliations(final Iterable<String> affiliationValues)
    {
        final Collection<Affiliation> affiliations = new LinkedList<>();

        for (final String value : affiliationValues)
            affiliations.add(new Affiliation(value));

        addAffiliations(affiliations);
    }


    /**
     * Adds organisational or institutional affiliations of the person.
     * <br>e.g. Council of Ricks
     *
     * @param affiliations the organisational or institutional {@linkplain Affiliation}s of the person
     */
    public void addAffiliations(final Collection<Affiliation> affiliations)
    {
        this.affiliations = CollectionUtils.addToSet(this.affiliations, affiliations);
    }
}
