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
 * This class defines JSON objects that represent precise research disciplines.
 *
 * @author Fidan Limani, Robin Weiss
 */
@Data @EqualsAndHashCode(callSuper = true)
public class ResearchDiscipline extends AbstractResearch
{
    private final ResearchArea area;
    private final String disciplineName;


    /**
     * Constructor that requires the RNBR, name, and are of the discipline.
     *
     * @param rbnr a number that serves as a unique key of a discipline within an area
     * @param disciplineName a human readable name of the discipline
     * @param area to which the discipline belongs
     */
    public ResearchDiscipline(final int rbnr, final String disciplineName, final ResearchArea area)
    {
        super(rbnr);
        this.disciplineName = disciplineName;
        this.area = area;
    }


    @Override
    public String getAreaName()
    {
        return area.getAreaName();
    }


    @Override
    public String getCategoryName()
    {
        return area.getCategoryName();
    }


    @Override
    public String getRnbrAsString()
    {
        return String.format(
                   DataCiteResearchConstants.DISCIPLINE_RNBR_FORMAT,
                   area.getRbnr(),
                   getRbnr());
    }
}
