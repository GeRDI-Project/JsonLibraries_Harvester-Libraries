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
 * This JSON object defines a person or institution who has worked on the data.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Contributor extends Creator
{
    /**
     * The role of the contributor.
     */
    public ContributorType type;


    /**
     * Simple constructor that requires all mandatory fields.
     * @param name the name of the contributor
     * @param type the role of the contributor
     */
    public Contributor(String name, ContributorType type)
    {
        super(name);
        this.type = type;
    }

    /**
     * This enumeration describes the role of a {@link Contributor}.
     * @author Robin Weiss
     *
     */
    public enum ContributorType {
        ContactPerson,
        DataCollector,
        DataCurator,
        DataManager,
        Distributor,
        Editor,
        HostingInstitution,
        Other,
        Producer,
        ProjectLeader,
        ProjectManager,
        ProjectMember,
        RegistrationAgency,
        RegistrationAuthority,
        RelatedPerson,
        ResearchGroup,
        RightsHolder,
        Researcher,
        Sponsor,
        Supervisor,
        WorkPackageLeader
    }
}
