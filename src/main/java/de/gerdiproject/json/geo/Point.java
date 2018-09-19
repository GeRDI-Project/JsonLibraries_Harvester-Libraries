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

/**
 * A geographical point. It must contain longitude and latitude and may contain the elevation as well.
 *
 * @author Robin Weiss
 */
public class Point implements IGeoCoordinates
{
    /**
     * A geographic coordinate that specifies the east-west position of a point on the Earth's surface.
     * It ranges from -180.0° to 180.0°. The Prime Meridian is at 0.0°.
     */
    private double longitude;

    /**
     * A geographic coordinate that specifies the north–south position of a point on the Earth's surface.
     * It ranges from -90.0° to 90.0°. The Equator is at 0.0°.
     */
    private double latitude;

    /**
     * The elevation of a geographic location is its height above or below the Earth's sea level.
     */
    private double elevation;


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
     * Constructor that also lets you set the elevation.
     *
     * @param longitude a geographic coordinate that specifies the east-west position of a point on the Earth's surface
     * @param latitude a geographic coordinate that specifies the north–south position of a point on the Earth's surface
     * @param elevation the elevation of a geographic location is its height above or below the Earth's sea level
     */
    public Point(double longitude, double latitude, double elevation)
    {
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
    }


    /**
     * Constructor that constructs the point from a json array.
     *
     * @param array a JsonArray containing two or three numbers
     */
    public Point(JsonArray array)
    {
        this.longitude = array.get(0).getAsDouble();
        this.latitude = array.get(1).getAsDouble();

        if (array.size() >= 3)
            this.elevation = array.get(2).getAsDouble();
        else
            this.elevation = Double.NaN;
    }


    public double getLongitude()
    {
        return longitude;
    }


    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }


    public double getLatitude()
    {
        return latitude;
    }


    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }


    public double getElevation()
    {
        return elevation;
    }


    public void setElevation(double elevation)
    {
        this.elevation = elevation;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(elevation);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Point))
            return false;

        Point other = (Point) obj;

        if (Double.doubleToLongBits(elevation) != Double.doubleToLongBits(other.elevation))
            return false;

        if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
            return false;

        if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
            return false;

        return true;
    }
}
