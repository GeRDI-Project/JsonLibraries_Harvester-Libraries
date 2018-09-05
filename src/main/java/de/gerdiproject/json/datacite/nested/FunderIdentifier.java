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

import de.gerdiproject.json.datacite.enums.FunderIdentifierType;

/**
 * Uniquely identifies a funding entity, according to various types.
 * <br>This is not indexed!
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
public class FunderIdentifier
{
    /**
     * The value of the FunderIdentifier.
     * In XML, this is the value between the funderIdentifier-tags.
     */
    private String value;

    /**
     * The type of the funder identifier.
     */
    private FunderIdentifierType funderIdentifierType;


    /**
     * Constructor that requires all mandatory fields.
     *
     * @param value the value of the FunderIdentifier
     */
    public FunderIdentifier(String value)
    {
        this.value = value;
    }


    /**
     * Constructor that requires all fields.
     *
     * @param value the value of the FunderIdentifier
     * @param type the type of the funder identifier
     */
    public FunderIdentifier(String value, FunderIdentifierType type)
    {
        this.value = value;
        this.funderIdentifierType = type;
    }


    /**
     * Returns the value of the FunderIdentifier. In XML, this is the value between the funderIdentifier tags.
     *
     * @return the value of the FunderIdentifier
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the value of the FunderIdentifier. In XML, this is the value between the funderIdentifier tags.
     *
     * @param value the value of the FunderIdentifier
     */
    public void setValue(String value)
    {
        this.value = value;
    }


    /**
     * Returns the type of the funder identifier.
     *
     * @return the type of the funder identifier
     */
    public FunderIdentifierType getFunderIdentifierType()
    {
        return funderIdentifierType;
    }


    /**
     * Changes the type of the funder identifier.
     *
     * @param funderIdentifierType the type of the funder identifier
     */
    public void setFunderIdentifierType(FunderIdentifierType funderIdentifierType)
    {
        this.funderIdentifierType = funderIdentifierType;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((funderIdentifierType == null) ? 0 : funderIdentifierType.hashCode());
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

        if (!(obj instanceof FunderIdentifier))
            return false;

        FunderIdentifier other = (FunderIdentifier) obj;

        if (funderIdentifierType != other.funderIdentifierType)
            return false;

        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;

        return true;
    }
}
