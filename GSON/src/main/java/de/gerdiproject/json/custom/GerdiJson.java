package de.gerdiproject.json.custom;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.gson.JsonObject;
import de.gerdiproject.harvest.ICleanable;
import de.gerdiproject.harvest.IDocument;
import de.gerdiproject.harvest.utils.StringCleaner;
import de.gerdiproject.json.GsonUtils;
import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.geo.GeoJson;
import de.gerdiproject.json.impl.GsonObject;

/**
 * This JSON object represents a document that complies to our first schema draft.
 * @author Robin Weiss
 *
 */
@Deprecated
public class GerdiJson implements ICleanable, IDocument
{
    private static final Base64.Encoder encoder = Base64.getEncoder();

    private String label;
    private String viewUrl;

    private String lastUpdated;
    private IJsonArray downloadUrl;
    private String logoUrl;
    private IJsonArray description;
    private IJsonArray geo;
    private IJsonArray year;
    private IJsonArray tag;

    /**
     * Simple constructor that requires only mandatory fields.
     * @param label a title describing the document
     * @param viewUrl a URL that links to the webpage that provides the indexed data
     */
    public GerdiJson(String label, String viewUrl)
    {
        this.label = label;
        this.viewUrl = viewUrl;
    }

    public String getLastUpdated()
    {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public IJsonArray getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setDownloadUrl(IJsonArray downloadUrl)
    {
        this.downloadUrl = downloadUrl;
    }

    public String getLogoUrl()
    {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl)
    {
        this.logoUrl = logoUrl;
    }

    public IJsonArray getDescription()
    {
        return description;
    }

    public void setDescription(IJsonArray description)
    {
        this.description = description;
    }

    public IJsonArray getGeo()
    {
        return geo;
    }

    public void setGeo(IJsonArray geo)
    {
        this.geo = geo;
    }

    public IJsonArray getYear()
    {
        return year;
    }

    public void setYear(IJsonArray year)
    {
        this.year = year;
    }

    public IJsonArray getTag()
    {
        return tag;
    }

    public void setTag(IJsonArray tag)
    {
        this.tag = tag;
    }

    public String getLabel()
    {
        return label;
    }

    public String getViewUrl()
    {
        return viewUrl;
    }

    @Override
    public void clean()
    {
        label = StringCleaner.clean(label);
        viewUrl = StringCleaner.clean(viewUrl);
        logoUrl = StringCleaner.clean(logoUrl);

        // clean descriptions
        if (description != null) {
            for (int i = 0, len = description.size(); i < len; i++)
                description.put(i, StringCleaner.clean(description.getString(i)));
        }

        // correct possibly erroneous polygons
        if (geo != null) {
            for (int i = 0, len = geo.size(); i < len; i++) {
                GeoJson geoObj = GsonUtils.jsonStringToObject(geo.getJsonObject(i).toJsonString(), GeoJson.class);
                geoObj.clean();

                JsonObject cleanedGeo = (JsonObject) GsonUtils.objectToJson(geoObj);

                geo.put(i, new GsonObject(cleanedGeo));
            }
        }
    }

    @Override
    public String getElasticSearchId()
    {
        // base64 encoding:
        String base64EncodedString = new String(encoder.encode(viewUrl.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        return base64EncodedString;
    }
}
