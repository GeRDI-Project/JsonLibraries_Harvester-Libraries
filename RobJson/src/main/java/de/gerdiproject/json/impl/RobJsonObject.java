package de.gerdiproject.json.impl;

import java.util.HashMap;
import java.util.Iterator;

import de.gerdiproject.json.IJson;
import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;

public class RobJsonObject extends HashMap<String, Object> implements IJsonObject
{
	private static final long serialVersionUID = 1649386023436485594L;

	@Override
	public String toJsonString()
	{
		if(isEmpty())
		{
			return "{}";
		}
		
		StringBuilder sb = new StringBuilder("{");
		forEach((String key, Object value) ->
		{
			sb.append('"').append( key ).append( "\":" );
			
			if(value instanceof String)
			{
				sb.append('"').append( value ).append( '"' );
			}
			else if(value instanceof IJson)
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
		});
		
		// remove the last comma
		sb.deleteCharAt( sb.length() - 1 );
		
		sb.append( "}" );
		
		return sb.toString();
	}

	@Override
	public Iterator<java.util.Map.Entry<String, Object>> iterator()
	{
		return entrySet().iterator();
	}

	@Override
	public boolean isNull( String key )
	{
		return getOrDefault( key, null ) == null;
	}

	@Override
	public boolean isNonEmptyValue( String key )
	{
		Object val = super.get( key );
		return val != null	&& ( !(val instanceof IJson) || !((IJson) val).isEmpty());
	}

	@Override
	public Object get( String key )
	{
		return super.get(key);
	}

	@Override
	public Object get( String key, Object defaultValue )
	{
		return getOrDefault( key, defaultValue );
	}
	
	@Override
	public int getInt( String key ) throws NullPointerException, ClassCastException
	{
		return ((Number)super.get( key )).intValue();
	}

	@Override
	public int getInt( String key, int defaultValue )
	{
		Object obj = super.get( key );
		if(obj != null && obj instanceof Number)
		{
			return ((Number) obj).intValue();
		}
		return defaultValue;
	}

	@Override
	public double getDouble( String key ) throws NullPointerException, ClassCastException
	{
		return ((Number)super.get( key )).doubleValue();
	}

	@Override
	public double getDouble( String key, double defaultValue )
	{
		Object obj = super.get( key );
		if(obj != null && obj instanceof Number)
		{
			return ((Number) obj).doubleValue();
		}
		return defaultValue;
	}

	@Override
	public String getString( String key ) throws NullPointerException, ClassCastException
	{
		return (String) super.get( key );
	}

	@Override
	public String getString( String key, String defaultValue )
	{
		Object obj = super.get( key );
		if(obj != null && obj instanceof String)
		{
			return (String) obj;
		}
		return defaultValue;
	}

	@Override
	public boolean getBoolean( String key ) throws NullPointerException, ClassCastException
	{
		return (Boolean) super.get( key );
	}

	@Override
	public boolean getBoolean( String key, boolean defaultValue )
	{
		Object obj = super.get( key );
		if(obj != null && obj instanceof Boolean)
		{
			return (Boolean) obj;
		}
		return defaultValue;
	}

	@Override
	public IJsonObject getJsonObject( String key ) throws ClassCastException
	{
		return (IJsonObject) super.get( key );
	}

	@Override
	public IJsonObject getJsonObject( String key, IJsonObject defaultValue )
	{
		Object obj = super.get( key );
		if(obj != null && obj instanceof IJsonObject)
		{
			return (IJsonObject) obj;
		}
		return defaultValue;
	}

	@Override
	public IJsonArray getJsonArray( String key ) throws ClassCastException
	{
		return (IJsonArray) super.get( key );
	}

	@Override
	public IJsonArray getJsonArray( String key, IJsonArray defaultValue )
	{
		Object obj = super.get( key );
		if(obj != null && obj instanceof IJsonArray)
		{
			return (IJsonArray) obj;
		}
		return defaultValue;
	}
	

	@Override
	public Object putNotNull( String key, Object value )
	{
		if(value != null)
		{
			return put(key, value);
		}
		return null;
	}

	@Override
	public Object remove( String key )
	{		
		return super.remove(key);
	}
	
	/*public <T extends Object> T get( String key, Class<T> clazz )
	{
		Object obj = super.get( key );
		if(clazz.isInstance( obj ))
		{
			return  clazz.cast( obj );
		}
		return null;
	}*/
}
