package de.gerdiproject.json;


public interface IGeoJsonBuilder
{
	/**
	 * Creates a geo json polygon using only two points.
	 * 
	 * @param longitudeWest
	 *            the longitude that describes the western edge of the rectangle
	 * @param latitudeNorth
	 *            the longitude that describes the eastern edge of the rectangle
	 * @param longitudeEast
	 *            the longitude that describes the northern edge of the
	 *            rectangle
	 * @param latitudeSouth
	 *            the longitude that describes the southern edge of the
	 *            rectangle
	 * @return a geojson polygon that represents a rectangle
	 */
	public IJsonObject createRectangle( double longitudeWest, double latitudeNorth, double longitudeEast,
			double latitudeSouth );


	/**
	 * Creates a geo json polygon that represents a horizontal ring around the
	 * globe.
	 * 
	 * @param latitudeNorth
	 *            the northern border of the ring
	 * @param latitudeSouth
	 *            the southern border of the ring
	 * @return a geo json polygon that represents a horizontal ring around the
	 *         globe
	 */
	public IJsonObject createHorizontalRing( double latitudeNorth, double latitudeSouth );


	/**
	 * Creates a geo json polygon that represents a vertical ring around the
	 * globe.
	 * 
	 * @param longitudeWest
	 *            the western border of the ring
	 * @param longitudeEast
	 *            the eastern border of the ring
	 * @return a geo json polygon that represents a vertical ring around the
	 *         globe
	 */
	public IJsonObject createVerticalRing( double longitudeWest, double longitudeEast );
}
