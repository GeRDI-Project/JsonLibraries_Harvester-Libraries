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

import de.gerdiproject.json.datacite.abstr.AbstractPerson;
import de.gerdiproject.json.datacite.enums.ContributorType;
import de.gerdiproject.json.datacite.nested.PersonName;

/**
 * The institution or person responsible for collecting, managing, distributing,
 * or otherwise contributing to the development of the resource.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Contributor extends AbstractPerson
{
    /**
     * The full name of the contributor.
     * <br>e.g. Patel, Emily; Nyugen, John, ABC Foundation
     */
    private PersonName contributorName;

    /**
     * The role of the contributor.
     */
    private ContributorType contributorType;


    /**
     * Constructor that creates a {@linkplain PersonName} out of a String.
     *
     * @param name the name of the contributor
     * @param type the role of the contributor
     */
    public Contributor(String name, ContributorType type)
    {
        super(name);
        this.contributorType = type;
    }


    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param nameObject the name and name type of the person
     * @param type the role of the contributor
     */
    public Contributor(PersonName nameObject, ContributorType type)
    {
        super(nameObject);
        this.contributorType = type;
    }


    /**
     * Returns the type of contributor of the resource.
     *
     * @return the type of contributor
     */
    public ContributorType getType()
    {
        return contributorType;
    }


    /**
     * Changes the type of contributor of the resource.
     *
     * @param type the type of contributor
     */
    public void setType(ContributorType type)
    {
        this.contributorType = type;
    }


    @Override
    public PersonName getName()
    {
        return contributorName;
    }


    @Override
    public void setName(PersonName name)
    {
        this.contributorName = name;
    }
}
