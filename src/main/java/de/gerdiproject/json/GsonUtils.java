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
package de.gerdiproject.json;


import com.google.gson.Gson;
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
 * This is a static utility class that provides methods that are commonly used
 * by this GSON implementation. The init function must be called once prior to calling getGson and getPrettyGson.
 *
 * @author Robin Weiss
 */
public final class GsonUtils
{
    private static final String ERROR_ALREADY_INITIALIZED = "GsonUtils is already initialized!";
    private static final String ERROR_NOT_INITIALIZED = "GsonUtils was not initialized!";

    private static Gson GSON;
    private static Gson PRETTY_GSON;


    /**
     * Initializes the {@linkplain Gson} instances with a specified builder.
     * GeoJson adapters are automatically added.
     *
     * @param builder a GsonBuilder instance that may have registered Adapters
     */
    public static void init(GsonBuilder builder)
    {
        if (GSON != null)
            throw new IllegalStateException(ERROR_ALREADY_INITIALIZED);

        builder.registerTypeAdapter(Point.class, new PointAdapter())
        .registerTypeAdapter(MultiPoint.class, new MultiPointAdapter())
        .registerTypeAdapter(LineString.class, new LineStringAdapter())
        .registerTypeAdapter(MultiLineString.class, new MultiLineStringAdapter())
        .registerTypeAdapter(Polygon.class, new PolygonAdapter())
        .registerTypeAdapter(MultiPolygon.class, new MultiPolygonAdapter())
        .registerTypeAdapter(GeoJson.class, new GeoJsonAdapter())
        .registerTypeAdapter(AbstractDate.class, new DateAdapter())
        .registerTypeAdapter(DateRange.class, new DateAdapter())
        .registerTypeAdapter(Date.class, new DateAdapter())
        .registerTypeAdapter(AbstractResearch.class, new ResearchAdapter())
        .registerTypeAdapter(ResearchArea.class, new ResearchAdapter())
        .registerTypeAdapter(ResearchDiscipline.class, new ResearchAdapter());

        GSON = builder.create();
        PRETTY_GSON = builder.setPrettyPrinting().create();
    }


    /**
     * Retrieves a non-pretty printing {@linkplain Gson} instance, which
     * can be used for converting JSON objects to Strings or Java objects,
     * and vice versa.
     *
     * @return a non-pretty printing {@linkplain Gson} instance
     */
    public static Gson getGson()
    {
        if (GSON == null)
            throw new IllegalStateException(ERROR_NOT_INITIALIZED);

        return GSON;
    }


    /**
     * Retrieves a pretty printing {@linkplain Gson} instance, which
     * can be used for converting JSON objects to Strings or Java objects,
     * and vice versa.
     *
     * @return a pretty printing {@linkplain Gson} instance
     */
    public static Gson getPrettyGson()
    {
        if (PRETTY_GSON == null)
            throw new IllegalStateException(ERROR_NOT_INITIALIZED);

        return PRETTY_GSON;
    }


    /**
     * Empty constructor, because this is a static class.
     */
    private GsonUtils()
    {
    }
}
