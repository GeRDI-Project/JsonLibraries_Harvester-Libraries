/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package de.gerdiproject.json.utils;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;


/**
 * A helper class for manipulating JsonObjects.
 *
 * @author row
 */
public class JsonHelper
{

	/**
	 * Since this is a static class, no constructor is needed.
	 */
	private JsonHelper()
	{
	}


	/**
	 * Retrieves a date value from a JSON-object.
	 *
	 * @param obj
	 *            the JSON-object from which the date is to be retrieved
	 * @param key
	 *            the key of the date attribute
	 * @return a SQL-date or null, if the date cannot be parsed
	 */
	public static Date getDateFromObject( IJsonObject obj, String key )
	{
		try
		{
			return Date.valueOf( obj.getString( key ) );
		}
		catch (IllegalArgumentException e)
		{
			return null;
		}
	}


	/**
	 * Tries to find an element in a JSON-array that has a specified key-value
	 * pair.
	 *
	 * @param array
	 *            the array that is to be searched
	 * @param key
	 *            a required key of the element that is to be found
	 * @param value
	 *            the value of the key
	 * @return a JsonObject or null, if no element has the required key-value
	 *         pair
	 */
	public static IJsonObject findObjectInArray( IJsonArray array, String key, String value )
	{
		for (Object element : array)
		{
			if (element instanceof IJsonObject)
			{
				IJsonObject obj = (IJsonObject) element;

				if (value.equals( obj.getString( key, null ) ))
				{
					return obj;
				}
			}
		}
		return null;
	}


	/**
	 * Retrieves a list of string values from a JSON-array of JSON-objects.
	 *
	 * @param array
	 *            a JsonArray of JsonObjects
	 * @param key
	 *            the key of each element in the array of which the value is
	 *            retrieved
	 * @return a list of string values
	 */
	public static List<String> arrayToStringList( IJsonArray array, String key )
	{
		ArrayList<String> list = new ArrayList<>( array.size() );

		array.forEach( (Object element ) ->
		{
			if (element instanceof IJsonObject)
			{
				String value = ((IJsonObject) element).getString( key, null );

				if (value != null)
				{
					list.add( value );
				}
			}
		});
		return list;
	}
}
