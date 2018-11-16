/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gerdiproject.json.datacite;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import de.gerdiproject.harvest.IDocument;
import de.gerdiproject.harvest.utils.CollectionUtils;
import de.gerdiproject.json.GsonUtils;
import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.extension.ResearchData;
import de.gerdiproject.json.datacite.extension.WebLink;
import de.gerdiproject.json.datacite.extension.abstr.AbstractResearch;
import de.gerdiproject.json.datacite.extension.metadatabowl.soep.SoepVariable;

/**
 * A JSON object representing an extended DataCite document, representing core
 * metadata properties chosen for an accurate and consistent identification of a
 * resource for citation. The resource that is being identified can be of any
 * kind, but it is typically a dataset. It may include not only numerical data,
 * but any other research data outputs. The metadata schema is extended by
 * fields that are required by GeRDI features. Source:
 * https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 *
 * @author Mathis Neumann, Robin Weiss, Ingo Thomsen
 */
public class DataCiteJson implements IDocument
{
    private static final Gson GSON = GsonUtils.createGerdiDocumentGsonBuilder().create();

    /**
     * An identifier of the source of the document. The identifier must be unique
     * within the context of the harvester that generates it.
     */
    private final transient String sourceId;

    /**
     * The Identifier is a unique string that identifies the resource.
     */
    private Identifier identifier;

    /**
     * The main researchers involved in producing the data, or the authors of the
     * publication, in priority order.
     */
    private List<Creator> creators;

    /**
     * Names or title by which the resource is known.
     */
    private Set<Title> titles;

    /**
     * The name of the entity that holds, archives, publishes prints, distributes,
     * releases, issues, or produces the resource. This property will be used to
     * formulate the citation, so consider the prominence of the role.
     */
    private String publisher;

    /**
     * The year when the data was or will be made publicly available.
     */
    private short publicationYear;

    /**
     * A description of the resource.
     */
    private ResourceType resourceType;

    /**
     * Subjects, keywords, classification codes, or key phrases describing the
     * resource.
     */
    private Set<Subject> subjects;

    /**
     * The institutions or persons responsible for collecting, managing,
     * distributing, or otherwise contributing to the development of the resource.
     */
    private Set<Contributor> contributors;

    /**
     * Different dates relevant to the work.
     */
    private Set<AbstractDate> dates;

    /**
     * Primary language of the resource. Allowed values are taken from IETF BCP 47,
     * ISO 639-1 language codes. <br>
     * e.g. de, en-US
     */
    private String language;

    /**
     * An identifier or identifiers other than the primary Identifier applied to the
     * resource being registered. This may be any alphanumeric string which is
     * unique within its domain of issue. May be used for local identifiers.
     * AlternateIdentifier should be used for another identifier of the same
     * instance (same location, same file).
     */
    private Set<AlternateIdentifier> alternateIdentifiers;

    /**
     * Identifiers of related resources. These must be globally unique identifiers.
     */
    private Set<RelatedIdentifier> relatedIdentifiers;

    /**
     * Unstructured information about the resource size, duration, or extent. <br>
     * e.g. "15 pages", "6 MB", "15 seconds"
     */
    private Set<String> sizes;

    /**
     * Technical format of the resource. Use file extension or MIME type where
     * possible. <br>
     * e.g. PDF, XML, application/pdf, text/xml
     */
    private Set<String> formats;

    /**
     * Version number of the resource. If the primary resource has changed the
     * version number increases.
     */
    private String version;

    /**
     * Any rights information for this resource.
     */
    private Set<Rights> rightsList;

    /**
     * All additional information that does not fit in any of the other categories.
     */
    private Set<Description> descriptions;

    /**
     * Spatial regions or named places where the data was gathered or about which
     * the data is focused.
     */
    private Set<GeoLocation> geoLocations;

    /**
     * Information about financial support (funding) for the resource being
     * registered.
     */
    private Set<FundingReference> fundingReferences;

    /**
     * Links to the data provider's website.
     */
    private Set<WebLink> webLinks;

    /**
     * Downloadable source data files.
     */
    private Set<ResearchData> researchDataList;

