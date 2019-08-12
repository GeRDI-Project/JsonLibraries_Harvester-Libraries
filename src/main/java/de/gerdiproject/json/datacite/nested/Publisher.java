/**
 * Copyright © 2019 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
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
package de.gerdiproject.json.datacite.nested;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The entity that holds, archives, publishes prints, distributes,
 * releases, issues, or produces the resource. This property will be used to
 * formulate the citation, so consider the prominence of the role.
 *
 * @author Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class Publisher
{
    /**
     * The name of the publisher.
     */
    private final String value;


    /**
     * An optional IETF language tag of the publisher.
     * <br>e.g. de, en-US
     */
    private String lang;
}
