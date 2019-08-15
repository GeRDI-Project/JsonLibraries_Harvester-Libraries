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
package de.gerdiproject.json.datacite.nested;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The code assigned by the funder to a sponsored award (grant).
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class AwardNumber
{
    /**
     * -- GETTER --
     * Retrieves the value of the AwardNumber.
     * @return the value of the AwardNumber
     *
     * -- SETTER --
     * Sets the value of the AwardNumber.
     * @param value the value of the AwardNumber
     */
    private final String value;


    /**
     * -- GETTER --
     * Retrieves the URI leading to a page for more information about the award.
     * @return the URI leading to a page for more information about the award
     *
     * -- SETTER --
     * Sets the URI leading to a page for more information about the award.
     * @param uri the URI leading to a page for more information about the award
     */
    @SerializedName("awardURI")
    private String uri;
}
