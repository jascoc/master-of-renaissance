package it.polimi.ingsw.model.resources;

import it.polimi.ingsw.model.Resource;

/**
 * This class is a Resource's sub-class used to create a coin
 */
public class Coin extends Resource {

    /**
     * @return a coin
     */
    public Coin getResource() { return new Coin(); }

    @Override
    public String toString() {
        return "Coin";
    }
}
