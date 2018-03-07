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
package de.gerdiproject.json.datacite.extension;

/**
 * A downloadable file.
 *
 * This object is NOT part of the original DataCite schema.
 * @author Mathis Neumann, Robin Weiss
 */
public class ResearchData
{
    /**
     * The URL that is used to access the file.
     * <br>e.g. http://fenixservices.fao.org/faostat/static/documents/QC/QC_methodology_e.pdf
     */
    private String researchDataURL;

    /**
     * Human readable name for the file.
     * <br>e.g. "Methodology - Crops Primary"
     */
    private String researchDataLabel;

    /**
     * Locally (within this document) unique identifier for the file.
     * The identifier is generated from a hash value of the target URL.
     */
    private String researchDataIdentifier;

    /**
     * File format, extension or mimetype.
     * <br>e.g. pdf, application/json
     */
    private String researchDataType;


    /**
     * Simple constructor that requires all mandatory fields.
     * @param url the file URL
     * @param label the file display name
     */
    public ResearchData(String url, String label)
    {
        this.researchDataURL = url;
        this.researchDataLabel = label;
        this.researchDataIdentifier = String.valueOf(url.hashCode());
    }


    /**
     * Returns the URL that is used to access the file.
     *
     * @return the URL that is used to access the file
     */
    public String getUrl()
    {
        return researchDataURL;
    }


    /**
     * Changes the URL that is used to access the file.
     * <br>e.g. http://fenixservices.fao.org/faostat/static/documents/QC/QC_methodology_e.pdf
     *
     * @param url the URL that is used to access the file
     */
    public void setUrl(String url)
    {
        this.researchDataURL = url;
        this.researchDataIdentifier = String.valueOf(url.hashCode());
    }


    /**
     * Returns the human readable name for the file.
     *
     * @return the human readable name for the file
     */
    public String getLabel()
    {
        return researchDataLabel;
    }


    /**
     * Changes the human readable name for the file.
     * <br>e.g. "Methodology - Crops Primary"
     *
     * @param label a human readable name for the file
     */
    public void setLabel(String label)
    {
        this.researchDataLabel = label;
    }

    /**
     * Returns the locally unique identifier for the file.
     * The identifier is generated from a hash value of the target URL.
     *
     * @return the locally unique identifier for the file
     */
    public String getIdentifier()
    {
        return researchDataIdentifier;
    }


    /**
     * Returns the file format, extension or mimetype of the file.
     *
     * @return a file format, extension or mimetype
     */
    public String getType()
    {
        return researchDataType;
    }


    /**
     * Changes the file format, extension or mimetype.
     * <br>e.g. pdf, application/json
     *
     * @param type a file format, extension or mimetype
     */
    public void setType(String type)
    {
        this.researchDataType = type;
    }
}
