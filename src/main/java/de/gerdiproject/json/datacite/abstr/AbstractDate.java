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



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gerdiproject.harvest.utils.StringCleaner;
import de.gerdiproject.json.datacite.constants.DataCiteDateConstants;
import de.gerdiproject.json.datacite.enums.DateType;

/**
 * This JsonObject describes a date that has been relevant to the work.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public abstract class AbstractDate
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractDate.class);

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
     * Tries to parse a String to a date. Ideally, the string is ISO 8601 compliant.
     * <br>e.g. 1994-11-05T13:15:30Z
     * <br><br>(see https://www.w3.org/TR/NOTE-datetime)
     *
     * @param stringValue the String that is to be parsed
     *
     * @return an {@linkplain Instant} that represents the string value
     */
    protected static Instant stringToInstant(String stringValue)
    {
        // empty strings should not be parsed
        if (stringValue == null || stringValue.isEmpty())
            return null;

        // make string DatatypeConverter compliant by adding seconds to it and removing HTML tags
        String cleanString = StringCleaner.clean(
                                 stringValue.replaceAll(
                                     DataCiteDateConstants.ISO_8601_TIME_WITHOUT_SECONDS,
                                     DataCiteDateConstants.ISO_8601_REPLACE_TIME_WITH_SECONDS
                                 )
                             );

        // try to parse formats that start with a character (e.g. "Nov-2015")
        if (!Character.isDigit(cleanString.charAt(0))) {
            // remove all text before the first word before the first number (if it exists)
            // this converts "Monthly time series starting February 2010" to "February 2010"
            cleanString = cleanString.replaceAll(
                              DataCiteDateConstants.WORD_BEFORE_NUMBER_REGEX,
                              DataCiteDateConstants.FIRST_MATCH);

            for (SimpleDateFormat format : DataCiteDateConstants.DATE_FORMATS_STARTING_WITH_CHAR) {
                try {
                    return format.parse(cleanString).toInstant();

                } catch (ParseException e) {}  // NOPMD - nothing to do here, just keep parsing
            }

            // remove all text before the first number (if it exists)
            // this converts "Annual data for the period 1997 onwards" to "1997 onwards"
            cleanString = cleanString.replaceAll(
                              DataCiteDateConstants.FIRST_NUMBER_REGEX,
                              DataCiteDateConstants.FIRST_MATCH);
        }

        // only continue parsing if the string starts with a number
        if (Character.isDigit(cleanString.charAt(0))) {

            // try to parse ISO 8601 string
            try {
                Calendar parsedCalendar = DatatypeConverter.parseDateTime(cleanString);
                parsedCalendar.setTimeZone(TimeZone.getTimeZone(DataCiteDateConstants.STANDARD_TIMEZONE));
                return parsedCalendar.toInstant();

            } catch (IllegalArgumentException e) {} // NOPMD - nothing to do here, just keep parsing

            // try to parse less common formats
            for (SimpleDateFormat format : DataCiteDateConstants.DATE_FORMATS_STARTING_WITH_NUM) {
                try {
                    return format.parse(cleanString).toInstant();

                } catch (ParseException e) {} // NOPMD - nothing to do here, just keep parsing
            }
        }

        LOGGER.warn(String.format(DataCiteDateConstants.PARSE_ERROR, stringValue));
        return null;
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
