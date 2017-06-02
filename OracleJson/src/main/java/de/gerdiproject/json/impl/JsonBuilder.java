package de.gerdiproject.json.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.utils.AbstractJsonBuilder;


public class JsonBuilder extends AbstractJsonBuilder<OracleJsonObject, OracleJsonArray, OracleJsonReader>
{

	public JsonBuilder()
	{
		super( OracleJsonObject.class, OracleJsonArray.class, OracleJsonReader.class );
	}

	
	@Override
	public IJsonArray createArrayFromLists( Iterable<?>... lists )
	{
		Set<Object> listSet = new LinkedHashSet<>();

		// put all list contents to a set, which removes duplicates
		int i = lists.length;
		while (i != 0)
		{
			i--;
			Iterable<?> list = lists[i];
			list.forEach( (ele) -> listSet.add( ele ) );
		}

		// create JsonArray out of the set
		JsonArrayBuilder builder = Json.createArrayBuilder();
		listSet.forEach( ( Object o ) -> OracleJsonArray.addObjectToBuilder( o, builder ) );

		return new OracleJsonArray( builder.build() );
	}
	
	@Override
	public IJsonArray createArrayFromObjects( Object... elements )
	{
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		int i = elements.length;
		while (i != 0)
		{
			i--;
			OracleJsonArray.addObjectToBuilder( elements[i], builder );
		}

		return new OracleJsonArray( builder.build() );
	}
}
