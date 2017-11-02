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
import de.gerdiproject.json.datacite.enums.DescriptionType;

/**
 * Additional information that does not fit in any of the other categories.
 * May be used for technical information.
 * NOTE:    The DataCite schema allows br-tags here, which are not supported
 *          by Elastic Search and should be replaced with \n!
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Description implements ICleanable
{
    /**
     * Free descriptive text. In XML, this is the value between the description-tags.
     */
    private String value;

    /**
     *  What the description entails.
     */
    private DescriptionType descriptionType;

    /**
     * IETF language tag.
     * <br>e.g. de, en-us
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
        this.descriptionType = type;
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
     * Changes a free text description. In XML, this is the value between the description-tags.
     *
     * @param value a free text description
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the type of the description. In XML, this is the value between the description-tags.
     *
     * @return what the description entails
     */
    public DescriptionType getType()
    {
        return descriptionType;
    }


    /**
     * Changes the type of the description.
     *
     * @param type what the description entails
     */
    public void setType(DescriptionType type)
    {
        this.descriptionType = type;
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
     * <br>e.g. de, en-us
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
}
