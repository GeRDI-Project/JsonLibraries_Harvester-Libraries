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

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Subject, keywords (tags), classification codes, or key phrases describing the resource.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class Subject implements ICleanable
{
    /**
     * -- GETTER --
     * Retrieves the term that describes the resource.
     * <br>e.g. Fishery, Dates
     * @return a term that describes the resource
     *
     * -- SETTER --
     * Sets the term that describes the resource.
     * <br>e.g. Fishery, Dates
     * @param value a term that describes the resource
     */
    @NonNull
    private String value;


    /**
     * -- GETTER --
     * Retrieves the IETF language tag of the subject text.
     * <br>e.g. de, en-US
     * @return the IETF language tag of the subject text
     *
     * -- SETTER --
     * Sets the IETF language tag of the subject text.
     * <br>e.g. de, en-US
     * @param lang the IETF language tag of the subject text
     */
    private String lang;


    /**
     * -- GETTER --
     * Retrieves the free text name of the subject scheme or classification code or authority if one is used.
     * @return the free text name of the subject scheme
     *
     * -- SETTER --
     * Sets the free text name of the subject scheme or classification code or authority if one is used.
     * @param scheme the free text name of the subject scheme
     */
    @SerializedName("subjectScheme")
    private String scheme;


    /**
     * -- GETTER --
     * Retrieves the URI of the subject identifier scheme.
     * <br>e.g. http://id.loc.gov/authorities/subjects
     * @return the URI of the subject identifier scheme
     *
     * -- SETTER --
     * Sets the URI of the subject identifier scheme.
     * <br>e.g. http://id.loc.gov/authorities/subjects
     * @param schemeURI the URI of the subject identifier scheme
     */
    private String schemeURI;

    /**
     * -- GETTER --
     * Retrieves the URI of the subject term.
     * <br>e.g. http://id.loc.gov/authorities/subjects/sh85026196
     * @return the URI of the subject term
     *
     * -- SETTER --
     * Sets the URI of the subject term.
     * <br>e.g. http://id.loc.gov/authorities/subjects/sh85026196
     * @param valueURI the URI of the subject term
     */
    private String valueURI;


    /**
     * Constructor that allows to set the language.
     *
     * @param value a term that describes the resource
     * @param lang a IETF language tag of the subject text
     */
    public Subject(final String value, final String lang)
    {
        this(value);
        this.lang = lang;
    }


    @Override
    public boolean clean()
    {
        setValue(StringUtils.clean(value));
        return true;
    }
}
