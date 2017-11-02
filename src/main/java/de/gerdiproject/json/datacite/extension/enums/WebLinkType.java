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
package de.gerdiproject.json.datacite.extension.enums;

/**
 * The kind of data or webpage which is linked.
 * This enumeration is incomplete, as more types will be added in accordance with the requirements.
 *
 * @author Robin Weiss
 */
public enum WebLinkType {
    /**
     * A URL that points to the website at which the resource data can be viewed.<br>
     * e.g. http://www.arcgis.com/home/item.html?id=aa9a3a2dc6924f46adc5a999787f7961
     */
    ViewURL,

    /**
     * A URL that points to the raw resource data.<br>
     * e.g. http://api.seaaroundus.org/api/v1/taxa/600009
     */
    SourceURL,

    /**
     * A URL that points to a (small) logo of the organisation that provides the resource data.<br>
     * e.g. https://livingatlas.arcgis.com/emu/tailcoat/images/tailcoat/logo-esri.png
     */
    ProviderLogoURL,

    /**
     * A URL that points to a small image or thumbnail that represents the resource data itself.<br>
     * e.g. http://arcgis.com/sharing/rest/content/items/aa9a3a2dc6924f46adc5a999787f7961/info/thumbnail/ago_downloaded.png
     */
    ThumbnailURL,

    /**
     * A URL that points to a website that is related to the resource.<br>
     * e.g. http://webarchive.iiasa.ac.at/Research/LUC/External-World-soil-database/HWSD_Documentation.pdf
     */
    Related
}
