package it.polimi.ingsw.changes;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the beginning of the last round
 */
public class LastRoundsChanges extends Changes {

    private String player;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param player player name
     */
    public LastRoundsChanges(int playerRoundNumber, String player) {
        super(playerRoundNumber, FINAL_ROUNDS);
        this.player = player;
    }

    /**
     * Getter and Setter
     */
    public String getPlayer() { return player; }

    public void setPlayer(String player) { this.player = player; }

}
