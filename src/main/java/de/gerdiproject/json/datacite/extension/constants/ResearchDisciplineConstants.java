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
import de.gerdiproject.json.datacite.extension.ResearchDiscipline;
import java.util.Map;
import java.util.HashMap;

/**
  * This static class is a collection of constants that define a controlled list of ResearchDisciplines.
  * It was generated via the {@linkplain ResearchGenerator}.
  * If there are errors or inconsistencies, please contact the authors.
  *
  * @author Fidan Limani, Robin Weiss
  */
public class ResearchDisciplineConstants
{
    // ANCIENT_CULTURES
    public static final ResearchDiscipline PREHISTORY = new ResearchDiscipline(1, "Prehistory", ResearchAreaConstants.ANCIENT_CULTURES);
    public static final ResearchDiscipline CLASSICAL_PHILOLOGY = new ResearchDiscipline(2, "Classical Philology", ResearchAreaConstants.ANCIENT_CULTURES);
    public static final ResearchDiscipline ANCIENT_HISTORY = new ResearchDiscipline(3, "Ancient History", ResearchAreaConstants.ANCIENT_CULTURES);
    public static final ResearchDiscipline CLASSICAL_ARCHAEOLOGY = new ResearchDiscipline(4, "Classical Archaeology", ResearchAreaConstants.ANCIENT_CULTURES);
    public static final ResearchDiscipline EGYPTOLOGY_AND_ANCIENT_NEAR_EASTERN_STUDIES = new ResearchDiscipline(5, "Egyptology and Ancient Near Eastern Studies", ResearchAreaConstants.ANCIENT_CULTURES);

    // HISTORY
    public static final ResearchDiscipline MEDIEVAL_HISTORY = new ResearchDiscipline(1, "Medieval History", ResearchAreaConstants.HISTORY);
    public static final ResearchDiscipline EARLY_MODERN_HISTORY = new ResearchDiscipline(2, "Early Modern History", ResearchAreaConstants.HISTORY);
    public static final ResearchDiscipline MODERN_AND_CURRENT_HISTORY = new ResearchDiscipline(3, "Modern and Current History", ResearchAreaConstants.HISTORY);
    public static final ResearchDiscipline HISTORY_OF_SCIENCE = new ResearchDiscipline(4, "History of Science", ResearchAreaConstants.HISTORY);

    // FINE_ARTS
    public static final ResearchDiscipline ART_HISTORY = new ResearchDiscipline(1, "Art History", ResearchAreaConstants.FINE_ARTS);
    public static final ResearchDiscipline MUSICOLOGY = new ResearchDiscipline(2, "Musicology", ResearchAreaConstants.FINE_ARTS);
    public static final ResearchDiscipline THEATRE_AND_MEDIA_STUDIES = new ResearchDiscipline(3, "Theatre and Media Studies", ResearchAreaConstants.FINE_ARTS);

    // LINGUISTICS
    public static final ResearchDiscipline GENERAL_AND_COMPARATIVE_LINGUISTICS = new ResearchDiscipline(1, "General and Comparative Linguistics, Typology, Non-European Languages", ResearchAreaConstants.LINGUISTICS);
    public static final ResearchDiscipline INDIVIDUAL_LINGUISTICS = new ResearchDiscipline(2, "Individual Linguistics", ResearchAreaConstants.LINGUISTICS);
    public static final ResearchDiscipline HISTORICAL_LINGUISTICS = new ResearchDiscipline(3, "Historical Linguistics", ResearchAreaConstants.LINGUISTICS);
    public static final ResearchDiscipline APPLIED_LINGUISTICS = new ResearchDiscipline(4, "Applied Linguistics, Experimental Linguistics, Computational Linguistics", ResearchAreaConstants.LINGUISTICS);

    // LITERARY_STUDIES
    public static final ResearchDiscipline MEDIEVAL_GERMAN_LITERATURE = new ResearchDiscipline(1, "Medieval German Literature", ResearchAreaConstants.LITERARY_STUDIES);
    public static final ResearchDiscipline MODERN_GERMAN_LITERATURE = new ResearchDiscipline(2, "Modern German Literature", ResearchAreaConstants.LITERARY_STUDIES);
    public static final ResearchDiscipline EUROPEAN_AND_AMERICAN_LITERATURE = new ResearchDiscipline(3, "European and American Literature", ResearchAreaConstants.LITERARY_STUDIES);
    public static final ResearchDiscipline GENERAL_AND_COMPARATIVE_LITERATURE_AND_CULTURAL_STUDIES = new ResearchDiscipline(4, "General and Comparative Literature and Cultural Studies", ResearchAreaConstants.LITERARY_STUDIES);

    // SOCIAL_AND_CULTURAL_ANTHROPOLOGY
    public static final ResearchDiscipline SOCIAL_AND_CULTURAL_ANTHROPOLOGY_AND_ETHNOLOGY = new ResearchDiscipline(1, "Social and Cultural Anthropology and Ethnology", ResearchAreaConstants.SOCIAL_AND_CULTURAL_ANTHROPOLOGY);
    public static final ResearchDiscipline ASIAN_STUDIES = new ResearchDiscipline(2, "Asian Studies", ResearchAreaConstants.SOCIAL_AND_CULTURAL_ANTHROPOLOGY);
    public static final ResearchDiscipline AFRICAN = new ResearchDiscipline(3, "African, American and Oceania Studies", ResearchAreaConstants.SOCIAL_AND_CULTURAL_ANTHROPOLOGY);
    public static final ResearchDiscipline ISLAMIC_STUDIES = new ResearchDiscipline(4, "Islamic Studies, Arabian Studies, Semitic Studies", ResearchAreaConstants.SOCIAL_AND_CULTURAL_ANTHROPOLOGY);
    public static final ResearchDiscipline RELIGIOUS_STUDIES_AND_JEWISH_STUDIES = new ResearchDiscipline(5, "Religious Studies and Jewish Studies", ResearchAreaConstants.SOCIAL_AND_CULTURAL_ANTHROPOLOGY);

    // THEOLOGY
    public static final ResearchDiscipline PROTESTANT_THEOLOGY = new ResearchDiscipline(1, "Protestant Theology", ResearchAreaConstants.THEOLOGY);
    public static final ResearchDiscipline ROMAN_CATHOLIC_THEOLOGY = new ResearchDiscipline(2, "Roman Catholic Theology", ResearchAreaConstants.THEOLOGY);

    // PHILOSOPHY
    public static final ResearchDiscipline HISTORY_OF_PHILOSOPHY = new ResearchDiscipline(1, "History of Philosophy", ResearchAreaConstants.PHILOSOPHY);
    public static final ResearchDiscipline THEORETICAL_PHILOSOPHY = new ResearchDiscipline(2, "Theoretical Philosophy", ResearchAreaConstants.PHILOSOPHY);
    public static final ResearchDiscipline PRACTICAL_PHILOSOPHY = new ResearchDiscipline(3, "Practical Philosophy", ResearchAreaConstants.PHILOSOPHY);

