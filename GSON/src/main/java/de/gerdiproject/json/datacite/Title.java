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
 * This JSON object describes a title of the data.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Title implements ICleanable
{
    /**
     * a free text title
     */
    private String title;

    /**
     * describes where the title appears
     */
    private TitleType type;

    /**
     * IETF language tag
     */
    private String lang;

    /**
     * Simple constructor that requires all mandatory fields.
     * @param title the title text
     */
    public Title(String title)
    {
        this.title = title;
    }


    public String getTitle()
    {
        return title;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }


    public TitleType getType()
    {
        return type;
    }


    public void setType(TitleType type)
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

    @Override
    public void clean()
    {
        title = StringCleaner.clean(title);
    }


    /**
     * This enumeration describes the type of a {@link Title}.
     * @author Robin Weiss
     *
     */
    public enum TitleType {
        AlternativeTitle,
        Subtitle,
        TranslatedTitle,
        Other
    }
}
