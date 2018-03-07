/**
 * Copyright © 2017 Robin Weiss (http://www.gerdi-project.de)
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

import java.util.Collection;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * This class represents an array of coordinates (points).
 *
 * @author Robin Weiss
 */
public class MultiPoint extends LinkedList<Point> implements IGeoCoordinates
{
    private static final long serialVersionUID = -3091739333929089564L;


    /**
     * Constructor that requires an arbitrary number of coordinates.
     * @param points an arbitrary number of points
     */
    public MultiPoint(Point... points)
    {
        super();

        for (Point gp : points)
            add(gp);
    }


    /**
     * Constructor that requires any kind of collection of coordinates.
     * @param points a Collection of points
     */
    public MultiPoint(Collection<Point> points)
    {
        super(points);
    }


    /**
     * Constructor that requires a JsonArray of coordinates.
     * @param array a JsonArray of points
     */
    public MultiPoint(JsonArray array)
    {
        super();
        array.forEach((JsonElement ele) ->
                      add(new Point(ele.getAsJsonArray()))
                     );
    }
}
