package de.gerdiproject.json.impl;

import java.io.Reader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import de.gerdiproject.json.IJson;
import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;
import de.gerdiproject.json.IJsonReader;

public class OracleJsonReader implements IJsonReader
{
	protected JsonReader wrappedReader;

	@Override
	public void init( Reader reader )
	{
		wrappedReader = Json.createReader( reader );
	}

	@Override
	public void close()
	{
		wrappedReader.close();
		
	}

	@Override
	public IJson read() throws IllegalStateException
	{
		JsonStructure struct = wrappedReader.read();
		
		if(struct instanceof JsonArray)
		{
			return new OracleJsonArray( (JsonArray) struct);
		}
		else if(struct instanceof JsonObject)
		{
			return new OracleJsonObject( (JsonObject) struct);
		}
		
		return null;
	}

	@Override
	public IJsonArray readArray() throws IllegalStateException, ClassCastException
	{
		return new OracleJsonArray(wrappedReader.readArray());
	}

	@Override
	public IJsonObject readObject() throws IllegalStateException, ClassCastException
	{
		return new OracleJsonObject(wrappedReader.readObject());
	}

}
