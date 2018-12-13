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

import lombok.Data;

/**
 * An identifier other than the primary Identifier applied to the resource being registered.
 * May be used for local identifiers. AlternateIdentifier should be used for another identifier of the same
 * instance (same location, same file).
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data
public class AlternateIdentifier
{
    /**
     * A unique free text identifier. This may be any alphanumeric string
     * which is unique within its domain of issue. In XML, this is the value between the alternateIdentifier-tags.
     * <br>e.g. E‐GEOD‐34814
     */
    private final String value;

    /**
     * The free text type of the AlternateIdentifier.
     * <br>e.g. "A local accession number"
     */
    @SerializedName("alternateIdentifierType")
    private final String type;
}
