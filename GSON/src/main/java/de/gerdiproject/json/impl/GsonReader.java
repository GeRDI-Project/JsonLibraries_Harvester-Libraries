package de.gerdiproject.json.impl;


import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import de.gerdiproject.json.IJson;
import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;
import de.gerdiproject.json.IJsonReader;


/**
 * This Reader can read and parse JSON strings to create JSON objects and
 * arrays.
 * 
 * @author Robin Weiss
 *
 */
public class GsonReader implements IJsonReader
{
	private final JsonParser parser;
	private JsonReader reader;


	/**
	 * Constructor that creates a Json parser instance.
	 */
	public GsonReader()
	{
		parser = new JsonParser();
	}


	@Override
	public void init( Reader reader )
	{
		this.reader = new JsonReader( reader );
	}


	@Override
	public void close() throws IOException
	{
		reader.close();
	}


	@Override
	public IJson read() throws IllegalStateException, IOException, ParseException
	{
		JsonElement ele = parser.parse( reader );

		if (ele.isJsonObject())
		{
			return new GsonObject( ele.getAsJsonObject() );
		}
		else if (ele.isJsonArray())
		{
			return new GsonArray( ele.getAsJsonArray() );
		}

		return null;
	}


	@Override
	public IJsonArray readArray() throws IllegalStateException, ClassCastException, IOException, ParseException
	{
		JsonElement ele = parser.parse( reader );
		return new GsonArray( ele.getAsJsonArray() );
	}


	@Override
	public IJsonObject readObject() throws IllegalStateException, ClassCastException, IOException, ParseException
	{
		JsonElement ele = parser.parse( reader );
		return new GsonObject( ele.getAsJsonObject() );
	}

}
