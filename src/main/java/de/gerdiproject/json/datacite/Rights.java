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
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Rights implements ICleanable
{
    /**
     * Free text that describes the rights.
     * In XML, this is the value between the rights-tags.
     * <br>e.g. Creative Commons, Attribution 3.0 Germany
     */
    private String value;

    /**
     * The URI of the license.
     * <br>e.g. http://creativecommons.org/licenses/by/3.0/de/deed.en
     */
    private String rightsURI;

    /**
     * An optional IETF language tag of the subject text.
     * <br>e.g. de, en-US
     */
    private String lang;



    /**
     * Constructor that requires all mandatory fields.
     *
     * @param value free text that describes the rights
     */
    public Rights(String value)
    {
        this.value = value;
    }


    /**
     * Returns the free text that describes the rights.
     * In XML, this is the value between the rights-tags.
     *
     * @return free text that describes the rights
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the free text that describes the rights.
     * In XML, this is the value between the rights-tags.
     * <br>e.g. Creative Commons, Attribution 3.0 Germany
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
        return rightsURI;
    }


    /**
     * Changes the URI of the license.
     * <br>e.g. http://creativecommons.org/licenses/by/3.0/de/deed.en
     *
     * @param uri the URI of the license
     */
    public void setURI(String uri)
    {
        rightsURI = uri;
    }


    /**
     * Returns the IETF language tag of the rights text.
     *
     * @return the IETF language tag of the rights text
     */
    public String getLang()
    {
        return lang;
    }


    /**
     * Changes the IETF language tag of the rights text.
     * <br>e.g. de, en-US
     *
     * @param lang an IETF language tag of the rights text
     */
    public void setLang(String lang)
    {
        this.lang = lang;
    }


    @Override
    public void clean()
    {
        value = StringCleaner.clean(value);
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lang == null) ? 0 : lang.hashCode());
        result = prime * result + ((rightsURI == null) ? 0 : rightsURI.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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

        if (!(obj instanceof Rights))
            return false;

        Rights other = (Rights) obj;

        if (lang == null) {
            if (other.lang != null)
                return false;
        } else if (!lang.equals(other.lang))
            return false;

        if (rightsURI == null) {
            if (other.rightsURI != null)
                return false;
        } else if (!rightsURI.equals(other.rightsURI))
            return false;

        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;

        return true;
    }
}
