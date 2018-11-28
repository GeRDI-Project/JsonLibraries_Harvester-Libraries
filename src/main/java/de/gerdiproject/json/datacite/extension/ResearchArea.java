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
package de.gerdiproject.json.datacite.extension;

import de.gerdiproject.json.datacite.constants.DataCiteResearchConstants;
import de.gerdiproject.json.datacite.extension.abstr.AbstractResearch;
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
    private final String categoryName;
    private final String areaName;


    /**
     * Simple constructor that requires the RNBR, the human readable name and the
     * category of the research area.
     *
     * @param rbnr a number that serves as a unique key of the research area
     * @param name an english human readable name of the research area
     * @param category a human readable name of the general category
     */
    public ResearchArea(int rbnr, String name, String category)
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
