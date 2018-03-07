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
 * This enumeration represents the description of the relationship of the resource being registered (A)
 * and the related resource (B).
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Robin Weiss
 */
public enum RelationType {
    /**
     * Indicates that B includes A in a citation.
     */
    IsCitedBy,

    /**
     * Indicates that A includes B in a citation.
     */
    Cites,

    /**
     * Indicates that A is a supplement to B.
     */
    IsSupplementTo,

    /**
     * Indicates that B is a supplement to A.
     */
    IsSupplementedBy,

    /**
     * Indicates A is continued by the work B.
     */
    IsContinuedBy,

    /**
     * Indicates A is a continuation of the work B.
     */
    Continues,

    /**
     * Indicates A describes B.
     */
    Describes,

    /**
     * Indicates A is described by B.
     */
    IsDescribedBy,

    /**
     * Indicates resource A has additional metadata B.
     */
    HasMetadata,

    /**
     * Indicates additional metadata A for a   resource B.
     */
    IsMetadataFor,

    /**
     * Indicates A has a version B.
     */
    HasVersion,

    /**
     * Indicates A is a version of B.
     */
    IsVersionOf,

    /**
     * Indicates A is a new edition of B, where the new edition has been modified or updated.
     */
    IsNewVersionOf,

    /**
     * Indicates A is a previous edition of B.
     */
    IsPreviousVersionOf,

    /**
     * Indicates A is a portion of B; may be used for elements of a series.
     */
    IsPartOf,

    /**
     * Indicates A includes the part B.
     */
    HasPart,

    /**
     * Indicates A is used as a source of information by B.
     */
    IsReferencedBy,

    /**
     * Indicates B is used as a source of information for A.
     */
    References,

    /**
     * Indicates B is documentatio n about/ explaining A.
     */
    IsDocumentedBy,

    /**
     * Indicates A is documentatio n about/B.
     */
    Documents,

    /**
     * Indicates B is used to compile or create A.
     */
    IsCompiledBy,

    /**
     * Indicates B is the result of a compile or creation event using A.
     */
    Compiles,

    /**
     * Indicates A is a variant or different form of B, e.g. calculated or calibrated form or different packaging.
     */
    IsVariantFormOf,

    /**
     * Indicates A is the original form of B.
     */
    IsOriginalFormOf,

    /**
     * Indicates that A is identical to B, for use when there is a need to register two separate instances of the same resource.
     */
    IsIdenticalTo,

    /**
     * Indicates that A is reviewed by B.
     */
    IsReviewedBy,

    /**
     * Indicates that A is a review of B.
     */
    Reviews,

    /**
     * Indicates B is a source upon which A is based.
     */
    IsDerivedFrom,

    /**
     * Indicates A is a source upon which B is based.
     */
    IsSourceOf,

    /**
     * Indicates A is required by B.
     */
    IsRequiredBy,

    /**
     * Indicates A requires B.
     */
    Requires
}
