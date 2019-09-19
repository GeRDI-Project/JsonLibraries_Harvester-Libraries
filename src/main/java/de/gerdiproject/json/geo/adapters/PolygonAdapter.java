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
package de.gerdiproject.json.geo.adapters;

import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

/**
 * This adapter can serialize and deserialize {@linkplain Polygon} GeoJson objects.
 *
 * @author Robin Weiss
 */
public class PolygonAdapter extends AbstractGeometryAdapter<Polygon>
{
    /**
     * Forwarded super Constructor.
     *
     * @param geoFactory a factory for creating {@linkplain Geometry} objects
     */
    public PolygonAdapter(final GeometryFactory geoFactory)
    {
        super(geoFactory);
    }


    @Override
    protected JsonArray serializeCoordinates(final Polygon src)
    {
        final LineString hull = src.getExteriorRing();

        if (hull == null || hull.getCoordinates().length < 4)
            return null;

        final JsonArray jsonCoordinates = new JsonArray();

        // add Polygon hull
        jsonCoordinates.add(coordinatesToJsonArray(hull.getCoordinates()));

        // add Polygon holes
        final int holeCount = src.getNumInteriorRing();

        for (int i = 0; i < holeCount; i++) {
            final Coordinate[] holeCoordinates = src.getInteriorRingN(i).getCoordinates();
            jsonCoordinates.add(coordinatesToJsonArray(holeCoordinates));
        }

        return jsonCoordinates;
    }


    @Override
    protected Polygon deserializeGeometry(final JsonArray jsonCoordinates, final GeometryFactory factory)
    {
        final Iterator<JsonElement> jsonIter = jsonCoordinates.iterator();

        // add Polygon hull
        final Coordinate[] hullCoordinates = jsonArrayToCoordinates(jsonIter.next().getAsJsonArray());
        final LinearRing hull = factory.createLinearRing(hullCoordinates);

        // add Polygon holes
        final int holeCount = jsonCoordinates.size() - 1;
        final LinearRing[] holes = new  LinearRing[holeCount];

        for (int i = 0; i < holeCount; i++) {
            final Coordinate[] holeCoordinates = jsonArrayToCoordinates(jsonIter.next().getAsJsonArray());
            holes[i] = factory.createLinearRing(holeCoordinates);
        }

        return factory.createPolygon(hull, holes);
    }
}
