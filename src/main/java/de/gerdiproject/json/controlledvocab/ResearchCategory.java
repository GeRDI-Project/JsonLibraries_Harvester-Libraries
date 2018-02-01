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

/**
 * @author Robin Weiss
 *
 */
public enum ResearchCategory implements IResearchDiscipline{
    ANCIENT_CULTURES(101, "Ancient Cultures"),
    HISTORY(102, "History"),
    FINE_ARTS(103, "Fine Arts, Music, Theatre and Media Studies");

    // ... TO BE CONTINUED

    private final String displayName;
    private final String rbnr;


    private ResearchCategory(int rbnr, String displayName)
    {
        this.rbnr = String.valueOf( rbnr );
        this.displayName = displayName;
    }



    public String getName()
    {
        return displayName;
    }



    public String getRbnr()
    {
        return rbnr;
    }



    @Override
    public String toString()
    {
        return rbnr + " - " + displayName;
    }
}
