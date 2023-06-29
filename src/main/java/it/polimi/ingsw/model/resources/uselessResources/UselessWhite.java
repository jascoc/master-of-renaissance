package it.polimi.ingsw.model.resources.uselessResources;

import it.polimi.ingsw.model.Resource;

/**
 * This class is a useless resource
 */
public class UselessWhite extends Resource {

    /**
     * @return a white
     */
    public UselessWhite getResource() { return new UselessWhite(); }

    @Override
    public String toString() {
        return "White";
    }
}