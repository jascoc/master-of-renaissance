package it.polimi.ingsw.model.exceptions;

/**
 * TooManyPlayersException is thrown when we exceed 4
 */
public class TooManyPlayersException extends Exception{

    /**
     * Constructor
     * @param playerName player nickname
     */
    public TooManyPlayersException(String playerName) {
        System.out.println("This game room is already full, " + playerName + " can't play here");
    }

    /**
     * Empty constructor
     */
    public TooManyPlayersException() { }
}