    /**
     * A unique but human readable name of the repository. <br>
     * e.g. Sea Around Us, FAOSTAT
     */
    private String repositoryIdentifier;

    /**
     * A list of human readable names of the research disciplines, meaning the
     * topics or domains that this document covers. <br>
     * e.g. Computer Science, Geography
     */
    private Set<AbstractResearch> researchDisciplines;

    /**
     * This is where the discipline-specific metadata are specified in the document
     *
     * Research community: SOEP
     * A set of variables associated with a resource in SOEP study.
     */
    private Set<SoepVariable> soepDatasetVariables;


    /**
     * This constructor set the source identifier of the document which allows for
     * persisting it when one of its values change.
     *
     * @param sourceId a unique identifier of the source from which the document was
     *            retrieved
     *
     * @throws NullPointerException if the sourceId is null
     */
    public DataCiteJson(final String sourceId) throws NullPointerException
    {
        if (sourceId == null)
            throw new NullPointerException();

        this.sourceId = sourceId;
    }


    /**
     * Returns an identifier of the source of the document. If no such identifier
     * was set, a hash value of the JSON representation of this document is used as
     * a fallback, causing any reference to this document in the index to be lost if
     * any field value is updated.
     *
     * @return a unique identifier of the source from which the document was
     *         retrieved
     */
    @Override
    public String getSourceId()
    {
        return sourceId;
    }


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
     *         distributes, releases, issues, or produces the resource
     */
    public String getPublisher()
    {
        return publisher;
    }


    /**
     * Changes the name of the entity that holds, archives, publishes prints,
     * distributes, releases, issues, or produces the resource
     *
     * @param publisher the name of the entity that holds, archives, publishes
     *            prints, distributes, releases, issues, or produces the resource
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
     * Changes the primary language of the resource. Allowed values are taken from
     * IETF BCP 47, ISO 639-1 language codes.
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
     * @param publicationYear the year when the data was or will be made publicly
     *            available
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
     * Returns the unique but human readable name of the repository. <br>
     * e.g. Sea Around Us, FAOSTAT
     *
     * @return a unique but human readable name of the repository
     */
    public String getRepositoryIdentifier()
    {
        return repositoryIdentifier;
    }


    /**
     * Changes the unique but human readable name of the repository.
     *
     * @param repositoryIdentifier a unique but human readable name of the
     *            repository
     */
    public void setRepositoryIdentifier(String repositoryIdentifier)
    {
        this.repositoryIdentifier = repositoryIdentifier;
    }


    /**
     * Retrieves the list of human readable names of the research disciplines,
     * meaning the topics or domains that this document covers.
     *
     * @return a list of human readable names of the research disciplines
     */
    public Set<AbstractResearch> getResearchDisciplines()
    {
        return researchDisciplines;
    }


    /**
     * Changes the list of human readable names of the research disciplines, meaning
     * the topics or domains that this document covers.
     *
     * @param researchDisciplines a list of human readable names of the research
     *            disciplines
     */
    public void addResearchDisciplines(Collection<AbstractResearch> researchDisciplines)
    {
        this.researchDisciplines = CollectionUtils.addToSet(this.researchDisciplines, researchDisciplines);
    }


    /**
     * Returns unstructured size information about the resource.
     *
     * @return unstructured size information about the resource
     */
    public Set<String> getSizes()
    {
        return sizes;
    }


    /**
     * Changes the unstructured size information about the resource. <br>
     * e.g. "15 pages", "6 MB"
     *
     * @param sizes unstructured size information about the resource
     */
    public void addSizes(Collection<String> sizes)
    {
        this.sizes = CollectionUtils.addToSet(this.sizes, sizes);
    }


    /**
     * Returns technical format of the resource.
     *
     * @return technical format of the resource
     */
    public Set<String> getFormats()
    {
        return formats;
    }


    /**
     * Changes technical format of the resource. Use file extension or MIME type
     * where possible. <br>
     * e.g. PDF, XML, application/pdf, text/xml
     *
     * @param formats technical format of the resource
     */
    public void addFormats(Collection<String> formats)
    {
        this.formats = CollectionUtils.addToSet(this.formats, formats);

    }


