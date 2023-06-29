package it.polimi.ingsw.changes;

/**
 * this Class is used to notify the view of the evolution of the model
 */
public class Changes {

    private int playerRoundNumber;
    private String changeCode;

    /**
     * constructor
     * @param playerRoundNumber the position of the player in the round
     * @param changeCode the type of the change
     */
    public Changes(int playerRoundNumber, String changeCode) {
        this.playerRoundNumber = playerRoundNumber;
        this.changeCode = changeCode;
    }

    /**
     * Getters and Setters
     */
    public int getPlayerRoundNumber() { return playerRoundNumber; }

    public void setPlayerRoundNumber(int playerRoundNumber) { this.playerRoundNumber = playerRoundNumber; }

    public String getChangeCode() { return changeCode; }

    public void setChangeCode(String changeCode) { this.changeCode = changeCode; }
}
