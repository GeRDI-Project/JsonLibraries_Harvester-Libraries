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

import de.gerdiproject.json.datacite.DataCiteJson;

/**
 * This static class is a collection of constants that are used by {@linkplain DataCiteJson} objects.
 *
 * @author Robin Weiss
 */
public class DataCiteConstants
{
    public static final String ERROR_INVALID_LISTS =
        "All lists of the DataCiteJson object must support the remove() operations, for clean removal of invalid values.\n"
        + "The lists are now fixed during runtime, which costs performance. Please, check your code!";
    /**
     * Private constructor, because this is a static class.
     */
    private DataCiteConstants()
    {

    }
}
