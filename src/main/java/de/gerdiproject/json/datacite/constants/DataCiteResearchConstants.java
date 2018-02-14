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
package de.gerdiproject.json.datacite.constants;

import de.gerdiproject.json.datacite.extension.abstr.AbstractResearch;

/**
 * This static class is a collection of constants that are used by {@linkplain AbstractResearch} and its sub-classes.
 *
 * @author Robin Weiss
 */
public class DataCiteResearchConstants
{
    // Json Fields
    public static final String NAME_JSON = "name";
    public static final String RNBR_JSON = "RNBR";

    // Formatting
    public static final String AREA_RNBR_FORMAT = "%03d";
    public static final String AREA_NAME_FORMAT = AREA_RNBR_FORMAT + " %s (%s)";
    public static final String DISCIPLINE_RNBR_FORMAT = "%03d-%02d";
    public static final String DISCIPLINE_NAME_FORMAT = DISCIPLINE_RNBR_FORMAT + " %s (%s) (%s)";


    /**
     * Private constructor, because this is a static class.
     */
    private DataCiteResearchConstants()
    {

    }
}
