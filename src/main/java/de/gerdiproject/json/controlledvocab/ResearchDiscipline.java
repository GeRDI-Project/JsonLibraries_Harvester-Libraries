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
public enum ResearchDiscipline implements IResearchDiscipline{
	
    // ANCIENT CULTURES
    PREHISTORY(01,             "Prehistory",                                  ResearchCategory.ANCIENT_CULTURES),
    CLASSICAL_PHILOLOGY(02,   "Classical Philology",                         ResearchCategory.ANCIENT_CULTURES),
    ANCIENT_HISTORY(03,        "Ancient History",                            ResearchCategory.ANCIENT_CULTURES),
    CLASSICAL_ARCHAEOLOGY(04, "Classical Archaeology",                       ResearchCategory.ANCIENT_CULTURES),
    EGYPTOLOGY(05,             "Egyptology and Ancient Near Eastern Studies", ResearchCategory.ANCIENT_CULTURES),

    // HISTORY
    MEDIEVAL_HISTORY(01,       "Medieval History",           ResearchCategory.HISTORY),
    EARLY_MODERN_HISTORY(02,  "Early Modern History",       ResearchCategory.HISTORY),
    MODERN_HISTORY(03,         "Modern and Current History", ResearchCategory.HISTORY),
    SCIENCE_HISTORY(04,        "History of Science",         ResearchCategory.HISTORY);

    //...

    private final ResearchCategory category;
    private final String rbnr;
    private final String displayName;


    private ResearchDiscipline(int rbnr, String displayName, ResearchCategory category)
    {
        this.category = category;
        this.rbnr = String.format("%02d", rbnr );
        this.displayName = displayName;
    }
    
    
    public ResearchCategory getCategory()
	{
		return category;
	}


	@Override
	public String getRbnr()
	{
		return rbnr;
	}



	@Override
	public String getName()
	{
		return displayName;
	}
}
