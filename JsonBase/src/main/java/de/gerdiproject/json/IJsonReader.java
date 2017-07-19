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


import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;


/**
 * An interface for a wrapper class for readers that parse the read content to
 * JSON objects and arrays.
 *
 * @author Robin Weiss
 *
 */
public interface IJsonReader
{
    /**
     * Initializes the JSON reader using another reader as input.
     *
     * @param reader
     *            a reader that provides a JSON string
     */
    public void init(Reader reader);


    /**
     * Closes the reader.
     *
     * @throws IOException
     *             if an I/O error occurs
     */
    public void close() throws IOException;


    /**
     * Reads a JSON structure from the reader.
     *
     * @return a JSON structure
     * @throws IllegalStateException
     *             this exception is thrown if no reader was set up via init()
     *             before this method was called
     * @throws IOException
     *             this exception can be caused by the reader
     * @throws ParseException
     *             this exception is thrown if parsing the JSON string failed
     */
    public IJson read() throws IllegalStateException, IOException, ParseException;


    /**
     * Reads a JSON array from the reader.
     *
     * @return a JSON object
     * @throws IllegalStateException
     *             this exception is thrown if no reader was set up via init()
     *             before this method was called
     * @throws ClassCastException
     *             this exception is thrown if the parsed object is not a JSON
     *             array
     * @throws IOException
     *             this exception can be caused by the reader
     * @throws ParseException
     *             this exception is thrown if parsing the JSON string failed
     */
    public IJsonArray readArray() throws IllegalStateException, ClassCastException, IOException, ParseException;


    /**
     * Reads a JSON object from the reader.
     *
     * @return a JSON object
     * @throws IllegalStateException
     *             this exception is thrown if no reader was set up via init()
     *             before this method was called
     * @throws ClassCastException
     *             this exception is thrown if the parsed object is not a JSON
     *             object
     * @throws IOException
     *             this exception can be caused by the reader
     * @throws ParseException
     *             this exception is thrown if parsing the JSON string failed
     */
    public IJsonObject readObject() throws IllegalStateException, ClassCastException, IOException, ParseException;
}
