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

import java.util.Collection;
import java.util.Iterator;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.operation.polygonize.Polygonizer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This helper class fixes invalid {@linkplain Geometry} objects by splitting up self-intersections.
 *
 *
 * @see <a href="https://stackoverflow.com/questions/31473553/is-there-a-way-to-convert-a-self-intersecting-polygon-to-a-multipolygon-in-jts">StackOverflow</a>
 * @author https://stackoverflow.com/users/470062/tofarr
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeometryCleaner
{
    /**
     * Creates a valid representation of a specified {@linkplain Geometry} object.
     * If the {@linkplain Geometry} is a {@linkplain Polygon} or {@linkplain MultiPolygon},
     * self intersections and other inconsistencies are fixed.
     * Otherwise the {@linkplain Geometry} is returned as is.
     *
     * @param geo a possibly invalid {@linkplain Geometry} object
     * @return a valid {@linkplain Geometry} object
     */
    public static Geometry validate(final Geometry geo)
    {
        final Geometry validGeo;

        if (geo instanceof Polygon || geo instanceof MultiPolygon) {

            // normalize valid polygons in order to fix wrongly ordered rings
            if (geo.isValid())
                validGeo = geo.norm();
            else
                validGeo = validatePolygon(geo);

        } else // disregard non-polygonial Geometries
            validGeo = geo;

        return validGeo;
    }


    /**
     * Creates a valid representation of a specified {@linkplain Polygon} or {@linkplain MultiPolygon},
     * fixing self intersections and other inconsistencies.
     * @param geo a possibly invalid {@linkplain Polygon} or {@linkplain MultiPolygon}
     *
     * @return a validated {@linkplain Geometry} object
     */
    private static Geometry validatePolygon(final Geometry geo)
    {
        final Polygonizer polygonizer = new Polygonizer();

        final int polygonCount = geo.getNumGeometries();

        for (int i = 0; i < polygonCount; i++) {
            final Polygon polygon = (Polygon)geo.getGeometryN(i);

            final Geometry exteriorShape = validateLineString(polygon.getExteriorRing());
            polygonizer.add(exteriorShape);

            final int shapeCount = polygon.getNumInteriorRing();

            for (int j = 0; j < shapeCount; j++) {
                final Geometry interiorShape = validateLineString(polygon.getInteriorRingN(j));
                polygonizer.add(interiorShape);
            }
        }

        return createValidPolygon(polygonizer);
    }


    /**
     * Validates a {@linkplain LineString}, adding self-intersections as
     * explicit points.
     *
     * @param lineString the {@linkplain LineString} that is to be validated
     *
     * @return a validated {@linkplain LineString}
     */
    private static Geometry validateLineString(final LineString lineString)
    {
        final GeometryFactory factory = lineString.getFactory();

        // convert LinearRing to LineString
        final LineString realLineString;

        if (lineString instanceof LinearRing)
            realLineString = factory.createLineString(lineString.getCoordinateSequence());
        else
            realLineString = lineString;

        // unioning the LineString with its first point makes self-intersections explicit
        final Point point = factory.createPoint(realLineString.getCoordinateN(0));
        return realLineString.union(point);
    }


    /**
     * Retrieves {@linkplain Polygon} geometry from a {@linkplain Polygonizer},
     * fixing potentially overlapping shapes.
     *
     * @param polygonizer a {@linkplain Polygonizer} that contains a
     *         {@linkplain Collection} of validated {@linkplain Polygons}
     *
     * @return a validated {@linkplain Polygon} or {@linkplain MultiPolygon} defined by
     *          the specified {@linkplain Polygonizer}, or null if there are no polygons
     */
    @SuppressWarnings("unchecked") // polygonizer.getPolygons() returns the correct types
    private static Geometry createValidPolygon(final Polygonizer polygonizer)
    {
        final Collection<Polygon> polygons = polygonizer.getPolygons();
        Geometry polygonGeo;

        // check if polygon is invalid
        if (polygons.isEmpty())
            polygonGeo = null;

        // fix multiple overlapping polygon shapes using sym difference
        else {
            final Iterator<Polygon> iter = polygons.iterator();
            polygonGeo = iter.next();

            while (iter.hasNext())
                polygonGeo = polygonGeo.symDifference(iter.next());
        }

        return polygonGeo;
    }
}
