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
 * An identifier other than the primary Identifier applied to the resource being registered.
 * This may be any alphanumeric string which is unique within its domain of issue.
 * May be used for local identifiers. AlternateIdentifier should be used for another identifier of the same
 * instance (same location, same file).
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class AlternateIdentifier
{
    /**
     * alternateIdentifier in DataCite schema
     */
    private String value;

    /**
     * free text, e.g. URL
     */
    private String type;

    /**
     * Simple constructor that requires all mandatory fields.
     * @param value the identifier value
     * @param type free text describing the identifier
     */
    public AlternateIdentifier(String value, String type)
    {
        this.value = value;
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
