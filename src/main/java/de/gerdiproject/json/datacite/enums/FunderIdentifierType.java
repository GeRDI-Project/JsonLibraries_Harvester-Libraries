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
 * This enumeration describes the type of the funder identifier.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Robin Weiss
 */
public enum FunderIdentifierType {
    /**
     * International Standard Name Identifier;
     * the globally recognized and adopted international standard approved by ISO for the unique identification of the public identities of persons and organizations across all fields of creative activity.
     * see http://www.isni.org/
     */
    ISNI,

    /**
     * Global Research Identifier Database ID;
     * GRID is comprised of a worldwide collection of institutes associated with academic research. The institutes contained are distinguished by a unique identifier, the GRID ID.
     * see https://www.grid.ac/
     */
    GRID,

    /**
     * Crossref is a not-for-profit membership organization for scholarly publishing working to make content easy to find, cite, link, and assess.
     * Funder IDs can be found in the Crossref Funder Registry.
     * see https://www.crossref.org/services/funder-registry/
     */
    Crossref_Funder_ID,

    /**
     * An identifier type that does not fit into any other category.
     */
    Other
}
