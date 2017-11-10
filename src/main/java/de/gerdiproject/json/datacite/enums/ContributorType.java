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
package de.gerdiproject.json.datacite.enums;

import de.gerdiproject.json.datacite.Contributor;

/**
 * This enumeration describes the role of a {@link Contributor}.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Robin Weiss
 */
public enum ContributorType {
    /**
     * Person with knowledge of how to access, troubleshoot, or otherwise field issues related to the resource.
     */
    ContactPerson,

    /**
     * Person/institution responsible for finding, gathering/collecting data under the guidelines of the author(s) or Principal Investigator (PI).
     */
    DataCollector,

    /**
     * Person tasked with reviewing, enhancing, cleaning, or standardizing metadata and the associated data submitted for storage, use, and maintenance within a data centre or repository.
     */
    DataCurator,

    /**
     * Person (or organisation with a staff of data managers, such as a data centre) responsible for maintaining the finished resource.
     */
    DataManager,

    /**
     * Institution tasked with responsibility to generate/disseminate copies of the resource in either electronic or print form.
     */
    Distributor,

    /**
     * A person who oversees the details related to the publication format of the resource.
     */
    Editor,

    /**
     * Typically, the organisation allowing the resource to be available on the internet through the provision of its hardware/software/operating support.
     */
    HostingInstitution,

    /**
     * Typically a person or organisation responsible for the artistry and form of a media product.
     */
    Producer,

    /**
     * Person officially designated as head of project team or sub‐project team instrumental in the work necessary to development of the resource.
     */
    ProjectLeader,

    /**
     * Person officially designated as manager of a project. Project may consist of one or many project teams and sub‐teams.
     */
    ProjectManager,

    /**
     * Person on the membership list of a designated project/project team.
     */
    ProjectMember,

    /**
     * Institution/organisation officially appointed by a Registration Authority to handle specific tasks within a defined area of responsibility.
     */
    RegistrationAgency,

    /**
     * A standards‐setting body from which Registration Agencies obtain official recognition and guidance.
     */
    RegistrationAuthority,

    /**
     * A person without a specifically defined role in the development of the resource, but who is someone the author wishes to recognize.
     */
    RelatedPerson,

    /**
     * A person involved in analyzing data or the results of an experiment or formal study. May indicate an intern or assistant to one of the authors who helped with research but who was not so “key” as to be listed as an author.
     */
    Researcher,

    /**
     * Typically refers to a group of individuals with a lab, department, or division; the group has a particular, defined focus of activity.
     */
    ResearchGroup,

    /**
     * Person or institution owning or managing property rights, including intellectual property rights over the resource.
     */
    RightsHolder,

    /**
     * Person or organisation that issued a contract or under the auspices of which a work has been written, printed, published, developed, etc.
     */
    Sponsor,

    /**
     * Designated administrator over one or more groups/teams working to produce a resource or over one or more steps of a development process.
     */
    Supervisor,

    /**
     * A Work Package is a recognized data product, not all of which is included in publication. The package, instead, may include notes, discarded documents, etc.
     * The Work Package Leader is responsible for ensuring the comprehensive contents, versioning, and availability of the Work Package during the development of the resource.
     */
    WorkPackageLeader,

    /**
     * Any person or institution making a significant contribution to the development and/or maintenance of the resource, but whose contribution does not “fit” other controlled vocabulary for contributorType.
     */
    Other
}
