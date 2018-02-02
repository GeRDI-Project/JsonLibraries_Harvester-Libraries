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
package de.gerdiproject.json.controlledvocab;

import de.gerdiproject.json.controlledvocab.abstr.AbstractResearch;
import de.gerdiproject.json.controlledvocab.constants.ResearchConstants;

/**
 * @author Robin Weiss
 *
 */
public class ResearchDiscipline extends AbstractResearch
{
    private final ResearchCategory category;


    public ResearchDiscipline(int rbnr, String displayName, ResearchCategory category)
    {
        super(rbnr, displayName);
        this.category = category;
    }


    public ResearchCategory getCategory()
    {
        return category;
    }


    @Override
    public String toString()
    {
        return String.format(ResearchConstants.DISCIPLINE_WITH_NAME_FORMAT, category.getRbnr(), rbnr, displayName, category.getName());
    }
}
