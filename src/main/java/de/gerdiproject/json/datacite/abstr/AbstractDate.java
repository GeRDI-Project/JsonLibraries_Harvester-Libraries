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
package de.gerdiproject.json.datacite.abstr;



import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.datacite.enums.DateType;
import lombok.Data;

/**
 * This JsonObject describes a date that has been relevant to the work.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
@Data
public abstract class AbstractDate implements ICleanable
{
    /**
     * The event that is marked by this date.
     */
    @SerializedName("dateType")
    private final DateType type;

    /**
     * Specific free text information about the date, if appropriate.
     */
    private String dateInformation;


    /**
     * Returns the value of the date. In XML, this is the value between the date-tags.
     *
     * @return the value of the date
     */
    abstract public String getValue();


    /**
     * Changes the date value. In XML, this is the value between the date-tags.
     *
     * @param value the new value
     */
    abstract public void setValue(String value);


    @Override
    public boolean clean()
    {
        // nothing to clean, but if the date value is null, the date is invalid
        return getValue() != null;
    }
}
