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

import de.gerdiproject.generator.research.utils.ResearchGenerator;
import de.gerdiproject.json.datacite.extension.ResearchArea;
import java.util.Map;
import java.util.HashMap;

/**
  * This static class is a collection of constants that define a controlled list of ResearchAreas.
  * It was generated via the {@linkplain ResearchGenerator}.
  * If there are errors or inconsistencies, please contact the authors.
  *
  * @author Fidan Limani, Robin Weiss
  */
public class ResearchAreaConstants
{
    // HUMANITIES_AND_SOCIAL_SCIENCES
    public static final ResearchArea ANCIENT_CULTURES = new ResearchArea(101, "Ancient Cultures", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea HISTORY = new ResearchArea(102, "History", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea FINE_ARTS = new ResearchArea(103, "Fine Arts, Music, Theatre and Media Studies", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea LINGUISTICS = new ResearchArea(104, "Linguistics", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea LITERARY_STUDIES = new ResearchArea(105, "Literary Studies", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea SOCIAL_AND_CULTURAL_ANTHROPOLOGY = new ResearchArea(106, "Social and Cultural Anthropology, Non-European Cultures, Jewish Studies and Religious Studies", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea THEOLOGY = new ResearchArea(107, "Theology", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea PHILOSOPHY = new ResearchArea(108, "Philosophy", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea EDUCATIONAL_RESEARCH = new ResearchArea(109, "Educational Research", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea PSYCHOLOGY = new ResearchArea(110, "Psychology", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea SOCIAL_SCIENCES = new ResearchArea(111, "Social Sciences", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea ECONOMICS = new ResearchArea(112, "Economics", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);
    public static final ResearchArea JURISPRUDENCE = new ResearchArea(113, "Jurisprudence", ResearchCategoryConstants.HUMANITIES_AND_SOCIAL_SCIENCES);

    // LIFE_SCIENCES
    public static final ResearchArea BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE = new ResearchArea(201, "Basic Research in Biology and Medicine", ResearchCategoryConstants.LIFE_SCIENCES);
    public static final ResearchArea PLANT_SCIENCES = new ResearchArea(202, "Plant Sciences", ResearchCategoryConstants.LIFE_SCIENCES);
    public static final ResearchArea ZOOLOGY = new ResearchArea(203, "Zoology", ResearchCategoryConstants.LIFE_SCIENCES);
    public static final ResearchArea MICROBIOLOGY = new ResearchArea(204, "Microbiology, Virology and Immunology", ResearchCategoryConstants.LIFE_SCIENCES);
    public static final ResearchArea MEDICINE = new ResearchArea(205, "Medicine", ResearchCategoryConstants.LIFE_SCIENCES);
    public static final ResearchArea NEUROSCIENCES = new ResearchArea(206, "Neurosciences", ResearchCategoryConstants.LIFE_SCIENCES);
    public static final ResearchArea AGRICULTURE = new ResearchArea(207, "Agriculture, Forestry and Veterinary Medicine", ResearchCategoryConstants.LIFE_SCIENCES);

    // NATURAL_SCIENCES
    public static final ResearchArea MOLECULAR_CHEMISTRY = new ResearchArea(301, "Molecular Chemistry", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea CHEMICAL_SOLID_STATE_AND_SURFACE_RESEARCH = new ResearchArea(302, "Chemical Solid State and Surface Research", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea PHYSICAL_AND_THEORETICAL_CHEMISTRY = new ResearchArea(303, "Physical and Theoretical Chemistry", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea ANALYTICAL_CHEMISTRY = new ResearchArea(304, "Analytical Chemistry, Method Development (Chemistry)", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea BIOLOGICAL_CHEMISTRY_AND_FOOD_CHEMISTRY = new ResearchArea(305, "Biological Chemistry and Food Chemistry", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea POLYMER_RESEARCH = new ResearchArea(306, "Polymer Research", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea CONDENSED_MATTER_PHYSICS = new ResearchArea(307, "Condensed Matter Physics", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea OPTICS = new ResearchArea(308, "Optics, Quantum Optics and Physics of Atoms, Molecules and Plasmas", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea PARTICLES = new ResearchArea(309, "Particles, Nuclei and Fields", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea STATISTICAL_PHYSICS = new ResearchArea(310, "Statistical Physics, Soft Matter, Biological Physics, Nonlinear Dynamics", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea ASTROPHYSICS_AND_ASTRONOMY = new ResearchArea(311, "Astrophysics and Astronomy", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea MATHEMATICS = new ResearchArea(312, "Mathematics", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea ATMOSPHERIC_SCIENCE = new ResearchArea(313, "Atmospheric Science, Oceanography and Climate Research", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea GEOLOGY_AND_PALAEONTOLOGY = new ResearchArea(314, "Geology and Palaeontology", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea GEOPHYSICS_AND_GEODESY = new ResearchArea(315, "Geophysics and Geodesy", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea GEOCHEMISTRY = new ResearchArea(316, "Geochemistry, Mineralogy and Crystallography", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea GEOGRAPHY = new ResearchArea(317, "Geography", ResearchCategoryConstants.NATURAL_SCIENCES);
    public static final ResearchArea WATER_RESEARCH = new ResearchArea(318, "Water Research", ResearchCategoryConstants.NATURAL_SCIENCES);

    // ENGINEERING_SCIENCES
    public static final ResearchArea PRODUCTION_TECHNOLOGY = new ResearchArea(401, "Production Technology", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea MECHANICS_AND_CONSTRUCTIVE_MECHANICAL_ENGINEERING = new ResearchArea(402, "Mechanics and Constructive Mechanical Engineering", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea PROCESS_ENGINEERING = new ResearchArea(403, "Process Engineering, Technical Chemistry", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea HEAT_ENERGY_TECHNOLOGY = new ResearchArea(404, "Heat Energy Technology, Thermal Machines, Fluid Mechanics", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea MATERIALS_ENGINEERING = new ResearchArea(405, "Materials Engineering", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea MATERIALS_SCIENCE = new ResearchArea(406, "Materials Science", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea SYSTEMS_ENGINEERING = new ResearchArea(407, "Systems Engineering", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea ELECTRICAL_ENGINEERING_AND_INFORMATION_TECHNOLOGY = new ResearchArea(408, "Electrical Engineering and Information Technology", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea COMPUTER_SCIENCE = new ResearchArea(409, "Computer Science", ResearchCategoryConstants.ENGINEERING_SCIENCES);
    public static final ResearchArea CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE = new ResearchArea(410, "Construction Engineering and Architecture", ResearchCategoryConstants.ENGINEERING_SCIENCES);

    // Convenience Map
    private static final Map<Integer, ResearchArea> RESEARCH_MAP =
        createResearchMap(
            ANCIENT_CULTURES,
            HISTORY,
            FINE_ARTS,
            LINGUISTICS,
            LITERARY_STUDIES,
            SOCIAL_AND_CULTURAL_ANTHROPOLOGY,
            THEOLOGY,
            PHILOSOPHY,
            EDUCATIONAL_RESEARCH,
            PSYCHOLOGY,
            SOCIAL_SCIENCES,
            ECONOMICS,
            JURISPRUDENCE,
            BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE,
            PLANT_SCIENCES,
            ZOOLOGY,
            MICROBIOLOGY,
            MEDICINE,
            NEUROSCIENCES,
            AGRICULTURE,
            MOLECULAR_CHEMISTRY,
            CHEMICAL_SOLID_STATE_AND_SURFACE_RESEARCH,
            PHYSICAL_AND_THEORETICAL_CHEMISTRY,
            ANALYTICAL_CHEMISTRY,
            BIOLOGICAL_CHEMISTRY_AND_FOOD_CHEMISTRY,
            POLYMER_RESEARCH,
            CONDENSED_MATTER_PHYSICS,
            OPTICS,
            PARTICLES,
            STATISTICAL_PHYSICS,
            ASTROPHYSICS_AND_ASTRONOMY,
            MATHEMATICS,
            ATMOSPHERIC_SCIENCE,
            GEOLOGY_AND_PALAEONTOLOGY,
            GEOPHYSICS_AND_GEODESY,
            GEOCHEMISTRY,
            GEOGRAPHY,
            WATER_RESEARCH,
            PRODUCTION_TECHNOLOGY,
            MECHANICS_AND_CONSTRUCTIVE_MECHANICAL_ENGINEERING,
            PROCESS_ENGINEERING,
            HEAT_ENERGY_TECHNOLOGY,
            MATERIALS_ENGINEERING,
            MATERIALS_SCIENCE,
            SYSTEMS_ENGINEERING,
            ELECTRICAL_ENGINEERING_AND_INFORMATION_TECHNOLOGY,
            COMPUTER_SCIENCE,
            CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE
        );


    /**
     * This function returns a {@linkplain ResearchArea} by parsing a string
     * which contains the area RNBR.
     *
     * @param rnbrString the area RNBR as a string
     *
     * @return an area that matches the RNBR
     */
    public static ResearchArea getByRnbrString(String rnbrString)
    {
        int rnbr = Integer.parseInt(rnbrString);
        return RESEARCH_MAP.get(rnbr);
    }


    /**
     * A convenience function for initializing the research map.
     *
     * @param areas a list of areas that are to be added to the map
     *
     * @return a hashmap that maps area RNBRs to research areas
     */
    private static Map<Integer, ResearchArea> createResearchMap(ResearchArea ...areas)
    {
        final Map<Integer, ResearchArea> map = new HashMap<>();

        for (ResearchArea ra : areas)
            map.put(ra.getRbnr(), ra);

        return map;
    }

    /**
     * Private constructor, because this is a static class.
     */
    private ResearchAreaConstants()
    {

    }
}