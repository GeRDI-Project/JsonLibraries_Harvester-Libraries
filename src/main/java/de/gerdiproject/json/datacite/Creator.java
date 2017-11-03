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

import de.gerdiproject.json.datacite.nested.AbstractPerson;
import de.gerdiproject.json.datacite.nested.PersonName;

/**
 * The main researcher involved in producing the data, or an author of the publication.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class Creator extends AbstractPerson
{
    /**
     * The full name of the creator.
     * <br>e.g. Sanchez, Rick
     */
    private PersonName creatorName;


    /**
     * Constructor that creates a {@linkplain PersonName} out of a String.
     *
     * @param name the name of the creator
     */
    public Creator(String name)
    {
        super(name);
    }
    

    /**
     * Simple constructor that requires all mandatory fields.
     *
     * @param nameObject the name and name type of the person
     */
    public Creator(PersonName nameObject)
    {
        super(nameObject);
    }
    

    @Override
    public PersonName getName()
    {
        return creatorName;
    }


    @Override
    public void setName(PersonName name)
    {
        this.creatorName = name;
    }
}
