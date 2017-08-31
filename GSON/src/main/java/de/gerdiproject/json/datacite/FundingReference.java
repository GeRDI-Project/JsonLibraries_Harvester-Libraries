/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
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
package de.gerdiproject.json.datacite;

/**
 * Information about financial support (funding) for the resource being registered.
 * This is not indexed!
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
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
    private String funderIdentifier;

    /**
     * The type of the funder identifier.
     */
    private FunderIdentifierType funderIdentifierType;

    /**
     * The code assigned by the funder to a sponsored award (grant).
     * <br>e.g. GBMF3859.01
     */
    private String awardNumber;

    /**
     * The URI leading to a page provided by the funder for more
     * information about the award (grant).
     * <br>e.g. https://www.moore.org/grants/list/GBMF3859.01
     */
    private String awardURI;

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
    public String getFunderIdentifier()
    {
        return funderIdentifier;
    }


    /**
     * Changes the unique identifier of the funding entity.
     * <br>e.g. http://dx.doi.org/10.13039/100000936
     *
     * @param funderIdentifier a unique identifier of the funding entity
     */
    public void setFunderIdentifier(String funderIdentifier)
    {
        this.funderIdentifier = funderIdentifier;
    }


    /**
     * Returns the code assigned by the funder to a sponsored award (grant).
     *
     * @return the code assigned by the funder to a sponsored award (grant)
     */
    public String getAwardNumber()
    {
        return awardNumber;
    }


    /**
     * Changes the code assigned by the funder to a sponsored award (grant).
     * <br>e.g. GBMF3859.01
     *
     * @param awardNumber the code assigned by the funder to a sponsored award (grant)
     */
    public void setAwardNumber(String awardNumber)
    {
        this.awardNumber = awardNumber;
    }


    /**
     * Returns the URI leading to a page provided by the funder for more
     * information about the award (grant).
     *
     * @return the URI leading to a page for more information about the award
     */
    public String getAwardURI()
    {
        return awardURI;
    }


    /**
     * Changes the URI leading to a page provided by the funder for more
     * information about the award (grant).
     * <br>e.g. https://www.moore.org/grants/list/GBMF3859.01
     *
     * @param awardURI the URI leading to a page for more information about the award
     */
    public void setAwardURI(String awardURI)
    {
        this.awardURI = awardURI;
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


    /**
     * Returns the type of the funder identifier.
     *
     * @return the type of the funder identifier
     */
    public FunderIdentifierType getFunderIdentifierType()
    {
        return funderIdentifierType;
    }


    /**
     * Changes the type of the funder identifier.
     *
     * @param funderIdentifierType the type of the funder identifier
     */
    public void setFunderIdentifierType(FunderIdentifierType funderIdentifierType)
    {
        this.funderIdentifierType = funderIdentifierType;
    }


    /**
     * This enumeration describes the type of the funder identifier.
     *
     * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
     * @author Robin Weiss
     */
    public enum FunderIdentifierType {
        /**
         * International Standard Name Identifier;
         * the globally recognized and adopted international standard approved by ISO for the unique identification of the public identities of persons and organizations across all fields of creative activity.
         * see http://www.isni.org/
         */
        ISNI,

        /**
         * Global Research Identifier Database ID;
         * GRID is comprised of a worldwide collection of institutes associated with academic research. The institutes contained are distinguished by a unique identifier, the GRID ID.
         * see https://www.grid.ac/
         */
        GRID,

        /**
         * Crossref is a not-for-profit membership organization for scholarly publishing working to make content easy to find, cite, link, and assess.
         * Funder IDs can be found in the Crossref Funder Registry.
         * see https://www.crossref.org/services/funder-registry/
         */
        Crossref_Funder_ID,

        /**
         * An identifier type that does not fit into any other category.
         */
        Other
    }
}
