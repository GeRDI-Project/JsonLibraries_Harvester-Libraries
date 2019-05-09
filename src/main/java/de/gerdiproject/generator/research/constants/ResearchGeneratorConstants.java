/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gerdiproject.generator.research.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * This static class is a collection of constants for automatically creating research discipline constants classes.
 *
 * @author Fidan Limani, Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResearchGeneratorConstants
{
    private static final List<Character> SEPARATOR_CHARS = Collections.unmodifiableList(Arrays.asList('-', '_', ';', '(', ')',  '[', ']',  '{', '}', '.', ':', '/'));

    public static final String GENERATOR_STARTED = "Started generating ResearchDisciplineConstants.java, ResearchAreaConstants.java, and ResearchCategoryConstants.java";
    public static final String GENERATOR_DONE = "Finished Generating";

    public static final String CONSTANTS_FILE_NAME = "src/main/java/de/gerdiproject/json/datacite/extension/generic/constants/%sConstants.java";
    public static final String DEFAULT_SOURCE_PATH = "src/main/java/de/gerdiproject/generator/research/source/DfgTopics.json";

    public static final String CATEGORY_CLASSNAME = "ResearchCategory";
    public static final String AREA_CLASSNAME = "ResearchArea";
    public static final String DISCIPLINE_CLASSNAME = "ResearchDiscipline";
    public static final String DISCIPLINE_MAP_CLASSNAME = "Map<Integer, ResearchDiscipline>";

    public static final String CATEGORY_DEF = "%n    public static final String %s = \"%s\";";
    public static final String AREA_DEF = "%n    public static final ResearchArea %s = new ResearchArea(%d, \"%s\", ResearchCategoryConstants.%s);";
    public static final String DISCIPLINE_DEF = "%n    public static final ResearchDiscipline %s = new ResearchDiscipline(%d, \"%s\", ResearchAreaConstants.%s);";

    public static final String CLASS_END = "\n}";
    public static final String COMMENT = "%n    // %s";

    public static final String FILE_CREATE_ERROR = "Could not create file '%s'!";
    public static final String FILE_READ_ERROR = "Could not read file '%s'!";
    public static final String FILE_WRITE_ERROR = "Could not write to file!";
    public static final String FILE_FOLDER_ERROR = "Could not create directories for file '%s'!";

    public static final List<String> NAME_BREAKING_STRINGS = Collections.unmodifiableList(Arrays.asList(" -", ","));
    public static final String SEPARATOR_CHARS_PATTERN = createSeparatorPattern(SEPARATOR_CHARS);
    public static final String DUPLICATE_SPACES_PATTERN = " +";
    public static final char NEW_LINE = '\n';

    public static final String IMPORT_DEF = "%nimport %s;";
    public static final String AREA_IMPORT = "de.gerdiproject.json.datacite.extension.generic.ResearchArea";
    public static final String DISCIPLINE_IMPORT = "de.gerdiproject.json.datacite.extension.generic.ResearchDiscipline";
    public static final String MAP_IMPORT = "java.util.Map";
    public static final String HASH_MAP_IMPORT = "java.util.HashMap";

    public static final String CLASS_START =
        "/**"
        + "%n * Copyright © 2017 Fidan Limani, Robin Weiss (http://www.gerdi-project.de)"
        + "%n *" // NOPMD putting "%n *" in a dedicated variable makes code less readable
        + "%n * Licensed under the Apache License, Version 2.0 (the \"License\");"
        + "%n * you may not use this file except in compliance with the License."
        + "%n * You may obtain a copy of the License at"
        + "%n *"
        + "%n *     http://www.apache.org/licenses/LICENSE-2.0"
        + "%n *"
        + "%n * Unless required by applicable law or agreed to in writing, software"
        + "%n * distributed under the License is distributed on an \"AS IS\" BASIS,"
        + "%n * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied."
        + "%n * See the License for the specific language governing permissions and"
        + "%n * limitations under the License."
        + "%n */"
        + "%npackage de.gerdiproject.json.datacite.extension.generic.constants;"
        + "%n"
        + "%nimport de.gerdiproject.generator.research.utils.ResearchGenerator;"
        + "%2$s"
        + "%nimport lombok.AccessLevel;"
        + "%nimport lombok.NoArgsConstructor;"
        + "%n"
        + "%n/**"
        + "%n * This class serves as a collection of constants that define a controlled list of %1$ss."
        + "%n * It was generated via the {@linkplain ResearchGenerator}."
        + "%n * If there are errors or inconsistencies, please contact the authors."
        + "%n *"
        + "%n * @author Fidan Limani, Robin Weiss"
        + "%n */"
        + "%n@NoArgsConstructor(access=AccessLevel.PRIVATE)"
        + "%npublic class %1$sConstants"
        + "%n{";

    public static final String RESEARCH_MAP_INSTRUCTION = "%n            %s";

    public static final String RESEARCH_MAP_INITIALIZATION =
        "%n    // Convenience Map"
        + "%n    private static final Map<Integer, %s> RESEARCH_MAP ="
        + "%n        createResearchMap("
        + "%s"
        + "%n        );";

    public static final String RESEARCH_AREA_GETTER =
        "\n\n\n    /**"   // NOPMD - intended for readability
        + "\n     * This function returns a {@linkplain ResearchArea} by parsing a string"
        + "\n     * which contains the area RNBR."
        + "\n     *"    // NOPMD - intended for readability
        + "\n     * @param rnbrString the area RNBR as a string"
        + "\n     *"
        + "\n     * @return an area that matches the RNBR"
        + "\n     */"   // NOPMD - intended for readability
        + "\n    public static ResearchArea getByRnbrString(String rnbrString)"
        + "\n    {"     // NOPMD - intended forreadability
        + "\n        int rnbr = Integer.parseInt(rnbrString);"
        + "\n        return RESEARCH_MAP.get(rnbr);"
        + "\n    }";     // NOPMD - intended for readability

    public static final String RESEARCH_AREA_CREATE_MAP_METHOD =
        "\n\n\n    /**"
        + "\n     * A convenience function for initializing the research map."
        + "\n     *"
        + "\n     * @param areas a list of areas that are to be added to the map"
        + "\n     *"
        + "\n     * @return a hashmap that maps area RNBRs to research areas"
        + "\n     */"
        + "\n    private static Map<Integer, ResearchArea> createResearchMap(ResearchArea ...areas)"
        + "\n    {"
        + "\n        final Map<Integer, ResearchArea> map = new HashMap<>();"
        + "\n"
        + "\n        for (ResearchArea ra : areas)"
        + "\n            map.put(ra.getRbnr(), ra);"
        + "\n"
        + "\n        return map;"
        + "\n    }";

    public static final String RESEARCH_DISCIPLINE_GETTER =
        "\n\n\n    /**"
        + "\n     * This function returns a {@linkplain ResearchDiscipline} by parsing a string"
        + "\n     * which contains the area RNBR and the internal RNBR of the discipline."
        + "\n     *"
        + "\n     * @param rnbrString a string of the format AREA_RNBR-DISCIPLINE_RNBR"
        + "\n     *"
        + "\n     * @return a discipline that has a matching RNBR"
        + "\n     */"
        + "\n    public static ResearchDiscipline getByRnbrString(String rnbrString)"
        + "\n    {"
        + "\n        String[] splitRnbr = rnbrString.split(\"-\");"
        + "\n        int areaRnbr = Integer.parseInt(splitRnbr[0]);"
        + "\n        int disciplineRnbr = Integer.parseInt(splitRnbr[1]);"
        + "\n"
        + "\n        Map<Integer, ResearchDiscipline> subClasses = RESEARCH_MAP.get(areaRnbr);"
        + "\n"
        + "\n        return (subClasses != null) ? subClasses.get(disciplineRnbr) : null;"
        + "\n    }";

    public static final String RESEARCH_DISCIPLINE_CREATE_MAP_METHOD =
        "\n\n\n    /**"
        + "\n     * A convenience function for initializing the research map."
        + "\n     *"
        + "\n     * @param disciplines a list of disciplines that are to be added to the map"
        + "\n     *"
        + "\n     * @return a hashmap that maps area RNBRs to hashmaps of discipline RNBRs and disciplines"
        + "\n     */"
        + "\n    private static Map<Integer, Map<Integer, ResearchDiscipline>> createResearchMap(ResearchDiscipline ...disciplines)"
        + "\n    {"
        + "\n        final Map<Integer, Map<Integer, ResearchDiscipline>> map = new HashMap<>();"
        + "\n"
        + "\n        for (ResearchDiscipline rd : disciplines) {"
        + "\n            int categoryRnbr = rd.getArea().getRbnr();"
        + "\n            map.putIfAbsent(categoryRnbr, new HashMap<>());"
        + "\n            map.get(categoryRnbr).put(rd.getRbnr(), rd);"
        + "\n        }"
        + "\n"
        + "\n        return map;"
        + "\n    }";


    /**
     * Creates a pattern for matching characters that are illegal in class names.
     *
     * @return a pattern for matching characters that are illegal in class names
     */
    private static String createSeparatorPattern(final List<Character> illegalChars)
    {

        final StringBuilder sb = new StringBuilder();
        sb.append('['); // NOPMD - yes, append is called twice, but this is fine here
        illegalChars.forEach((final Character c) -> sb.append('\\').append(c));
        sb.append(']');

        return sb.toString();
    }
}
