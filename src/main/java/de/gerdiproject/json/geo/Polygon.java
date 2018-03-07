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
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * A single shape that may have holes.
 *
 * @author Robin Weiss
 */
public class Polygon extends LinkedList<List<Point>> implements IGeoCoordinates
{
    private static final long serialVersionUID = -9154174270949179550L;


    /**
     * Constructor that creates a single shape without any holes.
     *
     * @param filledShape a list of points that must form a ring
     */
    public Polygon(List<Point> filledShape)
    {
        super();
        add(filledShape);
    }


    /**
     * Constructor that creates a shape with holes.
     *
     * @param filledShape a list of points that must form a ring, defining the filled shape
     * @param holes a list of rings of coordinates, where each ring defines a hole in the filledShape
     */
    public Polygon(List<Point> filledShape, Collection<List<Point>> holes)
    {

        super();
        add(filledShape);
        addAll(holes);
    }


    /**
     * Constructor that requires a collection of rings. The first ring defines the filled shape and every subsequent ring defines a whole in the filled shape.
     *
     * @param shapes a collection of coordinate rings
     */
    public Polygon(Collection<List<Point>> shapes)
    {
        super(shapes);
    }


    /**
     * Constructor that requires a JsonArray of rings.
     * The first ring defines the filled shape and every subsequent ring defines a whole in the filled shape.
     *
     * @param array a JsonArray of coordinate rings
     */
    public Polygon(JsonArray array)
    {
        super();
        array.forEach((JsonElement ele) -> {
            JsonArray ring = ele.getAsJsonArray();
            List<Point> shape = new LinkedList<>();

            // create list of points for each shape
            ring.forEach((JsonElement point) ->
                         shape.add(new Point(point.getAsJsonArray()))
                        );
            add(shape);
        });
    }


    /**
     * Checks if holes exist in the polygon.
     *
     * @return true, if the polygon has holes in it
     */
    public boolean hasHoles()
    {
        return size() > 1;
    }


    /**
     * Returns the filled shape of the polygon.
     *
     * @return the filled shape of the polygon
     */
    public List<Point> getFilledShape()
    {
        return get(0);
    }


    /**
     * Changes the filled shape of the polygon witout changing the holes.
     *
     * @param shape a ring of coordinates, defining a shape
     */
    public void setFilledShape(List<Point> shape)
    {
        set(0, shape);
    }


    /**
     * Returns a list of all holes.
     *
     * @return a list of rings, defining the holes of the polygon
     */
    public List<List<Point>> getHoles()
    {
        return super.subList(1, size());
    }


    /**
     * Removes all holes of the polygon.
     */
    public void removeAllHoles()
    {
        super.removeAll(getHoles());
    }
}
