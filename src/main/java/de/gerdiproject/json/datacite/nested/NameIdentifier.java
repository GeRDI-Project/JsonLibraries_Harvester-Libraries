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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A unique identifier for an individual or legal entity, according to various schemes.
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class NameIdentifier
{
    /**
     * Uniquely identifies an individual or legal entity, according to various schemes.
     * In XML, this is the value between the nameIdentifier-tags.
     * <br>e.g. orcid id number
     */
    private final String value;

    /**
     * The name of the name identifier scheme.
     * <br>e.g. ORCID
     */
    private final String nameIdentifierScheme;

    /**
     * The URI of the name identifier scheme.
     * <br>e.g. http://orcid.org/
     */
    private String schemeURI;
}
