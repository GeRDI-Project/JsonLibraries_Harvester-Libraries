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
package de.gerdiproject.json.datacite.extension.generic;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * A downloadable file.
 *
 * This object is NOT part of the original DataCite schema.
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data
public class ResearchData
{
    /**
     * The URL that is used to access the file.
     * <br>e.g. http://fenixservices.fao.org/faostat/static/documents/QC/QC_methodology_e.pdf
     */
    @SerializedName("researchDataURL")
    private final String url;

    /**
     * Human readable name for the file.
     * <br>e.g. "Methodology - Crops Primary"
     */
    @SerializedName("researchDataLabel")
    private final String label;

    /**
     * Locally (within this document) unique identifier for the file.
     * The identifier is generated from a hash value of the target URL.
     */
    @SerializedName("researchDataIdentifier")
    private final String identifier;

    /**
     * File format, extension or mimetype.
     * <br>e.g. pdf, application/json
     */
    @SerializedName("researchDataType")
    private String type;


    /**
     * Simple constructor that requires all mandatory fields.
     * @param url the file URL
     * @param label the file display name
     */
    public ResearchData(String url, String label)
    {
        this.url = url;
        this.label = label;
        this.identifier = String.valueOf(url.hashCode());
    }


    /**
     * Constructor that requires all fields.
     *
     * @param url the file URL
     * @param label the file display name
     * @param type file format, extension or mimetype
     */
    public ResearchData(String url, String label, String type)
    {
        this(url, label);
        this.type = type;
    }
}
