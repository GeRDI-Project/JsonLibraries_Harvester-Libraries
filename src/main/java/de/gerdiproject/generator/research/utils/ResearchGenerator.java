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
package de.gerdiproject.generator.research.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import de.gerdiproject.generator.research.constants.ResearchGeneratorConstants;
import de.gerdiproject.generator.research.source.json.ResearchAreaSource;
import de.gerdiproject.generator.research.source.json.ResearchCategorySource;
import de.gerdiproject.generator.research.source.json.ResearchDisciplineSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Generator class that wraps reads the DFG vocabulary from a JSON source file and generates constants files.
 *
 * @author Fidan Limani, Robin Weiss
 */
public class ResearchGenerator
{
    private static final Logger logger = LoggerFactory.getLogger(ResearchGenerator.class);


    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException
    {
        final ResearchGenerator generator = new ResearchGenerator();

        if (args == null || args.length == 0)
            generator.generateConstants(ResearchGeneratorConstants.DEFAULT_SOURCE_PATH);
        else
            generator.generateConstants(args);
    }


    public void generateConstants(String... filePaths)
    {
        logger.info(ResearchGeneratorConstants.GENERATOR_STARTED);

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
                logger.error(String.format(ResearchGeneratorConstants.FILE_READ_ERROR, filePath), e);
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
            logger.error(ResearchGeneratorConstants.FILE_WRITE_ERROR, e);
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
            logger.error(ResearchGeneratorConstants.FILE_WRITE_ERROR, e);
        }


        // finish class declaration and save files
        finishConstantsFile(categoryWriter, ResearchGeneratorConstants.CATEGORY_CLASSNAME);
        finishConstantsFile(areaWriter, ResearchGeneratorConstants.AREA_CLASSNAME);
        finishConstantsFile(disciplineWriter, ResearchGeneratorConstants.DISCIPLINE_CLASSNAME);

        logger.info(ResearchGeneratorConstants.GENERATOR_DONE);
    }


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
                logger.error(String.format(ResearchGeneratorConstants.FILE_CREATE_ERROR, filePath), e);
            }
        } else
            logger.error(String.format(ResearchGeneratorConstants.FILE_FOLDER_ERROR, filePath));

        return writer;
    }


    private void finishConstantsFile(OutputStreamWriter writer, String className)
    {
        try {
            writer.write(String.format(ResearchGeneratorConstants.CONSTRUCTOR, className));
            writer.write(ResearchGeneratorConstants.CLASS_END);

            writer.close();
        } catch (IOException e) {
            logger.error(ResearchGeneratorConstants.FILE_WRITE_ERROR, e);
        }
    }


    private List<ResearchCategorySource> readResearchListFromFile(String filepath) throws IOException
    {
        List<ResearchCategorySource> researchList = null;

        // define type of list
        final Type listType = new TypeToken<List<ResearchCategorySource>>() {} .getType();

        // load JSON file
        JsonReader reader = new JsonReader(
            new InputStreamReader(
                new FileInputStream(filepath),
                StandardCharsets.UTF_8));

        // parse list from JSON content
        researchList = new Gson().fromJson(reader, listType);

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