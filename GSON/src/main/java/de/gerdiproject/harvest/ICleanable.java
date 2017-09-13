package de.gerdiproject.harvest;

/**
 * This interface represents objects that can be cleaned up to make them compatible
 * with the ElasticSearch index.
 * @author Robin Weiss
 *
 */
public interface ICleanable
{
    /**
     * Cleans the object, making it compatible with the ElasticSearch index.
     */
    void clean();
}
