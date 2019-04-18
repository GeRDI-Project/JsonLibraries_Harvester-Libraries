/*
 *  Copyright © 2019 Robin Weiss (http://www.gerdi-project.de/)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package de.gerdiproject.harvest.constants;

import de.gerdiproject.harvest.utils.CollectionUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class contains constants used by {@linkplain CollectionUtils} and related classes.
 *
 * @author Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionConstants
{
    public static final String REMOVED_INVALID_OBJECT = "Discarded %s, because it was deemed invalid:%n%s";
    public static final String REMOVED_DUPLICATE_OBJECT = "Discarded %s, because it is a duplicate:%n%s";
}
