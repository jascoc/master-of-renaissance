package it.polimi.ingsw.changes;

import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of the waiting room
 */
public class PlayerWaitingChanges extends Changes {

    private String name;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param name player name
     */
    public PlayerWaitingChanges(int playerRoundNumber, String name) {
        super(playerRoundNumber, PLAYER_WAITING);
        this.name = name;
    }

    /**
     * Getter and Setter
     */
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
