package de.gerdiproject.json.utils;


import java.io.Reader;
import java.util.LinkedHashSet;
import java.util.Set;

import de.gerdiproject.json.IGeoJsonBuilder;
import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonBuilder;
import de.gerdiproject.json.IJsonObject;
import de.gerdiproject.json.IJsonReader;


public abstract class AbstractJsonBuilder<O extends IJsonObject, A extends IJsonArray, R extends IJsonReader> implements IJsonBuilder
{
	private final Class<O> jsonObjectClass;
	private final Class<A> jsonArrayClass;
	private final Class<R> jsonReaderClass;
	private final IGeoJsonBuilder geoBuilder;


	/**
	 * Constructor that requires implementing JSON classes.
	 * 
	 * @param jsonObjectClass
	 *            a non-abstract class that implements {@link IJsonObject}
	 * @param jsonArrayClass
	 *            a non-abstract class that implements {@link IJsonArray}
	 * @param jsonReaderClass
	 *            a non-abstract class that implements {@link IJsonReader}
	 */
	public AbstractJsonBuilder( Class<O> jsonObjectClass, Class<A> jsonArrayClass, Class<R> jsonReaderClass )
	{
		this.jsonObjectClass = jsonObjectClass;
		this.jsonArrayClass = jsonArrayClass;
		this.jsonReaderClass = jsonReaderClass;

		geoBuilder = new GeoJsonBuilder();
	}


	@Override
	public IJsonReader createReader( Reader reader )
	{
		try
		{
			IJsonReader jsonReader = jsonReaderClass.newInstance();
			jsonReader.init( reader );

			return jsonReader;
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}


	@Override
	public IJsonObject createObject()
	{
		try
		{
			return jsonObjectClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}


	@Override
	public IJsonArray createArray()
	{
		try
		{
			return jsonArrayClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}


	@Override
	public IJsonArray createArrayFromLists( Iterable<?>... iterables )
	{
		// put all list contents to a set, which removes duplicates
		Set<Object> listSet = new LinkedHashSet<>();
		for (Iterable<?> iter : iterables)
		{
			iter.forEach( ( ele ) -> listSet.add( ele ) );
		}

		// create JsonArray out of the set
		IJsonArray array = createArray();
		array.addAll( listSet );

		return array;
	}


	@Override
	public IJsonArray createArrayFromObjects( Object... elements )
	{
		IJsonArray array = createArray();
		for (Object ele : elements)
		{
			array.add( ele );
		}
		return array;
	}


	@Override
	public IGeoJsonBuilder geoBuilder()
	{
		return geoBuilder;
	}

	protected class GeoJsonBuilder implements IGeoJsonBuilder
	{
		public static final String TYPE_JSON = "type";
		public static final String COORDINATES_JSON = "coordinates";

		public final static int MIN_LATITUDE = -90;
		public final static int MAX_LATITUDE = 90;

		public final static int MIN_LONGITUDE = -180;
		public final static int MAX_LONGITUDE = 180;

		public final static String POLYGON_TYPE = "Polygon";


		public IJsonObject createRectangle( double longitudeWest, double latitudeNorth, double longitudeEast,
				double latitudeSouth )
		{
			IJsonArray topLeft = createArrayFromObjects( longitudeWest, latitudeNorth );
			IJsonArray topRight = createArrayFromObjects( longitudeEast, latitudeNorth );
			IJsonArray bottomLeft = createArrayFromObjects( longitudeWest, latitudeSouth );
			IJsonArray bottomRight = createArrayFromObjects( longitudeEast, latitudeSouth );

			IJsonArray innerPoly = createArrayFromObjects(
					topLeft,
					topRight,
					bottomRight,
					bottomLeft,
					topLeft );

			// wrap the polygon inside another array, because we only need a
			// single polygon
			IJsonArray coordinates = createArrayFromObjects( innerPoly );

			// create the geojson object
			IJsonObject geoJson = createObject();
			geoJson.put( TYPE_JSON, POLYGON_TYPE );
			geoJson.put( COORDINATES_JSON, coordinates );

			return geoJson;
		}


		public IJsonObject createHorizontalRing( double latitudeNorth, double latitudeSouth )
		{
			return createRectangle( MIN_LONGITUDE, latitudeNorth, MAX_LONGITUDE, latitudeSouth );
		}


		public IJsonObject createVerticalRing( double longitudeWest, double longitudeEast )
		{
			return createRectangle( longitudeWest, MAX_LATITUDE, longitudeEast, MIN_LATITUDE );
		}
	}
}
