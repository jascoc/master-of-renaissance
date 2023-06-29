package it.polimi.ingsw.model;

import it.polimi.ingsw.model.tokens.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents Single Player
 */
public class SinglePlayer extends Player{

    private ArrayList<ActionToken> actionTokens;
    private ActionToken blueActionToken;
    private ActionToken yellowActionToken;
    private ActionToken purpleActionToken;
    private ActionToken greenActionToken;
    private ActionToken moveActionToken1;
    private ActionToken moveActionToken2;
    private ActionToken moveAndShuffleActionToken;

    /**
     * Constructor and initialization of the token list
     */
    public SinglePlayer() {
        actionTokens = new ArrayList<>();
        blueActionToken = new BlueActionToken();
        yellowActionToken = new YellowActionToken();
        purpleActionToken = new PurpleActionToken();
        greenActionToken = new GreenActionToken();
        moveActionToken1 = new MoveActionToken();
        moveActionToken2 = new MoveActionToken();
        moveAndShuffleActionToken = new MoveAndShuffleActionToken();

        actionTokens.add(blueActionToken);
        actionTokens.add(yellowActionToken);
        actionTokens.add(purpleActionToken);
        actionTokens.add(greenActionToken);
        actionTokens.add(moveActionToken1);
        actionTokens.add(moveActionToken2);
        actionTokens.add(moveAndShuffleActionToken);
        Collections.shuffle(actionTokens);
    }

    /**
     * setter
     * @param actionTokens
     */
    public void setActionTokens(ArrayList<ActionToken> actionTokens) { this.actionTokens = actionTokens; }

    /**
     * getter
     * @return
     */
    public List<ActionToken> getActionTokens() { return actionTokens; }

    /**
     * called to active the token on the top of the list
     * @param gameModel
     */
    public void activeActionToken(GameModel gameModel) { actionTokens.get(actionTokens.size()-1).activeToken(gameModel); }

}
