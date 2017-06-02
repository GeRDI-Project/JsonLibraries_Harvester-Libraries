package de.gerdiproject.json.impl;


import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import de.gerdiproject.json.IJson;
import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;
import de.gerdiproject.json.IJsonReader;


public class RobJsonReader implements IJsonReader
{
	protected char currentChar;
	protected Reader reader;
	protected final NumberFormat numberFormatter;
	
	public RobJsonReader()
	{
		// always use english locale to interpret dots as decimal separator
		numberFormatter = NumberFormat.getInstance(Locale.ENGLISH);
	}


	@Override
	public void init( Reader reader )
	{
		this.reader = reader;
	}


	@Override
	public void close() throws IOException
	{
		reader.close();

	}


	@Override
	public IJson read() throws IllegalStateException, IOException, ClassCastException, ParseException
	{
		currentChar = (char) reader.read();

		if (currentChar == '{')
		{
			return parseObject();
		}
		else if (currentChar == '[')
		{
			return parseArray();
		}
		return null;
	}


	@Override
	public IJsonArray readArray() throws IllegalStateException, ClassCastException, IOException, ParseException
	{
		currentChar = (char) reader.read();
		if (currentChar == '[')
		{
			return parseArray();
		}
		return null;
	}


	@Override
	public IJsonObject readObject() throws IllegalStateException, ClassCastException, IOException, ParseException
	{
		currentChar = (char) reader.read();
		if (currentChar == '{')
		{
			return parseObject();
		}
		return null;
	}


	private IJsonObject parseObject() throws ParseException, IOException
	{
		IJsonObject object = new RobJsonObject();
		
		String key = null;
		Object value;

		int i;
		loop: while((i = reader.read()) != -1)
		{
			currentChar = (char) i;
			
			// ignore spaces
			if(Character.isSpaceChar( currentChar ))
			{
				continue;
			}
			
			// retrieve a key
			if(key == null)
			{
				switch(currentChar)
				{
					case ',':
						continue loop;
					case '}' :
						break loop;
					case '"':
						key = parseString();
						break;
						
					default:
						throw new ParseException("Could not parse JSON object. Unexpected char while looking for key: " + currentChar, 0);
				}
			}
			// retrieve a value
			else
			{
				switch(currentChar)
				{
					// colon
					case ':' :
						continue loop;
						
					// null value
					case 'n' :
						value = parseNull();
						break;
						
					// object value
					case '{' :
						value = parseObject();
						break;
						
					// array value
					case '[' :
						value = parseArray();
						break;
						
					// string value
					case '"' :
						value = parseString();
						break ;
						
					// boolean value
					case 't' :
					case 'f' :
						value = parseBoolean();
						break ;
						
					// number value
					default:
						value = parseNumber();
				}
				
				// add key-value pair to object
				object.put( key, value );
				
				// reset key
				key = null;
				
				// check if the char that marked the end of the parsed value actually closes this object
				if(currentChar == '}' && !(value instanceof IJsonObject))
				{
					break loop;
				}
			}
		}
		return object;
	}
	
	private String parseString() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		
		int i;
		char previousChar;
		while( (i = reader.read()) != -1)
		{
			previousChar = currentChar;
			currentChar = (char) i;
			
			// finish parsing the string when finding an unescaped quote
			if( currentChar == '"' && previousChar != '\\')
			{
				break;
			}
			sb.append( currentChar );
		}
		return sb.toString();
	}
	
	
	private Object parseNull() throws IOException, ParseException
	{
		StringBuilder sb = new StringBuilder();
		sb.append( currentChar );
		
		int i;
		while( (i = reader.read()) != -1)
		{
			currentChar = (char) i;
			
			if( currentChar == ',' || currentChar == ']' || currentChar == '}' || Character.isWhitespace( currentChar ) )
			{
				break;
			}
			else
			{
				sb.append( currentChar );
			}
		}

		String nullString = sb.toString();
		if(nullString.equalsIgnoreCase( "null" ))
		{
			return null;
		}
		else
		{
			throw new ParseException( String.format( "Could not parse JSON null value '%s'", nullString), 0  );
		}
	}
	
	private Boolean parseBoolean() throws IOException, ParseException
	{
		StringBuilder sb = new StringBuilder();
		sb.append( currentChar );
		
		int i;
		while( (i = reader.read()) != -1)
		{
			currentChar = (char) i;
			
			if( currentChar == ',' || currentChar == ']' || currentChar == '}' || Character.isWhitespace( currentChar ) )
			{
				break;
			}
			else
			{
				sb.append( currentChar );
			}
		}
		
		String boolString = sb.toString();
		if(boolString.equalsIgnoreCase( "true" ))
		{
			return new Boolean(true);
		}
		else if(boolString.equalsIgnoreCase( "false" ))
		{
			return new Boolean(false);
		}
		else
		{
			throw new ParseException( String.format( "Could not parse JSON boolean value '%s'", boolString), 0 );
		}
	}
	
	private Number parseNumber() throws IOException, ParseException
	{
		StringBuilder sb = new StringBuilder();
		sb.append( currentChar );
		
		int i;
		while( (i = reader.read()) != -1)
		{
			currentChar = (char) i;
			if( currentChar == ',' || currentChar == ']' || currentChar == '}' || Character.isWhitespace( currentChar ) )
			{
				break;
			}
			else
			{
				sb.append( currentChar );
			}
		}
		Number number = numberFormatter.parse( sb.toString() );
		
		// by default natural numbers are always parsed as Long even if it can be an Integer
		if(number instanceof Long && number.longValue() < Integer.MAX_VALUE)
		{
			number = number.intValue();
		}
		return number;
	}

	private IJsonArray parseArray() throws ParseException, IOException
	{
		IJsonArray array = new RobJsonArray();
		
		int i;
		Object element;
		
		loop: while((i = reader.read()) != -1)
		{
			currentChar = (char) i;
			
			// ignore spaces
			if(Character.isSpaceChar( currentChar ))
			{
				continue;
			}
			
			// retrieve a value
			switch(currentChar)
			{
				// colon
				case ']' :
					break loop;
					
				case ',':
					continue loop;
					
				// null value
				case 'n' :
					element = parseNull();
					break;
					
				// object value
				case '{' :
					element = parseObject();
					break;
					
				// array value
				case '[' :
					element = parseArray();
					break;
					
				// string value
				case '"' :
					element = parseString();
					break ;
					
				// boolean value
				case 't' :
				case 'f' :
					element = parseBoolean();
					break ;
					
				// number value
				default:
					element = parseNumber();
			}
			
			// add key-value pair to object
			array.add( element );
			
			// check if the char that marked the end of the parsed value actually closes this array
			if(currentChar == ']' && !(element instanceof IJsonArray))
			{
				break loop;
			}
		}

		return array;
	}
}
