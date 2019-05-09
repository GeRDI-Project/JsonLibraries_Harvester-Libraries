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
package de.gerdiproject.generator.research.source.json;

import java.util.List;

import lombok.Data;

/**
 * A class that models specific subject areas of the DFG vocabulary.
 *
 * @author Fidan Limani
 */
@Data
public class ResearchAreaSource
{
    private String name;
    private String rbnr;
    private List<ResearchDisciplineSource> subclasses;


    /**
     * Returns the RBNR as an integer value.
     *
     * @return the RBNR as an integer value
     */
    public int getRbnrAsInt()
    {
        return Integer.parseInt(rbnr);
    }


    @Override
    public String toString()
    {
        return "Subject area: " + name + "; # of subject disciplines: " + subclasses.size();
    }
}