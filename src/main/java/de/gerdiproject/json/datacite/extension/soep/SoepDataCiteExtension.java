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
package de.gerdiproject.json.datacite.extension.soep;

import java.util.Collection;
import java.util.Set;

import de.gerdiproject.harvest.utils.CollectionUtils;
import de.gerdiproject.json.datacite.extension.IDataCiteExtension;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a JSON object that contains SOEP
 * specific metadata. It serves as a community specific extension
 * of the GeRDI metadata schema.
 *
 * @author Fidan Limani, Robin Weiss
 */
@Data @NoArgsConstructor
public class SoepDataCiteExtension implements IDataCiteExtension
{
    public static final String KEY = "soep";

    /**
     * This is where the discipline-specific metadata is specified in the document.
     *
     * Research community: SOEP
     * A set of variables associated with a resource from a social science study.
     */
    @Setter(AccessLevel.NONE)
    private Set<SoepVariable> datasetVariables = null;


    @Override
    public String getKey()
    {
        return KEY;
    }


    /**
     * Changes the SOEP dataset variables.
     *
     * @param soepDatasetVariables the soep variables that are to be set
     *
     */
    public void addSoepDatasetVariables(Collection<SoepVariable> soepDatasetVariables)
    {
        this.datasetVariables = CollectionUtils.addToSet(this.datasetVariables, soepDatasetVariables);
    }
}