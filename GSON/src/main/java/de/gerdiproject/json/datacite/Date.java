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

/**
 * This JsonObject describes a date that has been relevant to the work.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Date
{
    /**
     *  All formats supported by ES, see https://www.elastic.co/guide/en/elasticsearch/reference/5.2/mapping-date-format.html
     */
    public java.sql.Date date;

    /**
     * The event that is marked by this date.
     */
    public DateType type;

    /**
     * Simple constructor that requires all mandatory fields.
     * @param date the date that is described by this object
     */
    public Date(java.sql.Date date)
    {
        this.date = date;
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
