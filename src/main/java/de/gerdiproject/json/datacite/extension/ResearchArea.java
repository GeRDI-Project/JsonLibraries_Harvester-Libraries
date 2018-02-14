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
package de.gerdiproject.json.datacite.extension;

import de.gerdiproject.json.datacite.constants.DataCiteResearchConstants;
import de.gerdiproject.json.datacite.extension.abstr.AbstractResearch;

/**
 * This class defines a JSON object that represents the general area to which a
 * {@linkplain ResearchDiscipline} belongs.
 *
 * @author Fidan Limani, Robin Weiss
 */
public class ResearchArea extends AbstractResearch
{
    private final String category;


    /**
     * Simple constructor that requires the RNBR, the human readable name and the
     * category of the research area.
     *
     * @param rbnr a number that serves as a unique key of the research area
     * @param displayName an english human readable name of the research area
     * @param category a human readable name of the general category
     */
    public ResearchArea(int rbnr, String displayName, String category)
    {
        super(rbnr, displayName);
        this.category = category;
    }


    /**
     * Returns a human readable name of the general category.
     * @return the name of the general research category
     */
    public String getCategory()
    {
        return category;
    }


    @Override
    public String toString()
    {
        return String.format(DataCiteResearchConstants.AREA_NAME_FORMAT, rbnr, displayName, category);
    }
}
