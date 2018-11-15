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

import java.time.Instant;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.enums.DateType;

/**
 * This JsonObject describes a date that has been relevant to the work.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Date extends AbstractDate implements ICleanable
{
    /**
     *  The date value.
     *  In XML, this is the value between the date-tags.
     */
    private Instant value;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param dateString a ISO-8601-compliant date string
     * @param type the event that is marked by this date
     */
    public Date(String dateString, DateType type)
    {
        super(type);
        setValue(dateString);
    }


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param epochMilli milliseconds that passed since 01/01/1970 00:00:00
     * @param type the event that is marked by this date
     */
    public Date(long epochMilli, DateType type)
    {
        super(type);
        setDate(epochMilli);
    }


    /**
     * Returns the date as ISO-8601-compliant String.
     * <br>e.g. 1994-11-05T13:15:30Z
     * <br><br>(see https://www.w3.org/TR/NOTE-datetime)
     *
     * @return the date as ISO-8601-compliant String, or null if the date is invalid
     */
    @Override
    public String getValue()
    {
        return value != null ? value.toString() : null;
    }


    /**
     * Tries to set the date by parsing an ISO 8601 compliant String.
     * <br>e.g. 1994-11-05T13:15:30Z
     * <br><br>(see https://www.w3.org/TR/NOTE-datetime)
     *
     * @param stringValue the String that is to be parsed
     */
    @Override
    public void setValue(String stringValue)
    {
        this.value = stringToInstant(stringValue);
    }


    /**
     * Changes the date value using the amount of milliseconds that passed
     * from 01/01/1970 00:00:00 until this date.
     *
     * @param epochMilli the amount of milliseconds between 01/01/1970 00:00:00 and this date
     */
    public void setDate(long epochMilli)
    {
        this.value = unixTimestampToInstant(epochMilli);
    }


    /**
     * Changes the date value using an {@linkplain Instant} that marks a date.
     *
     * @param date an {@linkplain Instant} that represents the new date
     */
    public void setDate(Instant date)
    {
        this.value = date;
    }


    @Override
    public boolean clean()
    {
        // nothing to clean, but it invalidates if it is null
        return getValue() != null;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj))
            return false;

        if (!(obj instanceof Date))
            return false;

        Date other = (Date) obj;

        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;

        return true;
    }
}
