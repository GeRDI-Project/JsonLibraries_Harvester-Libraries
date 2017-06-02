package de.gerdiproject.json.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;

public class OracleJsonArray implements IJsonArray
{
	protected JsonArray wrappedObj;

	public OracleJsonArray()
	{
		this.wrappedObj = Json.createArrayBuilder().build();
	}

	public OracleJsonArray( JsonArray wrappedObject )
	{
		this.wrappedObj = wrappedObject;
	}

	@Override
	public boolean isEmpty()
	{
		return wrappedObj.isEmpty();
	}

	@Override
	public int size()
	{
		return wrappedObj.size();
	}

	@Override
	public boolean isNull( int index )
	{
		return wrappedObj.isNull( index );
	}

	@Override
	public boolean isNonEmptyValue( int index )
	{
		final JsonValue val = wrappedObj.get( index );
		return val != null && val.getValueType() != JsonValue.ValueType.NULL
				&& ((val.getValueType() == JsonValue.ValueType.OBJECT && !((JsonObject) val).isEmpty())
						|| (val.getValueType() == JsonValue.ValueType.ARRAY && !((JsonArray) val).isEmpty()));
	}

	@Override
	public int getInt( int index ) throws NullPointerException, ClassCastException
	{
		return wrappedObj.getInt( index );
	}

	@Override
	public int getInt( int index, int defaultValue )
	{
		return wrappedObj.getInt( index, defaultValue );
	}

	@Override
	public double getDouble( int index ) throws NullPointerException, ClassCastException
	{
		JsonNumber num = wrappedObj.getJsonNumber( index );
		return num.doubleValue();
	}

	@Override
	public double getDouble( int index, double defaultValue )
	{
		JsonNumber num = wrappedObj.getJsonNumber( index );
		return (num != null) ? num.doubleValue() : defaultValue;
	}

	@Override
	public String getString( int index ) throws NullPointerException, ClassCastException
	{
		return wrappedObj.getString( index );
	}

	@Override
	public String getString( int index, String defaultValue )
	{
		return wrappedObj.getString( index, defaultValue );
	}

	@Override
	public boolean getBoolean( int index ) throws NullPointerException, ClassCastException
	{
		return wrappedObj.getBoolean( index );
	}

	@Override
	public boolean getBoolean( int index, boolean defaultValue )
	{
		return wrappedObj.getBoolean( index, defaultValue );
	}

	@Override
	public IJsonObject getJsonObject( int index ) throws ClassCastException
	{
		JsonObject obj = wrappedObj.getJsonObject( index );
		return (obj != null) ? new OracleJsonObject( obj ) : null;
	}

	@Override
	public IJsonObject getJsonObject( int index, IJsonObject defaultValue )
	{

		JsonObject obj = wrappedObj.getJsonObject( index );
		return (obj != null) ? new OracleJsonObject( obj ) : defaultValue;
	}

	@Override
	public IJsonArray getJsonArray( int index ) throws ClassCastException
	{
		JsonArray array = wrappedObj.getJsonArray( index );
		return (array != null) ? new OracleJsonArray( array ) : null;
	}

	@Override
	public IJsonArray getJsonArray( int index, IJsonArray defaultValue )
	{
		JsonArray array = wrappedObj.getJsonArray( index );
		return (array != null) ? new OracleJsonArray( array ) : defaultValue;
	}

	@Override
	public Object get( int index )
	{
		JsonValue val = wrappedObj.get( index );

		if (val == null)
		{
			return null;
		}

		switch (val.getValueType())
		{
			case OBJECT:
				return new OracleJsonObject((JsonObject) val);

			case ARRAY:
				return new OracleJsonArray((JsonArray) val);

			case NUMBER:
				JsonNumber num = ((JsonNumber) val);
				return ((double) num.intValue() == num.doubleValue())
						? new Integer( num.intValue() )
						: new Double( num.doubleValue() );

			case STRING:
				return ((JsonString) val).getString();

			case FALSE:
				return new Boolean( false );

			case TRUE:
				return new Boolean( true );

			default:
				return null;
		}
	}

	@Override
	public Object get( int index, Object defaultValue )
	{
		Object obj = get( index );
		return (obj != null) ? obj : defaultValue;
	}

