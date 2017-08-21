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


/**
 * Highly recommended!
 * This JSON object represents a descriptive free text.
 * NOTE: The DataCite schema allows br-tags here, which are not supported and should be replaced with \n!
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Description
{
    /**
     * The actual text. Will be stripped of HTML
     */
    private String description;

    /**
     *  What the description entails.
     */
    private DescriptionType type;

    /**
     * IETF language tag
     */
    private String lang;

    /**
     * Simple constructor that requires all mandatory fields.
     * @param description free text description
     * @param type the type of the free text
     */
    public Description(String description, DescriptionType type)
    {
        this.description = description;
        this.type = type;
    }



    public String getDescription()
    {
        return description;
    }



    public void setDescription(String description)
    {
        this.description = description;
    }



    public DescriptionType getType()
    {
        return type;
    }



    public void setType(DescriptionType type)
    {
        this.type = type;
    }



    public String getLang()
    {
        return lang;
    }



    public void setLang(String lang)
    {
        this.lang = lang;
    }



    /**
     * This enumeration describes what a description entails.
     * @author Robin Weiss
     *
     */
    public enum DescriptionType {
        Abstract,
        Methods,
        SeriesInformation,
        TableOfContents,
        TechnicalInfo,
        Other
    }


}
