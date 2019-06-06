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

import de.gerdiproject.json.datacite.enums.IdentifierType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The primary Identifier of the resource being registered.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor
public class Identifier
{
    /**
     * A unique string that identifies the resource.
     * In XML, this is the value between the identifier-tags.
     * <br>e.g. 10.1234/foo
     */
    private final String value;

    /**
     * The type of the identifier.
     */
    @SerializedName("identifierType")
    private final IdentifierType type;


    /**
     * Constructs a DOI identifier.
     *
     * @param value a DOI identifier string of the format "10.1234/foo"
     */
    public Identifier(final String value)
    {
        this.value = value;
        this.type = IdentifierType.DOI;
    }
}
