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
 * Any rights information for this resource.
 * Provide a rights management statement for the resource or reference a service
 * providing such information.
 * Include embargo information if applicable. Use the complete title of a license and
 * include version information if applicable.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 *
 */
@Data @RequiredArgsConstructor @AllArgsConstructor
public class Rights implements ICleanable
{
    /**
     * Free text that describes the rights.
     * In XML, this is the value between the rights-tags.
     * <br>e.g. Creative Commons, Attribution 3.0 Germany
     */
    @NonNull
    private String value;

    /**
     * An optional IETF language tag of the text.
     * <br>e.g. de, en-US
     */
    private String lang;

    /**
     * The URI of the license.
     * <br>e.g. http://creativecommons.org/licenses/by/3.0/de/deed.en
     */
    @SerializedName("rightsURI")
    private String uri;

    /**
     * A short, standardized version of the license name.
     * <br>e.g. CC-BY-3.0
     * <br><br>
     * It’s suggested to use the identifiers from the SPDX licence list (https://spdx.org/licenses/).
     */
    private String rightsIdentifier;

    /**
     * The name of the scheme.
     * <br>e.g. SPDX
     */
    private String rightsIdentifierScheme;

    /**
     * The URI of the rightsIdentifierScheme.
     * <br>e.g. https://spdx.org/licenses/
     */
    private String schemeURI;


    /**
     * Constructor that allows to set the language.
     *
     * @param value free text that describes the rights
     * @param lang a IETF language tag of the text
     */
    public Rights(final String value, final String lang)
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
