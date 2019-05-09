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

import de.gerdiproject.json.datacite.abstr.AbstractPerson;
import de.gerdiproject.json.datacite.enums.ContributorType;
import de.gerdiproject.json.datacite.nested.PersonName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * The institution or person responsible for collecting, managing, distributing,
 * or otherwise contributing to the development of the resource.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor @EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Contributor extends AbstractPerson
{
    /**
     * The full name of the contributor.
     * <br>e.g. Patel, Emily; Nyugen, John, ABC Foundation
     */
    @SerializedName("contributorName")
    private final PersonName name;

    /**
     * The role of the contributor.
     */
    @SerializedName("contributorType")
    private final ContributorType type;


    /**
     * Constructor that creates a {@linkplain PersonName} out of a String.
     *
     * @param name the name of the contributor
     * @param type the role of the contributor
     */
    public Contributor(final String name, final ContributorType type)
    {
        this(new PersonName(name), type);
    }
}
