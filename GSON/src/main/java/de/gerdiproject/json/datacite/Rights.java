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
 * Any rights information for this resource.
 * Provide a rights management statement for the resource or reference a service
 * providing such information.
 * Include embargo information if applicable. Use the complete title of a license and
 * include version information if applicable.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Rights implements ICleanable
{
    /**
     * Free text that describes the rights.
     * e.g. Creative Commons, Attribution 3.0 Germany
     */
    private String value;

    /**
     * The URI of the license.
     * e.g. http://creativecommons.org/licenses/by/3.0/de/deed.en
     */
    private String URI;


    /**
     * Returns the free text that describes the rights.
     *
     * @return free text that describes the rights
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the free text that describes the rights.
     * e.g. Creative Commons, Attribution 3.0 Germany
     *
     * @param value free text that describes the rights
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the URI of the license.
     *
     * @return the URI of the license
     */
    public String getURI()
    {
        return URI;
    }


    /**
     * Changes the URI of the license.
     * e.g. http://creativecommons.org/licenses/by/3.0/de/deed.en
     *
     * @param uri the URI of the license
     */
    public void setURI(String uri)
    {
        URI = uri;
    }


    @Override
    public void clean()
    {
        value = StringCleaner.clean(value);
    }
}
