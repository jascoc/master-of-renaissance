package it.polimi.ingsw.model.tokens;

import it.polimi.ingsw.changes.DevelopmentCardStructureChanges;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.network.server.Controller;

import static it.polimi.ingsw.Variables.*;

/**
 * This class represent green action token
 */
public class GreenActionToken extends ActionToken {
    private final String colour = GREEN;

    public String getColour() { return colour; }

    /**
     * removes 2 green development card in the market
     * @param gameModel
     */
    public void activeToken (GameModel gameModel) {
        /**
         * remove two times starting from lvl 1 dev card column, row for lvl column for colour
         */
        for (int j = 0; j < 2; j++) {
            if (gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0, 0).size() > 0) {
                DevelopmentCardStructureChanges changes = new DevelopmentCardStructureChanges(gameModel.getActivePlayer().getRoundPosition(), 0, 0, gameModel.getDevelopmentCardMarket().getCardFromStructure(0,0));
                for (Controller controller : gameModel.getControllers()) { controller.notify(changes); }
                gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0, 0).remove(0);
            } else if (gameModel.getDevelopmentCardMarket().getDevelopmentCardList(1, 0).size() > 0) {
                DevelopmentCardStructureChanges changes = new DevelopmentCardStructureChanges(gameModel.getActivePlayer().getRoundPosition(), 1, 0, gameModel.getDevelopmentCardMarket().getCardFromStructure(1,0));
                for (Controller controller : gameModel.getControllers()) { controller.notify(changes); }
                gameModel.getDevelopmentCardMarket().getDevelopmentCardList(1, 0).remove(0);
            } else if (gameModel.getDevelopmentCardMarket().getDevelopmentCardList(2, 0).size() > 0) {
                DevelopmentCardStructureChanges changes = new DevelopmentCardStructureChanges(gameModel.getActivePlayer().getRoundPosition(), 2, 0, gameModel.getDevelopmentCardMarket().getCardFromStructure(2,0));
                for (Controller controller : gameModel.getControllers()) { controller.notify(changes); }
                gameModel.getDevelopmentCardMarket().getDevelopmentCardList(2, 0).remove(0);
            }
        }
        gameModel.getSinglePlayer().getActionTokens().remove(gameModel.getSinglePlayer().getActionTokens().size()-1);
    }
    @Override
    public String toString() { return GREEN_ACTION_TOKEN; }
}

