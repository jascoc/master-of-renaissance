package it.polimi.ingsw.model.exceptions;

import it.polimi.ingsw.model.Resource;
import java.util.ArrayList;

/**
 * NotEnoughResourcesException is thrown when someone tries to buy a devCard or to start a production without the needed Resources.
 */
public class NotEnoughResourcesException extends Exception{

    private ArrayList<Resource> missingResource;
    private String errorMessage = "";

    /**
     * This constructor just tells what is the Exception thrown.
     */
    public NotEnoughResourcesException() { }

    /**
     * This constructor gets the error message to show in the client.
     */
    public NotEnoughResourcesException(String errorMessage) { this.errorMessage = errorMessage; }

    /**
     * This constructor tells what Resources are missing. (or needed if it's too difficult).
     * @param missingResources, ArrayList of missing Resources.
     */
    public NotEnoughResourcesException(ArrayList<Resource> missingResources) {
        this.missingResource = missingResources;
        //System.out.println("You don't have enough Resources: ");
        //missingResources.forEach((res) -> System.out.print(res + ", "));
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
