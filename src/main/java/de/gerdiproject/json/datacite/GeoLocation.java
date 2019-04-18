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

import java.util.Arrays;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.geo.Point;
import de.gerdiproject.json.geo.Polygon;
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
     * Free text description of the geographic location.
     */
    @SerializedName("geoLocationPlace")
    private String place;

    /**
     * A point location in space.
     */
    @SerializedName("geoLocationPoint")
    private GeoJson point;

    /**
     *  The spatial limits of a box.
     *  It is somewhat redundant with the polygon, however,
     *  it is compliant to the DataCite schema.
     */
    @SerializedName("geoLocationBox")
    private GeoJson box;

    /**
     *  A list of drawn polygon areas, defined by sets of points and
     *  lines connecting the points in closed chains.
     */
    @SerializedName("geoLocationPolygon")
    private List<GeoJson> polygons;


    /**
     * Constructor that sets the name of the location.
     *
     * @param place free text description of the geographic location
     */
    public GeoLocation(String place)
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
        double westBoundLongitude,
        double eastBoundLongitude,
        double southBoundLatitude,
        double northBoundLatitude
    )
    {
        List<Point> boxShape = Arrays.asList(new Point(westBoundLongitude, northBoundLatitude),
                                             new Point(eastBoundLongitude, northBoundLatitude),
                                             new Point(eastBoundLongitude, southBoundLatitude),
                                             new Point(westBoundLongitude, southBoundLatitude),
                                             new Point(westBoundLongitude, northBoundLatitude));
        this.box = new GeoJson(new Polygon(boxShape));
    }


    /**
     * Cleans each GeoJson attached to the location.
     * Each invalid GeoJsons will become null.
     */
    @Override
    public boolean clean()
    {
        if (point != null) {
            point.clean();

            // remove geoJson if it became invalid
            if (!point.isValid())
                point = null;
        }

        // remove each polygon, if it is invalid
        if (polygons != null) {
            int i = polygons.size();

            while (i != 0) {
                i--;
                GeoJson geo = polygons.get(i);

                if (geo == null)
                    polygons.remove(i);
                else {
                    geo.clean();

                    if (!geo.isValid())
                        polygons.remove(i);
                }
            }

            // nullify the whole polygon list, if it is now empty
            if (polygons.isEmpty())
                polygons = null;
        }

        if (box != null) {
            box.clean();

            // remove geoJson if it became invalid
            if (!box.isValid())
                box = null;
        }

        return isValid();
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
