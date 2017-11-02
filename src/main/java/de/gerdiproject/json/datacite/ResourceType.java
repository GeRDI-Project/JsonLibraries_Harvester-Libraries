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

import de.gerdiproject.json.datacite.enums.ResourceTypeGeneral;

/**
 * This object represents a description of the resource.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class ResourceType
{
    /**
     * A free text description of the resource.
     * In XML, this is the value between the resourceType-tags.
     * <br>e.g. "Census Data"
     */
    private String value;

    /**
     * The general type of a resource.
     */
    private ResourceTypeGeneral resourceTypeGeneral;


    /**
     * Returns the free text description of the resource.
     * In XML, this is the value between the resourceType-tags.
     *
     * @return the free text description of the resource
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the free text description of the resource.
     * In XML, this is the value between the resourceType-tags.
     * <br>e.g. "Census Data"
     *
     * @param value a free text description of the resource
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the general type of the resource.
     *
     * @return the general type of the resource
     */
    public ResourceTypeGeneral getGeneralType()
    {
        return resourceTypeGeneral;
    }


    /**
     * Changes the general type of the resource.
     *
     * @param generalType the general type of the resource
     */
    public void setGeneralType(ResourceTypeGeneral generalType)
    {
        this.resourceTypeGeneral = generalType;
    }
}
