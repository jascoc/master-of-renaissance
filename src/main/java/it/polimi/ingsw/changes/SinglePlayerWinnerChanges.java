package it.polimi.ingsw.changes;

import java.util.HashMap;

/**
 * this Class is used to notify the view in case the single player wins
 */
public class SinglePlayerWinnerChanges extends WinnerChanges {

    private boolean hasSinglePlayerWon;

    /**
     * Constructor
     * @param playerRoundNumber player position in the round
     * @param changeCode change code
     * @param playerPointsHashMap hashmap position
     * @param hasSinglePlayerWon if wins
     */
    public SinglePlayerWinnerChanges(int playerRoundNumber, String changeCode, HashMap<String, Integer> playerPointsHashMap, boolean hasSinglePlayerWon) {
        super(playerRoundNumber, changeCode, playerPointsHashMap, "");
        this.hasSinglePlayerWon = hasSinglePlayerWon;
        this.hasSinglePlayerWon = hasSinglePlayerWon;
    }

    /**
     * Getter and Setter
     */
    public boolean hasSinglePlayerWon() { return hasSinglePlayerWon; }

    public void setHasSinglePlayerWon(boolean hasSinglePlayerWon) { this.hasSinglePlayerWon = hasSinglePlayerWon; }

}
