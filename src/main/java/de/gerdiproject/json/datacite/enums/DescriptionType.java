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
package de.gerdiproject.json.datacite.enums;

/**
 * This enumeration describes what a description entails.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Robin Weiss
 */
@SuppressWarnings("PMD.FieldNamingConventions") // enums should be upper-case, but DataCite fields are excluded
public enum DescriptionType {
    /**
     * A brief description of the resource and the context in which the resource was created.
     */
    Abstract,

    /**
     * The methodology employed for the study or research.
     */
    Methods,

    /**
     * Information about a repeating series, such as volume, issue, number.
     */
    SeriesInformation,

    /**
     * A listing of the Table of Contents.
     */
    TableOfContents,

    /**
     * Detailed information that may be associated with design, implementation, operation, use, and/or maintenance of a process or system.
     */
    TechnicalInfo,

    /**
     * Other description information that does not fit into an existing category.
     */
    Other
}