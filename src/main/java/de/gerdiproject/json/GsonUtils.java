/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gerdiproject.json;


import com.google.gson.GsonBuilder;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;

import de.gerdiproject.json.datacite.Date;
import de.gerdiproject.json.datacite.DateRange;
import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.adapters.DataCiteExtensionsAdapter;
import de.gerdiproject.json.datacite.adapters.DateAdapter;
import de.gerdiproject.json.datacite.adapters.FunderIdentifierTypeAdapter;
import de.gerdiproject.json.datacite.enums.FunderIdentifierType;
import de.gerdiproject.json.datacite.extension.DataCiteExtensions;
import de.gerdiproject.json.datacite.extension.adapters.SoepDataCiteExtensionAdapter;
import de.gerdiproject.json.datacite.extension.generic.AbstractResearch;
import de.gerdiproject.json.datacite.extension.generic.ResearchArea;
import de.gerdiproject.json.datacite.extension.generic.ResearchDiscipline;
import de.gerdiproject.json.datacite.extension.generic.adapter.ResearchAdapter;
import de.gerdiproject.json.datacite.extension.soep.SoepDataCiteExtension;
import de.gerdiproject.json.geo.adapters.GeometryAdapter;
import de.gerdiproject.json.geo.adapters.LineStringAdapter;
import de.gerdiproject.json.geo.adapters.MultiLineStringAdapter;
import de.gerdiproject.json.geo.adapters.MultiPointAdapter;
import de.gerdiproject.json.geo.adapters.MultiPolygonAdapter;
import de.gerdiproject.json.geo.adapters.PointAdapter;
import de.gerdiproject.json.geo.adapters.PolygonAdapter;
import de.gerdiproject.json.geo.constants.GeometryConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * This is a static utility class that provides methods for creating {@linkplain GsonBuilder}s.
 *
 * @author Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GsonUtils
{
    /**
     * Creates a GsonBuilder that is able to (de-) serialize JSON objects of the
     * GeRDI metadata schema.
     *
     * @return a GsonBuilder that is able to (de-) serialize JSON objects of the
     * GeRDI metadata schema
     */
    public static GsonBuilder createGerdiDocumentGsonBuilder()
    {
        return createGerdiDocumentGsonBuilder(createGeoJsonGsonBuilder());
    }


    /**
     * Creates a GsonBuilder that is able to (de-) serialize JSON objects of the
     * GeRDI metadata schema.
     *
     * @param geoDecimalPlaces the number of decimal places of GeoJson {@linkplain Coordinate}s
     *
     * @return a GsonBuilder that is able to (de-) serialize JSON objects of the
     * GeRDI metadata schema
     */
    public static GsonBuilder createGerdiDocumentGsonBuilder(final int geoDecimalPlaces)
    {
        return createGerdiDocumentGsonBuilder(createGeoJsonGsonBuilder(geoDecimalPlaces));
    }


    /**
     * Creates a GsonBuilder that is able to (de-) serialize {@linkplain Geometry} objects with
     * an unbounded precision.
     *
     * @return a GsonBuilder that is able to (de-) serialize {@linkplain Geometry} objects
     */
    public static GsonBuilder createGeoJsonGsonBuilder()
    {
        return createGeoJsonGsonBuilder(new GeometryFactory());
    }


    /**
     * Creates a GsonBuilder that is able to (de-) serialize {@linkplain Geometry} objects
     * with a specified number of decimal places.
     *
     * @param decimalPlaces the number of decimal places of GeoJson {@linkplain Coordinate}s
     *
     * @return a GsonBuilder that is able to (de-) serialize {@linkplain Geometry} objects
     */
    public static GsonBuilder createGeoJsonGsonBuilder(final int decimalPlaces)
    {
        if (decimalPlaces <= 0)
            throw new IllegalArgumentException(GeometryConstants.INVALID_DECIMALS_ERROR);

        final double precision = Math.pow(10.0, decimalPlaces - 1);
        return createGeoJsonGsonBuilder(new GeometryFactory(new PrecisionModel(precision)));
    }


    private static GsonBuilder createGerdiDocumentGsonBuilder(final GsonBuilder geoJsonBuilder)
    {
        return geoJsonBuilder
               .registerTypeAdapter(AbstractDate.class, new DateAdapter())
               .registerTypeAdapter(FunderIdentifierType.class, new FunderIdentifierTypeAdapter())
               .registerTypeAdapter(DateRange.class, new DateAdapter())
               .registerTypeAdapter(Date.class, new DateAdapter())
               .registerTypeAdapter(AbstractResearch.class, new ResearchAdapter())
               .registerTypeAdapter(ResearchArea.class, new ResearchAdapter())
               .registerTypeAdapter(ResearchDiscipline.class, new ResearchAdapter())
               .registerTypeAdapter(DataCiteExtensions.class, new DataCiteExtensionsAdapter())
               .registerTypeAdapter(SoepDataCiteExtension.class, new SoepDataCiteExtensionAdapter());
    }


    private static GsonBuilder createGeoJsonGsonBuilder(final GeometryFactory geoFactory)
    {
        return new GsonBuilder()
               .registerTypeAdapter(Geometry.class, new GeometryAdapter())
               .registerTypeAdapter(Point.class, new PointAdapter(geoFactory))
               .registerTypeAdapter(MultiPoint.class, new MultiPointAdapter(geoFactory))
               .registerTypeAdapter(LineString.class, new LineStringAdapter(geoFactory))
               .registerTypeAdapter(MultiLineString.class, new MultiLineStringAdapter(geoFactory))
               .registerTypeAdapter(Polygon.class, new PolygonAdapter(geoFactory))
               .registerTypeAdapter(MultiPolygon.class, new MultiPolygonAdapter(geoFactory));
    }
}
