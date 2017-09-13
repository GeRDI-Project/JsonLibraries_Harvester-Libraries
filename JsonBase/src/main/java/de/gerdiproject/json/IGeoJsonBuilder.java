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


/**
 * This interface provides helper methods for creating well-formed
 * geo-json-objects.
 *
 * @author Robin Weiss
 *
 */
public interface IGeoJsonBuilder
{
    /**
     * Creates a geo json polygon using only two points.
     *
     * @param longitudeWest
     *            the longitude that describes the western edge of the rectangle
     * @param latitudeNorth
     *            the longitude that describes the eastern edge of the rectangle
     * @param longitudeEast
     *            the longitude that describes the northern edge of the
     *            rectangle
     * @param latitudeSouth
     *            the longitude that describes the southern edge of the
     *            rectangle
     * @return a geojson polygon that represents a rectangle
     */
    IJsonObject createRectangle(double longitudeWest, double latitudeNorth, double longitudeEast,
                                double latitudeSouth);


    /**
     * Creates a geo json polygon that represents a horizontal ring around the
     * globe.
     *
     * @param latitudeNorth
     *            the northern border of the ring
     * @param latitudeSouth
     *            the southern border of the ring
     * @return a geo json polygon that represents a horizontal ring around the
     *         globe
     */
    IJsonObject createHorizontalRing(double latitudeNorth, double latitudeSouth);


    /**
     * Creates a geo json polygon that represents a vertical ring around the
     * globe.
     *
     * @param longitudeWest
     *            the western border of the ring
     * @param longitudeEast
     *            the eastern border of the ring
     * @return a geo json polygon that represents a vertical ring around the
     *         globe
     */
    IJsonObject createVerticalRing(double longitudeWest, double longitudeEast);
}
