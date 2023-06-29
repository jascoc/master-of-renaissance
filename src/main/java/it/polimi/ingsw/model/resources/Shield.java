package it.polimi.ingsw.model.resources;

import it.polimi.ingsw.model.Resource;

/**
 * This class is a Resource's sub-class used to create a shield
 */
public class Shield extends Resource {

    /**
     * @return a shield
     */
    public Shield getResource(){
        return new Shield();
    }

    @Override
    public String toString() {
        return "Shield";
    }
}
