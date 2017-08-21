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

/**
 * This JsonObject describes a date that has been relevant to the work.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Date
{
    /**
     *  Timestamp in milliseconds since 01/01/1970 00:00:00.
     *  All formats supported by ES, see https://www.elastic.co/guide/en/elasticsearch/reference/5.2/mapping-date-format.html
     */
    private long date;

    /**
     * The event that is marked by this date.
     */
    private DateType type;


    /**
     * Simple constructor that requires all mandatory fields.
     * @param date the date that is described by this object
     */
    public Date(Instant date)
    {
        setDate(date);
    }

    /**
     * Simple constructor that requires all mandatory fields.
     * @param date the date that is described by this object
     */
    public Date(Calendar date)
    {
        setDate(date);
    }

    /**
     * Simple constructor that requires all mandatory fields.
     * @param date the date that is described by this object
     */
    public Date(java.util.Date date)
    {
        setDate(date);
    }

    /**
     * Simple constructor that requires all mandatory fields.
     * @param date the date that is described by this object
     */
    public Date(java.sql.Date date)
    {
        setDate(date);
    }

    /**
     * Simple constructor that requires all mandatory fields.
     * @param epochMilli milliseconds that passed since 01/01/1970 00:00:00
     */
    public Date(long epochMilli)
    {
        setDate(epochMilli);
    }


    public long getTimeInMillis()
    {
        return date;
    }

    public void setDate(Instant date)
    {
        this.date = date.toEpochMilli();
    }

    public void setDate(Calendar date)
    {
        this.date = date.getTimeInMillis();
    }

    public void setDate(java.util.Date date)
    {
        this.date = date.getTime();
    }

    public void setDate(java.sql.Date date)
    {
        this.date = date.getTime();
    }

    public void setDate(long epochMilli)
    {
        this.date = epochMilli;
    }

    public DateType getType()
    {
        return type;
    }

    public void setType(DateType type)
    {
        this.type = type;
    }

    /**
     * This enumeration describes an event that is marked by a date.
     * @author Robin Weiss
     *
     */
    public enum DateType {
        Accepted,
        Available,
        Collected,
        Copyrighted,
        Created,
        Issued,
        Submitted,
        Updated,
        Valid
    }
}
