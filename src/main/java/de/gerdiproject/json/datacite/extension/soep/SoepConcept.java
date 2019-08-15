/*
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

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * This class models concepts from the social sciences domain.
 *
 * @author Fidan Limani
 */
@Data
public class SoepConcept
{
    /**
     * -- GETTER --
     * Retrieves the name of the concept, i.e. how social scientist
     * refer to it.
     * @return the name of the concept
     *
     * -- SETTER --
     * Sets the name of the concept, i.e. how social scientist
     * refer to it.
     * @param name the name of the concept
     */
    private final String name;


    /**
     * -- GETTER --
     * Retrieves a description of the concept.
     * @return the description of the concept
     *
     * -- SETTER --
     * Sets the description of the concept.
     * @param label a description of the concept
     */
    private final String label;


    /**
     * -- GETTER --
     * Retrieves the concept label language.
     * <br>e.g. de, en-US
     * @return the concept label language
     *
     * -- SETTER --
     * Sets the concept label language.
     * <br>e.g. de, en-US
     * @param lang the concept label language
     */
    private final String lang;
}
