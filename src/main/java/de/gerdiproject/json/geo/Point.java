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
package de.gerdiproject.json.geo;

import com.google.gson.JsonArray;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A geographical point. It must contain longitude and latitude and may contain the elevation as well.
 *
 * @author Robin Weiss
 */
@Data @RequiredArgsConstructor
public class Point implements IGeoCoordinates
{
    /**
     * A geographic coordinate that specifies the east-west position of a point on the Earth's surface.
     * It ranges from -180.0° to 180.0°. The Prime Meridian is at 0.0°.
     */
    private final double longitude;

    /**
     * A geographic coordinate that specifies the north–south position of a point on the Earth's surface.
     * It ranges from -90.0° to 90.0°. The Equator is at 0.0°.
     */
    private final double latitude;

    /**
     * The elevation of a geographic location is its height above or below the Earth's sea level.
     */
    private final double elevation;


    /**
     * Simple constructor that only requires longitude and latitude.
     *
     * @param longitude a geographic coordinate that specifies the east-west position of a point on the Earth's surface
     * @param latitude a geographic coordinate that specifies the north–south position of a point on the Earth's surface
     */
    public Point(double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = Double.NaN;
    }


    /**
     * Constructor that constructs the point from a json array.
     *
     * @param array a JsonArray containing two or three numbers
     */
    public Point(JsonArray array)
    {
        double lon;
        double lat;
        double ele;

        try {
            lon = array.get(0).getAsDouble();
            lat = array.get(1).getAsDouble();
            ele = array.size() >= 3
                  ? array.get(2).getAsDouble()
                  : Double.NaN;
        } catch (UnsupportedOperationException e) {
            // rare edge case: one of the coordinates is null
            lon = Double.NaN;
            lat = Double.NaN;
            ele = Double.NaN;
        }

        this.longitude = lon;
        this.latitude = lat;
        this.elevation = ele;
    }
}