    /**
     * Returns the main researchers involved in producing the data, or the authors
     * of the publication. These are sorted by priority (most important as first
     * element)
     *
     * @return the main researchers and/or the authors of the publication
     */
    public List<Creator> getCreators()
    {
        return this.creators;
    }


    /**
     * Sets the main researchers involved in producing the data, or the authors of
     * the publication, sorted by priority (most important as first element).
     * Duplicate entries are ignored.
     *
     * @param creators the main researchers and/or the authors of the publication
     */
    public void addCreators(List<Creator> creators)
    {
        this.creators = CollectionUtils.addToList(this.creators, creators);
    }


    /**
     * Returns names or titles by which the resource is known.
     *
     * @return names or titles by which the resource is known
     */
    public Set<Title> getTitles()
    {
        return this.titles;
    }


    /**
     * Changes names or titles by which the resource is known.
     *
     * @param titles names or titles by which the resource is known
     */
    public void addTitles(Collection<Title> titles)
    {
        this.titles = CollectionUtils.addToSet(this.titles, titles);
    }


    /**
     * Returns all additional information that does not fit in any of the other
     * categories.
     *
     * @return all additional information
     */
    public Set<Description> getDescriptions()
    {
        return descriptions;
    }


    /**
     * Changes all additional information that does not fit in any of the other
     * categories.
     *
     * @param descriptions all additional information
     */
    public void addDescriptions(Collection<Description> descriptions)
    {
        this.descriptions = CollectionUtils.addToSet(this.descriptions, descriptions);
    }


    /**
     * Returns subjects, keywords, classification codes, or key phrases describing
     * the resource.
     *
     * @return subjects, keywords, classification codes, or key phrases describing
     *         the resource
     */
    public Set<Subject> getSubjects()
    {
        return subjects;
    }


    /**
     * Changes the subjects, keywords, classification codes, and key phrases
     * describing the resource.
     *
     * @param subjects subjects, keywords, classification codes, or key phrases
     *            describing the resource
     */
    public void addSubjects(Collection<Subject> subjects)
    {
        this.subjects = CollectionUtils.addToSet(this.subjects, subjects);
    }


    /**
     * Returns the institutions or persons responsible for collecting, managing,
     * distributing, or otherwise contributing to the development of the resource.
     *
     * @return institutions or persons responsible for contributing to the
     *         development of the resource
     */
    public Set<Contributor> getContributors()
    {
        return contributors;
    }


    /**
     * Changes the institutions or persons responsible for collecting, managing,
     * distributing, or otherwise contributing to the development of the resource.
     *
     * @param contributors institutions or persons responsible for contributing to
     *            the development of the resource
     */
    public void addContributors(Collection<Contributor> contributors)
    {
        this.contributors = CollectionUtils.addToSet(this.contributors, contributors);
    }


    /**
     * Returns different dates relevant to the work.
     *
     * @return dates relevant to the work
     */
    public Set<AbstractDate> getDates()
    {
        return dates;
    }


    /**
     * Changes dates relevant to the work.
     *
     * @param dates dates relevant to the work
     */
    public void addDates(Collection<AbstractDate> dates)
    {
        this.dates = CollectionUtils.addToSet(this.dates, dates);
    }


    /**
     * Returns the spatial regions or named places where the data was gathered or
     * about which the data is focused.
     *
     * @return spatial regions and/or named places
     */
    public Set<GeoLocation> getGeoLocations()
    {
        return geoLocations;
    }


    /**
     * Changes the spatial regions or named places where the data was gathered or
     * about which the data is focused.
     *
     * @param geoLocations spatial regions and/or named places
     */
    public void addGeoLocations(Collection<GeoLocation> geoLocations)
    {
        this.geoLocations = CollectionUtils.addToSet(this.geoLocations, geoLocations);
    }


    /**
     * Returns identifiers of related resources.
     *
     * @return identifiers of related resources
     */
    public Set<RelatedIdentifier> getRelatedIdentifiers()
    {
        return relatedIdentifiers;
    }


