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

import de.gerdiproject.json.datacite.Date;
import de.gerdiproject.json.datacite.DateRange;
import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.adapter.DateAdapter;
import de.gerdiproject.json.datacite.extension.ResearchArea;
import de.gerdiproject.json.datacite.extension.ResearchDiscipline;
import de.gerdiproject.json.datacite.extension.abstr.AbstractResearch;
import de.gerdiproject.json.datacite.extension.adapter.ResearchAdapter;
import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.geo.LineString;
import de.gerdiproject.json.geo.MultiLineString;
import de.gerdiproject.json.geo.MultiPoint;
import de.gerdiproject.json.geo.MultiPolygon;
import de.gerdiproject.json.geo.Point;
import de.gerdiproject.json.geo.Polygon;
import de.gerdiproject.json.geo.adapter.GeoJsonAdapter;
import de.gerdiproject.json.geo.adapter.LineStringAdapter;
import de.gerdiproject.json.geo.adapter.MultiLineStringAdapter;
import de.gerdiproject.json.geo.adapter.MultiPointAdapter;
import de.gerdiproject.json.geo.adapter.MultiPolygonAdapter;
import de.gerdiproject.json.geo.adapter.PointAdapter;
import de.gerdiproject.json.geo.adapter.PolygonAdapter;


/**
 * This is a static utility class that provides methods that for creating commonly used GSON builders.
 *
 * @author Robin Weiss
 */
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
        return createGeoJsonGsonBuilder()
               .registerTypeAdapter(AbstractDate.class, new DateAdapter())
               .registerTypeAdapter(DateRange.class, new DateAdapter())
               .registerTypeAdapter(Date.class, new DateAdapter())
               .registerTypeAdapter(AbstractResearch.class, new ResearchAdapter())
               .registerTypeAdapter(ResearchArea.class, new ResearchAdapter())
               .registerTypeAdapter(ResearchDiscipline.class, new ResearchAdapter());
    }


    /**
     * Creates a GsonBuilder that is able to (de-) serialize {@linkplain GeoJson} objects.
     *
     * @return a GsonBuilder that is able to (de-) serialize {@linkplain GeoJson} objects
     */
    public static GsonBuilder createGeoJsonGsonBuilder()
    {
        return new GsonBuilder().registerTypeAdapter(Point.class, new PointAdapter())
               .registerTypeAdapter(MultiPoint.class, new MultiPointAdapter())
               .registerTypeAdapter(LineString.class, new LineStringAdapter())
               .registerTypeAdapter(MultiLineString.class, new MultiLineStringAdapter())
               .registerTypeAdapter(Polygon.class, new PolygonAdapter())
               .registerTypeAdapter(MultiPolygon.class, new MultiPolygonAdapter())
               .registerTypeAdapter(GeoJson.class, new GeoJsonAdapter());
    }


    /**
     * Private constructor, because this class has only static functions.
     */
    private GsonUtils()
    {
    }
}
