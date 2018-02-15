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
package de.gerdiproject.json.datacite.extension.abstr;

/**
 * An abstract class that represents research topics of a document.
 *
 * @author Fidan Limani, Robin Weiss
 */
public abstract class AbstractResearch
{
    protected final int rbnr;


    /**
     * Constructor that requires the RBNR and name of the research topic.
     *
     * @param rbnr a unique key that represents the topic
     */
    public AbstractResearch(int rbnr)
    {
        this.rbnr = rbnr;
    }


    /**
     * Returns the human readable name of the research discipline.
     *
     * @return the human readable name of the research discipline,
     * or null if it is unknown
     */
    public abstract String getDisciplineName();


    /**
     * Returns the human readable name of the research area.
     *
     * @return the human readable name of the research area
     */
    public abstract String getAreaName();


    /**
     * Returns the human readable name of the research category.
     *
     * @return the human readable name of the research category
     */
    public abstract String getCategoryName();


    /**
     * Returns the RNBR as a string.
     *
     * @return ther RNBR as a string
     */
    public abstract String getRnbrAsString();


    /**
     * Returns the unique key that represents the topic.
     *
     * @return a unique key that represents the topic
     */
    public int getRbnr()
    {
        return rbnr;
    }
}
