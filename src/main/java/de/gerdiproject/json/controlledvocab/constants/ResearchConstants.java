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
package de.gerdiproject.json.controlledvocab.constants;

import java.util.HashMap;
import java.util.Map;

import de.gerdiproject.json.controlledvocab.ResearchCategory;
import de.gerdiproject.json.controlledvocab.ResearchDiscipline;

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


    // Helper Maps
    private static Map<Integer, Map<Integer, ResearchDiscipline>> disciplineMap = createDisciplineMap();
    private static Map<Integer, ResearchCategory> categoryMap = createCategoryMap();

    // Json Fields
    public static final String NAME_JSON = "name";
    public static final String RNBR_JSON = "RNBR";

    // Formatting
    public static final String CATEGORY_FORMAT = "%03d";
    public static final String CATEGORY_WITH_NAME_FORMAT = CATEGORY_FORMAT + " %s";
    public static final String DISCIPLINE_FORMAT = "%03d-%02d";
    public static final String DISCIPLINE_WITH_NAME_FORMAT = DISCIPLINE_FORMAT + " %s (%s)";


    /**
     * Private constructor, because this is a static class.
     */
    private ResearchConstants()
    {

    }


    public static ResearchCategory getCategoryByRnbr(int rnbr)
    {
        return categoryMap.get(rnbr);
    }


    public static ResearchDiscipline getDisciplineByRnbr(int categoryRnbr, int disciplineRnbr)
    {
        Map<Integer, ResearchDiscipline> subClasses = disciplineMap.get(categoryRnbr);

        return (subClasses != null) ? subClasses.get(disciplineRnbr) : null;
    }


    private static void addResearchDisciplinesToMap(Map<Integer, Map<Integer, ResearchDiscipline>> map,
                                                    ResearchDiscipline ...disciplines)
    {
        for (ResearchDiscipline rd : disciplines) {
            int categoryRnbr = rd.getCategory().getRbnr();
            map.putIfAbsent(categoryRnbr, new HashMap<>());
            map.get(categoryRnbr).put(rd.getRbnr(), rd);
        }
    }


    private static Map<Integer, Map<Integer, ResearchDiscipline>> createDisciplineMap()
    {
        final Map<Integer, Map<Integer, ResearchDiscipline>> map = new HashMap<>();

        addResearchDisciplinesToMap(map, PREHISTORY, CLASSICAL_PHILOLOGY, ANCIENT_HISTORY, CLASSICAL_ARCHAEOLOGY, EGYPTOLOGY);
        addResearchDisciplinesToMap(map, MEDIEVAL_HISTORY, EARLY_MODERN_HISTORY, MODERN_HISTORY, SCIENCE_HISTORY);

        return map;
    }


    private static void addResearchCategoriesToMap(Map<Integer, ResearchCategory> map,
                                                   ResearchCategory ...categories)
    {
        for (ResearchCategory rc : categories)
            map.put(rc.getRbnr(), rc);
    }


    private static Map<Integer, ResearchCategory> createCategoryMap()
    {
        final Map<Integer, ResearchCategory> map = new HashMap<>();

        addResearchCategoriesToMap(map, ANCIENT_CULTURES, HISTORY);

        return map;
    }
}
