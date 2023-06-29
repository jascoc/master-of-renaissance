package it.polimi.ingsw.model.exceptions;

/**
 * Error message exception
 */
public class ErrorInMessageException extends Exception {

    private String error;

    /**
     * invoked when the input from the client is wrong
     * @param error
     */
    public ErrorInMessageException(String error) { this.error = error; }

    public String getError() { return error; }
}
