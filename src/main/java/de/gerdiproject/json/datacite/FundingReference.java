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
package de.gerdiproject.json.datacite;

import de.gerdiproject.json.datacite.nested.AwardNumber;
import de.gerdiproject.json.datacite.nested.FunderIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Information about financial support (funding) for the resource being registered.
 * <br>This is not indexed!
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class FundingReference
{
    /**
     * -- GETTER --
     * Retrieves the name of the funding provider.
     * <br>e.g. Gordon and Betty Moore Foundation
     * @return the name of the funding provider
     *
     * -- SETTER --
     * Sets the name of the funding provider.
     * <br>e.g. Gordon and Betty Moore Foundation
     * @param funderName the name of the funding provider
     */
    private final String funderName;


    /**
     * -- GETTER --
     * Retrieves the unique identifier of the funding entity.
     * <br>e.g. http://dx.doi.org/10.13039/100000936
     * @return the unique identifier of the funding entity.
     *
     * -- SETTER --
     * Sets the unique identifier of the funding entity.
     * <br>e.g. http://dx.doi.org/10.13039/100000936
     * @param funderIdentifier a unique identifier of the funding entity.
     */
    private FunderIdentifier funderIdentifier;


    /**
     * -- GETTER --
     * Retrieves the code assigned by the funder to a sponsored award (grant).
     * <br>e.g. GBMF3859.01
     * @return the code assigned by the funder to a sponsored award
     *
     * -- SETTER --
     * Sets the code assigned by the funder to a sponsored award (grant).
     * <br>e.g. GBMF3859.01
     * @param awardNumber the code assigned by the funder to a sponsored award
     */
    private AwardNumber awardNumber;


    /**
     * -- GETTER --
     * Retrieves the human readable title of the award (grant).
     * <br>e.g. Socioenvironmental Monitoring of the Amazon Basin and Xingu
     * @return the human readable title of the award
     *
     * -- SETTER --
     * Sets the human readable title of the award (grant).
     * <br>e.g. Socioenvironmental Monitoring of the Amazon Basin and Xingu
     * @param awardTitle a human readable title of the award
     */
    private String awardTitle;
}
