package it.polimi.ingsw.model.resources;

import it.polimi.ingsw.model.Resource;

/**
 * This class is a Resource's sub-class used to create a stone
 */
public class Stone extends Resource {

    /**
     * @return a stone
     */
    public Stone getResource(){
        return new Stone();
    }

    @Override
    public String toString() {
        return "Stone";
    }
}
