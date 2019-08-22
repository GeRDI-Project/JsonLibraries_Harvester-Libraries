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
package de.gerdiproject.json.datacite;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.geo.utils.GeoJsonUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Spatial region or named place where the data was gathered or about which the data is focused.
 * This schema deviates from the DataCite schema, mapping the geo coordinates to GeoJson objects
 * that can be read by ElasticSearch.
 *
 * Source: https://schema.datacite.org/meta/kernel-4.1/doc/DataCite-MetadataKernel_v4.1.pdf
 *
 * @author Mathis Neumann, Robin Weiss
 */
@Data @NoArgsConstructor
public class GeoLocation implements ICleanable
{
    /**
     * -- GETTER --
     * Retrieves the free text description of the geographic location.
     * @return the free text description of the geographic location
     *
     * -- SETTER --
     * Sets the free text description of the geographic location.
     * @param place the free text description of the geographic location
     */
    @SerializedName("geoLocationPlace")
    private String place;


    /**
     * -- GETTER --
     * Retrieves the point location in space.
     * @return the point location in space
     *
     * -- SETTER --
     * Sets the point location in space.
     * @param point a point location in space
     */
    @SerializedName("geoLocationPoint")
    private Point point;


    /**
     * -- GETTER --
     * Retrieves the spatial limits of a box.
     *
     * @return the spatial limits of a box
     *
     * -- SETTER --
     * Sets the spatial limits of a box.
     * @param box the spatial limits of a box
     */
    @SerializedName("geoLocationBox")
    private Polygon box;


    /**
     * -- GETTER --
     * Retrieves a list of drawn polygon areas, defined by sets of points and
     * lines connecting the points in closed chains.
     * @return the list of drawn polygon areas
     *
     * -- SETTER --
     * Sets the list of drawn polygon areas, defined by sets of points and
     * lines connecting the points in closed chains.
     * @param polygons the list of drawn polygon areas
     */
    @SerializedName("geoLocationPolygon")
    private List<Polygon> polygons;


    /**
     * Constructor that sets the name of the location.
     *
     * @param place free text description of the geographic location
     */
    public GeoLocation(final String place)
    {
        this.place = place;
    }


    /**
     * Changes the spatial limits of a box, defining its shape.
     *
     * @param westBoundLongitude western longitudinal dimension of the box
     * @param eastBoundLongitude eastern longitudinal dimension of the box
     * @param southBoundLatitude southern latitudinal dimension of the box
     * @param northBoundLatitude northern latitudinal dimension of the box
     */
    public void setBox(
        final double westBoundLongitude,
        final double eastBoundLongitude,
        final double southBoundLatitude,
        final double northBoundLatitude
    )
    {
        // TODO
        this.box = new GeoJsonUtils().createBox(
            westBoundLongitude,
            eastBoundLongitude,
            southBoundLatitude,
            northBoundLatitude);
    }

    public void setPoint(final double longitude, final double latitude)
    {
        // TODO
        this.point = new GeoJsonUtils().createPoint(longitude, latitude);
    }


    /**
     * Cleans each GeoJson attached to the location.
     * Each invalid GeoJsons will become null.
     */
    @Override
    public boolean clean()
    {
        cleanPoint();
        cleanPolygons();
        cleanBox();

        return isValid();
    }


    /**
     * Cleans the geo location point and
     * sets it to null if it becomes invalid.
     */
    private void cleanPoint()
    {
        if (point == null)
            return;

        // TODO point.clean();

        if (!point.isValid())
            point = null;
    }


    /**
     * Cleans and removes invalid polygons and
     * sets the polygon list to null if it becomes empty.
     */
    private void cleanPolygons()
    {
        if (polygons == null)
            return;

        int i = polygons.size();

        while (i != 0) {
            i--;
            final Polygon geo = polygons.get(i);

            if (geo == null)
                polygons.remove(i);
            else {
                // TODO geo.clean();

                if (!geo.isValid())
                    polygons.remove(i);
            }
        }

        if (polygons.isEmpty())
            polygons = null;
    }


    /**
     * Cleans the geo location box and
     * sets it to null if it becomes invalid.
     */
    private void cleanBox()
    {
        if (box == null)
            return;

        // TODO box.clean();

        //if (!box.isValid())
        //    box = null;
    }


    /**
     * Returns true if the GeoLocation has any geographical data.
     *
     * @return true, if the GeoLocation has any geographical data
     */
    public boolean isValid()
    {
        return place != null || box != null || polygons != null && !polygons
               .isEmpty() || point != null;
    }
}
