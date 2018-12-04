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
package de.gerdiproject.generator.research.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import de.gerdiproject.generator.research.constants.ResearchGeneratorConstants;
import de.gerdiproject.generator.research.source.json.ResearchAreaSource;
import de.gerdiproject.generator.research.source.json.ResearchCategorySource;
import de.gerdiproject.generator.research.source.json.ResearchDisciplineSource;
import de.gerdiproject.json.datacite.extension.generic.ResearchArea;
import de.gerdiproject.json.datacite.extension.generic.ResearchDiscipline;
import de.gerdiproject.json.datacite.extension.generic.constants.ResearchAreaConstants;
import de.gerdiproject.json.datacite.extension.generic.constants.ResearchCategoryConstants;
import de.gerdiproject.json.datacite.extension.generic.constants.ResearchDisciplineConstants;

/**
 * Generator class that wraps reads the DFG vocabulary from a JSON source file and generates constants files.
 *
 * @author Fidan Limani, Robin Weiss
 */
public class ResearchGenerator
{
    private static final Type RESEARCH_CATEGORY_LIST_TYPE = new TypeToken<List<ResearchCategorySource>>() {} .getType();
    private static final Logger LOGGER = LoggerFactory.getLogger(ResearchGenerator.class);


    /**
     * This main function creates a {@linkplain ResearchGenerator} and runs it, using a default path, or optionally
     * an arbitrary number of filepaths that can be passed via arguments
     *
     * @param args an arbitrary number of filepaths to JSON files that are to be parsed
     */
    public static void main(String[] args)
    {
        final ResearchGenerator generator = new ResearchGenerator();

        if (args == null || args.length == 0)
            generator.generateConstants(ResearchGeneratorConstants.DEFAULT_SOURCE_PATH);
        else
            generator.generateConstants(args);
    }


    /**
     * This method accepts a list of filePaths that point to JSON files. These files should contain
     * a list of {@linkplain ResearchCategorySource} objects. The objects are read and written down
     * in {@linkplain ResearchDisciplineConstants}, {@linkplain ResearchAreaConstants}, and {@linkplain ResearchCategoryConstants},
     * overwriting the original classes.
     *
     * @param filePaths a list of filePaths that point to JSON files
     */
    public void generateConstants(String... filePaths)
    {
        LOGGER.info(ResearchGeneratorConstants.GENERATOR_STARTED);

        final OutputStreamWriter categoryWriter = initConstantsFile(
                                                      ResearchGeneratorConstants.CATEGORY_CLASSNAME);

        final OutputStreamWriter areaWriter = initConstantsFile(
                                                  ResearchGeneratorConstants.AREA_CLASSNAME,
                                                  ResearchGeneratorConstants.AREA_IMPORT,
                                                  ResearchGeneratorConstants.MAP_IMPORT,
                                                  ResearchGeneratorConstants.HASH_MAP_IMPORT);

        final OutputStreamWriter disciplineWriter = initConstantsFile(
                                                        ResearchGeneratorConstants.DISCIPLINE_CLASSNAME,
                                                        ResearchGeneratorConstants.DISCIPLINE_IMPORT,
                                                        ResearchGeneratorConstants.MAP_IMPORT,
                                                        ResearchGeneratorConstants.HASH_MAP_IMPORT);

        final StringBuilder areaMapBuilder = new StringBuilder();
        final StringBuilder disciplineMapBuilder = new StringBuilder();

        // Loop through the list of files
        for (String filePath : filePaths) {
            try {
                addConstantsFromFile(
                    filePath,
                    categoryWriter,
                    areaWriter,
                    disciplineWriter,
                    areaMapBuilder,
                    disciplineMapBuilder
                );
            } catch (IOException e) {
                LOGGER.error(String.format(ResearchGeneratorConstants.FILE_READ_ERROR, filePath), e);
            }
        }

        // add area constants class specific methods
        try {
            areaWriter.append(String.format(
                                  ResearchGeneratorConstants.RESEARCH_MAP_INITIALIZATION,
                                  ResearchGeneratorConstants.AREA_CLASSNAME,
                                  areaMapBuilder.toString()));

            areaWriter.append(ResearchGeneratorConstants.RESEARCH_AREA_GETTER);
            areaWriter.append(ResearchGeneratorConstants.RESEARCH_AREA_CREATE_MAP_METHOD);
        } catch (IOException e) {
            LOGGER.error(ResearchGeneratorConstants.FILE_WRITE_ERROR, e);
        }

        // add discipline constants class specific methods
        try {
            disciplineWriter.append(String.format(
                                        ResearchGeneratorConstants.RESEARCH_MAP_INITIALIZATION,
                                        ResearchGeneratorConstants.DISCIPLINE_MAP_CLASSNAME,
                                        disciplineMapBuilder.toString()));

            disciplineWriter.append(ResearchGeneratorConstants.RESEARCH_DISCIPLINE_GETTER);
            disciplineWriter.append(ResearchGeneratorConstants.RESEARCH_DISCIPLINE_CREATE_MAP_METHOD);
        } catch (IOException e) {
            LOGGER.error(ResearchGeneratorConstants.FILE_WRITE_ERROR, e);
        }

        // finish class declaration and save files
        finishConstantsFile(categoryWriter);
        finishConstantsFile(areaWriter);
        finishConstantsFile(disciplineWriter);

        LOGGER.info(ResearchGeneratorConstants.GENERATOR_DONE);
    }


