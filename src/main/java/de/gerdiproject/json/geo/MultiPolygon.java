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

import java.util.Collection;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * A set of {@link Polygon}s.
 *
 * @author Robin Weiss
 */
public class MultiPolygon extends LinkedList<Polygon> implements IGeoCoordinates
{
    private static final long serialVersionUID = 1337151528933932554L;


    /**
     * Constructor that requires an arbitrary number of polygons.
     * @param polygons an arbitrary number of polygons
     */
    public MultiPolygon(Polygon... polygons)
    {
        super();

        for (Polygon p : polygons)
            add(p);
    }


    /**
     * Constructor that requires any kind of collection of polygons.
     * @param polygons a Collection of polygons
     */
    public MultiPolygon(Collection<Polygon> polygons)
    {
        super(polygons);
    }


    /**
     * Constructor that requires a JsonArray of polygons.
     * @param array a JsonArray of polygons
     */
    public MultiPolygon(JsonArray array)
    {
        super();
        array.forEach((JsonElement ele) -> add(new Polygon(ele.getAsJsonArray())));
    }
}
