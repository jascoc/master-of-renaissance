package it.polimi.ingsw.model.tokens;

import it.polimi.ingsw.model.GameModel;

import java.util.Collections;

import static it.polimi.ingsw.Variables.MOVE_AND_SHUFFLE_ACTION_TOKEN;

/**
 * This class represent move and shuffle action token
 */
public class MoveAndShuffleActionToken extends ActionToken{

    /**
     *move the single player and shuffle his tokens
     */
    public void activeToken(GameModel gameModel){

        ActionToken blueActionToken = new BlueActionToken();
        ActionToken yellowActionToken = new YellowActionToken();
        ActionToken purpleActionToken = new PurpleActionToken();
        ActionToken greenActionToken = new GreenActionToken();
        ActionToken moveActionToken1 = new MoveActionToken();
        ActionToken moveActionToken2 = new MoveActionToken();
        ActionToken moveAndShuffleActionToken = new MoveAndShuffleActionToken();

        gameModel.getSinglePlayer().getActionTokens().clear();

        gameModel.getSinglePlayer().getActionTokens().add(yellowActionToken);
        gameModel.getSinglePlayer().getActionTokens().add(purpleActionToken);
        gameModel.getSinglePlayer().getActionTokens().add(greenActionToken);
        gameModel.getSinglePlayer().getActionTokens().add(blueActionToken);

        gameModel.getSinglePlayer().getActionTokens().add(moveActionToken1);
        gameModel.getSinglePlayer().getActionTokens().add(moveActionToken2);
        gameModel.getSinglePlayer().getActionTokens().add(moveAndShuffleActionToken);
        Collections.shuffle(gameModel.getSinglePlayer().getActionTokens());

        gameModel.moveFaithTrack(1, gameModel.getSinglePlayer());
    }
    @Override
    public String toString() { return MOVE_AND_SHUFFLE_ACTION_TOKEN; }
}
