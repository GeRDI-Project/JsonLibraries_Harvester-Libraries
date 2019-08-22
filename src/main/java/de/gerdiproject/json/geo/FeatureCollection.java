/**
 * Copyright © 2019 Robin Weiss (http://www.gerdi-project.de)
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
package de.gerdiproject.json.geo;

import java.util.List;

import lombok.Data;

/**
 * This class represents a feature collection JSON object.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7946#section-3.2">https://tools.ietf.org/html/rfc7946#section-3.2</a>
 *
 * @author Robin Weiss
 *
 * @param <T> the type of the {@linkplain Feature} properties
 */
@Data
public class FeatureCollection <T>
{
    /**
     * -- GETTER --
     * Retrieves a {@linkplain List} of {@linkplain Feature}s.
     * It is possible for this list to be empty.
     *
     * @return a {@linkplain List} of {@linkplain Feature}s.
     *
     * -- SETTER --
     * Sets a {@linkplain List} of {@linkplain Feature}s.
     * It is possible for this list to be empty.
     * @param features the {@linkplain List} of {@linkplain Feature}s.
     */
    private List<Feature<T>> features;


    /**
     * -- GETTER --
     * Retrieves the type of the feature collection, which should
     * always be "FeatureCollection".
     * @return the type of the feature collection
     *
     * -- SETTER --
     * Sets the type of the feature collection, which should
     * always be "FeatureCollection".
     * @param type the type of the feature collection
     */
    private String type;
}
