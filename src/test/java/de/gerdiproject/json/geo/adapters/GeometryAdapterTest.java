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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.gson.Gson;
import com.vividsolutions.jts.geom.Geometry;

import de.gerdiproject.json.GsonUtils;
import lombok.Value;

/**
 * This class offers Unit Tests for the {@linkplain GeometryAdapter} class.
 *
 * @author Robin Weiss
 */
@RunWith(Parameterized.class)
public class GeometryAdapterTest
{
    private static final String GEOJSON_FORMAT = "{\"type\":\"%s\", \"coordinates\":%s}";

    private final TestVO testVo;

    @Parameters(name = "{0}")
    public static Object[] getParameters() throws IOException
    {
        return new Object[] {
                   new TestVO("Point", "[123.456789, 987.654321]"),
                   new TestVO("MultiPoint", "[[123.456789, 987.654321], [987.654321, 123.456789]]"),
                   new TestVO("LineString", "[[123.456789, 987.654321], [987.654321, 123.456789]]"),
                   new TestVO("MultiLineString", "[[[123.456789, 987.654321], [987.654321, 123.456789]], [[321.987654, 789.123456], [789.123456, 321.987654]]]"),
                   new TestVO("Polygon", "[[[123.000001, 123.000001], [456.000001, 456.000001], [789.000001, 789.000001], [123.000001, 123.000001]],[[1.0,1.0],[2.0,2.0],[3.0,1.0],[1.0,1.0]]]"),
                   new TestVO("MultiPolygon", "[[[[123.000001, 123.000001], [456.000001, 456.000001], [789.000001, 789.000001], [123.000001, 123.000001]]], [[[456.000001, 456.000001], [123.000001, 123.000001], [789.000001, 789.000001], [456.000001, 456.000001]]]]")
               };
    }



    /**
     * Constructor that adds a GeoJson String as a parameter.
     *
     * @param testData test data related to testing the geometry cleaning
     */
    public GeometryAdapterTest(final TestVO testVo)
    {
        this.testVo = testVo;
    }


    @Test
    public void testDeserializedType()
    {
        final Gson gson = GsonUtils.createGeoJsonGsonBuilder().create();

        final Geometry geo = gson.fromJson(testVo.getJson(), Geometry.class);

        assertEquals(
            String.format("The deserialization of a %ss should not return an object of type %s; ", testVo.getGeometryType(), geo.getGeometryType()),
            testVo.getGeometryType(),
            geo.getGeometryType());
    }


    @Test
    public void testSerializedString()
    {
        final Gson gson = GsonUtils.createGeoJsonGsonBuilder().create();

        final Geometry geo = gson.fromJson(testVo.getJson(), Geometry.class);
        final String serializedGeo = gson.toJson(geo);

        assertEquals(
            String.format("The serialization of a deserialized %ss should return the original JSON String; ", testVo.getGeometryType(), geo.getGeometryType()),
            testVo.getJson(),
            serializedGeo);
    }


    @Test
    public void testSerializedPrecision()
    {
        final int maxDecimalPlaces = 3;
        final Gson gson = GsonUtils.createGeoJsonGsonBuilder(maxDecimalPlaces).create();

        final Geometry geo = gson.fromJson(testVo.getJson(), Geometry.class);

        // at this point, the JSON String should have a limited number of decimal places
        final String serializedGeo = gson.toJson(geo);

        // try to find a higher than allowed max number of decimal places
        final Pattern highPrecisionPattern = Pattern.compile("\\.\\d{" + (maxDecimalPlaces + 1) + "}");
        final Matcher matcher = highPrecisionPattern.matcher(serializedGeo);

        assertFalse(
            String.format("The serialization %ss must not contain a number with more than %d decimal places; ", testVo.getGeometryType(), maxDecimalPlaces),
            matcher.find());
    }


    /**
     * This value object offers data required for a single set of Unit Tests.
     *
     * @author Robin Weiss
     */
    @Value
    private static class TestVO
    {
        private final String geometryType;
        private final String json;


        public TestVO(final String geometryType, final String coordinates)
        {
            this.geometryType = geometryType;
            this.json = String.format(GEOJSON_FORMAT, geometryType, coordinates).replaceAll("\\s", "");
        }

        @Override
        public String toString()
        {
            return geometryType;
        }
    }
}
