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
package de.gerdiproject.json.datacite.nested;

/**
 * The code assigned by the funder to a sponsored award (grant).
 * <br><br>
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Robin Weiss
 */
public class AwardNumber
{
	/**
	 * The value of the AwardNumber.
	 * In XML, this is the value between the awardNumber-tags.
	 */
    private String value;
    
    /**
     * The URI leading to a page for more information about the award.
     */
    private String awardURI;


    /**
     * Returns the value of the AwardNumber. In XML, this is the value between the awardNumber tags.
     *
     * @return the value of the AwardNumber
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Changes the value of the AwardNumber. In XML, this is the value between the awardNumber tags.
     *
     * @param value the value of the AwardNumber
     */
    public void setValue(String value)
    {
        this.value = value;
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
}
