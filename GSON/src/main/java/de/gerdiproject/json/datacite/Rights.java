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
 * This JSON object represents legal rights regarding the usage of the data.
 * Not indexed by ElasticSearch!
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class Rights
{
    /**
     * e.g. CC0 1.0 Universal
     */
    private String rights;

    /**
     * rightsURI in DataCite schema
     */
    private String URI;


    public String getRights()
    {
        return rights;
    }


    public void setRights(String rights)
    {
        this.rights = rights;
    }


    public String getURI()
    {
        return URI;
    }


    public void setURI(String uRI)
    {
        URI = uRI;
    }


}
