package de.gerdiproject.json.impl;

import java.util.Set;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;
import javax.json.JsonValue;

import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;

public class OracleJsonObject implements IJsonObject
{
	protected JsonObject wrappedObj;

	public OracleJsonObject()
	{
		this.wrappedObj = Json.createObjectBuilder().build();
	}
	
	public OracleJsonObject( JsonObject wrappedObject)
	{
		this.wrappedObj = wrappedObject;
	}
	
	@Override
	public boolean isEmpty()
	{
		return wrappedObj.isEmpty();
	}

	@Override
	public boolean isNull( String key )
	{
		return wrappedObj.isNull( key );
	}

	@Override
	public boolean isNonEmptyValue( String key )
	{
		final JsonValue val = wrappedObj.get( key );
		return val != null && val.getValueType() != JsonValue.ValueType.NULL 
				&& ((val.getValueType() == JsonValue.ValueType.OBJECT && !((JsonObject) val).isEmpty())
						|| (val.getValueType() == JsonValue.ValueType.ARRAY && !((JsonArray) val).isEmpty())
						);
	}
	
	@Override
	public int size()
	{
		return wrappedObj.size();
	}

	@Override
	public int getInt( String key ) throws NullPointerException, ClassCastException
	{
		return wrappedObj.getInt( key );
	}

	@Override
	public int getInt( String key, int defaultValue )
	{
		return wrappedObj.getInt( key, defaultValue );
	}

	@Override
	public double getDouble( String key ) throws NullPointerException, ClassCastException
	{
		final JsonNumber num = wrappedObj.getJsonNumber( key );
		return num.doubleValue();
	}

	@Override
	public double getDouble( String key, double defaultValue )
	{
		final JsonNumber num = wrappedObj.getJsonNumber( key );
		return (num != null) ? num.doubleValue() : defaultValue;
	}

	@Override
	public String getString( String key ) throws NullPointerException, ClassCastException
	{
		return wrappedObj.getString( key );
	}

	@Override
	public String getString( String key, String defaultValue )
	{
		return wrappedObj.getString( key, defaultValue );
	}

	@Override
	public boolean getBoolean( String key ) throws NullPointerException, ClassCastException
	{
		return wrappedObj.getBoolean( key );
	}

	@Override
	public boolean getBoolean( String key, boolean defaultValue )
	{
		return wrappedObj.getBoolean( key, defaultValue );
	}

	@Override
	public IJsonObject getJsonObject( String key ) throws ClassCastException
	{
		if( wrappedObj.containsKey( key ) && !wrappedObj.isNull( key ))
		{
			return new OracleJsonObject( wrappedObj.getJsonObject( key ) );
		}
		return null;
	}

	@Override
	public IJsonObject getJsonObject( String key, IJsonObject defaultValue )
	{
		if( wrappedObj.containsKey( key ) && !wrappedObj.isNull( key ))
		{
			return new OracleJsonObject( wrappedObj.getJsonObject( key ) );
		}
		return defaultValue;
	}

	@Override
	public IJsonArray getJsonArray( String key ) throws ClassCastException
	{
		if( wrappedObj.containsKey( key ) && !wrappedObj.isNull( key ))
		{
			return new OracleJsonArray( wrappedObj.getJsonArray( key ) );
		}
		return null;
	}

	@Override
	public IJsonArray getJsonArray( String key, IJsonArray defaultValue )
	{
		if( wrappedObj.containsKey( key ) && !wrappedObj.isNull( key ))
		{
			return new OracleJsonArray( wrappedObj.getJsonArray( key ) );
		}
		return defaultValue;
	}

	@Override
	public Object get( String key )
	{
		JsonValue val = wrappedObj.get( key );
		
		if(val == null)
		{
			return null;
		}
		
		switch(val.getValueType())
		{
			case OBJECT:
				return new OracleJsonObject((JsonObject) val);

			case ARRAY:
				return new OracleJsonArray((JsonArray) val);
				
			case NUMBER:
				JsonNumber num = ((JsonNumber) val);
				return ((double)num.intValue() == num.doubleValue())
						? new Integer(num.intValue())
						: new Double(num.doubleValue());
						
			case STRING:
				return ((JsonString) val).getString();
				
			case FALSE:
				return new Boolean(false);
				
			case TRUE:
				return new Boolean(true);
				
			default:
				return null;
		}
	}

	@Override
	public Object get( String key, Object defaultValue )
	{
		Object obj = get(key);
		return (obj != null) ? obj : defaultValue;
	}

	@Override
	public Object put( String key, Object value )
	{
		Object oldVal = get(key);
		
		// copy existing fields into a builder
		final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		wrappedObj.entrySet().forEach( (Entry<String, JsonValue> e) -> objectBuilder.add( e.getKey(), e.getValue() ) );
		
		// add new field
		if(value == null)
		{
			objectBuilder.addNull( key );
		}
		else if(value instanceof String)
		{
			objectBuilder.add( key, (String)value );
		}
		else if(value instanceof Integer)
		{
			objectBuilder.add( key, (Integer)value );
		}
		else if(value instanceof Double)
		{
			objectBuilder.add( key, (Double)value );
		}
		else if(value instanceof OracleJsonObject)
		{
			objectBuilder.add( key, ((OracleJsonObject)value).wrappedObj );
		}
		else if(value instanceof OracleJsonArray)
		{
			objectBuilder.add( key, ((OracleJsonArray)value).wrappedObj );
		}
		else if(value instanceof Boolean)
		{
			objectBuilder.add( key, (Boolean)value );
		}
		else if(value instanceof JsonValue)
		{
			objectBuilder.add( key, (JsonValue) value );
		}
		else if(value instanceof Long)
		{
			objectBuilder.add( key, (Long)value );
		}
		else if(value instanceof BigDecimal)
		{
			objectBuilder.add( key, (BigDecimal)value );
		}
		else if(value instanceof BigInteger)
		{
			objectBuilder.add( key, (BigInteger)value );
		}
		// replace old object
		wrappedObj = objectBuilder.build();	
		
		return oldVal;
	}
	
	@Override
	public Object putNotNull( String key, Object value )
	{
		if(value != null)
		{
			return put( key, value );
		}
		return null;
	}

	@Override
	public Object remove( String key )
	{
		// memorize old value
		Object oldVal = get(key);
		
		// build a new object
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		Set<Entry<String, JsonValue>> originalFields = wrappedObj.entrySet();
		for(Entry<String, JsonValue> entry : originalFields)
		{
			if(!key.equals( entry.getKey() ))
			{
				objectBuilder.add( entry.getKey(), entry.getValue() );
			}
		}
		
		// replace old object
		wrappedObj = objectBuilder.build();
		
		return oldVal;
	}

	@Override
	public String toJsonString()
	{
		return wrappedObj.toString();
	}

	@Override
	public Iterator<Entry<String, Object>> iterator()
	{
		return new JsonIterator();
	}
	
	/**
	 * Custom iterator class for iterating the Json object.
	 * @author Robin Weiss
	 *
	 */
	protected class JsonIterator implements Iterator<Entry<String, Object>>
	{
		final Iterator<String> keySet = wrappedObj.keySet().iterator();

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
			
			return new AbstractMap.SimpleEntry<String,Object>(key, value);
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
