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
package de.gerdiproject.json.datacite.extension;

/**
 * A link to the data provider's website.
 *
 * This object is NOT part of the original DataCite schema.
 * @author Mathis Neumann, Robin Weiss
 */
public class WebLink
{
    /**
     * A descriptive name of the web link destination.
     */
    private String name;

    /**
     * The URL of the web link.
     */
    private String URL;

    /**
     * The link category.
     */
    private WebLinkType type;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param url the URL of the link
     */
    public WebLink(String url)
    {
        this.URL = url;
    }


    /**
     * Returns a descriptive name of the web link destination.
     * @return a descriptive name of the web link destination
     */
    public String getName()
    {
        return name;
    }


    /**
     * Changes the descriptive name of the web link destination.
     * @param name a descriptive name of the web link destination
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Returns the URL of the web link.
     * @return the URL of the web link
     */
    public String getUrl()
    {
        return URL;
    }


    /**
     * Changes the URL of the web link.
     * @param url the URL of the web link
     */
    public void setUrl(String url)
    {
        this.URL = url;
    }


    /**
     * Returns the type of the web link.
     * @return the type of the web link
     */
    public WebLinkType getType()
    {
        return type;
    }


    /**
     * Changes the type of the web link.
     * @param type the type of the web link
     */
    public void setType(WebLinkType type)
    {
        this.type = type;
    }


    /**
     * The kind of data or webpage which is linked.
     * This enumeration is incomplete, as more types will be added in accordance with the requirements.
     *
     * @author Robin Weiss
     */
    public enum WebLinkType {
        /**
         * A URL that points to the website at which the resource data can be viewed.
         */
        ViewURL,

        /**
         * A URL that points to a (small) logo of the organisation that provides the resource data.
         */
        ProviderLogoURL,

        /**
         * A URL that points to a small image or thumbnail that represents the resource data itself.
         */
        ThumbnailURL,

        /**
         * A URL that points to a website that is related to the resource.
         */
        Related
    }
}
