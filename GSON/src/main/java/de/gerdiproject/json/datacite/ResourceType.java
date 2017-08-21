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
 * This object represents the kind of information the document is about.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class ResourceType
{
    /**
     * free text, e.g. "Census Data"
     */
    public String value;

    /**
     * general category, e.g. "Dataset" for census data, see DataCite Schema PDF for more info on each type
     */
    public ResourceTypeCategory resourceTypeGeneral;

    /**
     * This enumeration describes the resource category.
     * @author Robin Weiss
     *
     */
    public enum ResourceTypeCategory {
        Audiovisual,
        Collection,
        Dataset,
        Event,
        Image,
        InteractiveResource,
        Model,
        PhysicalObject,
        Service,
        Software,
        Sound,
        Text,
        Workflow,
        Other
    }
}
