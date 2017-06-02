package de.gerdiproject.json;


import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;


public interface IJsonReader
{
	/**
	 * Initializes the JSON reader using another reader as input.
	 * 
	 * @param reader
	 *            a reader that provides a JSON string
	 */
	public void init( Reader reader );


	/**
	 * Closes the reader.
	 * 
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void close() throws IOException;


	/**
	 * Reads a JSON structure from the reader.
	 * 
	 * @return a JSON structure
	 * @throws IllegalStateException
	 *             this exception is thrown if no reader was set up via init()
	 *             before this method was called
	 * @throws IOException
	 *             this exception can be caused by the reader
	 * @throws ParseException
	 *             this exception is thrown if parsing the JSON string failed
	 */
	public IJson read() throws IllegalStateException, IOException, ParseException;


	/**
	 * Reads a JSON array from the reader.
	 * 
	 * @return a JSON object
	 * @throws IllegalStateException
	 *             this exception is thrown if no reader was set up via init()
	 *             before this method was called
	 * @throws ClassCastException
	 *             this exception is thrown if the parsed object is not a JSON
	 *             array
	 * @throws IOException
	 *             this exception can be caused by the reader
	 * @throws ParseException
	 *             this exception is thrown if parsing the JSON string failed
	 */
	public IJsonArray readArray() throws IllegalStateException, ClassCastException, IOException, ParseException;


	/**
	 * Reads a JSON object from the reader.
	 * 
	 * @return a JSON object
	 * @throws IllegalStateException
	 *             this exception is thrown if no reader was set up via init()
	 *             before this method was called
	 * @throws ClassCastException
	 *             this exception is thrown if the parsed object is not a JSON
	 *             object
	 * @throws IOException
	 *             this exception can be caused by the reader
	 * @throws ParseException
	 *             this exception is thrown if parsing the JSON string failed
	 */
	public IJsonObject readObject() throws IllegalStateException, ClassCastException, IOException, ParseException;
}
