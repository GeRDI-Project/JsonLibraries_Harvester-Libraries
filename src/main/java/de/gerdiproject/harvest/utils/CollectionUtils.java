/*
 *  Copyright © 2018 Robin Weiss (http://www.gerdi-project.de/)
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
package de.gerdiproject.harvest.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.gerdiproject.harvest.ICleanable;

/**
 * This class contains static methods for manipulating {@linkplain Collection}s and related classes.
 *
 * @author Robin Weiss
 */
public class CollectionUtils
{
    /**
     * Private constructor, because only static methods are provided here.
     */
    private CollectionUtils()
    {

    }


    /**
     * Static helper that adds elements to an existing {@linkplain Set}, or
     * creates a new {@linkplain HashSet} if nothing was added before. Also removes null elements
     * and cleans the items prior to adding them, if they implement {@linkplain ICleanable}.
     *
     * @param set the set to which the elements are added, or null if no set exists yet
     * @param addedElements the elements that are to be added
     * @param <T> the type of the set and added elements
     *
     * @return a set with the added elements, or null if the set is empty after the operation
     */
    public static <T> Set<T> addToSet(final Set<T> set, final Collection<T> addedElements)
    {
        // abort if we have nothing to add
        if (addedElements == null || addedElements.isEmpty())
            return set;

        // create a new set or use an existing one
        final Set<T> tempSet = (set == null) ? new HashSet<>() : set;

        for (T element : addedElements) {
            if (element == null)
                continue;

            if (element instanceof ICleanable) {
                // if element can be cleaned, do it and add it only if it is valid
                final ICleanable cleanableElement = (ICleanable) element;

                if (cleanableElement.clean())
                    tempSet.add(element);
            } else
                tempSet.add(element);
        }

        return tempSet.isEmpty() ? null : tempSet;
    }


    /**
     * Static helper that adds elements to an existing {@linkplain List}, or
     * creates a new {@linkplain LinkedList} if nothing was added before. Also removes null- and duplicate
     * elements and cleans the items prior to adding them, if they implement {@linkplain ICleanable}.
     * Elements that cannot be cleaned are not added to the list
     *
     * @param list the list to which the elements are added, or null if no list exists yet
     * @param addedElements the elements that are to be added
     * @param <T> the type of the list and added elements
     *
     * @return a list with the added elements, or null if the list is empty after the operation
     */
    public static <T> List<T> addToList(final List<T> list, final Collection<T> addedElements)
    {
        // abort if we have nothing to add
        if (addedElements == null || addedElements.isEmpty())
            return list;

        final List<T> tempList = list == null ? new LinkedList<>() : list;

        for (T element : addedElements) {
            if (element == null || tempList.contains(element))
                continue;

            if (!(element instanceof ICleanable) || ((ICleanable) element).clean())
                tempList.add(element);
        }

        return tempList.isEmpty() ? null : tempList;
    }


    /**
     * Static helper that puts a value to an existing {@linkplain Map}, or
     * creates a new {@linkplain HashMap} if nothing was added before.
     * Also removes cleans the value  prior to adding it, if it implements {@linkplain ICleanable}.
     * Values that cannot be cleaned are not put into the map.
     *
     * @param map the map to which the value is added, or null if no map exists yet
     * @param key the key at which the value is stored in the map
     * @param value the value that is to be put into the map
     * @param <T> the type of the added value
     *
     * @return a map with the added value, or null if the map is empty after the operation
     */
    public static <T> Map<String, T> addToMap(Map<String, T> map, String key, T value)
    {
        if (value == null)
            return map;

        final Map<String, T> tempMap = map == null ? new HashMap<>() : map;

        if (!(value instanceof ICleanable) || ((ICleanable) value).clean())
            tempMap.put(key, value);

        return tempMap.isEmpty() ? null : tempMap;
    }
}