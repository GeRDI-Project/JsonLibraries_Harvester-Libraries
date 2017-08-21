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

import java.util.List;

import de.gerdiproject.json.IDocument;


/**
 * A JSON object representing a DataCite document.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class DataCiteJson implements IDocument
{

    public String identifier;
    public String publisher;

    /**
     * Version number of the resource. If the primary resource has changed the version number increases.
     */
    public String version;

    /**
     * Primary language of the resource. Allowed values are taken from  IETF BCP 47, ISO 639-1 language codes. (e.g. de, en-US)
     */
    public String language;
    public short publicationYear;
    public ResourceType resourceType;
    public Object customData;
    public Source sources;

    /**
     * Unstructured size information about the resource.
     */
    public List<String> sizes;

    /**
     * Technical format of the resource. Use file extension or MIME type where possible.
     */
    public List<String> formats;
    public List<Creator> creators;
    public List<Title> titles;
    public List<Description> descriptions;
    public List<Subject> subjects;
    public List<Contributor> contributors;
    public List<Date> dates;
    public List<GeoLocation> geoLocations;
    public List<RelatedIdentifier> relatedIdentifiers;
    public List<AlternateIdentifier> alternateIdentifiers;
    public List<Rights> rightsList;
    public List<FundingReference> fundingReferences;
    public List<WebLink> webLinks;
    public List<File> files;
}
