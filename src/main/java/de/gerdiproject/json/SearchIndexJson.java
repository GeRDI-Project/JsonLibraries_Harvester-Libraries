/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gerdiproject.json;

import java.util.Collection;

/**
 * This JSON object provides a harvested search index, as well as the harvesting duration, timestamp
 * and checksum of the harvested documents.
 *
 * @author Robin Weiss
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
