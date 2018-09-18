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

/**
 * This class defines JSON objects that represent precise research disciplines.
 *
 * @author Fidan Limani, Robin Weiss
 */
public class ResearchDiscipline extends AbstractResearch
{
    private final ResearchArea area;
    private final String name;


    /**
     * Constructor that requires the RNBR, name, and are of the discipline.
     *
     * @param rbnr a number that serves as a unique key of a discipline within an area
     * @param name a human readable name of the discipline
     * @param area to which the discipline belongs
     */
    public ResearchDiscipline(int rbnr, String name, ResearchArea area)
    {
        super(rbnr);
        this.name = name;
        this.area = area;
    }


    /**
     * Returns the research area to which the discipline belongs.
     *
     * @return the area to which the discipline belongs
     */
    public ResearchArea getArea()
    {
        return area;
    }


    @Override
    public String getDisciplineName()
    {
        return name;
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


    @Override
    public String toString()
    {
        return String.format(DataCiteResearchConstants.DISCIPLINE_NAME_FORMAT, area.getRbnr(), rbnr, area, getAreaName(), getCategoryName());
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((area == null) ? 0 : area.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj))
            return false;

        if (!(obj instanceof ResearchDiscipline))
            return false;

        ResearchDiscipline other = (ResearchDiscipline) obj;

        if (area == null) {
            if (other.area != null)
                return false;
        } else if (!area.equals(other.area))
            return false;

        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        return true;
    }
}