    /**
     * This method parses all {@linkplain ResearchCategorySource} objects within a file.
     *
     * @param filePath the file path to the JSON file that is being read
     * @param categoryWriter the output stream writer for {@linkplain ResearchCategoryConstants}
     * @param areaWriter the output stream writer for {@linkplain ResearchAreaConstants}
     * @param disciplineWriter the output stream writer for {@linkplain ResearchDisciplineConstants}
     * @param areaMapBuilder a string builder that concatenates a list of constant names of {@linkplain ResearchArea}
     * @param disciplineMapBuilder a string builder that concatenates a list of constant names of {@linkplain ResearchDiscipline}
     *
     * @throws IOException this exception is thrown when a read or write operation of any file failed
     */
    private void addConstantsFromFile(String filePath, OutputStreamWriter categoryWriter, OutputStreamWriter areaWriter, OutputStreamWriter disciplineWriter, StringBuilder areaMapBuilder, StringBuilder disciplineMapBuilder) throws IOException
    {
        List<ResearchCategorySource> categories = readResearchListFromFile(filePath);

        if (categories == null || categories.isEmpty())
            return;

        for (ResearchCategorySource sCat : categories) {
            String categoryConstName = getConstantName(sCat.getName());
            categoryWriter.append(String.format(ResearchGeneratorConstants.CATEGORY_DEF, categoryConstName, sCat.getName()));
            areaWriter.append(String.format(ResearchGeneratorConstants.COMMENT, categoryConstName));

            for (ResearchAreaSource area : sCat.getSubclasses()) {
                String areaConstName = getConstantName(area.getName());
                areaWriter.append(String.format(ResearchGeneratorConstants.AREA_DEF, areaConstName, area.getRBNR(), area.getName(), categoryConstName));
                disciplineWriter.append(String.format(ResearchGeneratorConstants.COMMENT, areaConstName));

                // add area to initialization of research map
                if (areaMapBuilder.length() != 0)
                    areaMapBuilder.append(',');

                areaMapBuilder.append(String.format(ResearchGeneratorConstants.RESEARCH_MAP_INSTRUCTION, areaConstName));

                for (ResearchDisciplineSource discipline : area.getSubclasses()) {
                    String disciConstName = getConstantName(discipline.getName());
                    disciplineWriter.append(String.format(ResearchGeneratorConstants.DISCIPLINE_DEF, disciConstName, discipline.getRBNR(), discipline.getName(), areaConstName));


                    // add discipline to initialization of research map
                    if (disciplineMapBuilder.length() != 0)
                        disciplineMapBuilder.append(',');

                    disciplineMapBuilder.append(String.format(ResearchGeneratorConstants.RESEARCH_MAP_INSTRUCTION, disciConstName));
                }

                disciplineWriter.append(ResearchGeneratorConstants.NEW_LINE);
            }

            areaWriter.append(ResearchGeneratorConstants.NEW_LINE);
        }
    }


