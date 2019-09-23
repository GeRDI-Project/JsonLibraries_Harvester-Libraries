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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;

/**
 * This adapter can serialize and deserialize {@linkplain MultiLineString} GeoJson objects.
 *
 * @author Robin Weiss
 */
public class MultiLineStringAdapter extends AbstractGeometryAdapter<MultiLineString>
{
    /**
     * Forwarded super Constructor.
     *
     * @param geoFactory a factory for creating {@linkplain Geometry} objects
     */
    public MultiLineStringAdapter(final GeometryFactory geoFactory)
    {
        super(geoFactory);
    }


    @Override
    protected JsonArray serializeCoordinates(final MultiLineString src)
    {
        final JsonArray jsonCoordinates = new JsonArray();

        final int size = src.getNumGeometries();

        for (int i = 0; i < size; i++) {
            final Geometry lineString = src.getGeometryN(i);
            final JsonArray lineStringCoordinates = coordinatesToJsonArray(lineString.getCoordinates());
            jsonCoordinates.add(lineStringCoordinates);
        }

        return jsonCoordinates.size() == 0 ? null : jsonCoordinates;
    }


    @Override
    protected MultiLineString deserializeGeometry(final JsonArray jsonCoordinates, final GeometryFactory factory)
    {
        final int size = jsonCoordinates.size();
        final LineString[] lineStrings = new  LineString[size];

        int i = 0;

        for (final JsonElement lineStringEle : jsonCoordinates) {
            final Coordinate[] lineStringCoordinates = jsonArrayToCoordinates(lineStringEle.getAsJsonArray());
            lineStrings[i++] = factory.createLineString(lineStringCoordinates);
        }

        return factory.createMultiLineString(lineStrings);
    }
}
