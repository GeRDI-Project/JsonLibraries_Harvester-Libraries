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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.SerializedName;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.utils.CollectionUtils;
import de.gerdiproject.json.geo.utils.GeometryCleaner;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private final static String POLYGON_TYPE = Polygon.class.getSimpleName();
    private final static String MULTI_POLYGON_TYPE = MultiPolygon.class.getSimpleName();

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
    @Setter(AccessLevel.NONE)
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
    @Setter(AccessLevel.NONE)
    private Set<Polygon> polygons;


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
     * Changes the spatial limits of a box, defining its shape.
     *
     * @param geometry a GeoJson object, the bounding box of which becomes the box
     */
    public void setBox(final Geometry geometry)
    {
        this.box = geometry == null ? null : (Polygon) geometry.getEnvelope().convexHull();
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
     * Adds a {@linkplain Collection} of {@linkplain Geometry} objects to the {@linkplain List}
     * of {@linkplain Polygon}s. {@linkplain MultiPolygon}s are split up into {@linkplain Polygon}s
     * in order to be DataCite compliant.
     *
     * @param geoList a collection of {@linkplain Polygon}s and {@linkplain MultiPolygon}s
     */
    public void addPolygons(final Collection<Geometry> geoList)
    {
        if (geoList == null)
            return;

        final Iterator<Geometry> iter = geoList.iterator();

        final List<Polygon> polyList = new LinkedList<>();

        while (iter.hasNext()) {
            final Geometry geo = iter.next();
            final String geoType = geo.getGeometryType();

            if (geoType.equalsIgnoreCase(POLYGON_TYPE))
                polyList.add((Polygon)geo);

            else if (geoType.equalsIgnoreCase(MULTI_POLYGON_TYPE))
                polyList.addAll(multiPolygonToPolygonList((MultiPolygon)geo));
        }

        this.polygons = CollectionUtils.addToSet(this.polygons, polyList);
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

        final Set<Polygon> cleanedPolys = new HashSet<>();

        for (final Polygon poly : polygons)
            cleanedPolys.add((Polygon) GeometryCleaner.validate(poly));

        this.polygons = CollectionUtils.addToSet(null, cleanedPolys);
    }


    /**
     * Cleans the geo location box and
     * sets it to null if it becomes invalid.
     */
    private void cleanBox()
    {
        if (this.box == null)
            return;

        Polygon newBoxValue = null;

        if (this.box.isRectangle()) {
            final Coordinate[] boxCoordinates = this.box.getCoordinates();
            final Coordinate cornerCoordinate1 = boxCoordinates[1];
            final Coordinate cornerCoordinate2 = boxCoordinates[2];

            // check if the box is not empty
            if (!cornerCoordinate1.equals(cornerCoordinate2))
                newBoxValue = (Polygon) GeometryCleaner.validate(this.box);
        }

        this.box = newBoxValue;
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


    /**
     * Converts a {@linkplain MultiPolygon} to a {@linkplain List} of {@linkplain Polygon}s.
     *
     * @param multiPolygon the {@linkplain MultiPolygon} of which the {@linkplain Polygon}s are to be extracted
     *
     * @return a {@linkplain List} of {@linkplain Polygon}s
     */
    private static List<Polygon> multiPolygonToPolygonList(final MultiPolygon multiPolygon)
    {
        final List<Polygon> list = new LinkedList<>();

        if (multiPolygon != null) {
            final int len = multiPolygon.getNumGeometries();

            for (int i = 0; i < len; i++)
                list.add((Polygon)multiPolygon.getGeometryN(i));
        }

        return list;
    }
}
