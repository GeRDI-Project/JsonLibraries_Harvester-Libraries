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
package de.gerdiproject.json.geo.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

/**
 * @author Robin Weiss
 *
 */
public class GeoJsonUtils
{
    private GeometryFactory geometryFactory = new GeometryFactory();

    public Polygon createBox(
        final double westBoundLongitude,
        final double eastBoundLongitude,
        final double southBoundLatitude,
        final double northBoundLatitude)
    {

        final Coordinate[] coordinates = {
            new Coordinate(westBoundLongitude, northBoundLatitude),
            new Coordinate(eastBoundLongitude, northBoundLatitude),
            new Coordinate(eastBoundLongitude, southBoundLatitude),
            new Coordinate(westBoundLongitude, southBoundLatitude),
            new Coordinate(westBoundLongitude, northBoundLatitude)
        };
        return geometryFactory.createPolygon(coordinates);
    }


    public Point createPoint(final double longitude, final double latitude)
    {
        return geometryFactory.createPoint(
                   new Coordinate(longitude, latitude));
    }
}
