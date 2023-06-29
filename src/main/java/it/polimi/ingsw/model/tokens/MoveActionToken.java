package it.polimi.ingsw.model.tokens;

import it.polimi.ingsw.model.GameModel;

import static it.polimi.ingsw.Variables.MOVE_ACTION_TOKEN;

/**
 * This class represent move action token
 */
public class MoveActionToken extends ActionToken{

    /**
     * move the black cross ( Single Player Faith track of 2)
     */
    public void activeToken(GameModel gameModel){
        gameModel.moveFaithTrack(2, gameModel.getSinglePlayer());
        gameModel.getSinglePlayer().getActionTokens().remove(gameModel.getSinglePlayer().getActionTokens().size()-1);
    }
    @Override
    public String toString() {
        return MOVE_ACTION_TOKEN;
    }
}
