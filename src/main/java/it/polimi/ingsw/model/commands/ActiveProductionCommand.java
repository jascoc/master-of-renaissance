package it.polimi.ingsw.model.commands;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.leaderCards.ExtraProductionLeader;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;
import it.polimi.ingsw.network.Parser;
import it.polimi.ingsw.network.messages.ActiveProductionMessage;
import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;

/**
 * This class implements the command to notify the activation of a production
 */
public class ActiveProductionCommand extends Command {

    /**
     * Used to active a production in the model when the actual message arrives
     * @param message message from client
     * @param gameModel game model
     * @throws NotEnoughResourcesException if there is not enough resources to active the production
     */
    public void executeCommand(ActiveProductionMessage message, GameModel gameModel) throws NotEnoughResourcesException {
        ArrayList<String> chosenProduction = message.getChosenProduction();
        String wantedResourceBase = message.getWantedResourceBase();
        String wantedResourceL1 = message.getWantedResourceL1();
        String wantedResourceL2 = message.getWantedResourceL2();
        ArrayList<String> usedResource = message.getUsedResource();

        chosenProduction.set(0, chosenProduction.get(0).toLowerCase());
        chosenProduction.set(1, chosenProduction.get(1).toLowerCase());
        chosenProduction.set(2, chosenProduction.get(2).toLowerCase());
        chosenProduction.set(3, chosenProduction.get(3).toLowerCase());
        chosenProduction.set(4, chosenProduction.get(4).toLowerCase());

        if(chosenProduction.get(0).equals(TRUE)) {
            ArrayList<Resource> baseProduction = new ArrayList<>(3);
            baseProduction.add(convert(usedResource.get(0)));
            baseProduction.add(convert(usedResource.get(1)));
            baseProduction.add(convert(wantedResourceBase));
            gameModel.getRound().chooseBaseProduction(gameModel.getActivePlayer(), baseProduction, gameModel);
        }
        if(chosenProduction.get(1).equals(TRUE)) {
            ArrayList<DevelopmentCard> production1 = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[0];
            gameModel.getRound().chooseProductionColumn(gameModel,production1.get(production1.size()-1).getProductionPriceResource(), 0);
        }
        if(chosenProduction.get(2).equals(TRUE)) {
            ArrayList<DevelopmentCard> production2 = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[1];
            gameModel.getRound().chooseProductionColumn(gameModel,production2.get(production2.size()-1).getProductionPriceResource(), 1);
        }
        if(chosenProduction.get(3).equals(TRUE)) {
            ArrayList<DevelopmentCard> production3 = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[2];
            gameModel.getRound().chooseProductionColumn(gameModel,production3.get(production3.size()-1).getProductionPriceResource(), 2);
        }
        if(chosenProduction.get(4).equals(TRUE)) {
            if(gameModel.getActivePlayer().getLeaderCardList().get(0) != null) {
                ExtraProductionLeader leader1;
                if(gameModel.getActivePlayer().getLeaderCardList().get(0).getActivated() && gameModel.getActivePlayer().getLeaderCardList().get(0).getAbility().split("\\s")[0].equals("Add")) {
                    leader1 = (ExtraProductionLeader) gameModel.getActivePlayer().getLeaderCardList().get(0);
                    try { leader1.activeProduction(gameModel,convert(wantedResourceL1)); }
                    catch (NotEnoughResourcesException ignored){ }
                }
            }
        }
        if (chosenProduction.get(5).equals(TRUE)) {
            if(gameModel.getActivePlayer().getLeaderCardList().get(1) != null) {
                ExtraProductionLeader leader2;
                if(gameModel.getActivePlayer().getLeaderCardList().get(1).getActivated() && gameModel.getActivePlayer().getLeaderCardList().get(1).getAbility().split("\\s")[0].equals("Add")) {
                    leader2 = (ExtraProductionLeader) gameModel.getActivePlayer().getLeaderCardList().get(1);
                    try { leader2.activeProduction(gameModel,convert(wantedResourceL2)); }
                    catch (NotEnoughResourcesException ignored) { }
                }
            }
        }
        if (chosenProduction.get(0).equals(TRUE)) {
            gameModel.getRound().addProductionResourceBaseProduction(gameModel);
        }
        if (chosenProduction.get(1).equals(TRUE)) {
            gameModel.getRound().addProductionResourcesColumn1(gameModel);
        }
        if (chosenProduction.get(2).equals(TRUE)) {
            gameModel.getRound().addProductionResourcesColumn2(gameModel);
        }
        if (chosenProduction.get(3).equals(TRUE)) {
            gameModel.getRound().addProductionResourcesColumn3(gameModel);
        }
        if (chosenProduction.get(4).equals(TRUE)) {
            gameModel.getRound().addProductionResourceLeaderCard1(gameModel);
        }
        if (chosenProduction.get(5).equals(TRUE)) {
            gameModel.getRound().addProductionResourceLeaderCard2(gameModel);
        }
    }

    /**
     * Converts
     * @param resource String
     * @return to Resource Class object
     */
    private Resource convert(String resource){
        switch (resource) {
            case STONE:
                return new Stone();
            case SHIELD:
                return new Shield();
            case SERVANT:
                return new Servant();
            case COIN:
                return new Coin();
            default:
                return null; //remember default that returns null are shit, cause the cause problems and you can't even find them easily.
        }
    }
}
