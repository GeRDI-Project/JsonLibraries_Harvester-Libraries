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
package de.gerdiproject.json.datacite;

import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.geo.Point;
import de.gerdiproject.json.geo.Polygon;

/**
 * Spatial region or named place where the data was gathered or about which the data is focused.
 * NOTE: every property has to manually mapped to the DataCite schema
 * @author Mathis Neumann, Robin Weiss
 *
 */
public class GeoLocation
{
    /**
     * Free text name of a location - geoLocationPlace in DataCite schema
     */
    private String place;

    /**
     * geoLocationPlace in DataCite schema
     * NOTE: manually map! Represented as object with lat, lon, as string "lat,lon", as geohash or typle [lon,lat] (NOTE: reverse order to conform with GeoJSON)
     * DataCite schema: pointLongitude- and pointLatitude-tags in XML (might also allow string "lat lon")
     */
    private GeoJson<Point> point;

    /**
     *  first and last point must match (both ES and DataCite), at least 4 points (compatible with DataCite)
     */
    private GeoJson<Polygon> polygon;


    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public GeoJson<Point> getPoint()
    {
        return point;
    }

    public void setPoint(GeoJson<Point> point)
    {
        this.point = point;
    }

    public GeoJson<Polygon> getPolygon()
    {
        return polygon;
    }

    public void setPolygon(GeoJson<Polygon> polygon)
    {
        this.polygon = polygon;
    }

    // NOTE: datacite includes a "box" geoJson. this is redundant with the polygon type and was therefore not included


}