    /**
     * Changes the identifiers of related resources. These must be globally unique
     * identifiers.
     *
     * @param relatedIdentifiers identifiers of related resources
     */
    public void addRelatedIdentifiers(Collection<RelatedIdentifier> relatedIdentifiers)
    {
        this.relatedIdentifiers = CollectionUtils.addToSet(this.relatedIdentifiers, relatedIdentifiers);
    }


    /**
     * Returns identifiers other than the primary Identifier applied to the resource
     * being registered.
     *
     * @return identifiers other than the primary Identifier
     */
    public Set<AlternateIdentifier> getAlternateIdentifiers()
    {
        return alternateIdentifiers;
    }


    /**
     * Changes identifiers other than the primary Identifier applied to the resource
     * being registered.
     *
     * @param alternateIdentifiers identifiers other than the primary Identifier
     */
    public void addAlternateIdentifiers(Collection<AlternateIdentifier> alternateIdentifiers)
    {
        this.alternateIdentifiers = CollectionUtils.addToSet(this.alternateIdentifiers, alternateIdentifiers);
    }


    /**
     * Returns any rights information for this resource.
     *
     * @return rights information for this resource
     */
    public Set<Rights> getRightsList()
    {
        return rightsList;
    }


    /**
     * Changes rights information for this resource.
     *
     * @param rightsList rights information for this resource
     */
    public void addRights(Collection<Rights> rightsList)
    {
        this.rightsList = CollectionUtils.addToSet(this.rightsList, rightsList);
    }


    /**
     * Returns information about financial support (funding) for the resource being
     * registered.
     *
     * @return information about financial support
     */
    public Set<FundingReference> getFundingReferences()
    {
        return fundingReferences;
    }


    /**
     * Changes information about financial support (funding) for the resource being
     * registered.
     *
     * @param fundingReferences information about financial support
     */
    public void addFundingReferences(Collection<FundingReference> fundingReferences)
    {
        this.fundingReferences = CollectionUtils.addToSet(this.fundingReferences, fundingReferences);
    }


    /**
     * Returns links to the data provider's website.
     *
     * @return links to the data provider's website
     */
    public Set<WebLink> getWebLinks()
    {
        return webLinks;
    }


    /**
     * Changes the links to the data provider's website.
     *
     * @param webLinks links to the data provider's website
     */
    public void addWebLinks(Collection<WebLink> webLinks)
    {
        this.webLinks = CollectionUtils.addToSet(this.webLinks, webLinks);
    }


    /**
     * Returns downloadable source data files.
     *
     * @return downloadable files
     */
    public Set<ResearchData> getResearchDataList()
    {
        return researchDataList;
    }


    /**
     * Changes the downloadable source data files.
     *
     * @param researchDataList downloadable files
     */
    public void addResearchDataList(Collection<ResearchData> researchDataList)
    {
        this.researchDataList = CollectionUtils.addToSet(this.researchDataList, researchDataList);
    }


    /**
     * Changes the SOEP dataset variables.
     *
     * @param soepDatasetVariables the soep variables that are to be set
     *
     */
    public void addSoepDatasetVariables(Collection<SoepVariable> soepDatasetVariables)
    {
        this.soepDatasetVariables = CollectionUtils.addToSet(this.soepDatasetVariables, soepDatasetVariables);
    }


