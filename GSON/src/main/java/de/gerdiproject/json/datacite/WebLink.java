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
 * A link to the data provider's website.
 *
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class WebLink
{
    /**
     * A descriptive name of the URL.
     */
    private String name;

    /**
     * The URL of the link.
     */
    private String url;

    /**
     * The link category.
     */
    private WebLinkType type;


    /**
     * Simple constructor that requires all mandatory fields.
     * @param url the URL of the link
     */
    public WebLink(String url)
    {
        this.url = url;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public String getUrl()
    {
        return url;
    }


    public void setUrl(String url)
    {
        this.url = url;
    }


    public WebLinkType getType()
    {
        return type;
    }


    public void setType(WebLinkType type)
    {
        this.type = type;
    }


    /**
     * The kind of data or webpage which is linked.
     * @author Robin Weiss
     *
     */
    public enum WebLinkType {
        ViewURL,
        Related
        // TODO: add more types
    }

}
