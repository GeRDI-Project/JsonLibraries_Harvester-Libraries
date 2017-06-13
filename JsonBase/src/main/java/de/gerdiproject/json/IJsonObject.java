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
package de.gerdiproject.json;


import java.util.Map.Entry;


/**
 * An interface for JSON objects.
 * 
 * @author Robin Weiss
 *
 */
public interface IJsonObject extends IJson, Iterable<Entry<String, Object>>
{
	/**
	 * Checks if a null value is stored for the given key.
	 * 
	 * @param key
	 *            the key under which a value is stored
	 * @return true, if null is stored for the key
	 */
	public boolean isNull( String key );


	/**
	 * Checks if a value that is neither null nor empty, exists for a given key.
	 * 
	 * @param key
	 *            the key under which a value is stored
	 * @return true, if a non-empty, non-null value exists for the given key
	 */
	public boolean isNonEmptyValue( String key );


	/**
	 * Returns the number of key entries of the object.
	 * 
	 * @return the number of entries of the object
	 */
	public int size();


	/**
	 * Retrieves an integer value. Throws a NullpointerException if no value
	 * exists for the given key. Throws a ClassCastException if the value is not
	 * an integer.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @return an integer value
	 */
	public int getInt( String key ) throws NullPointerException, ClassCastException;


	/**
	 * Retrieves an integer value. Returns the default value if no value exists
	 * for the given key, or the value is not an integer.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored for the key
	 * @return an integer value
	 */
	public int getInt( String key, int defaultValue );


	/**
	 * Retrieves a double value. Throws a NullpointerException if no value
	 * exists for the given key. Throws a ClassCastException if the value is not
	 * double.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @return a double value
	 */
	public double getDouble( String key ) throws NullPointerException, ClassCastException;


	/**
	 * Retrieves a double value. Returns the default value if no value exists
	 * for the given key, or the value is not double.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored for the key
	 * @return a double value
	 */
	public double getDouble( String key, double defaultValue );


	/**
	 * Retrieves a string value. Throws a NullpointerException if no value
	 * exists for the given key. Throws a ClassCastException if the value is not
	 * a string.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @return a string
	 */
	public String getString( String key ) throws NullPointerException, ClassCastException;


	/**
	 * Retrieves a string value. Returns the default value if no value exists
	 * for the given key, or the value is not a string.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored for the key
	 * @return a string
	 */
	public String getString( String key, String defaultValue );


	/**
	 * Retrieves a boolean value. Throws a NullpointerException if no value
	 * exists for the given key. Throws a ClassCastException if the value is not
	 * a boolean value.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @return a boolean value
	 */
	public boolean getBoolean( String key ) throws NullPointerException, ClassCastException;


	/**
	 * Retrieves a boolean value. Returns the default value if no value exists
	 * for the given key, or the value is not a boolean.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored for the key
	 * @return a boolean value
	 */
	public boolean getBoolean( String key, boolean defaultValue );


	/**
	 * Retrieves a JSON Object. Throws a ClassCastException if the value is not
	 * a JSON Object.
	 * 
	 * @param key
	 *            the key under which the object is stored
	 * @return a JSON Object or null, if it does not exist
	 */
	public IJsonObject getJsonObject( String key ) throws ClassCastException;


	/**
	 * Retrieves a JSON Object. Returns the default value if no value exists for
	 * the given key, or the value is not a JSON Object.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored for the key
	 * @return a JSON Object
	 */
	public IJsonObject getJsonObject( String key, IJsonObject defaultValue );


	/**
	 * Retrieves a JSON array. Throws a ClassCastException if the value is not a
	 * JSON array.
	 * 
	 * @param key
	 *            the key under which the object is stored
	 * @return a JSON array or null, if it does not exist
	 */
	public IJsonArray getJsonArray( String key ) throws ClassCastException;


	/**
	 * Retrieves a JSON array. Returns the default value if no value exists for
	 * the given key, or the value is not a JSON array.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored for the key
	 * @return a JSON array
	 */
	public IJsonArray getJsonArray( String key, IJsonArray defaultValue );


	/**
	 * Retrieves an object.
	 * 
	 * @param key
	 *            the key under which the object is stored
	 * @return an object or null, if it does not exist
	 */
	public Object get( String key );


	/**
	 * Retrieves an object. Returns the default value if no value exists for the
	 * given key.
	 * 
	 * @param key
	 *            the key under which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored for the key
	 * @return an object
	 */
	public Object get( String key, Object defaultValue );


	/**
	 * Changes or creates a mapping.
	 * 
	 * @param key
	 *            the key under which the object is stored
	 * @param value
	 *            the value that is to be stored
	 * @return the old value or null, if no mapping used to exist
	 */
	public Object put( String key, Object value );


	/**
	 * Changes or createsa mapping if the value is not null.
	 * 
	 * @param key
	 *            the key under which the object is stored
	 * @param value
	 *            the value that is to be stored
	 * @return the old value or null, if no mapping used to exist
	 */
	public Object putNotNull( String key, Object value );


	/**
	 * Removes a mapping.
	 * 
	 * @param key
	 *            the key of the mapping that is to be removed
	 * @return the removed value or null, if no mapping used to exist
	 */
	public Object remove( String key );

}