    /**
     * Returns SOEP dataset variables.
     *
     * @return a set of variables
     */
    public Set<SoepVariable> getSoepDatasetVariables()
    {
        return soepDatasetVariables;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alternateIdentifiers == null) ? 0 : alternateIdentifiers.hashCode());
        result = prime * result + ((contributors == null) ? 0 : contributors.hashCode());
        result = prime * result + ((creators == null) ? 0 : creators.hashCode());
        result = prime * result + ((dates == null) ? 0 : dates.hashCode());
        result = prime * result + ((descriptions == null) ? 0 : descriptions.hashCode());
        result = prime * result + ((formats == null) ? 0 : formats.hashCode());
        result = prime * result + ((fundingReferences == null) ? 0 : fundingReferences.hashCode());
        result = prime * result + ((geoLocations == null) ? 0 : geoLocations.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((language == null) ? 0 : language.hashCode());
        result = prime * result + publicationYear;
        result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
        result = prime * result + ((relatedIdentifiers == null) ? 0 : relatedIdentifiers.hashCode());
        result = prime * result + ((repositoryIdentifier == null) ? 0 : repositoryIdentifier.hashCode());
        result = prime * result + ((researchDataList == null) ? 0 : researchDataList.hashCode());
        result = prime * result + ((researchDisciplines == null) ? 0 : researchDisciplines.hashCode());
        result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
        result = prime * result + ((rightsList == null) ? 0 : rightsList.hashCode());
        result = prime * result + ((sizes == null) ? 0 : sizes.hashCode());
        result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
        result = prime * result + ((titles == null) ? 0 : titles.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result = prime * result + ((webLinks == null) ? 0 : webLinks.hashCode());
        result = prime * result + ((soepDatasetVariables == null) ? 0 : soepDatasetVariables.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof DataCiteJson))
            return false;

        DataCiteJson other = (DataCiteJson) obj;

        if (alternateIdentifiers == null) {
            if (other.alternateIdentifiers != null)
                return false;
        } else if (!alternateIdentifiers.equals(other.alternateIdentifiers))
            return false;

        if (contributors == null) {
            if (other.contributors != null)
                return false;
        } else if (!contributors.equals(other.contributors))
            return false;

        if (creators == null) {
            if (other.creators != null)
                return false;
        } else if (!creators.equals(other.creators))
            return false;

        if (dates == null) {
            if (other.dates != null)
                return false;
        } else if (!dates.equals(other.dates))
            return false;

        if (descriptions == null) {
            if (other.descriptions != null)
                return false;
        } else if (!descriptions.equals(other.descriptions))
            return false;

        if (formats == null) {
            if (other.formats != null)
                return false;
        } else if (!formats.equals(other.formats))
            return false;

        if (fundingReferences == null) {
            if (other.fundingReferences != null)
                return false;
        } else if (!fundingReferences.equals(other.fundingReferences))
            return false;

        if (geoLocations == null) {
            if (other.geoLocations != null)
                return false;
        } else if (!geoLocations.equals(other.geoLocations))
            return false;

        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;

        if (language == null) {
            if (other.language != null)
                return false;
        } else if (!language.equals(other.language))
            return false;

        if (publicationYear != other.publicationYear)
            return false;

        if (publisher == null) {
            if (other.publisher != null)
                return false;
        } else if (!publisher.equals(other.publisher))
            return false;

        if (relatedIdentifiers == null) {
            if (other.relatedIdentifiers != null)
                return false;
        } else if (!relatedIdentifiers.equals(other.relatedIdentifiers))
            return false;

        if (repositoryIdentifier == null) {
            if (other.repositoryIdentifier != null)
                return false;
        } else if (!repositoryIdentifier.equals(other.repositoryIdentifier))
            return false;

        if (researchDataList == null) {
            if (other.researchDataList != null)
                return false;
        } else if (!researchDataList.equals(other.researchDataList))
            return false;

        if (researchDisciplines == null) {
            if (other.researchDisciplines != null)
                return false;
        } else if (!researchDisciplines.equals(other.researchDisciplines))
            return false;

        if (resourceType == null) {
            if (other.resourceType != null)
                return false;
        } else if (!resourceType.equals(other.resourceType))
            return false;

        if (rightsList == null) {
            if (other.rightsList != null)
                return false;
        } else if (!rightsList.equals(other.rightsList))
            return false;

        if (sizes == null) {
            if (other.sizes != null)
                return false;
        } else if (!sizes.equals(other.sizes))
            return false;

        if (subjects == null) {
            if (other.subjects != null)
                return false;
        } else if (!subjects.equals(other.subjects))
            return false;

        if (titles == null) {
            if (other.titles != null)
                return false;
        } else if (!titles.equals(other.titles))
            return false;

        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;

        if (webLinks == null) {
            if (other.webLinks != null)
                return false;
        } else if (!webLinks.equals(other.webLinks))
            return false;

        if (soepDatasetVariables == null) {
            if (other.soepDatasetVariables != null)
                return false;
        } else if (!soepDatasetVariables.equals(other.soepDatasetVariables))
            return false;

        return true;
    }


    @Override
    public String toJson()
    {
        return GSON.toJson(this);
    }
}