	@Override
	public Object put( int index, Object value )
	{
		Object oldVal = get( index );

		// copy existing fields into a builder
		final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		
		int i = 0;
		int len = size();

		// add fields before the new field
		while (i < len)
		{
			if (i == index)
			{
				addObjectToBuilder( value, arrayBuilder );
			}
			else
			{
				arrayBuilder.add( wrappedObj.get( i++ ) );
			}
		}

		// replace old object
		wrappedObj = arrayBuilder.build();

		return oldVal;
	}
	
	@Override
	public Object putNotNull( int index, Object value )
	{
		return (value != null) 
				? put(index, value) 
				: null;
	}

	@Override
	public boolean add( Object value )
	{
		// copy existing fields into a builder
		final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		// add old fields
		wrappedObj.forEach( ( JsonValue val ) -> arrayBuilder.add( val ) );

		// add new field
		addObjectToBuilder( value, arrayBuilder );

		// replace old object
		wrappedObj = arrayBuilder.build();
		
		return true;
	}
	
	@Override
	public void addNotNull( Object value )
	{
		if(value != null)
		{
			add(value);
		}
	}
	

	@Override
	public void addAll( Iterable<?> values )
	{
		// copy existing fields into a builder
		final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		// add old fields
		wrappedObj.forEach( ( JsonValue val ) -> arrayBuilder.add( val ) );

		// add new fields
		values.forEach( ( Object val ) -> addObjectToBuilder( val, arrayBuilder ) );

		// replace old object
		wrappedObj = arrayBuilder.build();
	}
	
	@Override
	public void addAllNotNull( Iterable<?> values )
	{
		// copy existing fields into a builder
		final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		// add old fields
		wrappedObj.forEach( ( JsonValue val ) -> arrayBuilder.add( val ) );

		// add new fields
		values.forEach( ( Object val ) ->{
			if(val != null)
			{
				addObjectToBuilder( val, arrayBuilder );
			}
		});

		// replace old object
		wrappedObj = arrayBuilder.build();
	}

	/**
	 * Helper function that checks the type of an object to correctly add it to
	 * a Json Array builder.
	 * 
	 * @param obj
	 *            the object that is to be added
	 * @param builder
	 *            the builder to which the object is to be added
	 */
	public static void addObjectToBuilder( Object obj, JsonArrayBuilder builder )
	{
		if (obj == null)
		{
			builder.addNull();
		}
		else if (obj instanceof String)
		{
			builder.add( (String) obj );
		}
		else if (obj instanceof Integer)
		{
			builder.add( (Integer) obj );
		}
		else if (obj instanceof Double)
		{
			builder.add( (Double) obj );
		}
		else if (obj instanceof OracleJsonObject)
		{
			builder.add( ((OracleJsonObject) obj).wrappedObj );
		}
		else if (obj instanceof OracleJsonArray)
		{
			builder.add( ((OracleJsonArray) obj).wrappedObj );
		}
		else if (obj instanceof Boolean)
		{
			builder.add( (Boolean) obj );
		}
		else if (obj instanceof JsonValue)
		{
			builder.add( (JsonValue) obj );
		}
		else if (obj instanceof Long)
		{
			builder.add( (Long) obj );
		}
		else if (obj instanceof BigDecimal)
		{
			builder.add( (BigDecimal) obj );
		}
		else if (obj instanceof BigInteger)
		{
			builder.add( (BigInteger) obj );
		}
	}

	@Override
	public Object remove( int index )
	{
		// memorize old value
		Object oldVal = get( index );

		// copy existing fields into a builder
		final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		int i = 0;
		int len = size();

		// add all fields except for the specified index
		while (i < len)
		{
			if (i != index)
			{
				arrayBuilder.add( wrappedObj.get( i ) );
			}
			i++;
		}

		// replace old object
		wrappedObj = arrayBuilder.build();

		return oldVal;
	}

	@Override
	public String toJsonString()
	{
		return wrappedObj.toString();
	}
	
	@Override
	public void clear()
	{
		this.wrappedObj = Json.createArrayBuilder().build();
	}

	@Override
	public Iterator<Object> iterator()
	{
		return new JsonIterator();
	}
	
	/**
	 * Custom iterator class for iterating the Json object.
	 * @author Robin Weiss
	 *
	 */
	protected class JsonIterator implements Iterator<Object>
	{
		int index = 0;
		final int size = size();

		@Override
		final public boolean hasNext()
		{
			return index != size;
		}

		@Override
		final public Object next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			return get( index++ );
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
