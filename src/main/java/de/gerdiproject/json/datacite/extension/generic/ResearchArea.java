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
package de.gerdiproject.json.datacite.extension.generic;

import de.gerdiproject.json.datacite.constants.DataCiteResearchConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class defines a JSON object that represents the general area to which a
 * {@linkplain ResearchDiscipline} belongs.
 *
 * @author Fidan Limani, Robin Weiss
 */
@Data @EqualsAndHashCode(callSuper = true)
public class ResearchArea extends AbstractResearch
{
    /**
     * -- GETTER --
     * Retrieves the name of the category to which the research are belongs.
     * @return the name of the category to which the research are belongs
     *
     * -- SETTER --
     * Sets the name of the category to which the research are belongs.
     * @param value the name of the category to which the research are belongs
     */
    private final String categoryName;


    /**
     * -- GETTER --
     * Retrieves the name of the research area.
     * @return the name of the research area
     *
     * -- SETTER --
     * Sets the name of the research area.
     * @param value the name of the research area
     */
    private final String areaName;


    /**
     * Simple constructor that requires the RNBR, the human readable name and the
     * category of the research area.
     *
     * @param rbnr a number that serves as a unique key of the research area
     * @param name an english human readable name of the research area
     * @param category a human readable name of the general category
     */
    public ResearchArea(final int rbnr, final String name, final String category)
    {
        super(rbnr);
        this.categoryName = category;
        this.areaName = name;
    }


    @Override
    public String getDisciplineName()
    {
        return null;
    }


    @Override
    public String getRnbrAsString()
    {
        return String.format(DataCiteResearchConstants.AREA_RNBR_FORMAT, getRbnr());
    }
}
