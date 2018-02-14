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
 * This class defines JSON objects that represent precise research disciplines.
 *
 * @author Fidan Limani, Robin Weiss
 */
public class ResearchDiscipline extends AbstractResearch
{
    private final ResearchArea area;


    /**
     * Constructor that requires the RNBR, name, and are of the discipline.
     *
     * @param rbnr a number that serves as a unique key of a discipline within an area
     * @param displayName a human readable name of the discipline
     * @param area to which the discipline belongs
     */
    public ResearchDiscipline(int rbnr, String displayName, ResearchArea area)
    {
        super(rbnr, displayName);
        this.area = area;
    }


    /**
     * Returns the research area to which the discipline belongs.
     *
     * @return the area to which the discipline belongs
     */
    public ResearchArea getArea()
    {
        return area;
    }


    @Override
    public String toString()
    {
        return String.format(DataCiteResearchConstants.DISCIPLINE_NAME_FORMAT, area.getRbnr(), rbnr, displayName, area.getName(), area.getCategory());
    }
}
