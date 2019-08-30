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

import java.io.IOException;

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
import de.gerdiproject.json.geo.utils.GeometryCleanerTestConstants.GeometryTestVO;

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


    private final GeometryTestVO testData;


    @Parameters(name = "{0}")
    public static Object[] getParameters() throws IOException
    {
        return GeometryCleanerTestConstants.TEST_CASES;
    }


    /**
     * Constructor that adds a GeoJson String as a parameter.
     *
     * @param testData test data related to testing the geometry cleaning
     */
    public GeometryCleanerTest(GeometryTestVO testData)
    {
        this.testData = testData;
    }


    /**
     * Tests if the parsed input {@linkplain Geometry} has the expected
     * validity.
     */
    @Test
    public void testIsValidInput()
    {
        assertEquals(
            "The method GeometryCleaner.validate() should return valid Geometry; ",
            testData.isInputValid,
            testData.inputGeometry.isValid());
    }


    /**
     * Tests if {@linkplain GeometryCleaner#validate(Geometry)} returns valid {@linkplain Geometry}.
     */
    @Test
    public void testValidate()
    {
        final Geometry fixedGeo = GeometryCleaner.validate(testData.inputGeometry);

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
        Assume.assumeTrue(String.format(SKIP_NON_POLY_TESTS_MESSAGE, testData.inputGeometry.getClass().getSimpleName()),
                          testData.inputGeometry instanceof Polygon || testData.inputGeometry instanceof MultiPolygon);

        final Geometry fixedGeo = GeometryCleaner.validate(testData.inputGeometry);

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
        Assume.assumeFalse(String.format(SKIP_POLY_TESTS_MESSAGE, testData.inputGeometry.getClass().getSimpleName()),
                           testData.inputGeometry instanceof Polygon || testData.inputGeometry instanceof MultiPolygon);

        final Geometry fixedGeo = GeometryCleaner.validate(testData.inputGeometry);

        assertEquals("The method GeometryCleaner.validate() should return the object reference if the input is not a (Multi-)Polygon; ",
                     testData.inputGeometry,
                     fixedGeo);
    }

    /**
     * Tests if {@linkplain GeometryCleaner#validate(Geometry)} results in the expected shape.
     */
    @Test
    public void testValidatedShape()
    {
        final Geometry fixedGeo = GeometryCleaner.validate(testData.inputGeometry);

//        System.out.println("\n" + testData.name);
//        System.out.println(" FIXED: "+ GsonUtils.createGeoJsonGsonBuilder().create().toJson(fixedGeo));
//        System.out.println(" EXPCT: "+ GsonUtils.createGeoJsonGsonBuilder().create().toJson(testData.outputGeometry));
//        System.out.println(" IS_SAME: "+ fixedGeo.symDifference(testData.outputGeometry).isEmpty());

        assertTrue("The method GeometryCleaner.validate() returned an unexpected shape; ",
                   fixedGeo.symDifference(testData.outputGeometry).isEmpty());
    }
}
