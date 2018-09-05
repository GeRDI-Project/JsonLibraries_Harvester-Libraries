/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gerdiproject.json.datacite.extension;

import de.gerdiproject.json.datacite.extension.enums.WebLinkType;

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
    private String webLinkName;

    /**
     * The URL of the web link.
     */
    private String webLinkURI;

    /**
     * The link category.
     */
    private WebLinkType webLinkType;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param url the URL of the link
     */
    public WebLink(String url)
    {
        this.webLinkURI = url;
    }


    /**
     * Returns a descriptive name of the web link destination.
     * @return a descriptive name of the web link destination
     */
    public String getName()
    {
        return webLinkName;
    }


    /**
     * Changes the descriptive name of the web link destination.
     * @param name a descriptive name of the web link destination
     */
    public void setName(String name)
    {
        this.webLinkName = name;
    }


    /**
     * Returns the URL of the web link.
     * @return the URL of the web link
     */
    public String getUrl()
    {
        return webLinkURI;
    }


    /**
     * Changes the URL of the web link.
     * @param url the URL of the web link
     */
    public void setUrl(String url)
    {
        this.webLinkURI = url;
    }


    /**
     * Returns the type of the web link.
     * @return the type of the web link
     */
    public WebLinkType getType()
    {
        return webLinkType;
    }


    /**
     * Changes the type of the web link.
     * @param type the type of the web link
     */
    public void setType(WebLinkType type)
    {
        this.webLinkType = type;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((webLinkName == null) ? 0 : webLinkName.hashCode());
        result = prime * result + ((webLinkType == null) ? 0 : webLinkType.hashCode());
        result = prime * result + ((webLinkURI == null) ? 0 : webLinkURI.hashCode());
        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof WebLink))
            return false;
        WebLink other = (WebLink) obj;
        if (webLinkName == null) {
            if (other.webLinkName != null)
                return false;
        } else if (!webLinkName.equals(other.webLinkName))
            return false;
        if (webLinkType != other.webLinkType)
            return false;
        if (webLinkURI == null) {
            if (other.webLinkURI != null)
                return false;
        } else if (!webLinkURI.equals(other.webLinkURI))
            return false;
        return true;
    }
}
