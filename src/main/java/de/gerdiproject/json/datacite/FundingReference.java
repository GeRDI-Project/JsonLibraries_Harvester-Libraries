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

/**
 * Information about financial support (funding) for the resource being registered.
 * <br>This is not indexed!
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class FundingReference
{
    /**
     * Name of the funding provider.
     * <br>e.g. Gordon and Betty Moore Foundation
     */
    private String funderName;

    /**
     * Uniquely identifies a funding entity, according to various types.
     * <br>e.g. http://dx.doi.org/10.13039/100000936
     */
    private FunderIdentifier funderIdentifier;

    /**
     * The code assigned by the funder to a sponsored award (grant).
     * <br>e.g. GBMF3859.01
     */
    private AwardNumber awardNumber;

    /**
     * The human readable title of the award (grant).
     * <br>e.g. Socioenvironmental Monitoring of the Amazon Basin and Xingu
     */
    private String awardTitle;


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param funderName name of the funding provider
     */
    public FundingReference(String funderName)
    {
        this.funderName = funderName;
    }


    /**
     * Returns the name of the funding provider.
     *
     * @return the name of the funding provider
     */
    public String getFunderName()
    {
        return funderName;
    }


    /**
     * Changes the name of the funding provider.
     * <br>e.g. Gordon and Betty Moore Foundation
     *
     * @param funderName the name of the funding provider
     */
    public void setFunderName(String funderName)
    {
        this.funderName = funderName;
    }


    /**
     * Returns a unique identifier of the funding entity.
     *
     * @return a unique identifier of the funding entity
     */
    public FunderIdentifier getFunderIdentifier()
    {
        return funderIdentifier;
    }


    /**
     * Changes the unique identifier of the funding entity.
     * <br>e.g. http://dx.doi.org/10.13039/100000936
     *
     * @param funderIdentifier a unique identifier of the funding entity
     */
    public void setFunderIdentifier(FunderIdentifier funderIdentifier)
    {
        this.funderIdentifier = funderIdentifier;
    }


    /**
     * Returns the code assigned by the funder to a sponsored award (grant).
     *
     * @return the code assigned by the funder to a sponsored award (grant)
     */
    public AwardNumber getAwardNumber()
    {
        return awardNumber;
    }


    /**
     * Changes the code assigned by the funder to a sponsored award (grant).
     * <br>e.g. GBMF3859.01
     *
     * @param awardNumber the code assigned by the funder to a sponsored award (grant)
     */
    public void setAwardNumber(AwardNumber awardNumber)
    {
        this.awardNumber = awardNumber;
    }


    /**
     * Returns the human readable title of the award (grant).
     *
     * @return the human readable title of the award (grant)
     */
    public String getAwardTitle()
    {
        return awardTitle;
    }


    /**
     * Changes the human readable title of the award (grant).
     * <br>e.g. Socioenvironmental Monitoring of the Amazon Basin and Xingu
     *
     * @param awardTitle the human readable title of the award (grant)
     */
    public void setAwardTitle(String awardTitle)
    {
        this.awardTitle = awardTitle;
    }
}
