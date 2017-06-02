package de.gerdiproject.json;


public interface IJson
{
	/**
	 * Represents the object as a JSON-conform string.
	 * 
	 * @return a JSON string
	 */
	public String toJsonString();


	/**
	 * Checks if this object is empty.
	 * 
	 * @return true, if this object is empty.
	 */
	public boolean isEmpty();
}
