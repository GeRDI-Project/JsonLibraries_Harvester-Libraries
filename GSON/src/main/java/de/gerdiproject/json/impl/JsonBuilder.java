package de.gerdiproject.json.impl;


import de.gerdiproject.json.utils.AbstractJsonBuilder;


/**
 * This is the builder class for creating JSON arrays, objects, and readers that
 * use the GSON library.
 * 
 * @author Robin Weiss
 *
 */
public class JsonBuilder extends AbstractJsonBuilder<GsonObject, GsonArray, GsonReader>
{
	/**
	 * Constructor for a JsonBuilder instance that calls the super class'
	 * constructor with GSON classes.
	 */
	public JsonBuilder()
	{
		super( GsonObject.class, GsonArray.class, GsonReader.class );

	}
}
