package it.polimi.ingsw.model.tokens;

import it.polimi.ingsw.changes.DevelopmentCardStructureChanges;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.network.server.Controller;

import static it.polimi.ingsw.Variables.*;

/**
 * This class represent purple action token
 */
public class PurpleActionToken extends ActionToken{
    private final String colour = PURPLE;

    public String getColour() { return colour; }

    /**
     * removes 2 purple development card in the market
     * @param gameModel
     */
    public void activeToken (GameModel gameModel) {
        /**
         * remove two times starting from lvl 1 dev card column, row for lvl column for colour
         */
        for (int j = 0; j < 2; j++) {
            if (gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0, 1).size() > 0) {
                DevelopmentCardStructureChanges changes = new DevelopmentCardStructureChanges(gameModel.getActivePlayer().getRoundPosition(), 0, 1, gameModel.getDevelopmentCardMarket().getCardFromStructure(0,1));
                for (Controller controller : gameModel.getControllers()) { controller.notify(changes); }
                gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0, 1).remove(0);
            } else if (gameModel.getDevelopmentCardMarket().getDevelopmentCardList(1, 1).size() > 0) {
                DevelopmentCardStructureChanges changes = new DevelopmentCardStructureChanges(gameModel.getActivePlayer().getRoundPosition(), 1, 1, gameModel.getDevelopmentCardMarket().getCardFromStructure(1,1));
                for (Controller controller : gameModel.getControllers()) { controller.notify(changes); }
                gameModel.getDevelopmentCardMarket().getDevelopmentCardList(1, 1).remove(0);
            } else if (gameModel.getDevelopmentCardMarket().getDevelopmentCardList(2, 1).size() > 0) {
                DevelopmentCardStructureChanges changes = new DevelopmentCardStructureChanges(gameModel.getActivePlayer().getRoundPosition(), 2, 1, gameModel.getDevelopmentCardMarket().getCardFromStructure(2,1));
                for (Controller controller : gameModel.getControllers()) { controller.notify(changes); }
                gameModel.getDevelopmentCardMarket().getDevelopmentCardList(2, 1).remove(0);
            }
        }
        gameModel.getSinglePlayer().getActionTokens().remove(gameModel.getSinglePlayer().getActionTokens().size()-1);
    }
    @Override
    public String toString() { return PURPLE_ACTION_TOKEN; }
}
