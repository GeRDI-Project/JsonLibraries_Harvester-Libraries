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
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class FundingReference
{
    /**
     * Human readable name of the funder.
     */
    private String funderName;

    /**
     * Indentifier of the funder.
     */
    private String funderIdentifier;

    /**
     * To object mapping? award.number would break with DataCite, but looks a bit nicer.
     */
    private String awardNumber;

    /**
     * Optional link to award.
     */
    private String awardURI;

    /**
     * Human readable version of the award.
     */
    private String awardTitle;

    /**
     * The type of the funder identifier.
     */
    private FunderIdType funderIdentifierType;


    /**
     * This enumeration describes the type of the funder identifier.
     * @author Robin Weiss
     *
     */
    public enum FunderIdType {
        ISNI,
        GRID,
        Crossref_Funder_ID,
        Other
    }


    public String getFunderName()
    {
        return funderName;
    }


    public void setFunderName(String funderName)
    {
        this.funderName = funderName;
    }


    public String getFunderIdentifier()
    {
        return funderIdentifier;
    }


    public void setFunderIdentifier(String funderIdentifier)
    {
        this.funderIdentifier = funderIdentifier;
    }


    public String getAwardNumber()
    {
        return awardNumber;
    }


    public void setAwardNumber(String awardNumber)
    {
        this.awardNumber = awardNumber;
    }


    public String getAwardURI()
    {
        return awardURI;
    }


    public void setAwardURI(String awardURI)
    {
        this.awardURI = awardURI;
    }


    public String getAwardTitle()
    {
        return awardTitle;
    }


    public void setAwardTitle(String awardTitle)
    {
        this.awardTitle = awardTitle;
    }


    public FunderIdType getFunderIdentifierType()
    {
        return funderIdentifierType;
    }


    public void setFunderIdentifierType(FunderIdType funderIdentifierType)
    {
        this.funderIdentifierType = funderIdentifierType;
    }



}
