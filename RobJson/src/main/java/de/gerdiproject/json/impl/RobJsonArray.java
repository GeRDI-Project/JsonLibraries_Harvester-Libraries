package de.gerdiproject.json.impl;

import java.util.LinkedList;
import de.gerdiproject.json.IJson;
import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;


public class RobJsonArray extends LinkedList<Object> implements IJsonArray
{
	private static final long serialVersionUID = -4810557675954847645L;


	@Override
	public String toJsonString()
	{
		if(isEmpty())
		{
			return "[]";
		}
		
		StringBuilder sb = new StringBuilder( "[" );
		forEach( ( Object value ) -> {
			
			if (value instanceof String)
			{
				sb.append( '"' ).append( value ).append( '"' );
			}
			else if (value instanceof IJson)
			{
				sb.append( ((IJson) value).toJsonString() );
			}
			else if (value == null)
			{
				sb.append( "null" );
			}
			else
			{
				sb.append( value.toString() );
			}
			sb.append( ',' );
		} );

		// remove the last comma
		sb.deleteCharAt( sb.length() - 1 );

		sb.append( "]" );

		return sb.toString();
	}


	@Override
	public boolean isNull( int index )
	{
		return get( index ) == null;
	}


	@Override
	public boolean isNonEmptyValue( int index )
	{
		Object val = get( index );
		return val != null && (!(val instanceof IJson) || !((IJson) val).isEmpty());
	}


	@Override
	public Object get( int index, Object defaultValue )
	{
		return get( index, defaultValue );
	}


	@Override
	public int getInt( int index ) throws NullPointerException, ClassCastException
	{
		return ((Number)get( index )).intValue();
	}


	@Override
	public int getInt( int index, int defaultValue )
	{
		Object obj = get( index );
		if (obj != null && obj instanceof Number)
		{
			return ((Number) obj).intValue();
		}
		return defaultValue;
	}


	@Override
	public double getDouble( int index ) throws NullPointerException, ClassCastException
	{
		return ((Number)get( index )).doubleValue();
	}


	@Override
	public double getDouble( int index, double defaultValue )
	{
		Object obj = get( index );
		if (obj != null && obj instanceof Number)
		{
			return ((Number) obj).doubleValue();
		}
		return defaultValue;
	}


	@Override
	public String getString( int index ) throws NullPointerException, ClassCastException
	{
		return (String) get( index );
	}


	@Override
	public String getString( int index, String defaultValue )
	{
		Object obj = get( index );
		if (obj != null && obj instanceof String)
		{
			return (String) obj;
		}
		return defaultValue;
	}


	@Override
	public boolean getBoolean( int index ) throws NullPointerException, ClassCastException
	{
		return (Boolean) get( index );
	}


	@Override
	public boolean getBoolean( int index, boolean defaultValue )
	{
		Object obj = get( index );
		if (obj != null && obj instanceof Boolean)
		{
			return (Boolean) obj;
		}
		return defaultValue;
	}


	@Override
	public IJsonObject getJsonObject( int index ) throws ClassCastException
	{
		return (IJsonObject) get( index );
	}


	@Override
	public IJsonObject getJsonObject( int index, IJsonObject defaultValue )
	{
		Object obj = get( index );
		if (obj != null && obj instanceof IJsonObject)
		{
			return (IJsonObject) obj;
		}
		return defaultValue;
	}


	@Override
	public IJsonArray getJsonArray( int index ) throws ClassCastException
	{
		return (IJsonArray) get( index );
	}


	@Override
	public IJsonArray getJsonArray( int index, IJsonArray defaultValue )
	{
		Object obj = get( index );
		if (obj != null && obj instanceof IJsonArray)
		{
			return (IJsonArray) obj;
		}
		return defaultValue;
	}


	@Override
	public Object putNotNull( int index, Object value )
	{
		if (value != null)
		{
			return put( index, value );
		}
		return null;
	}

	@Override
	public void addNotNull( Object value )
	{
		if (value != null)
		{
			add( value );
		}
	}


	@Override
	public void addAll( Iterable<?> values )
	{
		values.forEach( ( Object val ) -> add( val ) );
	}


	@Override
	public void addAllNotNull( Iterable<?> values )
	{
		values.forEach( ( Object val ) -> {
			if (val != null)
			{
				add( val );
			}
		} );
	}


	@Override
	public Object put( int index, Object value )
	{
		return super.set(
				index,
				value );
	}
}
