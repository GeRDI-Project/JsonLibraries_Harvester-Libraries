/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package de.gerdiproject.json.datacite;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.utils.StringCleaner;

/**
 * Additional information that does not fit in any of the other categories.
 * May be used for technical information.
 * NOTE:    The DataCite schema allows br-tags here, which are not supported
 *          and should be replaced with \n!
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Description implements ICleanable
{
    /**
     * Free descriptive text.
     */
    private String value;

    /**
     *  What the description entails.
     */
    private DescriptionType type;

    /**
     * IETF language tag.
     * e.g. de, en-us
     */
    private String lang;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param value free text description
     * @param type the type of the free text
     */
    public Description(String value, DescriptionType type)
    {
        this.value = value;
        this.type = type;
    }


    /**
     * Returns a free text description.
     *
     * @return a free text description
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes a free text description.
     *
     * @param value a free text description
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the type of the description.
     *
     * @return what the description entails
     */
    public DescriptionType getType()
    {
        return type;
    }


    /**
     * Changes the type of the description.
     *
     * @param type what the description entails
     */
    public void setType(DescriptionType type)
    {
        this.type = type;
    }


    /**
     * Returns the language in which the description is written.
     *
     * @return the language in which the description is written
     */
    public String getLang()
    {
        return lang;
    }


    /**
     * Changes the language in which the description is written.
     * e.g. de, en-us
     *
     * @param lang  the language in which the description is written
     */
    public void setLang(String lang)
    {
        this.lang = lang;
    }


    /**
     * Cleans the description text, removing HTML and unescaping special characters.
     */
    @Override
    public void clean()
    {
        value = StringCleaner.clean(value);
    }


    /**
     * This enumeration describes what a description entails.
     *
     * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
     * @author Robin Weiss
     */
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
}
