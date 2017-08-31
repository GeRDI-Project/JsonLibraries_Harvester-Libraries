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
 * A name or title by which a resource is known.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Title implements ICleanable
{
    /**
     * A free text title or name.
     * e.g. Crops, Catch Value in the Atlantic Ocean
     */
    private String value;

    /**
     * The type of Title.
     */
    private TitleType type;

    /**
     * IETF language tag describing the language of the title text.
     * e.g. de, en-US
     */
    private String lang;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param title the title text
     */
    public Title(String title)
    {
        this.value = title;
    }


    /**
     * Returns a free text title or name.
     *
     * @return a free text title or name
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the free text title or name.
     * e.g. Crops, Catch Value in the Atlantic Ocean
     *
     * @param value a free text title or name
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the type of title, or null if this is the main title.
     *
     * @return the type of title
     */
    public TitleType getType()
    {
        return type;
    }


    /**
     * Changes the type of title.
     * May be 'null' to represent the main title of the document.
     *
     * @param type the type of title
     */
    public void setType(TitleType type)
    {
        this.type = type;
    }


    /**
     * Returns the language of the title text.
     *
     * @return the language of the title text
     */
    public String getLang()
    {
        return lang;
    }


    /**
     * Changes the language of the title text.
     * e.g. de, en-US
     *
     * @param lang the language of the title text
     */
    public void setLang(String lang)
    {
        this.lang = lang;
    }


    @Override
    public void clean()
    {
        value = StringCleaner.clean(value);
    }


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
}
