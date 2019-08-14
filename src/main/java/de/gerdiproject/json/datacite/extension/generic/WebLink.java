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
package de.gerdiproject.json.datacite.extension.generic;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.json.datacite.extension.generic.enums.WebLinkType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A link to the data provider's website.
 *
 * This object is NOT part of the original DataCite schema.
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class WebLink
{
    /**
     * -- GETTER --
     * Retrieves the URI of the web link.
     * @return the URI of the web link
     *
     * -- SETTER --
     * Sets the URI of the web link.
     * @param uri the URI of the web link
     */
    @SerializedName("webLinkURI")
    private final String uri;


    /**
     * -- GETTER --
     * Retrieves the descriptive name of the web link destination.
     * @return the descriptive name of the web link destination
     *
     * -- SETTER --
     * Sets the descriptive name of the web link destination.
     * @param name the descriptive name of the web link destination
     */
    @SerializedName("webLinkName")
    private String name;


    /**
     * -- GETTER --
     * Retrieves the link category.
     * @return the link category
     *
     * -- SETTER --
     * Sets the link category.
     * @param type the link category
     */
    @SerializedName("webLinkType")
    private WebLinkType type;
}
