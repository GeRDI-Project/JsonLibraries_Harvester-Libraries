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
package de.gerdiproject.json.geo;

import java.util.Collection;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * A set of {@link LineString}s.
 *
 * @author Robin Weiss
 */
public class MultiLineString extends LinkedList<LineString> implements IGeoCoordinates
{
    private static final long serialVersionUID = -4567075857240196220L;


    /**
     * Constructor that requires an arbitrary number of line strings.
     *
     * @param lineStrings an arbitrary number of line strings
     */
    public MultiLineString(LineString... lineStrings)
    {
        super();

        for (LineString l : lineStrings)
            add(l);
    }


    /**
     * Constructor that requires any kind of collection of line strings.
     *
     * @param lineStrings a Collection of line strings
     */
    public MultiLineString(Collection<LineString> lineStrings)
    {
        super(lineStrings);
    }


    /**
     * Constructor that requires a JsonArray of coordinates.
     *
     * @param array a JsonArray of points
     */
    public MultiLineString(JsonArray array)
    {
        super();
        array.forEach((JsonElement ele) ->
                      add(new LineString(ele.getAsJsonArray()))
                     );
    }
}
