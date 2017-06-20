package de.gerdiproject.json.impl;



import de.gerdiproject.json.utils.AbstractJsonBuilder;


public class JsonBuilder extends AbstractJsonBuilder<GsonObject, GsonArray, GsonReader>
{
	public JsonBuilder()
	{
		super( GsonObject.class, GsonArray.class, GsonReader.class );
		
	}
}
