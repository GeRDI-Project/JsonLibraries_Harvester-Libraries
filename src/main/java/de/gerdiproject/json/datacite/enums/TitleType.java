/**
 * Copyright © 2017 Robin Weiss (http://www.gerdi-project.de)
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
package de.gerdiproject.json.datacite.enums;

import de.gerdiproject.json.datacite.Title;

/**
 * This enumeration describes the type of a {@link Title}.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Robin Weiss
 */
public enum TitleType {
    /**
     * An alternative title variant of the main title.
     */
    AlternativeTitle,

    /**
     * An extension of the main title.
     */
    Subtitle,

    /**
     * A title that is translated from the standard language (?)
     * This type is not properly documented and may be interpreted wrongly.
     */
    TranslatedTitle,

    /**
     * A title that does not match any other types and is not the main title.
     */
    Other
}