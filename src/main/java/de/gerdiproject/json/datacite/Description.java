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
package de.gerdiproject.json.datacite;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.utils.StringUtils;
import de.gerdiproject.json.datacite.enums.DescriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Additional information that does not fit in any of the other categories.
 * May be used for technical information.
 * NOTE:    The DataCite schema allows br-tags here, which are not supported
 *          by Elastic Search and should be replaced with \n!
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class Description implements ICleanable
{
    /**
     * -- GETTER --
     * Retrieves the free descriptive text.
     * @return the free descriptive text
     *
     * -- SETTER --
     * Sets the free descriptive text.
     * @param value free descriptive text
     */
    @NonNull
    private String value;


    /**
     * -- GETTER --
     * Retrieves the type that outlines what the description entails.
     * @return the type that outlines what the description entails
     *
     * -- SETTER --
     * Sets the type that outlines what the description entails.
     * @param type the type that outlines what the description entails
     */
    @SerializedName("descriptionType")
    private final DescriptionType type;


    /**
     * -- GETTER --
     * Retrieves the IETF language tag of the description text.
     * <br>e.g. de, en-US
     * @return the IETF language tag of the description text
     *
     * -- SETTER --
     * Sets the IETF language tag of the description text.
     * <br>e.g. de, en-US
     * @param lang the IETF language tag of the description text
     */
    private String lang;


    /**
     * Cleans the description text, removing HTML and unescaping special characters.
     */
    @Override
    public boolean clean()
    {
        setValue(StringUtils.clean(value));
        return true;
    }
}
