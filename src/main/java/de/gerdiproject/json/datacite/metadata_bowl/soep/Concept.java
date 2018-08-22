package de.gerdiproject.json.datacite.metadata_bowl.soep;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class models the SOEP-specific metadata: Concepts
 *
 * @author Fidan Limani
 **/
@AllArgsConstructor
@Data
public class Concept
{
    private String name;

    /**
     * Concept name in German
     */
    private String labelDE;

    /**
     * Concept name in English
     */
    private String label;
}
