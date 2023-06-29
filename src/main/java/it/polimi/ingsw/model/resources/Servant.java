package it.polimi.ingsw.model.resources;

import it.polimi.ingsw.model.Resource;

/**
 * This class is a Resource's sub-class used to create a servant
 */
public class Servant extends Resource {

    /**
     * @return a servant
     */
    public Servant getResource(){
        return new Servant();
    }

    @Override
    public String toString() {
        return "Servant";
    }
}
