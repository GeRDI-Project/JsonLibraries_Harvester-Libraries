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

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.geo.Point;
import de.gerdiproject.json.geo.Polygon;

/**
 * Spatial region or named place where the data was gathered or about which the data is focused.
 * This schema deviates from the DataCite schema, mapping the geo coordinates to GeoJson objects
 * that can be read by ElasticSearch.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 * @author Mathis Neumann, Robin Weiss
 */
public class GeoLocation implements ICleanable
{
    /**
     * Free text description of the geographic location.
     */
    private String geoLocationPlace;

    /**
     * A point location in space.
     */
    private GeoJson geoLocationPoint;

    /**
     *  The spatial limits of a box.
     *  It is somewhat redundant with the polygon, however,
     *  it is compliant to the DataCite schema.
     */
    private GeoJson geoLocationBox;

    /**
     *  A list of drawn polygon areas, defined by sets of points and
     *  lines connecting the points in closed chains.
     */
    @SerializedName("geoLocationPolygon")
    private List<GeoJson> geoLocationPolygons;


    /**
     * Returns the description of the geographic location.
     *
     * @return a free text description of the location
     */
    public String getPlace()
    {
        return geoLocationPlace;
    }

    /**
     * Changes the description of the geographic location.
     *
     * @param place a free text description of the location
     */
    public void setPlace(String place)
    {
        this.geoLocationPlace = place;
    }


    /**
     * Returns a point location in space.
     *
     * @return a point location
     */
    public GeoJson getPoint()
    {
        return geoLocationPoint;
    }


    /**
     * Changes the point location.
     *
     * @param point a point location
     */
    public void setPoint(GeoJson point)
    {
        this.geoLocationPoint = point;
    }


    /**
     * Returns a list of drawn polygon areas, defined by sets of
     * points and lines connecting the points in closed chains.
     *
     * @return a list of drawn polygon areas
     */
    public List<GeoJson> getPolygons()
    {
        return geoLocationPolygons;
    }


    /**
     * Changes the list of drawn polygon areas, defined by sets of
     * points and lines connecting the points in closed chains.
     *
     * @param polygons a list of drawn polygon areas
     */
    public void setPolygons(List<GeoJson> polygons)
    {
        this.geoLocationPolygons = polygons;
    }


    /**
     * Returns the spatial limits of a box.
     *
     * @return the spatial limits of a box
     */
    public GeoJson getBox()
    {
        return geoLocationBox;
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
        this.geoLocationBox = new GeoJson(new Polygon(boxShape));
    }


    /**
     * Cleans each GeoJson attached to the location.
     * Each invalid GeoJsons will become null.
     */
    @Override
    public void clean()
    {
        if (geoLocationPoint != null) {
            geoLocationPoint.clean();

            // remove geoJson if it became invalid
            if (!geoLocationPoint.isValid())
                geoLocationPoint = null;
        }

        // remove each polygon, if it is invalid
        if (geoLocationPolygons != null) {
            int i = geoLocationPolygons.size();

            while (i != 0) {
                i--;
                GeoJson geo = geoLocationPolygons.get(i);
                geo.clean();

                if (!geo.isValid())
                    geoLocationPolygons.remove(i);
            }

            // nullify the whole polygon list, if it is now empty
            if (geoLocationPolygons.isEmpty())
                geoLocationPolygons = null;
        }

        if (geoLocationBox != null) {
            geoLocationBox.clean();

            // remove geoJson if it became invalid
            if (!geoLocationBox.isValid())
                geoLocationBox = null;
        }
    }

    /**
     * Returns true if the GeoLocation has any geographical data.
     *
     * @return true, if the GeoLocation has any geographical data
     */
    public boolean isValid()
    {
        return geoLocationBox != null || (geoLocationPolygons != null && !geoLocationPolygons.isEmpty()) || geoLocationPoint != null;
    }
}
