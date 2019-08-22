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

import lombok.RequiredArgsConstructor;

/**
 *
 * @see <a href="https://stackoverflow.com/questions/31473553/is-there-a-way-to-convert-a-self-intersecting-polygon-to-a-multipolygon-in-jts">StackOverflow</a>
 * @author https://stackoverflow.com/users/470062/tofarr
 *
 */
@RequiredArgsConstructor
public class GeometryCleaner
{
    /**
     * Get / create a valid version of the geometry given. If the geometry is a polygon or multi polygon, self intersections /
     * inconsistencies are fixed. Otherwise the geometry is returned.
     *
     * @param geom TODO
     * @return a geometry
     */
    @SuppressWarnings("unchecked") // polygonizer.getPolygons() returns the correct types
    public Geometry validate(Geometry geom)
    {
        if (geom instanceof Polygon) {
            if (geom.isValid()) {
                geom.normalize(); // validate does not pick up rings in the wrong order - this will fix that
                return geom; // If the polygon is valid just return it
            }

            Polygonizer polygonizer = new Polygonizer();
            addPolygon((Polygon)geom, polygonizer);
            return toPolygonGeometry(polygonizer.getPolygons());

        } else if (geom instanceof MultiPolygon) {
            if (geom.isValid()) {
                geom.normalize(); // validate does not pick up rings in the wrong order - this will fix that
                return geom; // If the multipolygon is valid just return it
            }

            Polygonizer polygonizer = new Polygonizer();

            for (int n = geom.getNumGeometries(); n-- > 0;)
                addPolygon((Polygon)geom.getGeometryN(n), polygonizer);

            return toPolygonGeometry(polygonizer.getPolygons());
        } else
            return geom; // In my case, I only care about polygon / multipolygon geometries
    }

    
    /**
     * Add all line strings from the polygon given to the polygonizer given
     *
     * @param polygon polygon from which to extract line strings
     * @param polygonizer polygonizer
     */
    private void addPolygon(Polygon polygon, Polygonizer polygonizer)
    {
        addLineString(polygon.getExteriorRing(), polygonizer);

        for (int n = polygon.getNumInteriorRing(); n-- > 0;)
            addLineString(polygon.getInteriorRingN(n), polygonizer);
    }

    
    /**
     * Add the linestring given to the polygonizer
     *
     * @param linestring line string
     * @param polygonizer polygonizer
     */
    private void addLineString(LineString lineString, Polygonizer polygonizer)
    {
        final GeometryFactory factory = lineString.getFactory();
        
        // LinearRings are treated differently to line strings : we need a LineString NOT a LinearRing
        if (lineString instanceof LinearRing) 
            lineString = factory.createLineString(lineString.getCoordinateSequence());

        // unioning the linestring with the point makes any self intersections explicit.
        final Point point = factory.createPoint(lineString.getCoordinateN(0));
        final Geometry toAdd = lineString.union(point);

        //Add result to polygonizer
        polygonizer.add(toAdd);
    }
    

    /**
     * Get a geometry from a collection of polygons.
     *
     * @param polygons collection
     * @return null if there were no polygons, the polygon if there was only one, or a MultiPolygon containing all polygons otherwise
     */
    private Geometry toPolygonGeometry(Collection<Polygon> polygons)
    {
        switch (polygons.size()) {
            case 0:
                return null; // No valid polygons!

            case 1:
                return polygons.iterator().next(); // single polygon - no need to wrap

            default:
                //polygons may still overlap! Need to sym difference them
                Iterator<Polygon> iter = polygons.iterator();
                Geometry ret = iter.next();

                while (iter.hasNext())
                    ret = ret.symDifference(iter.next());

                return ret;
        }
    }
}
