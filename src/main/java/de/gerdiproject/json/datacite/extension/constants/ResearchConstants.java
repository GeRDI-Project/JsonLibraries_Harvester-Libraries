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
package de.gerdiproject.json.datacite.extension.constants;

import java.util.HashMap;
import java.util.Map;

import de.gerdiproject.json.datacite.extension.ResearchCategory;
import de.gerdiproject.json.datacite.extension.ResearchDiscipline;

/**
 * This static class is a collection of constants that define a controlled list of Research Disciplines and categories.
 *
 * @author Fidan Limani, Robin Weiss
 */
public class ResearchConstants
{
    // ANCIENT CULTURES
    public static final ResearchCategory ANCIENT_CULTURES = new ResearchCategory(101, "Ancient Cultures");
    public static final ResearchDiscipline PREHISTORY = new ResearchDiscipline(01, "Prehistory", ANCIENT_CULTURES);
    public static final ResearchDiscipline CLASSICAL_PHILOLOGY = new ResearchDiscipline(02, "Classical Philology", ANCIENT_CULTURES);
    public static final ResearchDiscipline ANCIENT_HISTORY = new ResearchDiscipline(03, "Ancient History", ANCIENT_CULTURES);
    public static final ResearchDiscipline CLASSICAL_ARCHAEOLOGY = new ResearchDiscipline(04, "Classical Archaeology", ANCIENT_CULTURES);
    public static final ResearchDiscipline EGYPTOLOGY = new ResearchDiscipline(05, "Egyptology and Ancient Near Eastern Studies", ANCIENT_CULTURES);

    // HISTORY
    public static final ResearchCategory HISTORY = new ResearchCategory(102, "Historys");
    public static final ResearchDiscipline MEDIEVAL_HISTORY = new ResearchDiscipline(01, "Medieval History", HISTORY);
    public static final ResearchDiscipline EARLY_MODERN_HISTORY = new ResearchDiscipline(02, "Early Modern History", HISTORY);
    public static final ResearchDiscipline MODERN_HISTORY = new ResearchDiscipline(03, "Modern and Current History", HISTORY);
    public static final ResearchDiscipline SCIENCE_HISTORY = new ResearchDiscipline(04, "History of Science", HISTORY);



    /**
     * Private constructor, because this is a static class.
     */
    private ResearchConstants()
    {

    }
}
