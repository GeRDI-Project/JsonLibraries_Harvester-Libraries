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

import de.gerdiproject.json.datacite.enums.ResourceTypeGeneral;
import lombok.Data;

/**
 * This object represents a description of the resource.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
@Data
public class ResourceType
{
    /**
     * -- GETTER --
     * Retrieves the free text description of the resource.
     * <br>e.g. "Census Data"
     * @return the free text description of the resource
     *
     * -- SETTER --
     * Sets the free text description of the resource.
     * <br>e.g. "Census Data"
     * @param value a free text description of the resource
     */
    private final String value;


    /**
     * -- GETTER --
     * Retrieves the general type of a resource.
     * @return the general type of a resource
     *
     * -- SETTER --
     * Sets the general type of a resource.
     * @param resourceTypeGeneral the general type of a resource
     */
    private final ResourceTypeGeneral resourceTypeGeneral;
}