    // EDUCATIONAL_RESEARCH
    public static final ResearchDiscipline GENERAL_EDUCATION_AND_HISTORY_OF_EDUCATION = new ResearchDiscipline(1, "General Education and History of Education", ResearchAreaConstants.EDUCATIONAL_RESEARCH);
    public static final ResearchDiscipline GENERAL_AND_DOMAIN_SPECIFIC_TEACHING_AND_LEARNING = new ResearchDiscipline(2, "General and Domain-Specific Teaching and Learning", ResearchAreaConstants.EDUCATIONAL_RESEARCH);
    public static final ResearchDiscipline EDUCATION_SYSTEMS_AND_EDUCATIONAL_INSTITUTIONS = new ResearchDiscipline(3, "Education Systems and Educational Institutions", ResearchAreaConstants.EDUCATIONAL_RESEARCH);
    public static final ResearchDiscipline EDUCATIONAL_RESEARCH_ON_SOCIALIZATION = new ResearchDiscipline(4, "Educational Research on Socialization, Welfare and Organisations", ResearchAreaConstants.EDUCATIONAL_RESEARCH);

    // PSYCHOLOGY
    public static final ResearchDiscipline GENERAL = new ResearchDiscipline(1, "General, Biological and Mathematical Psychology", ResearchAreaConstants.PSYCHOLOGY);
    public static final ResearchDiscipline DEVELOPMENTAL_AND_EDUCATIONAL_PSYCHOLOGY = new ResearchDiscipline(2, "Developmental and Educational Psychology", ResearchAreaConstants.PSYCHOLOGY);
    public static final ResearchDiscipline SOCIAL_PSYCHOLOGY = new ResearchDiscipline(3, "Social Psychology, Industrial and Organisational Psychology", ResearchAreaConstants.PSYCHOLOGY);
    public static final ResearchDiscipline DIFFERENTIAL_PSYCHOLOGY = new ResearchDiscipline(4, "Differential Psychology, Clinical Psychology, Medical Psychology, Methodology", ResearchAreaConstants.PSYCHOLOGY);

    // SOCIAL_SCIENCES
    public static final ResearchDiscipline SOCIOLOGICAL_THEORY = new ResearchDiscipline(1, "Sociological Theory", ResearchAreaConstants.SOCIAL_SCIENCES);
    public static final ResearchDiscipline EMPIRICAL_SOCIAL_RESEARCH = new ResearchDiscipline(2, "Empirical Social Research", ResearchAreaConstants.SOCIAL_SCIENCES);
    public static final ResearchDiscipline COMMUNICATION_SCIENCES = new ResearchDiscipline(3, "Communication Sciences", ResearchAreaConstants.SOCIAL_SCIENCES);
    public static final ResearchDiscipline POLITICAL_SCIENCE = new ResearchDiscipline(4, "Political Science", ResearchAreaConstants.SOCIAL_SCIENCES);

    // ECONOMICS
    public static final ResearchDiscipline ECONOMIC_THEORY = new ResearchDiscipline(1, "Economic Theory", ResearchAreaConstants.ECONOMICS);
    public static final ResearchDiscipline ECONOMIC_POLICY_AND_PUBLIC_FINANCE = new ResearchDiscipline(2, "Economic Policy and Public Finance", ResearchAreaConstants.ECONOMICS);
    public static final ResearchDiscipline BUSINESS_ADMINISTRATION = new ResearchDiscipline(3, "Business Administration", ResearchAreaConstants.ECONOMICS);
    public static final ResearchDiscipline STATISTICS_AND_ECONOMETRICS = new ResearchDiscipline(4, "Statistics and Econometrics", ResearchAreaConstants.ECONOMICS);
    public static final ResearchDiscipline ECONOMIC_AND_SOCIAL_HISTORY = new ResearchDiscipline(5, "Economic and Social History", ResearchAreaConstants.ECONOMICS);

    // JURISPRUDENCE
    public static final ResearchDiscipline PRINCIPLES_OF_LAW_AND_JURISPRUDENCE = new ResearchDiscipline(1, "Principles of Law and Jurisprudence", ResearchAreaConstants.JURISPRUDENCE);
    public static final ResearchDiscipline PRIVATE_LAW = new ResearchDiscipline(2, "Private Law", ResearchAreaConstants.JURISPRUDENCE);
    public static final ResearchDiscipline PUBLIC_LAW = new ResearchDiscipline(3, "Public Law", ResearchAreaConstants.JURISPRUDENCE);
    public static final ResearchDiscipline CRIMINAL_LAW_AND_LAW_OF_CRIMINAL_PROCEDURE = new ResearchDiscipline(4, "Criminal Law and Law of Criminal Procedure", ResearchAreaConstants.JURISPRUDENCE);
    public static final ResearchDiscipline CRIMINOLOGY = new ResearchDiscipline(5, "Criminology", ResearchAreaConstants.JURISPRUDENCE);

    // BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE
    public static final ResearchDiscipline BIOCHEMISTRY = new ResearchDiscipline(1, "Biochemistry", ResearchAreaConstants.BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE);
    public static final ResearchDiscipline BIOPHYSICS = new ResearchDiscipline(2, "Biophysics", ResearchAreaConstants.BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE);
    public static final ResearchDiscipline CELL_BIOLOGY = new ResearchDiscipline(3, "Cell Biology", ResearchAreaConstants.BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE);
    public static final ResearchDiscipline STRUCTURAL_BIOLOGY = new ResearchDiscipline(4, "Structural Biology", ResearchAreaConstants.BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE);
    public static final ResearchDiscipline GENERAL_GENETICS = new ResearchDiscipline(5, "General Genetics", ResearchAreaConstants.BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE);
    public static final ResearchDiscipline DEVELOPMENTAL_BIOLOGY = new ResearchDiscipline(6, "Developmental Biology", ResearchAreaConstants.BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE);
    public static final ResearchDiscipline BIOINFORMATICS_AND_THEORETICAL_BIOLOGY = new ResearchDiscipline(7, "Bioinformatics and Theoretical Biology", ResearchAreaConstants.BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE);
    public static final ResearchDiscipline ANATOMY = new ResearchDiscipline(8, "Anatomy", ResearchAreaConstants.BASIC_RESEARCH_IN_BIOLOGY_AND_MEDICINE);

    // PLANT_SCIENCES
    public static final ResearchDiscipline EVOLUTION_AND_SYSTEMATICS_OF_PLANTS_AND_FUNGI = new ResearchDiscipline(1, "Evolution and Systematics of Plants and Fungi", ResearchAreaConstants.PLANT_SCIENCES);
    public static final ResearchDiscipline PLANT_ECOLOGY_AND_ECOSYSTEM_ANALYSIS = new ResearchDiscipline(2, "Plant Ecology and Ecosystem Analysis", ResearchAreaConstants.PLANT_SCIENCES);
    public static final ResearchDiscipline INTER_ORGANISMIC_INTERACTIONS_AND_CHEMICAL_ECOLOGY_OF_PLANT_SYSTEMS = new ResearchDiscipline(3, "Inter-Organismic Interactions and Chemical Ecology of Plant Systems", ResearchAreaConstants.PLANT_SCIENCES);
    public static final ResearchDiscipline PLANT_PHYSIOLOGY = new ResearchDiscipline(4, "Plant Physiology", ResearchAreaConstants.PLANT_SCIENCES);
    public static final ResearchDiscipline PLANT_BIOCHEMISTRY_AND_BIOPHYSICS = new ResearchDiscipline(5, "Plant Biochemistry and Biophysics", ResearchAreaConstants.PLANT_SCIENCES);
    public static final ResearchDiscipline PLANT_CELL_AND_DEVELOPMENTAL_BIOLOGY = new ResearchDiscipline(6, "Plant Cell and Developmental Biology", ResearchAreaConstants.PLANT_SCIENCES);
    public static final ResearchDiscipline PLANT_GENETICS = new ResearchDiscipline(7, "Plant Genetics", ResearchAreaConstants.PLANT_SCIENCES);

