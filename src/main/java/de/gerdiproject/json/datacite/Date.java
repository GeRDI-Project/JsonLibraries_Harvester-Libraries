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
import java.util.Calendar;

import de.gerdiproject.json.datacite.enums.DateType;

/**
 * This JsonObject describes a date that has been relevant to the work.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Date
{
    /**
     *  Timestamp in milliseconds since 01/01/1970 00:00:00.
     *  In XML, this is the value between the identifier-tags.
     *  All formats supported by ES, see https://www.elastic.co/guide/en/elasticsearch/reference/5.2/mapping-date-format.html
     */
    private long value;

    /**
     * The event that is marked by this date.
     */
    private DateType dateType;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param date the date that is described by this object
     * @param type the event that is marked by this date
     */
    public Date(Instant date, DateType type)
    {
        setDate(date);
        this.dateType = type;
    }


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param date the date that is described by this object
     * @param type the event that is marked by this date
     */
    public Date(Calendar date, DateType type)
    {
        setDate(date);
        this.dateType = type;
    }


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param date the date that is described by this object
     * @param type the event that is marked by this date
     */
    public Date(java.util.Date date, DateType type)
    {
        setDate(date);
        this.dateType = type;
    }


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param date the date that is described by this object
     * @param type the event that is marked by this date
     */
    public Date(java.sql.Date date, DateType type)
    {
        setDate(date);
        this.dateType = type;
    }


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param epochMilli milliseconds that passed since 01/01/1970 00:00:00
     * @param type the event that is marked by this date
     */
    public Date(long epochMilli, DateType type)
    {
        setValue(epochMilli);
        this.dateType = type;
    }


    /**
     * Returns the amount of milliseconds that passed from
     * 01/01/1970 00:00:00 until this date.
     *
     * @return the amount of milliseconds between 01/01/1970 00:00:00 and this date
     */
    public long getValue()
    {
        return value;
    }


    /**
     * Changes the date value using the amount of milliseconds that passed
     * from 01/01/1970 00:00:00 until this date.
     *
     * @param epochMilli the amount of milliseconds between 01/01/1970 00:00:00 and this date
     */
    public void setValue(long epochMilli)
    {
        this.value = epochMilli;
    }


    /**
     * Changes the date value using an {@linkplain Instant} that marks a date.
     *
     * @param date an {@linkplain Instant} that represents the new date
     */
    public void setDate(Instant date)
    {
        this.value = date.toEpochMilli();
    }


    /**
     * Changes the date value using a {@linkplain Calendar} that marks a date.
     *
     * @param date a {@linkplain Calendar} that represents the new date
     */
    public void setDate(Calendar date)
    {
        this.value = date.getTimeInMillis();
    }


    /**
     * Changes the date value using a {@linkplain java.util.Date} that marks a date.
     *
     * @param date a {@linkplain java.util.Date} that represents the new date
     */
    public void setDate(java.util.Date date)
    {
        this.value = date.getTime();
    }


    /**
     * Changes the date value using a {@linkplain java.sql.Date} that marks a date.
     *
     * @param date a {@linkplain java.sql.Date} that represents the new date
     */
    public void setDate(java.sql.Date date)
    {
        this.value = date.getTime();
    }


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
}
