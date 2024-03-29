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
package de.gerdiproject.json.datacite.constants;

import de.gerdiproject.json.datacite.extension.generic.AbstractResearch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This static class is a collection of constants that are used by {@linkplain AbstractResearch} and its sub-classes.
 *
 * @author Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataCiteResearchConstants
{
    // Json Fields
    public static final String DISCIPLINE_JSON = "discipline";
    public static final String RNBR_JSON = "rnbr";
    public static final String AREA_JSON = "area";
    public static final String CATEGORY_JSON = "category";

    // Formatting
    public static final String AREA_RNBR_FORMAT = "%03d";
    public static final String DISCIPLINE_RNBR_FORMAT = "%03d-%02d";
}
