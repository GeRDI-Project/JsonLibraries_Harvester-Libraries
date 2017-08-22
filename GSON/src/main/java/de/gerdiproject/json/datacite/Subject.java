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
 * Subject, keywords (tags), classification codes, or key phrases describing the resource.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Subject implements ICleanable
{
    /**
     * Are only findable by exact match! Tags should therefore always be concise and probably a single term!
     */
    private String subject;

    /**
     * IETF language tag
     */
    private String lang;

    /**
     * The URI of the subject identifier scheme.
     */
    private String schemeURI;

    /**
     * The name of the subject scheme or classification code or authority if one is used.
     */
    private String subjectScheme;

    /**
     * The URI of the subject term.
     */
    private String valueURI;

    /**
     * Simple constructor that requires all mandatory fields.
     * @param subject the search tag
     */
    public Subject(String subject)
    {
        this.subject = subject;
    }


    public String getSubject()
    {
        return subject;
    }


    public void setSubject(String subject)
    {
        this.subject = subject;
    }


    public String getLang()
    {
        return lang;
    }


    public void setLang(String lang)
    {
        this.lang = lang;
    }


    public String getSchemeURI()
    {
        return schemeURI;
    }


    public void setSchemeURI(String schemeURI)
    {
        this.schemeURI = schemeURI;
    }


    public String getSubjectScheme()
    {
        return subjectScheme;
    }


    public void setSubjectScheme(String subjectScheme)
    {
        this.subjectScheme = subjectScheme;
    }


    public String getValueURI()
    {
        return valueURI;
    }


    public void setValueURI(String valueURI)
    {
        this.valueURI = valueURI;
    }


    @Override
    public void clean()
    {
        subject = StringCleaner.clean(subject);
    }
}
