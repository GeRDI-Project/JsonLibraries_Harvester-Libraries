package de.gerdiproject.json.impl;


import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/**
 * This is a wrapper for the GSON implementation of JSON objects. It wraps a
 * {@link JsonObject} and provides convenience functions.
 * 
 * @author Robin Weiss
 *
 */
public class GsonObject implements IJsonObject
{
	protected JsonObject wrappedObject;


	/**
	 * Simple constructor that initializes the wrapped JsonObject as an empty
	 * {@link JsonObject}.
	 */
	public GsonObject()
	{
		wrappedObject = new JsonObject();
	}


	/**
	 * Constructor that requires an existing {@link JsonObject} to be specified.
	 * 
	 * @param wrappedObject
	 *            a {@link JsonObject} that will be wrapped by this class
	 */
	public GsonObject( JsonObject wrappedObject )
	{
		this.wrappedObject = wrappedObject;
	}


	@Override
	public String toJsonString()
	{
		return wrappedObject.toString();
	}


	@Override
	public boolean isEmpty()
	{
		return wrappedObject.size() == 0;
	}


	@Override
	public Iterator<Entry<String, Object>> iterator()
	{
		return new JsonIterator();
	}


	@Override
	public boolean isNull( String key )
	{
		JsonElement ele = wrappedObject.get( key );
		return ele == null || ele.isJsonNull();
	}


	@Override
	public boolean isNonEmptyValue( String key )
	{
		JsonElement ele = wrappedObject.get( key );

		return ele != null
				&& !ele.isJsonNull()
				&& (!ele.isJsonArray() || ((JsonArray) ele).size() > 0)
				&& (!ele.isJsonObject() || ((JsonObject) ele).size() > 0);
	}


	@Override
	public int size()
	{
		return wrappedObject.size();
	}


	@Override
	public int getInt( String key ) throws NullPointerException, ClassCastException
	{
		return wrappedObject.get( key ).getAsInt();
	}


	@Override
	public int getInt( String key, int defaultValue )
	{
		JsonElement ele = wrappedObject.get( key );
		if (ele != null && ele.isJsonPrimitive())
		{
			if (ele.getAsJsonPrimitive().isNumber())
			{
				return ele.getAsInt();
			}
		}
		return defaultValue;
	}


	@Override
	public double getDouble( String key ) throws NullPointerException, ClassCastException
	{
		return wrappedObject.get( key ).getAsDouble();
	}


	@Override
	public double getDouble( String key, double defaultValue )
	{
		JsonElement ele = wrappedObject.get( key );
		if (ele != null && ele.isJsonPrimitive())
		{
			if (ele.getAsJsonPrimitive().isNumber())
			{
				return ele.getAsDouble();
			}
		}
		return defaultValue;
	}


	@Override
	public String getString( String key ) throws NullPointerException, ClassCastException
	{
		return wrappedObject.get( key ).getAsString();
	}


	@Override
	public String getString( String key, String defaultValue )
	{
		JsonElement ele = wrappedObject.get( key );
		if (ele != null && ele.isJsonPrimitive())
		{
			if (ele.getAsJsonPrimitive().isString())
			{
				return ele.getAsString();
			}
		}
		return defaultValue;
	}


	@Override
	public boolean getBoolean( String key ) throws NullPointerException, ClassCastException
	{
		return wrappedObject.get( key ).getAsBoolean();
	}


	@Override
	public boolean getBoolean( String key, boolean defaultValue )
	{
		JsonElement ele = wrappedObject.get( key );
		if (ele != null && ele.isJsonPrimitive())
		{
			if (ele.getAsJsonPrimitive().isBoolean())
			{
				return ele.getAsBoolean();
			}
		}
		return defaultValue;
	}


	@Override
	public IJsonObject getJsonObject( String key ) throws ClassCastException
	{
		JsonElement ele = wrappedObject.get( key );
		return (ele != null && !ele.isJsonNull()) ? new GsonObject( ele.getAsJsonObject() ) : null;
	}


	@Override
	public IJsonObject getJsonObject( String key, IJsonObject defaultValue )
	{
		JsonElement ele = wrappedObject.get( key );
		if (ele != null && ele.isJsonObject())
		{
			return new GsonObject( ele.getAsJsonObject() );
		}
		return defaultValue;
	}


	@Override
	public IJsonArray getJsonArray( String key ) throws ClassCastException
	{
		JsonElement ele = wrappedObject.get( key );
		return (ele != null && !ele.isJsonNull()) ? new GsonArray( ele.getAsJsonArray() ) : null;
	}


	@Override
	public IJsonArray getJsonArray( String key, IJsonArray defaultValue )
	{
		JsonElement ele = wrappedObject.get( key );
		if (ele != null && ele.isJsonArray())
		{
			return new GsonArray( ele.getAsJsonArray() );
		}
		return defaultValue;
	}


	@Override
	public Object get( String key )
	{
		JsonElement ele = wrappedObject.get( key );

		return GsonUtils.gsonToObject( ele );
	}


	@Override
	public Object get( String key, Object defaultValue )
	{
		Object obj = GsonUtils.gsonToObject( wrappedObject.get( key ) );
		return (obj != null) ? obj : defaultValue;
	}


	@Override
	public Object put( String key, Object value )
	{
		Object oldValue = get( key );

		if (value instanceof GsonObject)
		{
			wrappedObject.add( key, ((GsonObject) value).wrappedObject );
		}
		else if (value instanceof GsonArray)
		{
			wrappedObject.add( key, ((GsonArray) value).wrappedArray );
		}
		else if (value instanceof String)
		{
			wrappedObject.addProperty( key, (String) value );
		}
		else if (value instanceof Number)
		{
			wrappedObject.addProperty( key, (Number) value );
		}
		else if (value instanceof Boolean)
		{
			wrappedObject.addProperty( key, (Boolean) value );
		}

		return oldValue;
	}


	@Override
	public Object putNotNull( String key, Object value )
	{
		if (value != null)
		{
			return put( key, value );
		}
		return null;
	}


	@Override
	public Object remove( String key )
	{
		JsonElement oldValue = wrappedObject.remove( key );
		return GsonUtils.gsonToObject( oldValue );
	}

	/**
	 * Custom iterator class for iterating the Json object.
	 * 
	 * @author Robin Weiss
	 *
	 */
	protected class JsonIterator implements Iterator<Entry<String, Object>>
	{
		final Iterator<String> keySet = wrappedObject.keySet().iterator();


		@Override
		final public boolean hasNext()
		{
			return keySet.hasNext();
		}


		@Override
		final public Entry<String, Object> next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}

			String key = keySet.next();
			Object value = get( key );

			return new AbstractMap.SimpleEntry<String, Object>( key, value );
		}


		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
