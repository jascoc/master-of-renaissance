package it.polimi.ingsw.model.exceptions;

/**
 * NotEnoughSpaceException is thrown when there is not any free space in the personal board to put the development card
 */
public class NotEnoughSpaceException extends Exception{

    /**
     * invoked when there is not enough space in the warehouse
     */
    public NotEnoughSpaceException() {
        System.out.println("You don't have any more space to store your Resources!");
    }
}
