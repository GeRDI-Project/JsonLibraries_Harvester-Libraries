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
 * This enumeration describes an event that is marked by a date.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Robin Weiss
 */
@SuppressWarnings("PMD.FieldNamingConventions") // enums should be upper-case, but DataCite fields are excluded
public enum DateType {
    /**
     * The date that the publisher accepted the resource into their system.
     * <br>
     * To indicate the start of an embargo period, use {@linkplain Submitted} or {@linkplain Accepted}, as appropriate.
     */
    Accepted,

    /**
     * The date the resource is made publicly available. May be a range.
     * <br>
     * To indicate the end of an embargo period, use {@linkplain Available}.
     */
    Available,

    /**
     * The specific, documented date at which the resource receives a copyrighted status, if applicable.
     */
    Copyrighted,

    /**
     * The date or date range in which the resource content was collected.
     * <br>
     * To indicate precise or particular timeframes in which research was conducted.
     */
    Collected,

    /**
     * The date the resource itself was put together; this could be a date range or a single date for a final component,
     * e.g. the finalised file with all of the data.
     * <br>
     * Recommended for discovery.
     */
    Created,

    /**
     * The date that the resource is published or distributed e.g. to a data centre.
     */
    Issued,

    /**
     * The date the creator submits the resource to the publisher. This could be different from Accepted if the publisher then applies a selection process.
     * <br>
     * Recommended for discovery.
     * <br>
     * To indicate the start of an embargo period, use {@linkplain Submitted} or {@linkplain Accepted}, as appropriate.
     */
    Submitted,

    /**
     * The date of the last update to the resource, when the resource is being added to. May be a range.
     */
    Updated,

    /**
     * The date or date range during which the dataset or resource is accurate.
     */
    Valid,

    /**
     * The date the resource is removed.
     * <br>
     * It’s good practice to indicate the reason for retraction or withdrawal in the descriptionType.
     */
    Withdrawn,

    /**
     * This type should be applied if the date does not fit any of the other categories.
     */
    Other
}
