package de.gerdiproject.json.impl;


import de.gerdiproject.json.utils.AbstractJsonBuilder;


public class JsonBuilder extends AbstractJsonBuilder<RobJsonObject, RobJsonArray, RobJsonReader>
{

	public JsonBuilder()
	{
		super( RobJsonObject.class, RobJsonArray.class, RobJsonReader.class );
	}
}
