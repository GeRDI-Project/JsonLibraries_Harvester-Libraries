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

package de.gerdiproject.json.datacite.extension.metadatabowl.soep;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class models the SOEP-specific metadata: Variables
 * In it we depict the initial set of attributes identified so far; new use cases might dictate an extension/change.
 * @author Fidan Limani
 **/
@AllArgsConstructor
@Data
public class SoepVariable
{
    private String variableName;

    /**
     * The study source for the variable
     */
    private String source;

    /**
     * The concept that describes the variable
     */
    private SoepConcept concept;
}