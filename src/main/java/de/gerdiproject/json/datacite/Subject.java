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
 * Subject, keywords (tags), classification codes, or key phrases describing the resource.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Subject implements ICleanable
{
    /**
     * A term that describes the resource.
     * <br>e.g. Fishery, Dates
     */
    private String value;

    /**
     * A IETF language tag of the subject text.
     * <br>e.g. de, en-US
     */
    private String lang;

    /**
     * The free text name of the subject scheme or classification code or authority if one is used.
     */
    private String subjectScheme;

    /**
     * The URI of the subject identifier scheme.
     * <br>e.g. http://id.loc.gov/authorities/subjects
     */
    private String schemeURI;

    /**
     * The URI of the subject term.
     * <br>e.g. http://id.loc.gov/authorities/subjects/sh85026196
     */
    private String valueURI;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param value the search tag
     */
    public Subject(String value)
    {
        this.value = value;
    }


    /**
     * Returns a term that describes the resource.
     *
     * @return a term that describes the resource
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes a term that describes the resource.
     * <br>e.g. Fishery, Dates
     *
     * @param value a term that describes the resource
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the IETF language tag of the subject text.
     *
     * @return the IETF language tag of the subject text
     */
    public String getLang()
    {
        return lang;
    }


    /**
     * Changes the IETF language tag of the subject text.
     * <br>e.g. de, en-US
     *
     * @param lang an IETF language tag of the subject text
     */
    public void setLang(String lang)
    {
        this.lang = lang;
    }


    /**
     * Returns the URI of the subject identifier scheme.
     *
     * @return the URI of the subject identifier scheme
     */
    public String getSchemeURI()
    {
        return schemeURI;
    }


    /**
     * Changes the URI of the subject identifier scheme.
     * <br>e.g. http://id.loc.gov/authorities/subjects
     *
     * @param schemeURI the URI of the subject identifier scheme
     */
    public void setSchemeURI(String schemeURI)
    {
        this.schemeURI = schemeURI;
    }


    /**
     * Returns a free text name of the subject scheme or classification
     * code or authority if one is used
     *
     * @return a free text name of the subject scheme
     */
    public String getSubjectScheme()
    {
        return subjectScheme;
    }


    /**
     * Changes the free text name of the subject scheme or classification
     * code or authority if one is used.
     *
     * @param subjectScheme a free text name of the subject scheme
     */
    public void setSubjectScheme(String subjectScheme)
    {
        this.subjectScheme = subjectScheme;
    }


    /**
     * Returns the URI of the subject term.
     *
     * @return the URI of the subject term
     */
    public String getValueURI()
    {
        return valueURI;
    }


    /**
     * Changes the URI of the subject term.
     * <br>e.g. http://id.loc.gov/authorities/subjects/sh85026196
     *
     * @param valueURI the URI of the subject term
     */
    public void setValueURI(String valueURI)
    {
        this.valueURI = valueURI;
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
        result = prime * result + ((schemeURI == null) ? 0 : schemeURI.hashCode());
        result = prime * result + ((subjectScheme == null) ? 0 : subjectScheme.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((valueURI == null) ? 0 : valueURI.hashCode());
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

        if (!(obj instanceof Subject))
            return false;

        Subject other = (Subject) obj;

        if (lang == null) {
            if (other.lang != null)
                return false;
        } else if (!lang.equals(other.lang))
            return false;

        if (schemeURI == null) {
            if (other.schemeURI != null)
                return false;
        } else if (!schemeURI.equals(other.schemeURI))
            return false;

        if (subjectScheme == null) {
            if (other.subjectScheme != null)
                return false;
        } else if (!subjectScheme.equals(other.subjectScheme))
            return false;

        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;

        if (valueURI == null) {
            if (other.valueURI != null)
                return false;
        } else if (!valueURI.equals(other.valueURI))
            return false;

        return true;
    }
}
