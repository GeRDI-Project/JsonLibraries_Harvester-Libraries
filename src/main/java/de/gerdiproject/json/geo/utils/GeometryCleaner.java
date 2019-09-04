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

import java.util.Iterator;
import java.util.List;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
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
 * It is based on a solution offered in a StackOverflow thread.
 *
 * @see <a href="https://stackoverflow.com/questions/31473553/is-there-a-way-to-convert-a-self-intersecting-polygon-to-a-multipolygon-in-jts">StackOverflow</a>
 * @author https://stackoverflow.com/users/470062/tofarr
 * @author Robin Weiss
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeometryCleaner
{
    private final static String POLYGON_TYPE = Polygon.class.getSimpleName();
    private final static String MULTI_POLYGON_TYPE = MultiPolygon.class.getSimpleName();
    private final static String GEOMETRY_COLLECTION_TYPE = GeometryCollection.class.getSimpleName();

    /**
     * Creates a valid representation of a specified {@linkplain Geometry} object.
     * If the {@linkplain Geometry} is a {@linkplain Polygon} or {@linkplain MultiPolygon},
     * self intersections and other inconsistencies are fixed.
     * Otherwise the {@linkplain Geometry} is returned as is.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7946#section-3.2">https://tools.ietf.org/html/rfc7946#section-3.2</a>
     *
     * @param geo a possibly invalid {@linkplain Geometry} object
     * @return a valid {@linkplain Geometry} object
     */
    public static Geometry validate(final Geometry geo)
    {
        if (geo == null)
            return null;

        final Geometry validGeo;

        final String geoType = geo.getGeometryType();

        if (geoType.equalsIgnoreCase(POLYGON_TYPE) || geoType.equalsIgnoreCase(MULTI_POLYGON_TYPE)) {

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
        Geometry mergedPoly = null;

        final int polygonCount = geo.getNumGeometries();

        for (int i = 0; i < polygonCount; i++) {
            final Polygon polygon = (Polygon)geo.getGeometryN(i);

            Geometry poly = createValidPolygon(polygon.getExteriorRing());
            final int holeCount = polygon.getNumInteriorRing();

            for (int j = 0; j < holeCount; j++) {
                final Geometry hole = createValidPolygon(polygon.getInteriorRingN(j));
                poly = poly.symDifference(poly.intersection(hole));
            }

            if (mergedPoly == null)
                mergedPoly = poly;
            else
                mergedPoly = mergedPoly.union(poly);
        }

        return mergedPoly;
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
     * Creates a {@linkplain Polygon} or {@linkplain MultiPolygon} geometry from a specified
     * {@linkplain LineString}, fixing potentially overlapping shapes and self-intersections.
     *
     * @param lineString a {@linkplain LineString} part of a {@linkplain Polygon}
     *
     * @return a validated {@linkplain Polygon} or {@linkplain MultiPolygon} defined by
     *          the specified {@linkplain Polygonizer}, or null if there are no polygons
     */
    @SuppressWarnings("unchecked")
    private static Geometry createValidPolygon(final LineString lineString)
    {
        final Polygonizer polygonizer = new Polygonizer();
        polygonizer.add(validateLineString(lineString));

        final List<Polygon> polygons = (List<Polygon>)polygonizer.getPolygons();
        Geometry polygonGeo;

        // check if polygon is invalid
        if (polygons.isEmpty())
            polygonGeo = null;

        // fix multiple overlapping polygon shapes using sym difference
        else {
            final Iterator<Polygon> iter = polygons.iterator();
            polygonGeo = iter.next();

            while (iter.hasNext()) {

                final Polygon hole = iter.next();
                polygonGeo = polygonGeo.symDifference(hole);

                // edge case: remove dangling lines and/or points
                if (polygonGeo.getGeometryType().equalsIgnoreCase(GEOMETRY_COLLECTION_TYPE)) {
                    final int len = polygonGeo.getNumGeometries();

                    for (int i = 0; i < len; i++) {
                        final Geometry innerGeo = polygonGeo.getGeometryN(i);
                        final String innerGeoType = innerGeo.getGeometryType();

                        if (innerGeoType.equalsIgnoreCase(POLYGON_TYPE) || innerGeoType.equalsIgnoreCase(MULTI_POLYGON_TYPE)) {
                            polygonGeo = innerGeo;
                            break;
                        }
                    }
                }
            }
        }

        return polygonGeo;
    }
}
