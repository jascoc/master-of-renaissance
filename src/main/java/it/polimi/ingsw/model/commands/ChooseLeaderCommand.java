package it.polimi.ingsw.model.commands;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.exceptions.ErrorInMessageException;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.network.messages.ChooseLeaderMessage;

import static it.polimi.ingsw.Variables.*;

/**
 * This class implements the command to notify the modification of leader card list
 */
public class ChooseLeaderCommand extends Command {

    /**
     * Used to discard or active a leader card when the actual message arrives
     * @param message message from the client
     * @param gameModel game model
     * @throws NotEnoughResourcesException if there is not enough resources to active the production
     * @throws ErrorInMessageException if there is an error message
     */
    public void executeCommand(ChooseLeaderMessage message, GameModel gameModel) throws NotEnoughResourcesException, ErrorInMessageException {
        LeaderCard card = gameModel.getActivePlayer().getLeaderCardList().get(message.getPositionLeaderCard());
        switch (message.getLeaderCardAction()) {
            case ACTIVATE:
                gameModel.getRound().activateLeaderCard(gameModel, card);
                break;
            case DISCARD:
                gameModel.getActivePlayer().discardLeaderCard(gameModel, card);
                gameModel.moveFaithTrack(1, gameModel.getActivePlayer());
                break;
            default:
                throw new ErrorInMessageException(LEADER_CARD_ERROR);
        }
    }
}