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
 * The code assigned by the funder to a sponsored award (grant).
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
public class AwardNumber
{
    /**
     * The value of the AwardNumber.
     * In XML, this is the value between the awardNumber-tags.
     */
    private String value;

    /**
     * The URI leading to a page for more information about the award.
     */
    private String awardURI;


    /**
     * Constructor that requires all mandatory fields.
     *
     * @param value the value of the AwardNumber
     */
    public AwardNumber(String value)
    {
        this.value = value;
    }


    /**
     * Constructor that requires all fields.
     *
     * @param value the value of the AwardNumber
     * @param awardURI the URI leading to a page for more information about the award
     */
    public AwardNumber(String value, String awardURI)
    {
        this.value = value;
        this.awardURI = awardURI;
    }


    /**
     * Returns the value of the AwardNumber. In XML, this is the value between the awardNumber tags.
     *
     * @return the value of the AwardNumber
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the value of the AwardNumber. In XML, this is the value between the awardNumber tags.
     *
     * @param value the value of the AwardNumber
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the URI leading to a page provided by the funder for more
     * information about the award (grant).
     *
     * @return the URI leading to a page for more information about the award
     */
    public String getAwardURI()
    {
        return awardURI;
    }


    /**
     * Changes the URI leading to a page provided by the funder for more
     * information about the award (grant).
     * <br>e.g. https://www.moore.org/grants/list/GBMF3859.01
     *
     * @param awardURI the URI leading to a page for more information about the award
     */
    public void setAwardURI(String awardURI)
    {
        this.awardURI = awardURI;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((awardURI == null) ? 0 : awardURI.hashCode());
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

        if (!(obj instanceof AwardNumber))
            return false;

        AwardNumber other = (AwardNumber) obj;

        if (awardURI == null) {
            if (other.awardURI != null)
                return false;
        } else if (!awardURI.equals(other.awardURI))
            return false;

        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;

        return true;
    }
}
