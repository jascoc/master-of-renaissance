package it.polimi.ingsw.changes;

import java.util.ArrayList;
import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of faith track
 */
public class FaithTrackChanges extends Changes {

    private ArrayList<Integer> playerPositionFaithTrack;

    /**
     * Constructor
     * @param playerRoundNumber  position in the round
     * @param playerPositionFaithTrack faith track position
     */
    public FaithTrackChanges(int playerRoundNumber, ArrayList<Integer> playerPositionFaithTrack) {
        super(playerRoundNumber, FAITH_TRACK_CHANGES);
        this.playerPositionFaithTrack = playerPositionFaithTrack;
    }

    /**
     * Getter
     */
    public ArrayList<Integer> getPlayerPositionFaithTrack() { return playerPositionFaithTrack; }

}
