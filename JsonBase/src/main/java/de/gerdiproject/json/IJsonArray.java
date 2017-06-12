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


import java.util.Collection;


/**
 * A collection that can be converted to a JSON string and offers some
 * convenience functions for retrieving values of specific types.
 * 
 * @author Robin Weiss
 *
 */
public interface IJsonArray extends IJson, Collection<Object>
{
	/**
	 * Returns the number of elements inside the array.
	 * 
	 * @return the number of elements inside the array
	 */
	public int size();


	/**
	 * Checks if a null value is stored at the given index.
	 * 
	 * @param index
	 *            the index under which a value is stored
	 * @return true, if null is stored at the given index
	 */
	public boolean isNull( int index ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Checks if a value that is neither null nor empty, exists at the given
	 * index.
	 * 
	 * @param index
	 *            the index under which a value is stored
	 * @return true, if a non-empty, non-null value exists at the given index
	 */
	public boolean isNonEmptyValue( int index ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves an integer value. Throws a NullpointerException if no value
	 * exists at the given index. Throws a ClassCastException if the value is
	 * not an integer.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @return an integer value
	 */
	public int getInt( int index ) throws NullPointerException, ClassCastException, ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves an integer value. Returns the default value if no value exists
	 * at the given index, or the value is not an integer.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored at the given
	 *            index
	 * @return an integer value
	 */
	public int getInt( int index, int defaultValue ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a double value. Throws a NullpointerException if no value
	 * exists at the given index. Throws a ClassCastException if the value is
	 * not double.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @return a double value
	 */
	public double getDouble( int index )
			throws NullPointerException, ClassCastException, ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a double value. Returns the default value if no value exists at
	 * the given index, or the value is not double.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored at the given
	 *            index
	 * @return a double value
	 */
	public double getDouble( int index, double defaultValue ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a string value. Throws a NullpointerException if no value
	 * exists at the given index. Throws a ClassCastException if the value is
	 * not a string.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @return a string
	 */
	public String getString( int index )
			throws NullPointerException, ClassCastException, ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a string value. Returns the default value if no value exists at
	 * the given index, or the value is not a string.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored at the given
	 *            index
	 * @return a string
	 */
	public String getString( int index, String defaultValue ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a boolean value. Throws a NullpointerException if no value
	 * exists at the given index. Throws a ClassCastException if the value is
	 * not a boolean value.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @return a boolean value
	 */
	public boolean getBoolean( int index )
			throws NullPointerException, ClassCastException, ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a boolean value. Returns the default value if no value exists
	 * at the given index, or the value is not a boolean.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored at the given
	 *            index
	 * @return a boolean value
	 */
	public boolean getBoolean( int index, boolean defaultValue ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a JSON Object. Throws a ClassCastException if the value is not
	 * a JSON Object.
	 * 
	 * @param index
	 *            the index at which the object is stored
	 * @return a JSON Object or null, if it does not exist
	 */
	public IJsonObject getJsonObject( int index ) throws ClassCastException, ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a JSON Object. Returns the default value if no value exists for
	 * the given key, or the value is not a JSON Object.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored at the given
	 *            index
	 * @return a JSON Object
	 */
	public IJsonObject getJsonObject( int index, IJsonObject defaultValue ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a JSON array. Throws a ClassCastException if the value is not a
	 * JSON array.
	 * 
	 * @param index
	 *            the index at which the object is stored
	 * @return a JSON array or null, if it does not exist
	 */
	public IJsonArray getJsonArray( int index ) throws ClassCastException, ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves a JSON array. Returns the default value if no value exists for
	 * the given key, or the value is not a JSON array.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored at the given
	 *            index
	 * @return a JSON array
	 */
	public IJsonArray getJsonArray( int index, IJsonArray defaultValue ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves an object.
	 * 
	 * @param index
	 *            the index at which the object is stored
	 * @return an object or null, if it does not exist
	 */
	public Object get( int index ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Retrieves an object. Returns the default value if no value exists for the
	 * given key.
	 * 
	 * @param index
	 *            the index at which the value is stored
	 * @param defaultValue
	 *            the value that is returned if no value is stored at the given
	 *            index
	 * @return an object
	 */
	public Object get( int index, Object defaultValue ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Stores an object.
	 * 
	 * @param index
	 *            the index at which the object is stored
	 * @param value
	 *            the value that is to be stored
	 * @return the old value or null, if no mapping used to exist
	 */
	public Object put( int index, Object value ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Stores an object if it is not null.
	 * 
	 * @param index
	 *            the index at which the object is stored
	 * @param value
	 *            the value that is to be stored
	 * @return the old value or null, if no mapping used to exist
	 */
	public Object putNotNull( int index, Object value ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Appends an element.
	 * 
	 * @param value
	 *            the element that is to be added
	 * @return true if adding the element succeeded
	 */
	public boolean add( Object value );


	/**
	 * Appends an element if it is not null.
	 * 
	 * @param value
	 *            the element that is to be added
	 */
	public void addNotNull( Object value );


	/**
	 * Appends multiple ojects.
	 * 
	 * @param values
	 *            the values that are to be added
	 */
	public void addAll( Iterable<?> values );


	/**
	 * Appends multiple ojects if they are not null.
	 * 
	 * @param values
	 *            the values that are to be added
	 */
	public void addAllNotNull( Iterable<?> values );


	/**
	 * Removes an element from the array.
	 * 
	 * @param index
	 *            the index of the element that is to be removed
	 * @return the removed element or null, if no element used to exist
	 */
	public Object remove( int index ) throws ArrayIndexOutOfBoundsException;


	/**
	 * Removes all elements from the array.
	 * 
	 */
	public void clear();

}
