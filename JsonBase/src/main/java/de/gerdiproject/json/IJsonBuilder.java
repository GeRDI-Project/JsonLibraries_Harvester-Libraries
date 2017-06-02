package de.gerdiproject.json;


import java.io.Reader;


public interface IJsonBuilder
{
	/**
	 * Creates a JSON reader.
	 * 
	 * @param reader
	 *            a reader that will be wrapped by the JSON reader.
	 * @return an instance of a JSON reader.
	 */
	public IJsonReader createReader( Reader reader );


	/**
	 * Creates an empty JSON object.
	 * 
	 * @return a new empty JSON object
	 */
	public IJsonObject createObject();


	/**
	 * Creates an empty JSON array.
	 * 
	 * @return a new empty JSON array
	 */
	public IJsonArray createArray();


	/**
	 * Retrieves all Objects from iteratable objects and adds them to a single
	 * Json-Array, removing duplicates.
	 * 
	 * @param iterable
	 *            an arbitrary number of iterable objects that are to be merged
	 * 
	 * @return a Json array that contains the content of all lists without
	 *         duplicates
	 */
	public IJsonArray createArrayFromLists( Iterable<?>... iterable );


	/**
	 * Creates a JSON array out of objects.
	 * 
	 * @param elements
	 *            an arbitrary number of objects
	 * 
	 * @return a Json array that contains all objects
	 */
	public IJsonArray createArrayFromObjects( Object... elements );


	/**
	 * Retrieves a builder of geo json objects.
	 * 
	 * @return a builder of geo json objects
	 */
	public IGeoJsonBuilder geoBuilder();
}
