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
 * Subject, keywords (tags), classification codes, or key phrases describing the resource.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Subject
{
    /**
     * Are only findable by exact match! Tags should therefore always be concise and probably a single term!
     */
    public String subject;

    /**
     * IETF language tag
     */
    public String lang;

    /**
     * The URI of the subject identifier scheme.
     */
    public String schemeURI;

    /**
     * The name of the subject scheme or classification code or authority if one is used.
     */
    public String subjectScheme;

    /**
     * The URI of the subject term.
     */
    public String valueURI;

    /**
     * Simple constructor that requires all mandatory fields.
     * @param subject the search tag
     */
    public Subject(String subject)
    {
        this.subject = subject;
    }
}
