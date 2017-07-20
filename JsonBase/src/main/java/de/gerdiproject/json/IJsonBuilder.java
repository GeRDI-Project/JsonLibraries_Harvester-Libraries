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
package de.gerdiproject.json;


import java.io.Reader;


/**
 * An interface for a builder class that offers methods for creating instances
 * of JSON classes.
 *
 * @author Robin Weiss
 *
 */
public interface IJsonBuilder
{
    /**
     * Creates a JSON reader.
     *
     * @param reader
     *            a reader that will be wrapped by the JSON reader.
     * @return an instance of a JSON reader.
     */
    public IJsonReader createReader(Reader reader);


    /**
     * Creates an empty JSON object.
     *
     * @return a new empty JSON object
     */
    public IJsonObject createObject();


    /**
     * Creates an empty JSON array.
     *
     * @return a new empty JSON array
     */
    public IJsonArray createArray();


    /**
     * Retrieves all Objects from iteratable objects and adds them to a single
     * Json-Array, removing duplicates.
     *
     * @param iterable
     *            an arbitrary number of iterable objects that are to be merged
     *
     * @return a Json array that contains the content of all lists without
     *         duplicates
     */
    public IJsonArray createArrayFromLists(Iterable<?>... iterable);


    /**
     * Creates a JSON array out of objects.
     *
     * @param elements
     *            an arbitrary number of objects
     *
     * @return a Json array that contains all objects
     */
    public IJsonArray createArrayFromObjects(Object... elements);


    /**
     * Retrieves a builder of geo json objects.
     *
     * @return a builder of geo json objects
     */
    public IGeoJsonBuilder geoBuilder();
}
