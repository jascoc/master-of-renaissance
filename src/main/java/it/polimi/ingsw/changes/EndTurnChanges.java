package it.polimi.ingsw.changes;

import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of round
 */
public class EndTurnChanges extends Changes {

    private String currentActivePlayerName;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param currentActivePlayerName name of current player
     */
    public EndTurnChanges(int playerRoundNumber, String currentActivePlayerName) {
        super(playerRoundNumber, END_TURN);
        this.currentActivePlayerName = currentActivePlayerName;
    }

    /**
     * Getters and Settes
     */
    public String getCurrentPlayerName() { return currentActivePlayerName; }

    public void setCurrentPlayerName(String currentPlayerName) { this.currentActivePlayerName = currentPlayerName; }

}
