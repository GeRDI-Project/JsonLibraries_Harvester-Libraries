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

import lombok.Data;

/**
 * This class models the concepts in SOEP. The concept is represented in two (language) versions, German and English.
 *
 * @author Fidan Limani
 */
@Data
public class SoepConcept
{
    /** Concept name: how social scientists refer them */
    private final String name;

    /** (Human understandable) Concept label */
    private final String label;

    /** Concept label language (can be "DE" or "EN") */
    private final String language;
}