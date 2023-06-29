package it.polimi.ingsw.changes;

import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view in case there is 2 same nickname
 */
public class NickNameTakenChanges extends Changes {

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     */
    public NickNameTakenChanges(int playerRoundNumber) { super(playerRoundNumber, NICKNAME_TAKEN); }
}
