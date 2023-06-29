package it.polimi.ingsw.model.exceptions;


import it.polimi.ingsw.model.Resource;

/**
 * invoked during the searching of a specific resource if it's not found
 */
public class ResourceNotFoundException extends Exception{

    /**
     * Constructor
     * @param resource resource not found
     */
    public ResourceNotFoundException(Resource resource){ System.out.println(resource + "not found!"); }
}
