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

import de.gerdiproject.json.datacite.enums.IdentifierType;

/**
 * The primary Identifier of the resource being registered.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Identifier
{
    /**
     * A unique string that identifies the resource.
     * In XML, this is the value between the identifier-tags.
     * <br>e.g. 10.1234/foo
     */
    private String value;

    /**
     * The type of the identifier.
     */
    private IdentifierType identifierType;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param value the identifier value
     * @param type the type of the identifier
     */
    public Identifier(String value, IdentifierType type)
    {
        this.value = value;
        this.identifierType = type;
    }


    /**
     * Constructs a DOI identifier.
     *
     * @param value a DOI identifier string of the format "10.1234/foo"
     */
    public Identifier(String value)
    {
        this.value = value;
        this.identifierType = IdentifierType.DOI;
    }


    /**
     * Returns a unique identifier.
     * In XML, this is the value between the identifier-tags.
     *
     * @return a unique identifier
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the unique identifier.
     * In XML, this is the value between the identifier-tags.
     * <br>e.g. 10.1234/foo
     *
     * @param value a unique identifier
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the type of the identifier.
     *
     * @return the type of the identifier
     */
    public IdentifierType getType()
    {
        return identifierType;
    }


    /**
     * Changes the type of the identifier.
     *
     * @param type a type of the identifier
     */
    public void setType(IdentifierType type)
    {
        this.identifierType = type;
    }
}
