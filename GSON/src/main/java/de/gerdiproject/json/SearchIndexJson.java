package de.gerdiproject.json;

import java.util.Collection;

/**
 * This JSON object provides a harvested search index, as well as some harvesting metadata.
 * @author Robin Weiss
 *
 */
public class SearchIndexJson
{
    private Collection<?> data;
    private long harvestDate;
    private long durationInSeconds;
    private boolean wasHarvestedFromDisk;
    private String hash;

    /**
     * Simple constructor that requires only the harvested documents.
     * @param harvestedDocuments all documents that were harvested
     */
    public SearchIndexJson(Collection<?> harvestedDocuments)
    {
        this.data = harvestedDocuments;
    }


    public Collection<?> getData()
    {
        return data;
    }


    public void setData(Collection<?> data)
    {
        this.data = data;
    }


    public long getHarvestDate()
    {
        return harvestDate;
    }


    public void setHarvestDate(long harvestDate)
    {
        this.harvestDate = harvestDate;
    }


    public long getDurationInSeconds()
    {
        return durationInSeconds;
    }


    public void setDurationInSeconds(long durationInSeconds)
    {
        this.durationInSeconds = durationInSeconds;
    }


    public boolean isWasHarvestedFromDisk()
    {
        return wasHarvestedFromDisk;
    }


    public void setWasHarvestedFromDisk(boolean wasHarvestedFromDisk)
    {
        this.wasHarvestedFromDisk = wasHarvestedFromDisk;
    }


    public String getHash()
    {
        return hash;
    }


    public void setHash(String hash)
    {
        this.hash = hash;
    }
}
