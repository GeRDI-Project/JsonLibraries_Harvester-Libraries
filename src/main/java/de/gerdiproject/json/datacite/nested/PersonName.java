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
package de.gerdiproject.json.datacite.nested;

import de.gerdiproject.json.datacite.enums.NameType;

/**
 * The full name of a person.
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
public class PersonName
{
    /**
     * The free text full name of the person or entity.
     * In XML, this is the text between the name-tags.
     */
    private String value;

    /**
     * The type of name.
     * <br>default: Personal
     */
    private NameType nameType;


    /**
     * Simple constructor that does not initialize the nameType.
     * @param value the free text full name of the person or entity
     */
    public PersonName(String value)
    {
        this.value = value;
    }


    /**
     * Simple constructor.
     * @param value the free text full name of the person or entity
     * @param nameType the type of name
     */
    public PersonName(String value, NameType nameType)
    {
        this.value = value;
        this.nameType = nameType;
    }


    /**
     * Returns the free text full name of the person or entity.
     * In XML, this is the text between the name-tags.
     *
     * @return the free text full name of the person or entity
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the free text full name of the person or entity.
     * In XML, this is the text between the name-tags.
     *
     * @param value the free text full name of the person or entity
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the type of name.
     *
     * @return the type of name
     */
    public NameType getNameType()
    {
        return nameType;
    }


    /**
     * Changes the type of name.
     *
     * @param nameType the type of name
     */
    public void setNameType(NameType nameType)
    {
        this.nameType = nameType;
    }
}
