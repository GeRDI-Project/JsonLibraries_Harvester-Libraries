/*
 *  Copyright © 2019 Robin Weiss (http://www.gerdi-project.de/)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
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
package de.gerdiproject.json.datacite.extension;

import java.util.Map;

import de.gerdiproject.harvest.utils.CollectionUtils;
import lombok.Data;

/**
 * This class serves as a JSON object that contains {@linkplain IDataCiteExtension}s.
 *
 * @author Robin Weiss
 */
@Data
public class DataCiteExtensions
{
    /**
     * -- GETTER --
     * Retrieves the map of GeRDI defined DataCite extensions.
     * @return the map of GeRDI defined DataCite extensions
     *
     * -- SETTER --
     * Sets the map of GeRDI defined DataCite extensions.
     * @param extensions the map of GeRDI defined DataCite extensions
     */
    private Map<String, IDataCiteExtension> extensions;


    /**
     * Adds an {@linkplain IDataCiteExtension} to extensions map.
     *
     * @param extension the extension to be added
     */
    public void add(final IDataCiteExtension extension)
    {
        this.extensions = CollectionUtils.addToMap(this.extensions, extension.getKey(), extension);
    }
}
