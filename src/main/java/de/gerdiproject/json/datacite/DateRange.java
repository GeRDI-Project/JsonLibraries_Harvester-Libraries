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

import java.time.Instant;

import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.constants.DataCiteDateConstants;
import de.gerdiproject.json.datacite.enums.DateType;

/**
 * This JsonObject describes a date that has been relevant to the work.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class DateRange extends AbstractDate
{
    /**
     *  The date at which the range starts.
     *  In XML, this is the value between the date-tags, before the slash.
     */
    private Instant since;

    /**
     *  The date at which the range ends.
     *  In XML, this is the value between the date-tags, after the slash.
     */
    private Instant until;



    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param dateString a Dublin-Core-compliant String
     * @param type the event that is marked by this date range
     */
    public DateRange(String dateString, DateType type)
    {
        super(type);
        setValue(dateString);
    }


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param epochMilliSince milliseconds that passed since 01/01/1970 00:00:00 until the beginning of the range
     * @param epochMilliUntil milliseconds that passed since 01/01/1970 00:00:00 until the end of the range
     * @param type the event that is marked by this date range
     */
    public DateRange(long epochMilliSince, long epochMilliUntil, DateType type)
    {
        super(type);
        setRangeFrom(epochMilliSince);
        setRangeUntil(epochMilliUntil);
    }

    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param rangeFrom a Dublin-Core-compliant String that marks the beginning of the range, or null if there is no beginning
     * @param rangeUntil a Dublin-Core-compliant String that marks the end of the range, or null if ther is no end
     * @param type the event that is marked by this date range
     */
    public DateRange(String rangeFrom, String rangeUntil, DateType type)
    {
        super(type);
        setRangeFrom(rangeFrom);
        setRangeUntil(rangeUntil);
    }


    /**
     * Returns the date as a Dublin-Core-compliant String.
     * <br>e.g. 1997-07-16T19:30Z/1997-07-17T15:30Z
     * <br><br>(see http://www.ukoln.ac.uk/metadata/dcmi/collection-RKMS-ISO8601/)
     *
     * @return the amount of milliseconds between 01/01/1970 00:00:00 and this date
     */
    @Override
    public String getValue()
    {
        String sinceVal = "";
        String untilVal = "";

        if (since != null)
            sinceVal = since.toString();

        if (until != null)
            untilVal = until.toString();

        return sinceVal + DataCiteDateConstants.DATE_RANGE_SPLITTER + untilVal;
    }

    /**
     * Tries to set the date by parsing a Dublin-Core-compliant String.
     * <br>e.g. 1997-07-16T19:30Z/1997-07-17T15:30Z
     * <br><br>(see http://www.ukoln.ac.uk/metadata/dcmi/collection-RKMS-ISO8601/)
     *
     * @param stringValue the String that is to be parsed
     */
    @Override
    public void setValue(String stringValue)
    {
        // split up the dates
        String[] range = stringValue.split(DataCiteDateConstants.DATE_RANGE_SPLITTER);

        // check if we really had a separator
        if (range.length == 2) {
            setRangeFrom(range[0]);
            setRangeUntil(range[1]);
        } else if (range.length == 1)
            setRangeFrom(range[0]);
        else
            LOGGER.error(String.format(DataCiteDateConstants.PARSE_ERROR, stringValue));
    }


    /**
     * Changes the since-value using the amount of milliseconds that passed
     * from 01/01/1970 00:00:00 until this date.
     *
     * @param epochMilli the amount of milliseconds between 01/01/1970 00:00:00 and this date
     */
    public void setRangeFrom(long epochMilli)
    {
        this.since = unixTimestampToInstant(epochMilli);
    }

    /**
     * Changes the since-value by parsing a date-string.
     *
     * @param stringValue a date string that represents the start-date
     */
    public void setRangeFrom(String stringValue)
    {
        this.since = stringToInstant(stringValue);
    }

    /**
     * Changes the until-value using the amount of milliseconds that passed
     * from 01/01/1970 00:00:00 until this date.
     *
     * @param epochMilli the amount of milliseconds between 01/01/1970 00:00:00 and this date
     */
    public void setRangeUntil(long epochMilli)
    {
        this.until = unixTimestampToInstant(epochMilli);
    }

    /**
     * Changes the since-value by parsing a date-string.
     *
     * @param stringValue a date string that represents the end-date
     */
    public void setRangeUntil(String stringValue)
    {
        this.until = stringToInstant(stringValue);
    }
}
