package it.polimi.ingsw.model.tokens;

import it.polimi.ingsw.model.GameModel;

/**
 * This class represents an action token
 */
public abstract class ActionToken {

    /**
     * Method that is override
     * @param model game model
     */
    public abstract void activeToken(GameModel model);
}
