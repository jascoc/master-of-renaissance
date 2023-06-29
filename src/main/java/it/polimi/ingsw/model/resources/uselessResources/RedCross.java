package it.polimi.ingsw.model.resources.uselessResources;

import it.polimi.ingsw.model.Resource;

/**
 * This class represents a Red Cross
 */
public class RedCross extends Resource {

    /**
     * @return a red
     */
    public RedCross getResource() {
        return new RedCross();
    }

    @Override
    public String toString() {
        return "RedCross";
    }
}