    // ZOOLOGY
    public static final ResearchDiscipline SPECIAL_ZOOLOGY_AND_MORPHOLOGY = new ResearchDiscipline(1, "Special Zoology and Morphology", ResearchAreaConstants.ZOOLOGY);
    public static final ResearchDiscipline EVOLUTION = new ResearchDiscipline(2, "Evolution, Anthropology", ResearchAreaConstants.ZOOLOGY);
    public static final ResearchDiscipline ANIMAL_ECOLOGY = new ResearchDiscipline(3, "Animal Ecology, Biodiversity and Ecosystem Research", ResearchAreaConstants.ZOOLOGY);
    public static final ResearchDiscipline SENSORY_AND_BEHAVIOURAL_BIOLOGY = new ResearchDiscipline(4, "Sensory and Behavioural Biology", ResearchAreaConstants.ZOOLOGY);
    public static final ResearchDiscipline BIOCHEMISTRY_AND_ANIMAL_PHYSIOLOGY = new ResearchDiscipline(5, "Biochemistry and Animal Physiology", ResearchAreaConstants.ZOOLOGY);
    public static final ResearchDiscipline EVOLUTIONARY_CELL_AND_DEVELOPMENTAL_BIOLOGY_ZOOLOGY_ = new ResearchDiscipline(6, "Evolutionary Cell and Developmental Biology (Zoology)", ResearchAreaConstants.ZOOLOGY);

    // MICROBIOLOGY
    public static final ResearchDiscipline METABOLISM = new ResearchDiscipline(1, "Metabolism, Biochemistry and Genetics of Microorganisms", ResearchAreaConstants.MICROBIOLOGY);
    public static final ResearchDiscipline MICROBIAL_ECOLOGY_AND_APPLIED_MICROBIOLOGY = new ResearchDiscipline(2, "Microbial Ecology and Applied Microbiology", ResearchAreaConstants.MICROBIOLOGY);
    public static final ResearchDiscipline MEDICAL_MICROBIOLOGY = new ResearchDiscipline(3, "Medical Microbiology, Parasitology, Medical Mycology and Hygiene, Molecular Infection Biology", ResearchAreaConstants.MICROBIOLOGY);
    public static final ResearchDiscipline VIROLOGY = new ResearchDiscipline(4, "Virology", ResearchAreaConstants.MICROBIOLOGY);
    public static final ResearchDiscipline IMMUNOLOGY = new ResearchDiscipline(5, "Immunology", ResearchAreaConstants.MICROBIOLOGY);

