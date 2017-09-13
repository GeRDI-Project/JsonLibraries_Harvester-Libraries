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

import java.util.Arrays;
import java.util.List;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.geo.Point;
import de.gerdiproject.json.geo.Polygon;

/**
 * Spatial region or named place where the data was gathered or about which the data is focused.
 * This schema deviates from the DataCite schema, mapping the geo coordinates to GeoJson objects
 * that can be read by ElasticSearch.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.0/doc/DataCite-MetadataKernel_v4.0.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class GeoLocation implements ICleanable
{
    /**
     * Description of the geographic location.
     */
    private String place;

    /**
     * A point location in space.
     */
    private GeoJson point;

    /**
     *  The spatial limits of a box.
     *  It is somewhat redundant with the polygon, however,
     *  it is compliant to the DataCite schema.
     */
    private GeoJson box;

    /**
     *  A drawn polygon area, defined by a set of points and
     *  lines connecting the points in a closed chain.
     */
    private GeoJson polygon;


    /**
     * Returns the description of the geographic location.
     *
     * @return a free text description of the location
     */
    public String getPlace()
    {
        return place;
    }

    /**
     * Changes the description of the geographic location.
     *
     * @param place a free text description of the location
     */
    public void setPlace(String place)
    {
        this.place = place;
    }


    /**
     * Returns a point location in space.
     *
     * @return a point location
     */
    public GeoJson getPoint()
    {
        return point;
    }


    /**
     * Changes the point location.
     *
     * @param point a point location
     */
    public void setPoint(GeoJson point)
    {
        this.point = point;
    }


    /**
     * Returns a drawn polygon area, defined by a set of
     * points and lines connecting the points in a closed chain.
     *
     * @return a polygon area
     */
    public GeoJson getPolygon()
    {
        return polygon;
    }


    /**
     * Changes the drawn polygon area, defined by a set of
     * points and lines connecting the points in a closed chain.
     * The first and last point must be the same.
     *
     * @param polygon the polygon area
     */
    public void setPolygon(GeoJson polygon)
    {
        this.polygon = polygon;
    }


    /**
     * Returns the spatial limits of a box.
     *
     * @return the spatial limits of a box
     */
    public GeoJson getBox()
    {
        return box;
    }


    /**
     * Changes the spatial limits of a box, defining its shape.
     *
     * @param westBoundLongitude western longitudinal dimension of the box
     * @param eastBoundLongitude eastern longitudinal dimension of the box
     * @param southBoundLatitude southern latitudinal dimension of the box
     * @param northBoundLatitude northern latitudinal dimension of the box
     */
    public void setBox(double westBoundLongitude, double eastBoundLongitude, double southBoundLatitude, double northBoundLatitude)
    {
        List<Point> boxShape = Arrays.asList(
                                   new Point(westBoundLongitude, northBoundLatitude),
                                   new Point(eastBoundLongitude, northBoundLatitude),
                                   new Point(eastBoundLongitude, southBoundLatitude),
                                   new Point(westBoundLongitude, southBoundLatitude),
                                   new Point(westBoundLongitude, northBoundLatitude)
                               );
        this.box = new GeoJson(new Polygon(boxShape));
    }


    @Override
    public void clean()
    {
        if (point != null)
            point.clean();

        if (polygon != null)
            polygon.clean();

        if (box != null)
            box.clean();
    }
}
