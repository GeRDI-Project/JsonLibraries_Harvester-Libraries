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

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * This class supports GeRDI researchDiscipline metadata element assignment.
 *
 * @author Fidan Limani
 */
@Data
public class ResearchDisciplineSource
{
    private String name;

    @SerializedName("RBNR")
    private String rbnr;


    /**
     * Retrieves the RBNR as an integer value.
     *
     * @return the RBNR as an integer
     */
    public int getRbnrAsInt()
    {
        return Integer.parseInt(rbnr.split("-")[1]);
    }


    @Override
    public String toString()
    {
        return "Subject discipline: " + getName() + "; RBNR: " + getRbnr();
    }
}