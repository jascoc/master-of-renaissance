package it.polimi.ingsw.model.commands;

import it.polimi.ingsw.SetTestAdvantages;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.model.resources.*;
import it.polimi.ingsw.network.messages.RoundZeroMessage;

import static it.polimi.ingsw.Variables.*;

/**
 * This class implements the command to notify the beginning of round Zero
 */
public class RoundZeroCommand extends Command {

    /**
     * Used to initialize the game when the game starts, creation of the player, choosing of the bonus
     * resources and discard the leader card
     * @param message message from the client
     * @param gameModel game model
     * @param playerName player nickname
     */
    public synchronized void executeCommand(RoundZeroMessage message, GameModel gameModel, String playerName) {
        Player player = new Player();
        LeaderCard leaderCard = null;
        for(Player p : gameModel.getPlayerList()) { if(p.getName().equals(playerName)) { player = p; break; } }
        for(int i = 0; i < 2; i ++) {
            for(LeaderCard card : player.getLeaderCardList()) {
                if(card.getAbility().equals(message.getLeaderCards().get(0).getAbility()) && card.getResourceUsed().
                        toString().equals(message.getLeaderCards().get(0).getResourceUsed())) {
                    leaderCard = card;
                }
                else if(card.getAbility().equals(message.getLeaderCards().get(1).getAbility()) && card.getResourceUsed().
                        toString().equals(message.getLeaderCards().get(1).getResourceUsed())) {
                    leaderCard = card;
                }
                else if(card.getAbility().equals(message.getLeaderCards().get(0).getAbility()) && card.getResourceUsed().
                        toString().equals(message.getLeaderCards().get(0).getResourceUsed())) {
                    leaderCard = card;
                }
                else if(card.getAbility().equals(message.getLeaderCards().get(1).getAbility()) && card.getResourceUsed().
                        toString().equals(message.getLeaderCards().get(1).getResourceUsed())) {
                    leaderCard = card;
                }
            }
            player.discardLeaderCard(gameModel, leaderCard);
        }

        if(message.getResource().size() == 0) { }
        else if(message.getResource().size() == 1) {
            player.getPersonalBoard().getWarehouse().addResourceTop(gameModel, stringToRes(message.getResource().get(0)));
        }
        else {
            if(message.getResource().get(0).equals(message.getResource().get(1))) {
                player.getPersonalBoard().getWarehouse().addResourceMiddle(gameModel, stringToRes(message.getResource().get(0)));
                player.getPersonalBoard().getWarehouse().addResourceMiddle(gameModel, stringToRes(message.getResource().get(1)));
            }
            else {
                player.getPersonalBoard().getWarehouse().addResourceTop(gameModel, stringToRes(message.getResource().get(0)));
                player.getPersonalBoard().getWarehouse().addResourceMiddle(gameModel, stringToRes(message.getResource().get(1)));
            }
        }

        switch(message.getPlayerRoundPosition()) {
            case 2:
                gameModel.moveFaithTrack(1, player);
                break;
            case 3:
                gameModel.moveFaithTrack(1, player);
                break;
            default:
                break;
        }
    }

    /**
     * Converts
     * @param res from String into
     * @return Resource
     */
    private Resource stringToRes(String res) {
        switch(res) {
            case COIN:
                return new Coin();
            case STONE:
                return new Stone();
            case SHIELD:
                return new Shield();
            case SERVANT:
                return new Servant();
            default:
                return new Coin();
        }
    }
}
