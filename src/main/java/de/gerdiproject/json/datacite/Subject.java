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

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.utils.StringCleaner;
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
     * A term that describes the resource.
     * <br>e.g. Fishery, Dates
     */
    @NonNull
    private String value;

    /**
     * A IETF language tag of the subject text.
     * <br>e.g. de, en-US
     */
    private String lang;

    /**
     * The free text name of the subject scheme or classification code or authority if one is used.
     */
    private String subjectScheme;

    /**
     * The URI of the subject identifier scheme.
     * <br>e.g. http://id.loc.gov/authorities/subjects
     */
    private String schemeURI;

    /**
     * The URI of the subject term.
     * <br>e.g. http://id.loc.gov/authorities/subjects/sh85026196
     */
    private String valueURI;


    /**
     * Constructor that allows to set the language.
     *
     * @param value a term that describes the resource
     * @param lang a IETF language tag of the subject text
     */
    public Subject(String value, String lang)
    {
        this(value);
        this.lang = lang;
    }


    @Override
    public boolean clean()
    {
        setValue(StringCleaner.clean(value));
        return true;
    }
}
