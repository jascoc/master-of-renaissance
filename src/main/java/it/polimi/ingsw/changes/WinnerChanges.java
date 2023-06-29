package it.polimi.ingsw.changes;

import java.util.HashMap;
import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the end of the match
 */
public class WinnerChanges extends Changes {

    private HashMap<String, Integer> playerPointsHashMap;
    private String winner;

    /**
     * Constructor
     * @param playerRoundNumber player round position
     * @param changeCode change code
     * @param playerPointsHashMap position in the hash map
     * @param winner
     */
    public WinnerChanges(int playerRoundNumber, String changeCode, HashMap<String, Integer> playerPointsHashMap, String winner) {
        super(playerRoundNumber, changeCode);
        this.playerPointsHashMap = playerPointsHashMap;
        this.winner = winner;
    }

    /**
     * Getter and Setter
     */
    public HashMap<String, Integer> getPlayerPointsHashMap() { return playerPointsHashMap; }

    public void setPlayerPointsHashMap(HashMap<String, Integer> playerPointsHashMap) { this.playerPointsHashMap = playerPointsHashMap; }

    public String getWinner() { return winner; }

    public void setWinner(String winner) { this.winner = winner; }

}
