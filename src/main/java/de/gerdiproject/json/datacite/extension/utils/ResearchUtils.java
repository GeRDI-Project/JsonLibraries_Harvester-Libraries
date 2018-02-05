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
package de.gerdiproject.json.datacite.extension.utils;

import java.util.HashMap;
import java.util.Map;

import de.gerdiproject.json.datacite.extension.ResearchCategory;
import de.gerdiproject.json.datacite.extension.ResearchDiscipline;
import de.gerdiproject.json.datacite.extension.constants.ResearchConstants;

/**
 * This static class is a collection of constants that define a controlled list of Research Disciplines and categories.
 *
 * @author Fidan Limani, Robin Weiss
 */
public class ResearchUtils
{
    // Helper Maps
    private static Map<Integer, Map<Integer, ResearchDiscipline>> disciplineMap = createDisciplineMap();
    private static Map<Integer, ResearchCategory> categoryMap = createCategoryMap();

    /**
     * Private constructor, because this is a static class.
     */
    private ResearchUtils()
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

        addResearchDisciplinesToMap(map,
                                    ResearchConstants.PREHISTORY,
                                    ResearchConstants.CLASSICAL_PHILOLOGY,
                                    ResearchConstants.ANCIENT_HISTORY,
                                    ResearchConstants.CLASSICAL_ARCHAEOLOGY,
                                    ResearchConstants.EGYPTOLOGY);

        addResearchDisciplinesToMap(map,
                                    ResearchConstants.MEDIEVAL_HISTORY,
                                    ResearchConstants.EARLY_MODERN_HISTORY,
                                    ResearchConstants.MODERN_HISTORY,
                                    ResearchConstants.SCIENCE_HISTORY);

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

        addResearchCategoriesToMap(map, ResearchConstants.ANCIENT_CULTURES, ResearchConstants.HISTORY);

        return map;
    }
}
