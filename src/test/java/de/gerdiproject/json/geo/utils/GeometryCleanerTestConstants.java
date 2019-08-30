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

import com.google.gson.Gson;
import com.vividsolutions.jts.geom.Geometry;

import de.gerdiproject.json.GsonUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class offers GeoJson constants used for the {@linkplain GeometryCleanerTest}.
 *
 * @author Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeometryCleanerTestConstants
{
    protected static final Gson GSON = GsonUtils.createGeoJsonGsonBuilder().create();

    private static final String BORDER_HOLE = "[[0,1], [0,3], [1,3], [1,1], [0,1]]";
    private static final String INSIDE_HOLE = "[[3,1.5], [3,2], [3.5,2], [3.5, 1.5], [3,1.5]]";
    private static final String OUTSIDE_HOLE = "[[1,-2], [1,0], [3,0], [3,-2], [1,-2]]";
    private static final String OVERLAPPING_HOLE = "[[3,-1], [3,1], [5,1], [5,-1], [3,-1]]";

    private static final String POLY_FORMAT = "{\"coordinates\": %s, \"type\": \"Polygon\"}";
    private static final String MULTI_POLY_FORMAT = "{\"coordinates\": %s, \"type\": \"MultiPolygon\"}";

    private static final String BOX_HULL = "[[0,0], [0,4], [4,4], [4,0], [0,0]]";
    private static final String BOX_PLAIN = createPoly(BOX_HULL);
    private static final String BOX_WITH_HOLE = createPoly(BOX_HULL, INSIDE_HOLE);
    private static final String BOX_WITH_BORDER_HOLE = createPoly(BOX_HULL, BORDER_HOLE);
    private static final String BOX_WITH_OUTSIDE_HOLE = createPoly(BOX_HULL, OUTSIDE_HOLE);
    private static final String BOX_WITH_OVERLAPPING_HOLE = createPoly(BOX_HULL, OVERLAPPING_HOLE);
    private static final String BOX_WITH_HOLES = createPoly(BOX_HULL, INSIDE_HOLE, BORDER_HOLE, OUTSIDE_HOLE, OVERLAPPING_HOLE);
    private static final String BOX_WITH_BORDER_HOLE_FIXED = createPoly("[[0,0], [0,1], [1,1], [1,3], [0,3], [0,4], [4,4], [4,0], [0,0]]");
    private static final String BOX_WITH_OVERLAPPING_HOLE_FIXED = createPoly("[[0,0], [0,4], [4,4], [4,1], [3,1], [3,0], [0,0]]");
    private static final String BOX_WITH_HOLES_FIXED = createPoly("[[0,0], [0,1], [1,1], [1,3], [0,3], [0,4], [4,4], [4,1], [3,1], [3,0], [0,0]]", INSIDE_HOLE);

    private static final String HOURGLASS_HULL = "[[0,0], [0,4], [4,0], [4,4], [0,0]]";
    private static final String HOURGLASS_PLAIN = createPoly(HOURGLASS_HULL);
    private static final String HOURGLASS_WITH_HOLE = createPoly(HOURGLASS_HULL, INSIDE_HOLE);
    private static final String HOURGLASS_WITH_BORDER_HOLE = createPoly(HOURGLASS_HULL, BORDER_HOLE);
    private static final String HOURGLASS_WITH_OUTSIDE_HOLE = createPoly(HOURGLASS_HULL, OUTSIDE_HOLE);
    private static final String HOURGLASS_WITH_OVERLAPPING_HOLE = createPoly(HOURGLASS_HULL, OVERLAPPING_HOLE);
    private static final String HOURGLASS_WITH_HOLES = createPoly(HOURGLASS_HULL, INSIDE_HOLE, BORDER_HOLE, OUTSIDE_HOLE, OVERLAPPING_HOLE);

    private static final String HOURGLASS_PART_LEFT = "[[0,0], [0,4], [2,2], [0,0]]";
    private static final String HOURGLASS_PART_RIGHT = "[[4,0], [2,2], [4,4], [4,0]]";
    private static final String HOURGLASS_PLAIN_FIXED = createMultiPoly(
                                                            createShape(HOURGLASS_PART_LEFT),
                                                            createShape(HOURGLASS_PART_RIGHT));
    private static final String HOURGLASS_WITH_HOLE_FIXED = createMultiPoly(
                                                                createShape(HOURGLASS_PART_LEFT),
                                                                createShape(HOURGLASS_PART_RIGHT, INSIDE_HOLE));

    private static final String HOURGLASS_SPLIT_LEFT1 = "[[0,0], [0,1], [1,1], [0,0]]";
    private static final String HOURGLASS_SPLIT_LEFT2 = "[[0,3], [0,4], [1,3], [0,3]]";
    private static final String HOURGLASS_SPLIT_LEFT3 = "[[1,1], [1,3], [2,2], [1,1]]";
    private static final String HOURGLASS_WITH_BORDER_HOLE_FIXED = createMultiPoly(
                                                                       createShape(HOURGLASS_SPLIT_LEFT1),
                                                                       createShape(HOURGLASS_SPLIT_LEFT2),
                                                                       createShape(HOURGLASS_SPLIT_LEFT3),
                                                                       createShape(HOURGLASS_PART_RIGHT));
    private static final String HOURGLASS_SPLIT_RIGHT = "[[2,2], [4,4], [4,1], [3,1], [2,2]]";
    private static final String HOURGLASS_WITH_OVERLAPPING_HOLE_FIXED = createMultiPoly(
                                                                            createShape(HOURGLASS_PART_LEFT),
                                                                            createShape(HOURGLASS_SPLIT_RIGHT));
    private static final String HOURGLASS_WITH_HOLES_FIXED = createMultiPoly(
                                                                 createShape(HOURGLASS_SPLIT_LEFT1),
                                                                 createShape(HOURGLASS_SPLIT_LEFT2),
                                                                 createShape(HOURGLASS_SPLIT_LEFT3),
                                                                 createShape(HOURGLASS_SPLIT_RIGHT, INSIDE_HOLE));

    private static final String POINT_JSON = "{\"coordinates\": [123.456, 789], \"type\": \"Point\"}";

    public static final GeometryTestVO[] TEST_CASES = createTestCases();


    private static String createPoly(CharSequence... lineStrings)
    {
        return String.format(POLY_FORMAT, createShape(lineStrings));
    }


    private static String createShape(CharSequence... lineStrings)
    {
        return "[" + String.join(", ", lineStrings) + "]";
    }


    private static String createMultiPoly(CharSequence... polyLineStrings)
    {
        return String.format(MULTI_POLY_FORMAT, createShape(polyLineStrings));
    }


    private static GeometryTestVO[] createTestCases()
    {
        final GeometryTestVO[] testCases = {
            new GeometryTestVO("Polygon", BOX_PLAIN, BOX_PLAIN, true),
            new GeometryTestVO("Polygon + Hole", BOX_WITH_HOLE, BOX_WITH_HOLE, true),
            new GeometryTestVO("Polygon + Border Hole", BOX_WITH_BORDER_HOLE, BOX_WITH_BORDER_HOLE_FIXED, false),
            new GeometryTestVO("Polygon + Outside Hole", BOX_WITH_OUTSIDE_HOLE, BOX_PLAIN, false),
            new GeometryTestVO("Polygon + Partly Outside Hole", BOX_WITH_OVERLAPPING_HOLE, BOX_WITH_OVERLAPPING_HOLE_FIXED, false),
            new GeometryTestVO("Polygon + All Holes", BOX_WITH_HOLES, BOX_WITH_HOLES_FIXED, false),

            new GeometryTestVO("Polygon + Self-intersection", HOURGLASS_PLAIN, HOURGLASS_PLAIN_FIXED, false),
            new GeometryTestVO("Polygon + Self-intersection + Hole", HOURGLASS_WITH_HOLE, HOURGLASS_WITH_HOLE_FIXED, false),
            new GeometryTestVO("Polygon + Self-intersection + Border Hole", HOURGLASS_WITH_BORDER_HOLE, HOURGLASS_WITH_BORDER_HOLE_FIXED, false),
            new GeometryTestVO("Polygon + Self-intersection + Outside Hole", HOURGLASS_WITH_OUTSIDE_HOLE, HOURGLASS_PLAIN_FIXED, false),
            new GeometryTestVO("Polygon + Self-intersection + Partly Outside Hole", HOURGLASS_WITH_OVERLAPPING_HOLE, HOURGLASS_WITH_OVERLAPPING_HOLE_FIXED, false),
            new GeometryTestVO("Polygon + Self-intersection + All Holes", HOURGLASS_WITH_HOLES, HOURGLASS_WITH_HOLES_FIXED, false),

            new GeometryTestVO("Point", POINT_JSON, POINT_JSON, true)
        };
        return testCases;
    }


    public static class GeometryTestVO
    {
        public final String name;
        public final Geometry inputGeometry;
        public final Geometry outputGeometry;
        public final boolean isInputValid;


        public GeometryTestVO(final String name, final String inputJson, final String outputJson, final boolean isInputValid)
        {
            this.name = name;
            this.inputGeometry = GSON.fromJson(inputJson, Geometry.class);
            this.outputGeometry = GSON.fromJson(outputJson, Geometry.class);
            this.isInputValid = isInputValid;
        }


        @Override
        public String toString()
        {
            return name;
        }
    }
}
