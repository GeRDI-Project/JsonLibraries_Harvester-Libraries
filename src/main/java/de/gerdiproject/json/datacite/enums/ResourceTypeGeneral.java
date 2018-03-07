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
package de.gerdiproject.json.datacite.enums;

/**
 * This enumeration describes the resource category.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Robin Weiss
 */
public enum ResourceTypeGeneral {
    /**
     * A series of visual representations imparting an impression of motion when shown in succession. May or may not include sound.
     */
    Audiovisual,

    /**
     * An aggregation of resources of various types, or a list of resources that form part of a publication. If a collection exists of a single type, use the single type to describe it.
     */
    Collection,


    /**
     * A factual and objective publication with a focused intent to identify and describe specific data, sets of data, or data collections
     * to facilitate discoverability.
     */
    DataPaper,


    /**
     * Data encoded in a defined structure.
     */
    Dataset,

    /**
     * A non‐persistent, time‐ based occurrence.
     */
    Event,

    /**
     * A visual representation other than text.
     */
    Image,

    /**
     * A resource requiring interaction from the user to be understood, executed, or experienced.
     */
    InteractiveResource,

    /**
     * An abstract, conceptual, graphical, mathematical or visualization model that represents empirical objects, phenomena, or physical processes.
     */
    Model,

    /**
     * An inanimate, three‐ dimensional object or substance.
     */
    PhysicalObject,

    /**
     * A system that provides one or more functions of value to the end‐user.
     */
    Service,

    /**
     * A computer program in source code (text) or compiled form.
     */
    Software,

    /**
     * A resource primarily intended to be heard.
     */
    Sound,

    /**
     * A resource consisting primarily of words for reading.
     */
    Text,

    /**
     * A structured series of steps which can be executed to produce a final outcome, allowing users a means to specify and enact their work in a more reproducible manner.
     */
    Workflow,

    /**
     * If selected, supply a value for ResourceType.
     */
    Other
}