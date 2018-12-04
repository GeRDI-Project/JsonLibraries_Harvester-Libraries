/*
 *  Copyright © 2018 Robin Weiss (http://www.gerdi-project.de/)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
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
package de.gerdiproject.json.datacite.extension.generic;

import java.util.Collection;
import java.util.Set;

import de.gerdiproject.harvest.utils.CollectionUtils;
import de.gerdiproject.json.datacite.extension.IDataCiteExtension;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * This DataCite extension offers metadata that is relevant to all
 * data providers.
 *
 * @author Robin Weiss
 */
@Data
public class GerdiDataCiteExtension implements IDataCiteExtension
{
    /**
     * A unique but human readable name of the repository. <br>
     * e.g. Sea Around Us, FAOSTAT
     */
    private String repositoryIdentifier;

    /**
     * Links to the data provider's website.
     */
    @Setter(AccessLevel.NONE)
    private Set<WebLink> webLinks;

    /**
     * Downloadable source data files.
     */
    @Setter(AccessLevel.NONE)
    private Set<ResearchData> researchDataList;

    /**
     * A set of human readable names of the research disciplines, meaning the
     * topics or domains that this document covers. <br>
     * e.g. Computer Science, Geography
     */
    @Setter(AccessLevel.NONE)
    private Set<AbstractResearch> researchDisciplines;


    @Override
    public String getKey()
    {
        return "gerdi";
    }


    /**
     * Changes the set of human readable names of the research disciplines, meaning
     * the topics or domains that this document covers.
     *
     * @param researchDisciplines a collection of human readable names of the research
     *            disciplines
     */
    public void addResearchDisciplines(Collection<AbstractResearch> researchDisciplines)
    {
        this.researchDisciplines = CollectionUtils.addToSet(this.researchDisciplines, researchDisciplines);
    }


    /**
     * Changes the links to the data provider's website.
     *
     * @param webLinks links to the data provider's website
     */
    public void addWebLinks(Collection<WebLink> webLinks)
    {
        this.webLinks = CollectionUtils.addToSet(this.webLinks, webLinks);
    }


    /**
     * Changes the downloadable source data files.
     *
     * @param researchDataList downloadable files
     */
    public void addResearchData(Collection<ResearchData> researchDataList)
    {
        this.researchDataList = CollectionUtils.addToSet(this.researchDataList, researchDataList);
    }

}
