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

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.IDocument;


/**
 * A JSON object representing an extended DataCite document,
 * representing core metadata properties chosen for an accurate and consistent identification
 * of a resource for citation. The resource that is being identified can be of any kind,
 * but it is typically a dataset. It may include not only numerical data, but any other
 * research data outputs.
 *
 * The metadata schema is extended by fields that are required by GeRDI features.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class DataCiteJson implements IDocument, ICleanable
{
    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    /**
     *  The Identifier is a unique string that identifies the resource.
     */
    private Identifier identifier;

    /**
     * The name of the entity that holds, archives, publishes prints, distributes, releases, issues, or produces the resource.
     * This property will be used to formulate the citation, so consider the prominence of the role.
     */
    private String publisher;

    /**
     * Version number of the resource. If the primary resource has changed the version number increases.
     */
    private String version;

    /**
     * Primary language of the resource. Allowed values are taken from  IETF BCP 47, ISO 639-1 language codes.
     * <br>e.g. de, en-US
     */
    private String language;

    /**
     * The year when the data was or will be made publicly available.
     */
    private short publicationYear;

    /**
     * A description of the resource.
     */
    private ResourceType resourceType;

    /**
     * Any kind of non-indexed data that does not fit the other fields, but is important metadata nonetheless.
     * This object is NOT part of the original DataCite schema.
     */
    private Object customData;

    /**
     * Endpoint and URL used to retrieve the source meta data.
     * <br>e.g. link to JSON/XML
     */
    private Source sources;

    /**
     * Unstructured size information about the resource.
     * <br>e.g. "15 pages", "6 MB"
     */
    private List<String> sizes;

    /**
     * Technical format of the resource. Use file extension or MIME type where possible.
     * <br>e.g. PDF, XML, application/pdf, text/xml
     */
    private List<String> formats;

    /**
     * The main researchers involved in producing the data, or the authors of the publication,
     * in priority order.
     */
    private List<Creator> creators;

    /**
     * Names or title by which the resource is known.
     */
    private List<Title> titles;

    /**
     * All additional information that does not fit in any of the other categories.
     */
    private List<Description> descriptions;

    /**
     * Subjects, keywords, classification codes, or key phrases describing the resource.
     */
    private List<Subject> subjects;

    /**
     * The institutions or persons responsible for collecting, managing, distributing,
     * or otherwise contributing to the development of the resource.
     */
    private List<Contributor> contributors;

    /**
     * Different dates relevant to the work.
     */
    private List<Date> dates;

    /**
     * Spatial regions or named places where the data was gathered or about which the data is focused.
     */
    private List<GeoLocation> geoLocations;

    /**
     * Identifiers of related resources.
     * These must be globally unique identifiers.
     */
    private List<RelatedIdentifier> relatedIdentifiers;

    /**
     * An identifier or identifiers other than the primary Identifier applied to the resource being registered.
     * This may be any alphanumeric string which is unique within its domain of issue.
     * May be used for local identifiers. AlternateIdentifier should be used for another identifier
     * of the same instance (same location, same file).
     */
    private List<AlternateIdentifier> alternateIdentifiers;

    /**
     * Any rights information for this resource.
     */
    private List<Rights> rightsList;

    /**
     * Information about financial support (funding) for the resource
     * being registered.
     */
    private List<FundingReference> fundingReferences;

    /**
     * Links to the data provider's website.
     */
    private List<WebLink> webLinks;

    /**
     * Downloadable source data files.
     */
    private List<File> files;


    /**
     * Returns a unique identifier of the resource.
     *
     * @return a unique identifier of the resource
     */
    public Identifier getIdentifier()
    {
        return identifier;
    }


    /**
     * Changes the unique identifier of the resource.
     *
     * @param identifier a unique identifier of the resource
     */
    public void setIdentifier(Identifier identifier)
    {
        this.identifier = identifier;
    }


    /**
     * Returns the name of the entity that holds, archives, publishes prints,
     * distributes, releases, issues, or produces the resource.
     *
     * @return the name of the entity that holds, archives, publishes prints,
     * distributes, releases, issues, or produces the resource
     */
    public String getPublisher()
    {
        return publisher;
    }


    /**
     * Changes the name of the entity that holds, archives, publishes prints,
     * distributes, releases, issues, or produces the resource
     *
     * @param publisher the name of the entity that holds, archives, publishes prints,
     * distributes, releases, issues, or produces the resource
     */
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }


    /**
     * Returns the version number of the resource.
     *
     * @return the version number of the resource
     */
    public String getVersion()
    {
        return version;
    }


    /**
     * Changes the version number of the resource.
     *
     * @param version the version number of the resource
     */
    public void setVersion(String version)
    {
        this.version = version;
    }


    /**
     * Returns the primary language of the resource.
     *
     * @return the primary language of the resource
     */
    public String getLanguage()
    {
        return language;
    }


    /**
     * Changes the primary language of the resource.
     * Allowed values are taken from  IETF BCP 47, ISO 639-1 language codes.
     *
     * @param language the primary language of the resource
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }


    /**
     * Returns the year when the data was or will be made publicly available.
     *
     * @return the year when the data was or will be made publicly available
     */
    public short getPublicationYear()
    {
        return publicationYear;
    }


    /**
     * Changes the year when the data was or will be made publicly available.
     *
     * @param publicationYear the year when the data was or will be made publicly available
     */
    public void setPublicationYear(short publicationYear)
    {
        this.publicationYear = publicationYear;
    }


    /**
     * Returns a description of the resource.
     *
     * @return a description of the resource
     */
    public ResourceType getResourceType()
    {
        return resourceType;
    }


    /**
     * Changes the description of the resource.
     *
     * @param resourceType a description of the resource
     */
    public void setResourceType(ResourceType resourceType)
    {
        this.resourceType = resourceType;
    }


    /**
     * Returns an object of additional metadata that is important,
     * but does not fit the other fields.
     *
     * @return an object of additional metadata
     */
    public Object getCustomData()
    {
        return customData;
    }


    /**
     * Sets an object of additional metadata that is important,
     * but does not fit the other fields.
     *
     * @param customData an object of additional metadata
     */
    public void setCustomData(Object customData)
    {
        this.customData = customData;
    }


    /**
     * Returns the endpoint and URL used to retrieve the source meta data.
     *
     * @return the endpoint and URL used to retrieve the source meta data
     */
    public Source getSources()
    {
        return sources;
    }


    /**
     * Changes the endpoint and URL used to retrieve the source meta data.
     * <br>e.g. link to JSON/XML
     *
     * @param sources the endpoint and URL used to retrieve the source meta data
     */
    public void setSources(Source sources)
    {
        this.sources = sources;
    }


    /**
     * Returns unstructured size information about the resource.
     *
     * @return unstructured size information about the resource
     */
    public List<String> getSizes()
    {
        return sizes;
    }


    /**
     * Changes the unstructured size information about the resource.
     * <br>e.g. "15 pages", "6 MB"
     *
     * @param sizes unstructured size information about the resource
     */
    public void setSizes(List<String> sizes)
    {
        this.sizes = sizes;
    }


    /**
     * Returns technical format of the resource.
     *
     * @return technical format of the resource
     */
    public List<String> getFormats()
    {
        return formats;
    }


    /**
     * Changes technical format of the resource. Use file extension or MIME type where possible.
     * <br>e.g. PDF, XML, application/pdf, text/xml
     *
     * @param formats technical format of the resource
     */
    public void setFormats(List<String> formats)
    {
        this.formats = formats;
    }


    /**
     * Returns the main researchers involved in producing the data,
     * or the authors of the publication, in priority order.
     *
     * @return the main researchers and/or the authors of the publication
     */
    public List<Creator> getCreators()
    {
        return creators;
    }


    /**
     * Changes the main researchers involved in producing the data,
     * or the authors of the publication.
     *
     * @param creators the main researchers and/or the authors of the publication
     */
    public void setCreators(List<Creator> creators)
    {
        this.creators = creators;
    }


    /**
     * Returns names or titles by which the resource is known.
     *
     * @return names or titles by which the resource is known
     */
    public List<Title> getTitles()
    {
        return titles;
    }


    /**
     * Changes names or titles by which the resource is known.
     *
     * @param titles names or titles by which the resource is known
     */
    public void setTitles(List<Title> titles)
    {
        this.titles = titles;
    }


    /**
     * Returns all additional information that does not fit in any of the other categories.
     *
     * @return all additional information
     */
    public List<Description> getDescriptions()
    {
        return descriptions;
    }


    /**
     * Changes all additional information that does not fit in any of the other categories.
     *
     * @param descriptions all additional information
     */
    public void setDescriptions(List<Description> descriptions)
    {
        this.descriptions = descriptions;
    }


    /**
     * Returns subjects, keywords, classification codes, or key phrases describing the resource.
     *
     * @return subjects, keywords, classification codes, or key phrases describing the resource
     */
    public List<Subject> getSubjects()
    {
        return subjects;
    }


    /**
     * Changes the subjects, keywords, classification codes, and key phrases describing the resource.
     *
     * @param subjects subjects, keywords, classification codes, or key phrases describing the resource
     */
    public void setSubjects(List<Subject> subjects)
    {
        this.subjects = subjects;
    }


    /**
     * Returns the institutions or persons responsible for collecting, managing, distributing,
     * or otherwise contributing to the development of the resource.
     *
     * @return institutions or persons responsible for contributing to the development of the resource
     */
    public List<Contributor> getContributors()
    {
        return contributors;
    }


    /**
     * Changes the institutions or persons responsible for collecting, managing, distributing,
     * or otherwise contributing to the development of the resource.
     *
     * @param contributors institutions or persons responsible for contributing to the development of the resource
     */
    public void setContributors(List<Contributor> contributors)
    {
        this.contributors = contributors;
    }


    /**
     * Returns different dates relevant to the work.
     *
     * @return dates relevant to the work
     */
    public List<Date> getDates()
    {
        return dates;
    }


    /**
     * Changes dates relevant to the work.
     *
     * @param dates dates relevant to the work
     */
    public void setDates(List<Date> dates)
    {
        this.dates = dates;
    }


    /**
     * Returns the spatial regions or named places where the data was gathered
     * or about which the data is focused.
     *
     * @return spatial regions and/or named places
     */
    public List<GeoLocation> getGeoLocations()
    {
        return geoLocations;
    }


    /**
     * Changes the spatial regions or named places where the data was gathered
     * or about which the data is focused.
     *
     * @param geoLocations spatial regions and/or named places
     */
    public void setGeoLocations(List<GeoLocation> geoLocations)
    {
        this.geoLocations = geoLocations;
    }


    /**
     * Returns identifiers of related resources.
     * @return identifiers of related resources
     */
    public List<RelatedIdentifier> getRelatedIdentifiers()
    {
        return relatedIdentifiers;
    }


    /**
     * Changes the identifiers of related resources.
     * These must be globally unique identifiers.
     *
     * @param relatedIdentifiers identifiers of related resources
     */
    public void setRelatedIdentifiers(List<RelatedIdentifier> relatedIdentifiers)
    {
        this.relatedIdentifiers = relatedIdentifiers;
    }


    /**
     * Returns identifiers other than the primary Identifier applied to the resource being registered.
     *
     * @return identifiers other than the primary Identifier
     */
    public List<AlternateIdentifier> getAlternateIdentifiers()
    {
        return alternateIdentifiers;
    }


    /**
     * Changes identifiers other than the primary Identifier applied to the resource being registered.
     *
     * @param alternateIdentifiers identifiers other than the primary Identifier
     */
    public void setAlternateIdentifiers(List<AlternateIdentifier> alternateIdentifiers)
    {
        this.alternateIdentifiers = alternateIdentifiers;
    }


    /**
     * Returns any rights information for this resource.
     *
     * @return rights information for this resource
     */
    public List<Rights> getRightsList()
    {
        return rightsList;
    }


    /**
     * Changes rights information for this resource.
     *
     * @param rightsList rights information for this resource
     */
    public void setRightsList(List<Rights> rightsList)
    {
        this.rightsList = rightsList;
    }


    /**
     * Returns information about financial support (funding) for the resource
     * being registered.
     *
     * @return information about financial support
     */
    public List<FundingReference> getFundingReferences()
    {
        return fundingReferences;
    }


    /**
     * Changes information about financial support (funding) for the resource
     * being registered.
     *
     * @param fundingReferences information about financial support
     */
    public void setFundingReferences(List<FundingReference> fundingReferences)
    {
        this.fundingReferences = fundingReferences;
    }


    /**
     * Returns links to the data provider's website.
     *
     * @return links to the data provider's website
     */
    public List<WebLink> getWebLinks()
    {
        return webLinks;
    }


    /**
     * Changes the links to the data provider's website.
     * @param webLinks links to the data provider's website
     */
    public void setWebLinks(List<WebLink> webLinks)
    {
        this.webLinks = webLinks;
    }


    /**
     * Returns downloadable source data files.
     *
     * @return downloadable files
     */
    public List<File> getFiles()
    {
        return files;
    }


    /**
     * Changes the downloadable source data files.
     *
     * @param files downloadable files
     */
    public void setFiles(List<File> files)
    {
        this.files = files;
    }


    @Override
    public void clean()
    {
        if (titles != null)
            titles.forEach((Title t) -> t.clean());

        if (descriptions != null)
            descriptions.forEach((Description d) -> d.clean());

        if (subjects != null)
            subjects.forEach((Subject s) -> s.clean());

        if (rightsList != null)
            rightsList.forEach((Rights r) -> r.clean());

        if (geoLocations != null)
            geoLocations.forEach((GeoLocation g) -> g.clean());
    }

    @Override
    public String getElasticSearchId()
    {
        // base64 encoding:
        String base64EncodedString = new String(ENCODER.encode(sources.getURI().getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        return base64EncodedString;
    }
}
