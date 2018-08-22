package de.gerdiproject.json.datacite.metadata_bowl.soep;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class models the SOEP-specific metadata: Variables
 * In it we depict the initial set of attributes identified so far; new use cases might dictate an extension/change.
 * @author Fidan Limani
 **/
@AllArgsConstructor
@Data
public class Variable
{
    private String variableName;

    /**
     * The study source for the variable
     */
    private String source;

    /**
     * The concept that describes the variable
     */
    private Concept concept;
}