    // MEDICINE
    public static final ResearchDiscipline EPIDEMIOLOGY = new ResearchDiscipline(1, "Epidemiology, Medical Biometry, Medical Informatics", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline PUBLIC_HEALTH = new ResearchDiscipline(2, "Public Health, Health Services Research, Social Medicine", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline HUMAN_GENETICS = new ResearchDiscipline(3, "Human Genetics", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline PHYSIOLOGY = new ResearchDiscipline(4, "Physiology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline NUTRITIONAL_SCIENCES = new ResearchDiscipline(5, "Nutritional Sciences", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline PATHOLOGY = new ResearchDiscipline(6, "Pathology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline CLINICAL_CHEMISTRY_AND_PATHOBIOCHEMISTRY = new ResearchDiscipline(7, "Clinical Chemistry and Pathobiochemistry", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline PHARMACY = new ResearchDiscipline(8, "Pharmacy", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline PHARMACOLOGY = new ResearchDiscipline(9, "Pharmacology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline TOXICOLOGY = new ResearchDiscipline(10, "Toxicology, Occupational Medicine and Forensic Medicine", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline ANAESTHESIOLOGY = new ResearchDiscipline(11, "Anaesthesiology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline CARDIOLOGY = new ResearchDiscipline(12, "Cardiology, Angiology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline PNEUMOLOGY = new ResearchDiscipline(13, "Pneumology, Clinical Infectiology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline HEMATOLOGY = new ResearchDiscipline(14, "Hematology, Oncology, Transfusion Medicine", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline GASTROENTEROLOGY = new ResearchDiscipline(15, "Gastroenterology, Metabolism", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline NEPHROLOGY = new ResearchDiscipline(16, "Nephrology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline ENDOCRINOLOGY = new ResearchDiscipline(17, "Endocrinology, Diabetology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline RHEUMATOLOGY = new ResearchDiscipline(18, "Rheumatology, Clinical Immunology, Allergology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline DERMATOLOGY = new ResearchDiscipline(19, "Dermatology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline PEDIATRIC_AND_ADOLESCENT_MEDICINE = new ResearchDiscipline(20, "Pediatric and Adolescent Medicine", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline GYNAECOLOGY_AND_OBSTETRICS = new ResearchDiscipline(21, "Gynaecology and Obstetrics", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline REPRODUCTIVE_MEDICINE_BIOLOGY = new ResearchDiscipline(22, "Reproductive Medicine/Biology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline UROLOGY = new ResearchDiscipline(23, "Urology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline BIOGERONTOLOGY_AND_GERIATRIC_MEDICINE = new ResearchDiscipline(24, "Biogerontology and Geriatric Medicine", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline GENERAL_AND_VISCERAL_SURGERY = new ResearchDiscipline(25, "General and Visceral Surgery", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline CARDIOTHORACIC_AND_VASCULAR_SURGERY = new ResearchDiscipline(26, "Cardiothoracic and Vascular Surgery", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline TRAUMATOLOGY_AND_ORTHOPAEDICS = new ResearchDiscipline(27, "Traumatology and Orthopaedics", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline DENTISTRY = new ResearchDiscipline(28, "Dentistry, Oral Surgery", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline OTOLARYNGOLOGY = new ResearchDiscipline(29, "Otolaryngology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline RADIOLOGY_AND_NUCLEAR_MEDICINE = new ResearchDiscipline(30, "Radiology and Nuclear Medicine", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline RADIATION_ONCOLOGY_AND_RADIOBIOLOGY = new ResearchDiscipline(31, "Radiation Oncology and Radiobiology", ResearchAreaConstants.MEDICINE);
    public static final ResearchDiscipline BIOMEDICAL_TECHNOLOGY_AND_MEDICAL_PHYSICS = new ResearchDiscipline(32, "Biomedical Technology and Medical Physics", ResearchAreaConstants.MEDICINE);

    // NEUROSCIENCES
    public static final ResearchDiscipline MOLECULAR_NEUROSCIENCE_AND_NEUROGENETICS = new ResearchDiscipline(1, "Molecular Neuroscience and Neurogenetics", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline CELLULAR_NEUROSCIENCE = new ResearchDiscipline(2, "Cellular Neuroscience", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline DEVELOPMENTAL_NEUROBIOLOGY = new ResearchDiscipline(3, "Developmental Neurobiology", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline SYSTEMIC_NEUROSCIENCE = new ResearchDiscipline(4, "Systemic Neuroscience, Computational Neuroscience, Behaviour", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline ORGANISMIC_NEUROBIOLOGY = new ResearchDiscipline(5, "Organismic Neurobiology", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline COGNITIVE_NEUROSCIENCE = new ResearchDiscipline(6, "Cognitive Neuroscience", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline MOLECULAR_AND_CELLULAR_NEUROLOGY = new ResearchDiscipline(7, "Molecular and Cellular Neurology, Neuropathology", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline CLINICAL_NEUROSCIENCES_I = new ResearchDiscipline(8, "Clinical Neurosciences I - Neurology, Neurosurgery, Neuroradiology", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline BIOLOGICAL_AND_MOLECULAR_PSYCHIATRY = new ResearchDiscipline(9, "Biological and Molecular Psychiatry", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline CLINICAL_NEUROSCIENCES_II = new ResearchDiscipline(10, "Clinical Neurosciences II - Psychiatry, Psychotherapy, Child and Adolescent Psychiatry", ResearchAreaConstants.NEUROSCIENCES);
    public static final ResearchDiscipline CLINICAL_NEUROSCIENCES_III = new ResearchDiscipline(11, "Clinical Neurosciences III - Ophthalmology", ResearchAreaConstants.NEUROSCIENCES);

    // AGRICULTURE
    public static final ResearchDiscipline SOIL_SCIENCES = new ResearchDiscipline(1, "Soil Sciences", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline PLANT_CULTIVATION_AND_AGRICULTURAL_TECHNOLOGY = new ResearchDiscipline(2, "Plant Cultivation and Agricultural Technology", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline PLANT_NUTRITION = new ResearchDiscipline(3, "Plant Nutrition", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline ECOLOGY_OF_AGRICULTURAL_LANDSCAPES = new ResearchDiscipline(4, "Ecology of Agricultural Landscapes", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline PLANT_BREEDING = new ResearchDiscipline(5, "Plant Breeding", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline PHYTOMEDICINE = new ResearchDiscipline(6, "Phytomedicine", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline AGRICULTURAL_ECONOMICS_AND_SOCIOLOGY = new ResearchDiscipline(7, "Agricultural Economics and Sociology", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline FORESTRY = new ResearchDiscipline(8, "Forestry", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline ANIMAL_HUSBANDRY = new ResearchDiscipline(9, "Animal Husbandry, Breeding and Hygiene", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline ANIMAL_NUTRITION_AND_NUTRITION_PHYSIOLOGY = new ResearchDiscipline(10, "Animal Nutrition and Nutrition Physiology", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline BASIC_VETERINARY_MEDICAL_SCIENCE = new ResearchDiscipline(11, "Basic Veterinary Medical Science", ResearchAreaConstants.AGRICULTURE);
    public static final ResearchDiscipline BASIC_RESEARCH_ON_PATHOGENESIS = new ResearchDiscipline(12, "Basic Research on Pathogenesis, Diagnostics and Therapy and Clinical Veterinary Medicine", ResearchAreaConstants.AGRICULTURE);

    // MOLECULAR_CHEMISTRY
    public static final ResearchDiscipline INORGANIC_MOLECULAR_CHEMISTRY = new ResearchDiscipline(1, "Inorganic Molecular Chemistry", ResearchAreaConstants.MOLECULAR_CHEMISTRY);
    public static final ResearchDiscipline ORGANIC_MOLECULAR_CHEMISTRY = new ResearchDiscipline(2, "Organic Molecular Chemistry", ResearchAreaConstants.MOLECULAR_CHEMISTRY);

    // CHEMICAL_SOLID_STATE_AND_SURFACE_RESEARCH
    public static final ResearchDiscipline SOLID_STATE_AND_SURFACE_CHEMISTRY = new ResearchDiscipline(1, "Solid State and Surface Chemistry, Material Synthesis", ResearchAreaConstants.CHEMICAL_SOLID_STATE_AND_SURFACE_RESEARCH);
    public static final ResearchDiscipline PHYSICAL_CHEMISTRY_OF_SOLIDS_AND_SURFACES = new ResearchDiscipline(2, "Physical Chemistry of Solids and Surfaces, Material Characterisation", ResearchAreaConstants.CHEMICAL_SOLID_STATE_AND_SURFACE_RESEARCH);
    public static final ResearchDiscipline THEORY_AND_MODELLING = new ResearchDiscipline(3, "Theory and Modelling", ResearchAreaConstants.CHEMICAL_SOLID_STATE_AND_SURFACE_RESEARCH);

    // PHYSICAL_AND_THEORETICAL_CHEMISTRY
    public static final ResearchDiscipline PHYSICAL_CHEMISTRY_OF_MOLECULES = new ResearchDiscipline(1, "Physical Chemistry of Molecules, Interfaces and Liquids - Spectroscopy, Kinetics", ResearchAreaConstants.PHYSICAL_AND_THEORETICAL_CHEMISTRY);
    public static final ResearchDiscipline GENERAL_THEORETICAL_CHEMISTRY = new ResearchDiscipline(2, "General Theoretical Chemistry", ResearchAreaConstants.PHYSICAL_AND_THEORETICAL_CHEMISTRY);

    // ANALYTICAL_CHEMISTRY
    public static final ResearchDiscipline ANALYTICAL_CHEMISTRY = new ResearchDiscipline(1, "Analytical Chemistry, Method Development (Chemistry)", ResearchAreaConstants.ANALYTICAL_CHEMISTRY);

    // BIOLOGICAL_CHEMISTRY_AND_FOOD_CHEMISTRY
    public static final ResearchDiscipline BIOLOGICAL_AND_BIOMIMETIC_CHEMISTRY = new ResearchDiscipline(1, "Biological and Biomimetic Chemistry", ResearchAreaConstants.BIOLOGICAL_CHEMISTRY_AND_FOOD_CHEMISTRY);
    public static final ResearchDiscipline FOOD_CHEMISTRY = new ResearchDiscipline(2, "Food Chemistry", ResearchAreaConstants.BIOLOGICAL_CHEMISTRY_AND_FOOD_CHEMISTRY);

    // POLYMER_RESEARCH
    public static final ResearchDiscipline PREPARATORY_AND_PHYSICAL_CHEMISTRY_OF_POLYMERS = new ResearchDiscipline(1, "Preparatory and Physical Chemistry of Polymers", ResearchAreaConstants.POLYMER_RESEARCH);
    public static final ResearchDiscipline EXPERIMENTAL_AND_THEORETICAL_PHYSICS_OF_POLYMERS = new ResearchDiscipline(2, "Experimental and Theoretical Physics of Polymers", ResearchAreaConstants.POLYMER_RESEARCH);
    public static final ResearchDiscipline POLYMER_MATERIALS = new ResearchDiscipline(3, "Polymer Materials", ResearchAreaConstants.POLYMER_RESEARCH);

    // CONDENSED_MATTER_PHYSICS
    public static final ResearchDiscipline EXPERIMENTAL_CONDENSED_MATTER_PHYSICS = new ResearchDiscipline(1, "Experimental Condensed Matter Physics", ResearchAreaConstants.CONDENSED_MATTER_PHYSICS);
    public static final ResearchDiscipline THEORETICAL_CONDENSED_MATTER_PHYSICS = new ResearchDiscipline(2, "Theoretical Condensed Matter Physics", ResearchAreaConstants.CONDENSED_MATTER_PHYSICS);

    // OPTICS
    public static final ResearchDiscipline OPTICS = new ResearchDiscipline(1, "Optics, Quantum Optics, Atoms, Molecules, Plasmas", ResearchAreaConstants.OPTICS);

    // PARTICLES
    public static final ResearchDiscipline PARTICLES = new ResearchDiscipline(1, "Particles, Nuclei and Fields", ResearchAreaConstants.PARTICLES);

    // STATISTICAL_PHYSICS
    public static final ResearchDiscipline STATISTICAL_PHYSICS = new ResearchDiscipline(1, "Statistical Physics, Soft Matter, Biological Physics, Nonlinear Dynamics", ResearchAreaConstants.STATISTICAL_PHYSICS);

    // ASTROPHYSICS_AND_ASTRONOMY
    public static final ResearchDiscipline ASTROPHYSICS_AND_ASTRONOMY = new ResearchDiscipline(1, "Astrophysics and Astronomy", ResearchAreaConstants.ASTROPHYSICS_AND_ASTRONOMY);

    // MATHEMATICS
    public static final ResearchDiscipline MATHEMATICS = new ResearchDiscipline(1, "Mathematics", ResearchAreaConstants.MATHEMATICS);

    // ATMOSPHERIC_SCIENCE
    public static final ResearchDiscipline ATMOSPHERIC_SCIENCE = new ResearchDiscipline(1, "Atmospheric Science", ResearchAreaConstants.ATMOSPHERIC_SCIENCE);
    public static final ResearchDiscipline OCEANOGRAPHY = new ResearchDiscipline(2, "Oceanography", ResearchAreaConstants.ATMOSPHERIC_SCIENCE);

    // GEOLOGY_AND_PALAEONTOLOGY
    public static final ResearchDiscipline GEOLOGY_AND_PALAEONTOLOGY = new ResearchDiscipline(1, "Geology and Palaeontology", ResearchAreaConstants.GEOLOGY_AND_PALAEONTOLOGY);

    // GEOPHYSICS_AND_GEODESY
    public static final ResearchDiscipline GEOPHYSICS = new ResearchDiscipline(1, "Geophysics", ResearchAreaConstants.GEOPHYSICS_AND_GEODESY);
    public static final ResearchDiscipline GEODESY = new ResearchDiscipline(2, "Geodesy, Photogrammetry, Remote Sensing, Geoinformatics, Cartography", ResearchAreaConstants.GEOPHYSICS_AND_GEODESY);

    // GEOCHEMISTRY
    public static final ResearchDiscipline GEOCHEMISTRY = new ResearchDiscipline(1, "Geochemistry, Mineralogy and Crystallography", ResearchAreaConstants.GEOCHEMISTRY);

    // GEOGRAPHY
    public static final ResearchDiscipline PHYSICAL_GEOGRAPHY = new ResearchDiscipline(1, "Physical Geography", ResearchAreaConstants.GEOGRAPHY);
    public static final ResearchDiscipline HUMAN_GEOGRAPHY = new ResearchDiscipline(2, "Human Geography", ResearchAreaConstants.GEOGRAPHY);

    // WATER_RESEARCH
    public static final ResearchDiscipline HYDROGEOLOGY = new ResearchDiscipline(1, "Hydrogeology, Hydrology, Limnology, Urban Water Management, Water Chemistry, Integrated Water Resources Management", ResearchAreaConstants.WATER_RESEARCH);

    // PRODUCTION_TECHNOLOGY
    public static final ResearchDiscipline METAL_CUTTING_MANUFACTURING_ENGINEERING = new ResearchDiscipline(1, "Metal-Cutting Manufacturing Engineering", ResearchAreaConstants.PRODUCTION_TECHNOLOGY);
    public static final ResearchDiscipline PRIMARY_SHAPING_AND_RESHAPING_TECHNOLOGY = new ResearchDiscipline(2, "Primary Shaping and Reshaping Technology", ResearchAreaConstants.PRODUCTION_TECHNOLOGY);
    public static final ResearchDiscipline JOINING = new ResearchDiscipline(3, "Joining, Mounting and Separation Technology", ResearchAreaConstants.PRODUCTION_TECHNOLOGY);
    public static final ResearchDiscipline PLASTICS_ENGINEERING = new ResearchDiscipline(4, "Plastics Engineering", ResearchAreaConstants.PRODUCTION_TECHNOLOGY);
    public static final ResearchDiscipline PRODUCTION_MANAGEMENT_AND_OPERATIONS_MANAGEMENT = new ResearchDiscipline(5, "Production Management and Operations Management", ResearchAreaConstants.PRODUCTION_TECHNOLOGY);
    public static final ResearchDiscipline MACHINE_TOOLS_AND_PRODUCTION_AUTOMATION = new ResearchDiscipline(6, "Machine Tools and Production Automation", ResearchAreaConstants.PRODUCTION_TECHNOLOGY);

    // MECHANICS_AND_CONSTRUCTIVE_MECHANICAL_ENGINEERING
    public static final ResearchDiscipline ENGINEERING_DESIGN = new ResearchDiscipline(1, "Engineering Design, Machine Elements, Product Development", ResearchAreaConstants.MECHANICS_AND_CONSTRUCTIVE_MECHANICAL_ENGINEERING);
    public static final ResearchDiscipline MECHANICS = new ResearchDiscipline(2, "Mechanics", ResearchAreaConstants.MECHANICS_AND_CONSTRUCTIVE_MECHANICAL_ENGINEERING);
    public static final ResearchDiscipline LIGHTWEIGHT_CONSTRUCTION = new ResearchDiscipline(3, "Lightweight Construction, Textile Technology", ResearchAreaConstants.MECHANICS_AND_CONSTRUCTIVE_MECHANICAL_ENGINEERING);
    public static final ResearchDiscipline ACOUSTICS = new ResearchDiscipline(4, "Acoustics", ResearchAreaConstants.MECHANICS_AND_CONSTRUCTIVE_MECHANICAL_ENGINEERING);

    // PROCESS_ENGINEERING
    public static final ResearchDiscipline CHEMICAL_AND_THERMAL_PROCESS_ENGINEERING = new ResearchDiscipline(1, "Chemical and Thermal Process Engineering", ResearchAreaConstants.PROCESS_ENGINEERING);
    public static final ResearchDiscipline TECHNICAL_CHEMISTRY = new ResearchDiscipline(2, "Technical Chemistry", ResearchAreaConstants.PROCESS_ENGINEERING);
    public static final ResearchDiscipline MECHANICAL_PROCESS_ENGINEERING = new ResearchDiscipline(3, "Mechanical Process Engineering", ResearchAreaConstants.PROCESS_ENGINEERING);
    public static final ResearchDiscipline BIOLOGICAL_PROCESS_ENGINEERING = new ResearchDiscipline(4, "Biological Process Engineering", ResearchAreaConstants.PROCESS_ENGINEERING);

    // HEAT_ENERGY_TECHNOLOGY
    public static final ResearchDiscipline ENERGY_PROCESS_ENGINEERING = new ResearchDiscipline(1, "Energy Process Engineering", ResearchAreaConstants.HEAT_ENERGY_TECHNOLOGY);
    public static final ResearchDiscipline TECHNICAL_THERMODYNAMICS = new ResearchDiscipline(2, "Technical Thermodynamics", ResearchAreaConstants.HEAT_ENERGY_TECHNOLOGY);
    public static final ResearchDiscipline FLUID_MECHANICS = new ResearchDiscipline(3, "Fluid Mechanics", ResearchAreaConstants.HEAT_ENERGY_TECHNOLOGY);
    public static final ResearchDiscipline HYDRAULIC_AND_TURBO_ENGINES_AND_PISTON_ENGINES = new ResearchDiscipline(4, "Hydraulic and Turbo Engines and Piston Engines", ResearchAreaConstants.HEAT_ENERGY_TECHNOLOGY);

    // MATERIALS_ENGINEERING
    public static final ResearchDiscipline METALLURGICAL_AND_THERMAL_PROCESSES = new ResearchDiscipline(1, "Metallurgical and Thermal Processes, Thermomechanical Treatment of Materials", ResearchAreaConstants.MATERIALS_ENGINEERING);
    public static final ResearchDiscipline SINTERED_METALLIC_AND_CERAMIC_MATERIALS = new ResearchDiscipline(2, "Sintered Metallic and Ceramic Materials", ResearchAreaConstants.MATERIALS_ENGINEERING);
    public static final ResearchDiscipline COMPOSITE_MATERIALS = new ResearchDiscipline(3, "Composite Materials", ResearchAreaConstants.MATERIALS_ENGINEERING);
    public static final ResearchDiscipline MECHANICAL_BEHAVIOUR_OF_CONSTRUCTION_MATERIALS = new ResearchDiscipline(4, "Mechanical Behaviour of Construction Materials", ResearchAreaConstants.MATERIALS_ENGINEERING);
    public static final ResearchDiscipline COATING_AND_SURFACE_TECHNOLOGY = new ResearchDiscipline(5, "Coating and Surface Technology", ResearchAreaConstants.MATERIALS_ENGINEERING);

    // MATERIALS_SCIENCE
    public static final ResearchDiscipline THERMODYNAMICS_AND_KINETICS_OF_MATERIALS = new ResearchDiscipline(1, "Thermodynamics and Kinetics of Materials", ResearchAreaConstants.MATERIALS_SCIENCE);
    public static final ResearchDiscipline SYNTHESIS_AND_PROPERTIES_OF_FUNCTIONAL_MATERIALS = new ResearchDiscipline(2, "Synthesis and Properties of Functional Materials", ResearchAreaConstants.MATERIALS_SCIENCE);
    public static final ResearchDiscipline MICROSTRUCTURAL_MECHANICAL_PROPERTIES_OF_MATERIALS = new ResearchDiscipline(3, "Microstructural Mechanical Properties of Materials", ResearchAreaConstants.MATERIALS_SCIENCE);
    public static final ResearchDiscipline STRUCTURING_AND_FUNCTIONALISATION = new ResearchDiscipline(4, "Structuring and Functionalisation", ResearchAreaConstants.MATERIALS_SCIENCE);
    public static final ResearchDiscipline BIOMATERIALS = new ResearchDiscipline(5, "Biomaterials", ResearchAreaConstants.MATERIALS_SCIENCE);

    // SYSTEMS_ENGINEERING
    public static final ResearchDiscipline AUTOMATION = new ResearchDiscipline(1, "Automation, Control Systems, Robotics, Mechatronics, Cyber Physical Systems", ResearchAreaConstants.SYSTEMS_ENGINEERING);
    public static final ResearchDiscipline MEASUREMENT_SYSTEMS = new ResearchDiscipline(2, "Measurement Systems", ResearchAreaConstants.SYSTEMS_ENGINEERING);
    public static final ResearchDiscipline MICROSYSTEMS = new ResearchDiscipline(3, "Microsystems", ResearchAreaConstants.SYSTEMS_ENGINEERING);
    public static final ResearchDiscipline TRAFFIC_AND_TRANSPORT_SYSTEMS = new ResearchDiscipline(4, "Traffic and Transport Systems, Logistics, Intelligent and Automated Traffic", ResearchAreaConstants.SYSTEMS_ENGINEERING);
    public static final ResearchDiscipline HUMAN_FACTORS = new ResearchDiscipline(5, "Human Factors, Ergonomics, Human-Machine Systems", ResearchAreaConstants.SYSTEMS_ENGINEERING);
    public static final ResearchDiscipline BIOMEDICAL_SYSTEMS_TECHNOLOGY = new ResearchDiscipline(6, "Biomedical Systems Technology", ResearchAreaConstants.SYSTEMS_ENGINEERING);

    // ELECTRICAL_ENGINEERING_AND_INFORMATION_TECHNOLOGY
    public static final ResearchDiscipline ELECTRONIC_SEMICONDUCTORS = new ResearchDiscipline(1, "Electronic Semiconductors, Components, Circuits, Systems", ResearchAreaConstants.ELECTRICAL_ENGINEERING_AND_INFORMATION_TECHNOLOGY);
    public static final ResearchDiscipline COMMUNICATIONS = new ResearchDiscipline(2, "Communications, High-Frequency and Network Technology, Theoretical Electrical Engineering", ResearchAreaConstants.ELECTRICAL_ENGINEERING_AND_INFORMATION_TECHNOLOGY);
    public static final ResearchDiscipline ELECTRICAL_ENERGY_GENERATION = new ResearchDiscipline(3, "Electrical Energy Generation, Distribution, Application", ResearchAreaConstants.ELECTRICAL_ENGINEERING_AND_INFORMATION_TECHNOLOGY);

    // COMPUTER_SCIENCE
    public static final ResearchDiscipline THEORETICAL_COMPUTER_SCIENCE = new ResearchDiscipline(1, "Theoretical Computer Science", ResearchAreaConstants.COMPUTER_SCIENCE);
    public static final ResearchDiscipline SOFTWARE_ENGINEERING_AND_PROGRAMMING_LANGUAGES = new ResearchDiscipline(2, "Software Engineering and Programming Languages", ResearchAreaConstants.COMPUTER_SCIENCE);
    public static final ResearchDiscipline SECURITY_AND_DEPENDABILITY = new ResearchDiscipline(3, "Security and Dependability", ResearchAreaConstants.COMPUTER_SCIENCE);
    public static final ResearchDiscipline OPERATING = new ResearchDiscipline(4, "Operating, Communication, Database and Distributed Systems", ResearchAreaConstants.COMPUTER_SCIENCE);
    public static final ResearchDiscipline INTERACTIVE_AND_INTELLIGENT_SYSTEMS = new ResearchDiscipline(5, "Interactive and Intelligent Systems, Image and Language Processing, Computer Graphics and Visualisation", ResearchAreaConstants.COMPUTER_SCIENCE);
    public static final ResearchDiscipline INFORMATION_SYSTEMS = new ResearchDiscipline(6, "Information Systems, Process and Knowledge Management", ResearchAreaConstants.COMPUTER_SCIENCE);
    public static final ResearchDiscipline COMPUTER_ARCHITECTURE_AND_EMBEDDED_SYSTEMS = new ResearchDiscipline(7, "Computer Architecture and Embedded Systems", ResearchAreaConstants.COMPUTER_SCIENCE);
    public static final ResearchDiscipline MASSIVELY_PARALLEL_AND_DATA_INTENSIVE_SYSTEMS = new ResearchDiscipline(8, "Massively Parallel and Data-Intensive Systems", ResearchAreaConstants.COMPUTER_SCIENCE);

    // CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE
    public static final ResearchDiscipline ARCHITECTURE = new ResearchDiscipline(1, "Architecture, Building and Construction History, Construction Research, Sustainable Building Technology", ResearchAreaConstants.CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE);
    public static final ResearchDiscipline URBANISM = new ResearchDiscipline(2, "Urbanism, Spatial Planning, Transportation and Infrastructure Planning, Landscape Planning", ResearchAreaConstants.CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE);
    public static final ResearchDiscipline CONSTRUCTION_MATERIAL_SCIENCES = new ResearchDiscipline(3, "Construction Material Sciences, Chemistry, Building Physics", ResearchAreaConstants.CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE);
    public static final ResearchDiscipline STRUCTURAL_ENGINEERING = new ResearchDiscipline(4, "Structural Engineering, Building Informatics and Construction Operation", ResearchAreaConstants.CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE);
    public static final ResearchDiscipline APPLIED_MECHANICS = new ResearchDiscipline(5, "Applied Mechanics, Statics and Dynamics", ResearchAreaConstants.CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE);
    public static final ResearchDiscipline GEOTECHNICS = new ResearchDiscipline(6, "Geotechnics, Hydraulic Engineering", ResearchAreaConstants.CONSTRUCTION_ENGINEERING_AND_ARCHITECTURE);

    // Convenience Map
    private static final Map<Integer, Map<Integer, ResearchDiscipline>> RESEARCH_MAP =
        createResearchMap(
            PREHISTORY,
            CLASSICAL_PHILOLOGY,
            ANCIENT_HISTORY,
            CLASSICAL_ARCHAEOLOGY,
            EGYPTOLOGY_AND_ANCIENT_NEAR_EASTERN_STUDIES,
            MEDIEVAL_HISTORY,
            EARLY_MODERN_HISTORY,
            MODERN_AND_CURRENT_HISTORY,
            HISTORY_OF_SCIENCE,
            ART_HISTORY,
            MUSICOLOGY,
            THEATRE_AND_MEDIA_STUDIES,
            GENERAL_AND_COMPARATIVE_LINGUISTICS,
            INDIVIDUAL_LINGUISTICS,
            HISTORICAL_LINGUISTICS,
            APPLIED_LINGUISTICS,
            MEDIEVAL_GERMAN_LITERATURE,
            MODERN_GERMAN_LITERATURE,
            EUROPEAN_AND_AMERICAN_LITERATURE,
            GENERAL_AND_COMPARATIVE_LITERATURE_AND_CULTURAL_STUDIES,
            SOCIAL_AND_CULTURAL_ANTHROPOLOGY_AND_ETHNOLOGY,
            ASIAN_STUDIES,
            AFRICAN,
            ISLAMIC_STUDIES,
            RELIGIOUS_STUDIES_AND_JEWISH_STUDIES,
            PROTESTANT_THEOLOGY,
            ROMAN_CATHOLIC_THEOLOGY,
            HISTORY_OF_PHILOSOPHY,
            THEORETICAL_PHILOSOPHY,
            PRACTICAL_PHILOSOPHY,
            GENERAL_EDUCATION_AND_HISTORY_OF_EDUCATION,
            GENERAL_AND_DOMAIN_SPECIFIC_TEACHING_AND_LEARNING,
            EDUCATION_SYSTEMS_AND_EDUCATIONAL_INSTITUTIONS,
            EDUCATIONAL_RESEARCH_ON_SOCIALIZATION,
            GENERAL,
            DEVELOPMENTAL_AND_EDUCATIONAL_PSYCHOLOGY,
            SOCIAL_PSYCHOLOGY,
            DIFFERENTIAL_PSYCHOLOGY,
            SOCIOLOGICAL_THEORY,
            EMPIRICAL_SOCIAL_RESEARCH,
            COMMUNICATION_SCIENCES,
            POLITICAL_SCIENCE,
            ECONOMIC_THEORY,
            ECONOMIC_POLICY_AND_PUBLIC_FINANCE,
            BUSINESS_ADMINISTRATION,
            STATISTICS_AND_ECONOMETRICS,
            ECONOMIC_AND_SOCIAL_HISTORY,
            PRINCIPLES_OF_LAW_AND_JURISPRUDENCE,
            PRIVATE_LAW,
            PUBLIC_LAW,
            CRIMINAL_LAW_AND_LAW_OF_CRIMINAL_PROCEDURE,
            CRIMINOLOGY,
            BIOCHEMISTRY,
            BIOPHYSICS,
            CELL_BIOLOGY,
            STRUCTURAL_BIOLOGY,
            GENERAL_GENETICS,
            DEVELOPMENTAL_BIOLOGY,
            BIOINFORMATICS_AND_THEORETICAL_BIOLOGY,
            ANATOMY,
            EVOLUTION_AND_SYSTEMATICS_OF_PLANTS_AND_FUNGI,
            PLANT_ECOLOGY_AND_ECOSYSTEM_ANALYSIS,
            INTER_ORGANISMIC_INTERACTIONS_AND_CHEMICAL_ECOLOGY_OF_PLANT_SYSTEMS,
            PLANT_PHYSIOLOGY,
            PLANT_BIOCHEMISTRY_AND_BIOPHYSICS,
            PLANT_CELL_AND_DEVELOPMENTAL_BIOLOGY,
            PLANT_GENETICS,
            SPECIAL_ZOOLOGY_AND_MORPHOLOGY,
            EVOLUTION,
            ANIMAL_ECOLOGY,
            SENSORY_AND_BEHAVIOURAL_BIOLOGY,
            BIOCHEMISTRY_AND_ANIMAL_PHYSIOLOGY,
            EVOLUTIONARY_CELL_AND_DEVELOPMENTAL_BIOLOGY_ZOOLOGY_,
            METABOLISM,
            MICROBIAL_ECOLOGY_AND_APPLIED_MICROBIOLOGY,
            MEDICAL_MICROBIOLOGY,
            VIROLOGY,
            IMMUNOLOGY,
            EPIDEMIOLOGY,
            PUBLIC_HEALTH,
            HUMAN_GENETICS,
            PHYSIOLOGY,
            NUTRITIONAL_SCIENCES,
            PATHOLOGY,
            CLINICAL_CHEMISTRY_AND_PATHOBIOCHEMISTRY,
            PHARMACY,
            PHARMACOLOGY,
            TOXICOLOGY,
            ANAESTHESIOLOGY,
            CARDIOLOGY,
            PNEUMOLOGY,
            HEMATOLOGY,
            GASTROENTEROLOGY,
            NEPHROLOGY,
            ENDOCRINOLOGY,
            RHEUMATOLOGY,
            DERMATOLOGY,
            PEDIATRIC_AND_ADOLESCENT_MEDICINE,
            GYNAECOLOGY_AND_OBSTETRICS,
            REPRODUCTIVE_MEDICINE_BIOLOGY,
            UROLOGY,
            BIOGERONTOLOGY_AND_GERIATRIC_MEDICINE,
            GENERAL_AND_VISCERAL_SURGERY,
            CARDIOTHORACIC_AND_VASCULAR_SURGERY,
            TRAUMATOLOGY_AND_ORTHOPAEDICS,
            DENTISTRY,
            OTOLARYNGOLOGY,
            RADIOLOGY_AND_NUCLEAR_MEDICINE,
            RADIATION_ONCOLOGY_AND_RADIOBIOLOGY,
            BIOMEDICAL_TECHNOLOGY_AND_MEDICAL_PHYSICS,
            MOLECULAR_NEUROSCIENCE_AND_NEUROGENETICS,
            CELLULAR_NEUROSCIENCE,
            DEVELOPMENTAL_NEUROBIOLOGY,
            SYSTEMIC_NEUROSCIENCE,
            ORGANISMIC_NEUROBIOLOGY,
            COGNITIVE_NEUROSCIENCE,
            MOLECULAR_AND_CELLULAR_NEUROLOGY,
            CLINICAL_NEUROSCIENCES_I,
            BIOLOGICAL_AND_MOLECULAR_PSYCHIATRY,
            CLINICAL_NEUROSCIENCES_II,
            CLINICAL_NEUROSCIENCES_III,
            SOIL_SCIENCES,
            PLANT_CULTIVATION_AND_AGRICULTURAL_TECHNOLOGY,
            PLANT_NUTRITION,
            ECOLOGY_OF_AGRICULTURAL_LANDSCAPES,
            PLANT_BREEDING,
            PHYTOMEDICINE,
            AGRICULTURAL_ECONOMICS_AND_SOCIOLOGY,
            FORESTRY,
            ANIMAL_HUSBANDRY,
            ANIMAL_NUTRITION_AND_NUTRITION_PHYSIOLOGY,
            BASIC_VETERINARY_MEDICAL_SCIENCE,
            BASIC_RESEARCH_ON_PATHOGENESIS,
            INORGANIC_MOLECULAR_CHEMISTRY,
            ORGANIC_MOLECULAR_CHEMISTRY,
            SOLID_STATE_AND_SURFACE_CHEMISTRY,
            PHYSICAL_CHEMISTRY_OF_SOLIDS_AND_SURFACES,
            THEORY_AND_MODELLING,
            PHYSICAL_CHEMISTRY_OF_MOLECULES,
            GENERAL_THEORETICAL_CHEMISTRY,
            ANALYTICAL_CHEMISTRY,
            BIOLOGICAL_AND_BIOMIMETIC_CHEMISTRY,
            FOOD_CHEMISTRY,
            PREPARATORY_AND_PHYSICAL_CHEMISTRY_OF_POLYMERS,
            EXPERIMENTAL_AND_THEORETICAL_PHYSICS_OF_POLYMERS,
            POLYMER_MATERIALS,
            EXPERIMENTAL_CONDENSED_MATTER_PHYSICS,
            THEORETICAL_CONDENSED_MATTER_PHYSICS,
            OPTICS,
            PARTICLES,
            STATISTICAL_PHYSICS,
            ASTROPHYSICS_AND_ASTRONOMY,
            MATHEMATICS,
            ATMOSPHERIC_SCIENCE,
            OCEANOGRAPHY,
            GEOLOGY_AND_PALAEONTOLOGY,
            GEOPHYSICS,
            GEODESY,
            GEOCHEMISTRY,
            PHYSICAL_GEOGRAPHY,
            HUMAN_GEOGRAPHY,
            HYDROGEOLOGY,
            METAL_CUTTING_MANUFACTURING_ENGINEERING,
            PRIMARY_SHAPING_AND_RESHAPING_TECHNOLOGY,
            JOINING,
            PLASTICS_ENGINEERING,
            PRODUCTION_MANAGEMENT_AND_OPERATIONS_MANAGEMENT,
            MACHINE_TOOLS_AND_PRODUCTION_AUTOMATION,
            ENGINEERING_DESIGN,
            MECHANICS,
            LIGHTWEIGHT_CONSTRUCTION,
            ACOUSTICS,
            CHEMICAL_AND_THERMAL_PROCESS_ENGINEERING,
            TECHNICAL_CHEMISTRY,
            MECHANICAL_PROCESS_ENGINEERING,
            BIOLOGICAL_PROCESS_ENGINEERING,
            ENERGY_PROCESS_ENGINEERING,
            TECHNICAL_THERMODYNAMICS,
            FLUID_MECHANICS,
            HYDRAULIC_AND_TURBO_ENGINES_AND_PISTON_ENGINES,
            METALLURGICAL_AND_THERMAL_PROCESSES,
            SINTERED_METALLIC_AND_CERAMIC_MATERIALS,
            COMPOSITE_MATERIALS,
            MECHANICAL_BEHAVIOUR_OF_CONSTRUCTION_MATERIALS,
            COATING_AND_SURFACE_TECHNOLOGY,
            THERMODYNAMICS_AND_KINETICS_OF_MATERIALS,
            SYNTHESIS_AND_PROPERTIES_OF_FUNCTIONAL_MATERIALS,
            MICROSTRUCTURAL_MECHANICAL_PROPERTIES_OF_MATERIALS,
            STRUCTURING_AND_FUNCTIONALISATION,
            BIOMATERIALS,
            AUTOMATION,
            MEASUREMENT_SYSTEMS,
            MICROSYSTEMS,
            TRAFFIC_AND_TRANSPORT_SYSTEMS,
            HUMAN_FACTORS,
            BIOMEDICAL_SYSTEMS_TECHNOLOGY,
            ELECTRONIC_SEMICONDUCTORS,
            COMMUNICATIONS,
            ELECTRICAL_ENERGY_GENERATION,
            THEORETICAL_COMPUTER_SCIENCE,
            SOFTWARE_ENGINEERING_AND_PROGRAMMING_LANGUAGES,
            SECURITY_AND_DEPENDABILITY,
            OPERATING,
            INTERACTIVE_AND_INTELLIGENT_SYSTEMS,
            INFORMATION_SYSTEMS,
            COMPUTER_ARCHITECTURE_AND_EMBEDDED_SYSTEMS,
            MASSIVELY_PARALLEL_AND_DATA_INTENSIVE_SYSTEMS,
            ARCHITECTURE,
            URBANISM,
            CONSTRUCTION_MATERIAL_SCIENCES,
            STRUCTURAL_ENGINEERING,
            APPLIED_MECHANICS,
            GEOTECHNICS
        );


    /**
     * This function returns a {@linkplain ResearchDiscipline} by parsing a string
     * which contains the area RNBR and the internal RNBR of the discipline.
     *
     * @param rnbrString a string of the format AREA_RNBR-DISCIPLINE_RNBR
     *
     * @return a discipline that has a matching RNBR
     */
    public static ResearchDiscipline getByRnbrString(String rnbrString)
    {
        String[] splitRnbr = rnbrString.split("-");
        int areaRnbr = Integer.parseInt(splitRnbr[0]);
        int disciplineRnbr = Integer.parseInt(splitRnbr[1]);

        Map<Integer, ResearchDiscipline> subClasses = RESEARCH_MAP.get(areaRnbr);

        return (subClasses != null) ? subClasses.get(disciplineRnbr) : null;
    }


    /**
     * A convenience function for initializing the research map.
     *
     * @param disciplines a list of disciplines that are to be added to the map
     *
     * @return a hashmap that maps area RNBRs to hashmaps of discipline RNBRs and disciplines
     */
    private static Map<Integer, Map<Integer, ResearchDiscipline>> createResearchMap(ResearchDiscipline ...disciplines)
    {
        final Map<Integer, Map<Integer, ResearchDiscipline>> map = new HashMap<>();

        for (ResearchDiscipline rd : disciplines) {
            int categoryRnbr = rd.getArea().getRbnr();
            map.putIfAbsent(categoryRnbr, new HashMap<>());
            map.get(categoryRnbr).put(rd.getRbnr(), rd);
        }

        return map;
    }

    /**
     * Private constructor, because this is a static class.
     */
    private ResearchDisciplineConstants()
    {

    }
}