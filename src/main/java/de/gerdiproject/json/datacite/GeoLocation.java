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
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.geo.utils.GeometryCleaner;
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
    private final static GeometryFactory FACTORY = new GeometryFactory();

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
    private List<Geometry> polygons;


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
        final Coordinate[] coordinates = {
            new Coordinate(westBoundLongitude, northBoundLatitude),
            new Coordinate(eastBoundLongitude, northBoundLatitude),
            new Coordinate(eastBoundLongitude, southBoundLatitude),
            new Coordinate(westBoundLongitude, southBoundLatitude),
            new Coordinate(westBoundLongitude, northBoundLatitude)
        };
        this.box = FACTORY.createPolygon(coordinates);
    }


    /**
     * Changes the point location in space.
     *
     * @param longitude a geographic coordinate that specifies the east-west position of a point on the Earth's surface
     * @param latitude a geographic coordinate that specifies the north–south position of a point on the Earth's surface
     */
    public void setPoint(final double longitude, final double latitude)
    {
        this.point = FACTORY.createPoint(new Coordinate(longitude, latitude));
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
     * Sets the geo location point to null,
     * if it is not valid.
     */
    private void cleanPoint()
    {
        if (point != null && !point.isValid())
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
            Geometry poly = polygons.get(i);

            if (poly != null) {
                poly = GeometryCleaner.validate(poly);
                polygons.set(i, poly);
            }

            if (poly == null)
                polygons.remove(i);
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

        this.box = (Polygon) GeometryCleaner.validate(this.box);
    }


    /**
     * Returns true if the GeoLocation has any geographical data
     * or at least a textual description.
     *
     * @return true, if the GeoLocation has any data
     */
    public boolean isValid()
    {
        return place != null || box != null || polygons != null && !polygons
               .isEmpty() || point != null;
    }
}
