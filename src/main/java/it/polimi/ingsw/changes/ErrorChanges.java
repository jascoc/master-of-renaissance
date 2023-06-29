package it.polimi.ingsw.changes;

import static it.polimi.ingsw.Variables.ERROR;

/**
 * this Class is used to notify the view if there is any error
 */
public class ErrorChanges extends Changes {

    private String errorMessage;
    private String errorCode;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param errorMessage error message
     * @param errorCode error code
     */
    public ErrorChanges(int playerRoundNumber, String errorMessage, String errorCode) {
        super(playerRoundNumber, ERROR);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    /**
     * Getters and Setters
     */
    public String getErrorMessage() { return errorMessage; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getErrorCode() { return errorCode; }

    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

}
