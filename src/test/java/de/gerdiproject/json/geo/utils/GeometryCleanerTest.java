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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.gson.Gson;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

import de.gerdiproject.json.GsonUtils;

/**
 * This class provides test cases for the {@linkplain GeometryCleaner} class.
 * Multiple runs with varying GeoJson Strings are executed.
 *
 * @author Robin Weiss
 *
 */
@RunWith(Parameterized.class)
public class GeometryCleanerTest
{
    private static final String SKIP_NON_POLY_TESTS_MESSAGE = "Skipping test, since it is only relevant for (Multi-)Polygons!";
    private static final String SKIP_POLY_TESTS_MESSAGE = "Skipping test, since it is not relevant for (Multi-)Polygons!";

    final Gson gson = GsonUtils.createGeoJsonGsonBuilder().create();

    private static final String POINT_JSON = "{\"coordinates\": [123.456, 789],"
                                             + "  \"type\": \"Point\"}";

    private static final String POLY_JSON = "{"
                                            + "\"coordinates\": [[[1,1],[1,4],[4,4],[4,1],[1,1]]],"
                                            + "\"type\": \"Polygon\"}";

    private static final String INVALID_POLY_JSON1 = "{"
                                                     + "\"coordinates\": [[[1,1],[4,4],[1,4],[4,1],[1,1]]],"
                                                     + "\"type\": \"Polygon\"}";


    private static final String INVALID_POLY_JSON2 = "{"
                                                     + "\"coordinates\": [[[1,1],[3,4],[3,1],[1,4],[4,2],[1,1]]],"
                                                     + "\"type\": \"Polygon\"}";


    private static final String INVALID_POLY_JSON3 = "{"
                                                     + "\"coordinates\": [[[0,0],[2,0],[2,3],[3,3],[1,2],[0,2],[0,0]]],"
                                                     + "\"type\": \"Polygon\"}";

    private static final String INVALID_POLY_JSON4 = "{"
                                                     + "\"coordinates\": [[[0,0],[0,2],[-2,2],[0,2],[0,3],[3,3],[3,0],[0,0]]],"
                                                     + "\"type\": \"Polygon\"}";


    private static final String INVALID_MULTI_POLY_JSON = "{"
                                                          + "\"coordinates\": ["
                                                          + "[[[0,0],[0,2],[-2,2],[0,2],[0,3],[3,3],[3,0],[0,0]]],"
                                                          + "[[[1,1],[4,4],[1,4],[4,1],[1,1]]]"
                                                          + "],"
                                                          + "\"type\": \"MultiPolygon\"}";

    private final String inputJson;
    private final boolean isValid;


    @Parameters(name = "geometry: {0}, isValid: {1}")
    public static Object[][] getParameters()
    {
        return new Object[][] {
            {POINT_JSON, true},
            {POLY_JSON, true},
            {INVALID_POLY_JSON1, false},
            {INVALID_POLY_JSON2, false},
            {INVALID_POLY_JSON3, false},
            {INVALID_POLY_JSON4, false},
            {INVALID_MULTI_POLY_JSON, false}
        };
    }


    /**
     * Constructor that adds a GeoJson String as a parameter.
     *
     * @param inputJson a GeoJson String
     */
    public GeometryCleanerTest(String inputJson, boolean isValid)
    {
        this.inputJson = inputJson;
        this.isValid = isValid;
    }


    /**
     * Tests if the parsed input {@linkplain Geometry} has the expected
     * validity.
     */
    @Test
    public void testIsValidInput()
    {
        final Geometry inputGeo = gson.fromJson(inputJson, Geometry.class);

        assertEquals(
            "The method GeometryCleaner.validate() should return valid Geometry; ",
            isValid,
            inputGeo.isValid());
    }


    /**
     * Tests if {@linkplain GeometryCleaner#validate(Geometry)} returns valid {@linkplain Geometry}.
     */
    @Test
    public void testValidate()
    {
        final Geometry invalidGeo = gson.fromJson(inputJson, Geometry.class);
        final Geometry fixedGeo = GeometryCleaner.validate(invalidGeo);

        assertTrue(
            "The method GeometryCleaner.validate() should return valid Geometry; ",
            fixedGeo.isValid());
    }


    /**
     * Tests if {@linkplain GeometryCleaner#validate(Geometry)} with either {@linkplain Polygon}
     * or {@linkplain MultiPolygon} as input also returns a {@linkplain Polygon}
     * or {@linkplain MultiPolygon}.
     */
    @Test
    public void testValidatedPolygonType()
    {
        final Geometry invalidGeo = gson.fromJson(inputJson, Geometry.class);
        Assume.assumeTrue(String.format(SKIP_NON_POLY_TESTS_MESSAGE, invalidGeo.getClass().getSimpleName()),
                          invalidGeo instanceof Polygon || invalidGeo instanceof MultiPolygon);

        final Geometry fixedGeo = GeometryCleaner.validate(invalidGeo);

        assertTrue("The method GeometryCleaner.validate() should return a (Multi-)Polygon if the input is a (Multi-)Polygon; ",
                   fixedGeo instanceof Polygon || fixedGeo instanceof MultiPolygon);
    }


    /**
     * Tests if {@linkplain GeometryCleaner#validate(Geometry)} applied on a non-polygon
     * returns a reference to the same object that was passed as input.
     */
    @Test
    public void testValidatedNonPolygons()
    {
        final Geometry invalidGeo = gson.fromJson(inputJson, Geometry.class);
        Assume.assumeFalse(String.format(SKIP_POLY_TESTS_MESSAGE, invalidGeo.getClass().getSimpleName()),
                           invalidGeo instanceof Polygon || invalidGeo instanceof MultiPolygon);

        final Geometry fixedGeo = GeometryCleaner.validate(invalidGeo);

        assertEquals("The method GeometryCleaner.validate() should return the object reference if the input is not a (Multi-)Polygon; ",
                     invalidGeo,
                     fixedGeo);
    }
}
