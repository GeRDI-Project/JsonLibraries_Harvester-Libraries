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
    @SerializedName("affiliation") @Setter(AccessLevel.NONE)
    private Set<String> affiliations;


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
    public void addNameIdentifiers(Collection<NameIdentifier> nameIdentifiers)
    {
        this.nameIdentifiers = CollectionUtils.addToSet(this.nameIdentifiers, nameIdentifiers);
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
}
