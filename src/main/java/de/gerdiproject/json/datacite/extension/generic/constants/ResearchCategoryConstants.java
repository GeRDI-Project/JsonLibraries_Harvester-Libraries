/**
 * Copyright © 2017 Fidan Limani, Robin Weiss (http://www.gerdi-project.de)
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
package de.gerdiproject.json.datacite.extension.generic.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class serves as a collection of constants that define a controlled list of ResearchCategorys.
 * It was generated via the {@linkplain de.gerdiproject.generator.research.utils.ResearchGenerator}.
 * If there are errors or inconsistencies, please contact the authors.
 *
 * @author Fidan Limani, Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResearchCategoryConstants
{
    public static final String HUMANITIES_AND_SOCIAL_SCIENCES = "Humanities and Social Sciences";
    public static final String LIFE_SCIENCES = "Life Sciences";
    public static final String NATURAL_SCIENCES = "Natural Sciences";
    public static final String ENGINEERING_SCIENCES = "Engineering Sciences";
}