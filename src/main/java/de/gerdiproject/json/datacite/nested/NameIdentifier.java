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
package de.gerdiproject.json.datacite.nested;

/**
 * A unique identifier for an individual or legal entity, according to various schemes.
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
public class NameIdentifier
{
    /**
     * Uniquely identifies an individual or legal entity, according to various schemes.
     * In XML, this is the value between the nameIdentifier-tags.
     * <br>e.g. orcid id number
     */
    private String value;

    /**
     * The name of the name identifier scheme.
     * <br>e.g. ORCID
     */
    private String nameIdentifierScheme;

    /**
     * The URI of the name identifier scheme.
     * <br>e.g. http://orcid.org/
     */
    private String schemeURI;


    /**
     * Simple Constructor that requires all mandatory fields.
     * @param value uniquely identifies an individual or legal entity, according to various schemes
     * @param nameIdentifierScheme the name of the name identifier scheme
     */
    public NameIdentifier(String value, String nameIdentifierScheme)
    {
        this.value = value;
        this.nameIdentifierScheme = nameIdentifierScheme;
    }


    /**
     * Returns a unique identifier of an individual or legal entity, according to various schemes.
     * In XML, this is the value between the nameIdentifier-tags.
     *
     * @return a unique identifier of an individual or legal entity
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes a unique identifier of an individual or legal entity, according to various schemes.
     * In XML, this is the value between the nameIdentifier-tags.
     * <br>e.g. orcid id number
     *
     * @param nameIdentifier a unique identifier of an individual or legal entity
     */
    public void setValue(String nameIdentifier)
    {
        this.value = nameIdentifier;
    }


    /**
     * Returns the name of the name identifier scheme.
     *
     * @return the name of the name identifier scheme
     */
    public String getNameIdentifierScheme()
    {
        return nameIdentifierScheme;
    }


    /**
     * Changes the name of the name identifier scheme.
     * <br>e.g. ORCID
     *
     * @param nameIdentifierSchema the name of the name identifier scheme
     */
    public void setNameIdentifierScheme(String nameIdentifierSchema)
    {
        this.nameIdentifierScheme = nameIdentifierSchema;
    }


    /**
     * Returns the URI of the name identifier scheme.
     *
     * @return the URI of the name identifier scheme
     */
    public String getSchemeURI()
    {
        return schemeURI;
    }


    /**
     * Changes the URI of the name identifier scheme.
     * <br>e.g. http://orcid.org/
     *
     * @param schemeURI the URI of the name identifier scheme
     */
    public void setSchemeURI(String schemeURI)
    {
        this.schemeURI = schemeURI;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nameIdentifierScheme == null) ? 0 : nameIdentifierScheme.hashCode());
        result = prime * result + ((schemeURI == null) ? 0 : schemeURI.hashCode());
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

        if (!(obj instanceof NameIdentifier))
            return false;

        NameIdentifier other = (NameIdentifier) obj;

        if (nameIdentifierScheme == null) {
            if (other.nameIdentifierScheme != null)
                return false;
        } else if (!nameIdentifierScheme.equals(other.nameIdentifierScheme))
            return false;

        if (schemeURI == null) {
            if (other.schemeURI != null)
                return false;
        } else if (!schemeURI.equals(other.schemeURI))
            return false;

        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;

        return true;
    }
}
