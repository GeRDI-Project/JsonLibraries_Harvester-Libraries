/**
 * Copyright © 2017 Robin Weiss (http://www.gerdi-project.de)
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
package de.gerdiproject.generator.research.source.json;

import java.util.List;

/**
 * This class models the 4 general DFG vocabulary categories.
 *
 * @author Fidan Limani
 */
public class ResearchCategorySource
{
    private String name;
    private List<ResearchAreaSource> subclasses;


    public String getName()
    {
        return name;
    }


    public List<ResearchAreaSource> getSubclasses()
    {
        return subclasses;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public void setSubclasses(List<ResearchAreaSource> subclasses)
    {
        this.subclasses = subclasses;
    }


    @Override
    public String toString()
    {
        return "Subject category: " + name + "; # of subject areas: " + subclasses.size();
    }
}