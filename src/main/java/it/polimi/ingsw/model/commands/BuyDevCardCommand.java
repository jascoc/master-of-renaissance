package it.polimi.ingsw.model.commands;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.exceptions.CantBuyThisCardException;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.network.messages.BuyDevCardMessage;

/**
 * This class implements the command to notify the buying of a development card
 */
public class BuyDevCardCommand extends Command {

    /**
     * Used to buy a development card in the model when the actual message arrives
     * @param message message from client
     * @param gameModel game model
     * @throws NotEnoughResourcesException if there is not enough resources to active the production
     * @throws CantBuyThisCardException if there is not enough space in your slots
     */
    public void executeCommand(BuyDevCardMessage message, GameModel gameModel) throws NotEnoughResourcesException, CantBuyThisCardException {
        gameModel.getDevelopmentCardMarket().buyDevelopmentCard(gameModel, message.getLevel(), message.getColour(), message.getPositionDevCardSlot());
    }
}
