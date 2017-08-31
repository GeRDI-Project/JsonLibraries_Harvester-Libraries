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
 * Endpoints and URLs used to retrieve the source meta data, e.g. link to JSON/XML.
 *
 * This object is NOT part of the original DataCite schema.
 * @author Mathis Neumann, Robin Weiss
 */
public class Source
{
    /**
     * The URL that leads to the page, previously viewURL.
     * <br>e.g. http://www.fao.org/faostat/en/#country/2
     */
    private String URI;

    /**
     * A unique readable name of the repository.
     * <br>e.g. FAOSTAT
     */
    private String provider;

    /**
     * URL to the main page of the repository that provided this metadata.
     * <br>e.g. fao.org/faostat
     */
    private String providerURI;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param uri leads to the metadata page
     * @param provider the metadata provider name
     */
    public Source(String uri, String provider)
    {
        this.URI = uri;
        this.provider = provider;
    }


    /**
     * Returns the URL that leads to the source page.
     *
     * @return the URL that leads to the source page
     */
    public String getURI()
    {
        return URI;
    }


    /**
     * Changes the URL that leads to the source page, previously viewURL.
     * <br>e.g. http://www.fao.org/faostat/en/#country/2
     *
     * @param uri the URL that leads to the source page
     */
    public void setURI(String uri)
    {
        URI = uri;
    }


    /**
     * Returns the unique readable name of the repository.
     * @return the unique readable name of the repository
     */
    public String getProvider()
    {
        return provider;
    }


    /**
     * Changes the unique readable name of the repository.
     * <br>e.g. FAOSTAT
     *
     * @param provider a unique readable name of the repository
     */
    public void setProvider(String provider)
    {
        this.provider = provider;
    }


    /**
     * Returns the URL to the main page of the repository that provides the resource.
     * @return the URL to the main page of the repository
     */
    public String getProviderURI()
    {
        return providerURI;
    }


    /**
     * Changes the URL to the main page of the repository that provides the resource.
     * <br>e.g. fao.org/faostat
     *
     * @param providerURI the URL to the main page of the repository
     */
    public void setProviderURI(String providerURI)
    {
        this.providerURI = providerURI;
    }
}
