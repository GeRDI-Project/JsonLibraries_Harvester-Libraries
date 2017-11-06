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
package de.gerdiproject.json.datacite.abstr;



import java.time.Instant;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gerdiproject.json.datacite.enums.DateType;

/**
 * This JsonObject describes a date that has been relevant to the work.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public abstract class AbstractDate
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDate.class);
    protected static final String PARSE_ERROR = "Could not parse date string '%s'!";

    /**
     * The event that is marked by this date.
     */
    private DateType dateType;

    /**
     * Specific free text information about the date, if appropriate.
     */
    private String dateInformation;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param type the event that is marked by this date
     */
    public AbstractDate(DateType type)
    {
        this.dateType = type;
    }


    /**
     * Returns the value of the date. In XML, this is the value between the date-tags.
     *
     * @return the value of the date
     */
    abstract public String getValue();


    /**
     * Changes the date value. In XML, this is the value between the date-tags.
     *
     * @param value the new value
     */
    abstract public void setValue(String value);


    /**
     * Returns the event that is marked by this date.
     *
     * @return the event that is marked by this date
     */
    public DateType getType()
    {
        return dateType;
    }


    /**
     * Changes the event that is marked by this date.
     *
     * @param type the event that is marked by this date
     */
    public void setType(DateType type)
    {
        this.dateType = type;
    }


    /**
     * Returns specific free text information about the date.
     *
     * @return specific free text information about the date
     */
    public String getDateInformation()
    {
        return dateInformation;
    }


    /**
     * Changes the specific free text information about the date.
     *
     * @param dateInformation specific free text information about the date
     */
    public void setDateInformation(String dateInformation)
    {
        this.dateInformation = dateInformation;
    }

    /**
     * Parses an ISO 8601 compliant String.
     * <br>e.g. 1994-11-05T13:15:30Z
     * <br><br>(see https://www.w3.org/TR/NOTE-datetime)
     *
     * @param stringValue the String that is to be parsed
     *
     * @return an {@linkplain Instant} that represents the string value
     */
    protected static Instant stringToInstant(String stringValue)
    {
        Instant parsedInstant = null;

        if (stringValue != null && !stringValue.isEmpty()) {
            try {
                parsedInstant = Instant.parse(stringValue);

            } catch (DateTimeParseException e) {
                LOGGER.error(String.format(PARSE_ERROR, stringValue));
            }
        }

        return parsedInstant;
    }


    /**
     * Creates an instant using the amount of milliseconds that passed
     * from 01/01/1970 00:00:00 until this date.
     *
     * @param epochMilli the amount of milliseconds between 01/01/1970 00:00:00 and this date
     *
     * @return an {@linkplain Instant} that represents the timestamp
     */
    protected static Instant unixTimestampToInstant(long epochMilli)
    {
        return Instant.ofEpochMilli(epochMilli);
    }
}
