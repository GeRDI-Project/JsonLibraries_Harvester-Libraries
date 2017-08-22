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

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.IDocument;


/**
 * A JSON object representing a DataCite document.
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class DataCiteJson implements IDocument, ICleanable
{

    private String identifier;
    private String publisher;

    /**
     * Version number of the resource. If the primary resource has changed the version number increases.
     */
    private String version;

    /**
     * Primary language of the resource. Allowed values are taken from  IETF BCP 47, ISO 639-1 language codes. (e.g. de, en-US)
     */
    private String language;
    private short publicationYear;
    private ResourceType resourceType;
    private Object customData;
    private Source sources;

    /**
     * Unstructured size information about the resource.
     */
    private List<String> sizes;

    /**
     * Technical format of the resource. Use file extension or MIME type where possible.
     */
    private List<String> formats;
    private List<Creator> creators;
    private List<Title> titles;
    private List<Description> descriptions;
    private List<Subject> subjects;
    private List<Contributor> contributors;
    private List<Date> dates;
    private List<GeoLocation> geoLocations;
    private List<RelatedIdentifier> relatedIdentifiers;
    private List<AlternateIdentifier> alternateIdentifiers;
    private List<Rights> rightsList;
    private List<FundingReference> fundingReferences;
    private List<WebLink> webLinks;
    private List<File> files;

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public short getPublicationYear()
    {
        return publicationYear;
    }

    public void setPublicationYear(short publicationYear)
    {
        this.publicationYear = publicationYear;
    }

    public ResourceType getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType)
    {
        this.resourceType = resourceType;
    }

    public Object getCustomData()
    {
        return customData;
    }

    public void setCustomData(Object customData)
    {
        this.customData = customData;
    }

    public Source getSources()
    {
        return sources;
    }

    public void setSources(Source sources)
    {
        this.sources = sources;
    }

    public List<String> getSizes()
    {
        return sizes;
    }

    public void setSizes(List<String> sizes)
    {
        this.sizes = sizes;
    }

    public List<String> getFormats()
    {
        return formats;
    }

    public void setFormats(List<String> formats)
    {
        this.formats = formats;
    }

    public List<Creator> getCreators()
    {
        return creators;
    }

    public void setCreators(List<Creator> creators)
    {
        this.creators = creators;
    }

    public List<Title> getTitles()
    {
        return titles;
    }

    public void setTitles(List<Title> titles)
    {
        this.titles = titles;
    }

    public List<Description> getDescriptions()
    {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions)
    {
        this.descriptions = descriptions;
    }

    public List<Subject> getSubjects()
    {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects)
    {
        this.subjects = subjects;
    }

    public List<Contributor> getContributors()
    {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors)
    {
        this.contributors = contributors;
    }

    public List<Date> getDates()
    {
        return dates;
    }

    public void setDates(List<Date> dates)
    {
        this.dates = dates;
    }

    public List<GeoLocation> getGeoLocations()
    {
        return geoLocations;
    }

    public void setGeoLocations(List<GeoLocation> geoLocations)
    {
        this.geoLocations = geoLocations;
    }

    public List<RelatedIdentifier> getRelatedIdentifiers()
    {
        return relatedIdentifiers;
    }

    public void setRelatedIdentifiers(List<RelatedIdentifier> relatedIdentifiers)
    {
        this.relatedIdentifiers = relatedIdentifiers;
    }

    public List<AlternateIdentifier> getAlternateIdentifiers()
    {
        return alternateIdentifiers;
    }

    public void setAlternateIdentifiers(List<AlternateIdentifier> alternateIdentifiers)
    {
        this.alternateIdentifiers = alternateIdentifiers;
    }

    public List<Rights> getRightsList()
    {
        return rightsList;
    }

    public void setRightsList(List<Rights> rightsList)
    {
        this.rightsList = rightsList;
    }

    public List<FundingReference> getFundingReferences()
    {
        return fundingReferences;
    }

    public void setFundingReferences(List<FundingReference> fundingReferences)
    {
        this.fundingReferences = fundingReferences;
    }

    public List<WebLink> getWebLinks()
    {
        return webLinks;
    }

    public void setWebLinks(List<WebLink> webLinks)
    {
        this.webLinks = webLinks;
    }

    public List<File> getFiles()
    {
        return files;
    }

    public void setFiles(List<File> files)
    {
        this.files = files;
    }


    @Override
    public void clean()
    {
        titles.forEach((Title t) -> t.clean());
        descriptions.forEach((Description d) -> d.clean());
        subjects.forEach((Subject s) -> s.clean());
        rightsList.forEach((Rights r) -> r.clean());
        geoLocations.forEach((GeoLocation g) -> g.clean());
    }

	@Override
	public String getElasticSearchId()
	{
		return identifier;
	}
}
