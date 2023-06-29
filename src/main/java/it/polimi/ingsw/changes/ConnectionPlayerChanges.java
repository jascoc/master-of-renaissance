package it.polimi.ingsw.changes;

import static it.polimi.ingsw.Variables.CONNECTION;

/**
 * this Class is used to notify the view of the player connection
 */
public class ConnectionPlayerChanges extends Changes {

    private String playerName;
    private String whatHappened;

    /**
     * constructor
     * @param playerRoundNumber the position of the player in the round
     * @param playerName name of the player
     * @param whatHappened log message
     */
    public ConnectionPlayerChanges(int playerRoundNumber, String playerName, String whatHappened) {
        super(playerRoundNumber, CONNECTION);
        this.playerName = playerName;
        this.whatHappened = whatHappened;
    }

    /**
     * Getters and Setters
     */
    public String getPlayerName() { return playerName; }

    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public String getWhatHappened() { return whatHappened; }

    public void setWhatHappened(String whatHappened) { this.whatHappened = whatHappened; }

}
