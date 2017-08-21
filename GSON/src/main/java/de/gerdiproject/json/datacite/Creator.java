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
 * This JSON object describes data creators.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Creator
{
    /**
     * format: FamilyName, GivenName (e.g. Neumann, Mathis) "creatorName" in DataCite schema
     */
    public String name;

    /**
     * first name
     */
    public String givenName;

    /**
     * last name
     */
    public String familyName;

    /**
     * e.g. orcid id number
     */
    public String nameIdentifier;

    /**
     * name of the identifier scheme, e.g. ORCID
     */
    public String nameIdentifierSchema;

    /**
     * slightly differs from DataCite which is an XML attribute, e.g. http://orcid.org/
     */
    public String nameIdentifierSchemaURI;

    /**
     * organizaton the person is in
     */
    public String affiliation;

    /**
     * Simple constructor that requires all mandatory fields.
     * @param name the name of the creator
     */
    public Creator(String name)
    {
        this.name = name;
    }
}
