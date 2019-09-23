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
package de.gerdiproject.json.geo.constants;

import com.vividsolutions.jts.geom.Geometry;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class contains constants used for {@linkplain Geometry} related classes.
 *
 * @author Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeometryConstants
{
    public static final String POINT_TYPE = "Point";
    public static final String MULTI_POINT_TYPE = "MultiPoint";
    public static final String LINE_STRING_TYPE = "LineString";
    public static final String MULTI_LINE_STRING_TYPE = "MultiLineString";
    public static final String POLYGON_TYPE = "Polygon";
    public static final String MULTI_POLYGON_TYPE = "MultiPolygon";
    public static final String GEOMETRY_COLLECTION_TYPE = "GeometryCollection";

    public static final String TYPE_JSON_FIELD = "type";
    public static final String COORDINATES_JSON_FIELD = "coordinates";
    public static final String INVALID_DECIMALS_ERROR = "The number of decimal places must be greater than zero!";
    public static final String CANNOT_VALIDATE_ERROR = "Skipped GeoJson, because it cannot be validated: %s";
    public static final String CANNOT_VALIDATE_ERROR_SHORT = "Skipped %s GeoJson, because it cannot be validated.";
    public static final String UNKNOWN_GEOMETRY_TYPE_ERROR = "Unknown Geometry type '%s'.";
}
