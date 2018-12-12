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
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.IDocument;
import de.gerdiproject.harvest.utils.CollectionUtils;
import de.gerdiproject.json.GsonUtils;
import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.extension.IDataCiteExtension;
import de.gerdiproject.json.datacite.extension.generic.AbstractResearch;
import de.gerdiproject.json.datacite.extension.generic.ResearchData;
import de.gerdiproject.json.datacite.extension.generic.WebLink;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

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
@Data
public class DataCiteJson implements IDocument
{
    private static final Gson GSON = GsonUtils.createGerdiDocumentGsonBuilder().create();

    /**
     * An identifier of the source of the document. The identifier must be unique
     * within the context of the harvester that generates it.
     */
    @NonNull
    private final transient String sourceId;

    /**
     * The Identifier is a unique string that identifies the resource.
     */
    private Identifier identifier;

    /**
     * The main researchers involved in producing the data, or the authors of the
     * publication, in priority order.
     */
    @Setter(AccessLevel.NONE)
    private List<Creator> creators;

    /**
     * Names or title by which the resource is known.
     */
    @Setter(AccessLevel.NONE)
    private Set<Title> titles;

    /**
     * The name of the entity that holds, archives, publishes prints, distributes,
     * releases, issues, or produces the resource. This property will be used to
     * formulate the citation, so consider the prominence of the role.
     */
    private String publisher;

    /**
     * The year when the data was or will be made publicly available.
     * {@linkplain Integer} is used instead of the primitive type in order to
     * exclude the publicationYear from the JSON if it was not explicitly set.
     */
    private Integer publicationYear;

    /**
     * A description of the resource.
     */
    private ResourceType resourceType;

    /**
     * Subjects, keywords, classification codes, or key phrases describing the
     * resource.
     */
    @Setter(AccessLevel.NONE)
    private Set<Subject> subjects;

    /**
     * The institutions or persons responsible for collecting, managing,
     * distributing, or otherwise contributing to the development of the resource.
     */
    @Setter(AccessLevel.NONE)
    private Set<Contributor> contributors;

    /**
     * Different dates relevant to the work.
     */
    @Setter(AccessLevel.NONE)
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
    @Setter(AccessLevel.NONE)
    private Set<AlternateIdentifier> alternateIdentifiers;

    /**
     * Identifiers of related resources. These must be globally unique identifiers.
     */
    @Setter(AccessLevel.NONE)
    private Set<RelatedIdentifier> relatedIdentifiers;

    /**
     * Unstructured information about the resource size, duration, or extent. <br>
     * e.g. "15 pages", "6 MB", "15 seconds"
     */
    @Setter(AccessLevel.NONE)
    private Set<String> sizes;

    /**
     * Technical format of the resource. Use file extension or MIME type where
     * possible. <br>
     * e.g. PDF, XML, application/pdf, text/xml
     */
    @Setter(AccessLevel.NONE)
    private Set<String> formats;

    /**
     * Version number of the resource. If the primary resource has changed the
     * version number increases.
     */
    private String version;

    /**
     * Any rights information for this resource.
     */
    @Setter(AccessLevel.NONE)
    private Set<Rights> rightsList;

    /**
     * All additional information that does not fit in any of the other categories.
     */
    @Setter(AccessLevel.NONE)
    private Set<Description> descriptions;

    /**
     * Spatial regions or named places where the data was gathered or about which
     * the data is focused.
     */
    @Setter(AccessLevel.NONE)
    private Set<GeoLocation> geoLocations;

    /**
     * Information about financial support (funding) for the resource being
     * registered.
     */
    @Setter(AccessLevel.NONE)
    private Set<FundingReference> fundingReferences;

    // GENERIC EXTENSION:

    /**
     * A unique but human readable name of the repository. <br>
     * e.g. Sea Around Us, FAOSTAT
     */
    private String repositoryIdentifier;

    /**
     * Links to the data provider's website.
     */
    @Setter(AccessLevel.NONE)
    private Set<WebLink> webLinks;

    /**
     * Downloadable source data files.
     */
    @Setter(AccessLevel.NONE)
    private Set<ResearchData> researchDataList;

    /**
     * A set of human readable names of the research disciplines, meaning the
     * topics or domains that this document covers. <br>
     * e.g. Computer Science, Geography
     */
    @Setter(AccessLevel.NONE)
    private Set<AbstractResearch> researchDisciplines;


    // DISCIPLINE EXTENSIONS

    /**
     * Different dates relevant to the work.
     */
    @SerializedName("extension") @Setter(AccessLevel.NONE)
    private Map<String, IDataCiteExtension> extensions;


    /**
     * Constructor that sets up the GeRDI extension.
     *
     * @param sourceId a unique identifier of the source from which the document was
     *         retrieved
     *
     * @throws NullPointerException if the sourceId is null
     */
    public DataCiteJson(String sourceId) throws NullPointerException
    {
        if (sourceId == null)
            throw new NullPointerException();

        this.sourceId = sourceId;
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
     * Sets the main researchers involved in producing the data, or the authors of
     * the publication, sorted by priority (most important as first element).
     * Duplicate entries are ignored.
     *
     * @param creators the main researchers and/or the authors of the publication
     */
    public void addCreators(Collection<Creator> creators)
    {
        this.creators = CollectionUtils.addToList(this.creators, creators);
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
     * Changes dates relevant to the work.
     *
     * @param dates dates relevant to the work
     */
    public void addDates(Collection<AbstractDate> dates)
    {
        this.dates = CollectionUtils.addToSet(this.dates, dates);
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
     * Changes rights information for this resource.
     *
     * @param rightsList rights information for this resource
     */
    public void addRights(Collection<Rights> rightsList)
    {
        this.rightsList = CollectionUtils.addToSet(this.rightsList, rightsList);
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
     * Changes the links to the data provider's website.
     *
     * @param webLinks links to the data provider's website
     */
    public void addWebLinks(Collection<WebLink> webLinks)
    {
        this.webLinks = CollectionUtils.addToSet(this.webLinks, webLinks);
    }


    /**
     * Changes the downloadable source data files.
     *
     * @param researchDataList downloadable files
     */
    public void addResearchData(Collection<ResearchData> researchDataList)
    {
        this.researchDataList = CollectionUtils.addToSet(this.researchDataList, researchDataList);
    }


    /**
     * Changes the set of human readable names of the research disciplines, meaning
     * the topics or domains that this document covers.
     *
     * @param researchDisciplines a collection of human readable names of the research
     *            disciplines
     */
    public void addResearchDisciplines(Collection<AbstractResearch> researchDisciplines)
    {
        this.researchDisciplines = CollectionUtils.addToSet(this.researchDisciplines, researchDisciplines);
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
     * Adds an {@linkplain IDataCiteExtension} to the metadata.
     *
     * @param extension the extension to be added
     */
    public void addExtension(IDataCiteExtension extension)
    {
        this.extensions = CollectionUtils.addToMap(this.extensions, extension.getKey(), extension);
    }


    @Override
    public String toJson()
    {
        return GSON.toJson(this);
    }
}
