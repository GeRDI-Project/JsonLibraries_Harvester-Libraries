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

import lombok.Data;

/**
 * This class represents a feature as part of a {@linkplain FeatureCollection} JSON object.
 * A Feature object represents a spatially bounded thing.  Every Feature
 * object is a GeoJSON object no matter where it occurs in a GeoJSON
 * text.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7946#section-3.2">https://tools.ietf.org/html/rfc7946#section-3.2</a>
 *
 * @author Robin Weiss
 *
 * @param <T> the type of properties
 */
@Data
public class Feature<T>
{
    /**
     * -- GETTER --
     * The geometry object of the feature.
     * @return the {@linkplain GeoJson} geometry or null if the feature is unlocated
     *
     * -- SETTER --
     * Sets the geometry object of the feature.
     * @param geometry a {@linkplain GeoJson} geometry or null if the feature is unlocated
     */
    private GeoJson geometry;


    /**
     * -- GETTER --
     * Retrieves the type of the feature, which should always be "Feature".
     * @return the type of the feature
     *
     * -- SETTER --
     * Sets the type of the feature, which should always be "Feature".
     * @param type the type of the feature
     */
    private String type;


    /**
     * -- GETTER --
     * Retrieves additional properties that describe the feature.
     * @return additional properties that describe the feature
     *
     * -- SETTER --
     * Sets additional properties that describe the feature.
     * @param properties additional properties that describe the feature
     */
    private T properties;
}
