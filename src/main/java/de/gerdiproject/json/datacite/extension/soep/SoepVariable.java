/**
 * Copyright © 2018 Fidan Limani (http://www.gerdi-project.de)
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
package de.gerdiproject.json.datacite.extension.soep;

import java.util.Collection;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class models the SOEP-specific metadata: Variables
 * It includes the initial set of attributes identified so far; new use cases might dictate an extension/change.
 *
 * @author Fidan Limani
 */
@AllArgsConstructor @RequiredArgsConstructor
@Data
public class SoepVariable
{
    /**
     * -- GETTER --
     * Retrieves the name of the variable.
     * @return the name of the variable
     *
     * -- SETTER --
     * Sets the name of the variable.
     * @param name the name of the variable
     */
    @SerializedName("variableName")
    private final String name;


    /**
     * -- GETTER --
     * Retrieves the study source for the variable.
     * @return the study source for the variable
     *
     * -- SETTER --
     * Sets the study source for the variable.
     * @param source the study source for the variable
     */
    private final String source;


    /**
     * -- GETTER --
     * Retrieves the concepts that describe the variable.
     * @return the concepts that describe the variable
     *
     * -- SETTER --
     * Sets the concepts that describe the variable.
     * @param concepts the concepts that describe the variable
     */
    private Set<SoepConcept> concepts;


    /**
     * Adds {@linkplain SoepConcept}s of the variable.
     *
     * @param soepConcepts the {@linkplain SoepConcept}s that are to be added
     *
     */
    public void addSoepConcepts(final Collection<SoepConcept> soepConcepts)
    {
        this.concepts = CollectionUtils.addToSet(this.concepts, soepConcepts);
    }
}