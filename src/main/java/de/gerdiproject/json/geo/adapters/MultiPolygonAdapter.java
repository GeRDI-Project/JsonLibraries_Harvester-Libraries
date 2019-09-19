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
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

/**
 * This adapter can serialize and deserialize {@linkplain MultiPolygon} GeoJson objects.
 *
 * @author Robin Weiss
 */
public class MultiPolygonAdapter extends AbstractGeometryAdapter<MultiPolygon>
{
    private final PolygonAdapter polygonAdapter;

    /**
     * Forwarded super Constructor.
     *
     * @param geoFactory a factory for creating {@linkplain Geometry} objects
     */
    public MultiPolygonAdapter(final GeometryFactory geoFactory)
    {
        super(geoFactory);
        this.polygonAdapter = new PolygonAdapter(geoFactory);
    }


    @Override
    protected JsonArray serializeCoordinates(final MultiPolygon src)
    {
        final int size = src.getNumGeometries();

        if (size == 0)
            return null;

        final JsonArray jsonCoordinates = new JsonArray();

        for (int i = 0; i < size; i++) {
            final Polygon poly = (Polygon) src.getGeometryN(i);
            final JsonArray polyCoordinates = polygonAdapter.serializeCoordinates(poly);
            jsonCoordinates.add(polyCoordinates);
        }

        return jsonCoordinates;
    }


    @Override
    protected MultiPolygon deserializeGeometry(final JsonArray jsonCoordinates, final GeometryFactory factory)
    {
        final Polygon[] polygons = new  Polygon[jsonCoordinates.size()];

        int i = 0;

        for (final JsonElement ele : jsonCoordinates)
            polygons[i++] = polygonAdapter.deserializeGeometry(ele.getAsJsonArray(), factory);

        return factory.createMultiPolygon(polygons);
    }
}