    /**
     * Opens an output stream writer to a Constants class file and starts writing the header, imports
     * and class definition.
     *
     * @param constantType the type that ist most common within the constants class
     * @param imports an arbitrary number of class paths that are imported
     *
     * @return the output stream writer of the generated class
     */
    private OutputStreamWriter initConstantsFile(String constantType, String... imports)
    {
        String filePath = String.format(ResearchGeneratorConstants.CONSTANTS_FILE_NAME, constantType);
        File output = new File(filePath);

        // create directories
        boolean isDirectoryCreated = output.getParentFile().exists() || output.getParentFile().mkdirs();

        // open stream
        OutputStreamWriter writer = null;

        if (isDirectoryCreated) {
            try {
                writer = new OutputStreamWriter(
                    new FileOutputStream(output),
                    StandardCharsets.UTF_8);

                // assemble imports String
                StringBuilder importBuilder = new StringBuilder();

                for (String importClassName : imports)
                    importBuilder.append(String.format(ResearchGeneratorConstants.IMPORT_DEF, importClassName));

                // write class definition
                writer.append(String.format(ResearchGeneratorConstants.CLASS_START, constantType, importBuilder.toString()));
            } catch (IOException e) {
                LOGGER.error(String.format(ResearchGeneratorConstants.FILE_CREATE_ERROR, filePath), e);
            }
        } else
            LOGGER.error(String.format(ResearchGeneratorConstants.FILE_FOLDER_ERROR, filePath));

        return writer;
    }


    /**
     * Writes the closing brace of the class definition to a specified writer and closes the writer.
     *
     * @param writer the output stream writer of a constants file
     */
    private void finishConstantsFile(OutputStreamWriter writer)
    {
        try {
            writer.append(ResearchGeneratorConstants.CLASS_END);

            writer.close();
        } catch (IOException e) {
            LOGGER.error(ResearchGeneratorConstants.FILE_WRITE_ERROR, e);
        }
    }


    /**
     * Opens a JSON file and attempts to parse its content to a list of {@linkplain ResearchCategorySource}s.
     *
     * @param filepath the path to the JSON file
     *
     * @return a list of {@linkplain ResearchCategorySource}
     *
     * @throws IOException this exception is thrown when the JSON file could not be parsed
     */
    private List<ResearchCategorySource> readResearchListFromFile(String filepath) throws IOException
    {
        List<ResearchCategorySource> researchList = null;

        // load JSON file
        JsonReader reader = new JsonReader(
            new InputStreamReader(
                new FileInputStream(filepath),
                StandardCharsets.UTF_8));

        // parse list from JSON content
        researchList = new Gson().fromJson(reader, RESEARCH_CATEGORY_LIST_TYPE);

        return researchList;
    }


    /**
     * Creates a name for a constant in Java under the default naming conventions.
     *
     * @param sourceName the original name of the research topic
     *
     * @return a Java constant name
     */
    private static String getConstantName(String sourceName)
    {
        String constName = sourceName;

        // 1. cull the string by getting everything before the first occurrence of certain strings
        for (String nameBreakingString : ResearchGeneratorConstants.NAME_BREAKING_STRINGS) {
            int breakIndex = constName.indexOf(nameBreakingString);

            if (breakIndex != -1)
                constName = constName.substring(0, breakIndex);
        }

        // 2. Replace illegal, separating chars with spaces
        constName = constName.replaceAll(ResearchGeneratorConstants.SEPARATOR_CHARS_PATTERN, " ");

        // 4. Replace all spaces with underscores, remove duplicate spaces
        constName = constName.replaceAll(ResearchGeneratorConstants.DUPLICATE_SPACES_PATTERN, "_");

        return constName.toUpperCase();
    }
}