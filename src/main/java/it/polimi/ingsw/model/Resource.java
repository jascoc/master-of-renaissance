package it.polimi.ingsw.model;

/**
 * This abstract Class handles the Resource
 */
public abstract class Resource {

    private String resource;

    /**
     * Constructor
     * @param resource resource
     */
    public Resource(String resource) { this.resource = resource; }

    /**
     * Empty constructor
     */
    public Resource() { }

    /**
     * This method will be Override by Resource sub-class's methods
     */
    public abstract Resource getResource();
}
