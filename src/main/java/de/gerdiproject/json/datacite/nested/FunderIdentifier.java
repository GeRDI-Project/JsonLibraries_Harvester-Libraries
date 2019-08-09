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

import de.gerdiproject.json.datacite.enums.FunderIdentifierType;
import lombok.Data;

/**
 * Uniquely identifies a funding entity, according to various types.
 * <br>This is not indexed!
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
@Data
public class FunderIdentifier
{
    /**
     * The value of the FunderIdentifier.
     * In XML, this is the value between the funderIdentifier-tags.
     */
    private final String value;

    /**
     * The type of the funder identifier.
     */
    private final FunderIdentifierType funderIdentifierType;


    /**
     * The URI of the funderIdentifierType.
     */
    private String schemeURI;
}
