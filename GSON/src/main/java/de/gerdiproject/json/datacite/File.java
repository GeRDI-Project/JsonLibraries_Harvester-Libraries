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
 * This JSON object represents a downloadable file.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class File
{
    /**
     * The URL that is used to access this file.
     */
    public String url;

    /**
     * Human readable name for the file.
     */
    public String label;

    /**
     * Locally (within this document) unique identifier for the file, should be concise, e.g. a hash value.
     */
    public String identifier;

    /**
     * File format, extension or mimetype, e.g. csv or application/json.
     */
    public String type;


    /**
     * Simple constructor that requires all mandatory fields.
     * @param url the file URL
     * @param label the file display name
     * @param identifier the unique file identifier
     */
    public File(String url, String label, String identifier)
    {
        this.url = url;
        this.label = label;
        this.identifier = identifier;
    }

}
