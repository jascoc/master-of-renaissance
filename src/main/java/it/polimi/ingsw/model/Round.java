package it.polimi.ingsw.model;

import it.polimi.ingsw.changes.LeaderCardChanges;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.leaderCards.ExtraProductionLeader;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.model.resources.*;
import it.polimi.ingsw.network.server.Controller;
import java.util.*;

import static it.polimi.ingsw.Variables.*;

/**
 * This class represents Round
 */
public class Round {

    private List<Resource> productionFromColumn1;
    private List<Resource> productionFromColumn2;
    private List<Resource> productionFromColumn3;
    private List<Resource> baseProductionResource;
    private List<Resource> productionFromLeaderCard1;
    private List<Resource> productionFromLeaderCard2;

    /**
     * Constructor
     */
    public Round() {
        productionFromColumn1 = new ArrayList<>();
        productionFromColumn2 = new ArrayList<>();
        productionFromColumn3 = new ArrayList<>();
        baseProductionResource = new ArrayList<>();
        productionFromLeaderCard1 = new ArrayList<>();
        productionFromLeaderCard2 = new ArrayList<>();
    }

    /**
     * Called 1 time for each player.
     */
    public void discardLeaderCard(Player player, LeaderCard l1, LeaderCard l2) {
        player.getLeaderCardList().remove(l1);
        player.getLeaderCardList().remove(l2);
    }

    /**
     * Choose the production to activate.
     */
    public List<Resource> chooseProductionColumn(GameModel gameModel, List<Resource> productionPrice, int columnNumber) {
        List<DevelopmentCard> column = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[columnNumber];
        List<Resource> prodColumn1 = new ArrayList<>();
        if(column != null) {
            if(productionPrice.size() == 1) {
                switch (productionPrice.get(0).toString()) {
                    case SERVANT:
                        prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Servant) productionPrice.get(0));
                        break;
                    case COIN:
                        prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Coin) productionPrice.get(0));
                        break;
                    case SHIELD:
                        prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Shield) productionPrice.get(0));
                        break;
                    case STONE:
                        prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Stone) productionPrice.get(0));
                        break;
                }
            }
            else if (productionPrice.size() == 2) {
                if(productionPrice.get(0).toString().equals(STONE)&&productionPrice.get(1).toString().equals(SERVANT)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Stone) productionPrice.get(0),(Servant)productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(COIN)&&productionPrice.get(1).toString().equals(SHIELD)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Coin) productionPrice.get(0),(Shield) productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(STONE)&&productionPrice.get(1).toString().equals(SHIELD)){
                    prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Stone) productionPrice.get(0),(Shield) productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(COIN)&&productionPrice.get(1).toString().equals(SERVANT)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Coin) productionPrice.get(0),(Servant) productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(SHIELD)&&productionPrice.get(1).toString().equals(SHIELD)){
                    prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Shield) productionPrice.get(0),(Shield) productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(COIN)&&productionPrice.get(1).toString().equals(COIN)){
                    prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Coin) productionPrice.get(0),(Coin) productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(SERVANT)&&productionPrice.get(1).toString().equals(SERVANT)){
                    prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Servant) productionPrice.get(0),(Servant) productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(STONE)&&productionPrice.get(1).toString().equals(STONE)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Stone) productionPrice.get(0),(Stone) productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(SHIELD)&&productionPrice.get(1).toString().equals(SERVANT)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Shield) productionPrice.get(0),(Servant) productionPrice.get(1) );
                }
                else if(productionPrice.get(0).toString().equals(COIN)&&productionPrice.get(1).toString().equals(STONE)){
                    prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Coin) productionPrice.get(0),(Stone) productionPrice.get(1) );
                }
                else if(productionPrice.get(1).toString().equals(SHIELD)&&productionPrice.get(0).toString().equals(SERVANT)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Shield) productionPrice.get(1),(Servant) productionPrice.get(0) );
                }
                else if(productionPrice.get(1).toString().equals(COIN)&&productionPrice.get(0).toString().equals(STONE)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Coin) productionPrice.get(1),(Stone) productionPrice.get(0) );
                }
                else if(productionPrice.get(1).toString().equals(STONE)&&productionPrice.get(0).toString().equals(SERVANT)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Stone) productionPrice.get(1),(Servant)productionPrice.get(0) );
                }
                else if(productionPrice.get(1).toString().equals(COIN)&&productionPrice.get(0).toString().equals(SHIELD)){
                    prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Coin) productionPrice.get(1),(Shield) productionPrice.get(0) );
                }
                else if(productionPrice.get(1).toString().equals(STONE)&&productionPrice.get(0).toString().equals(SHIELD)){
                    prodColumn1= column.get(column.size()-1).activeProduction(gameModel,(Stone) productionPrice.get(1),(Shield) productionPrice.get(0) );
                }
                else if(productionPrice.get(1).toString().equals(COIN)&&productionPrice.get(0).toString().equals(SERVANT)){
                    prodColumn1=column.get(column.size()-1).activeProduction(gameModel,(Coin) productionPrice.get(1),(Servant) productionPrice.get(0) );
                }
            }
        }
        productionFromColumn1.clear();
        productionFromColumn1.addAll(prodColumn1);
        return prodColumn1;
    }

    /**
     * activate the base production
     * @param player
     * @param resources
     * @param gameModel
     * @return
     * @throws NotEnoughResourcesException
     */
    public Resource chooseBaseProduction(Player player, List<Resource> resources, GameModel gameModel) throws NotEnoughResourcesException {
        Resource r = player.getPersonalBoard().getBaseDevelopmentCard().activeBaseProduction(player,resources.get(0),resources.get(1),resources.get(2), gameModel);
        baseProductionResource.clear();
        baseProductionResource.add(r);
        return r;
    }

    /**
     * method to call in the end of the round after activated a production to add the resource in the StrongBox
     * @param gameModel
     */
    public void addProductionResourcesColumn1(GameModel gameModel) {
        List<DevelopmentCard> column1 = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[0];
        if(column1 != null) {
            List<Resource> price = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[0].get(gameModel.getActivePlayer().
                    getPersonalBoard().getDevelopmentCardSlots()[0].size()-1).getProductionPriceResource();
            gameModel.getActivePlayer().getPersonalBoard().addToStrongbox(productionFromColumn1);
        }
    }

    /**
     * adds the result of the production in the strongbox, called at the end of all production activation
     * @param gameModel
     */
    public void addProductionResourcesColumn2(GameModel gameModel) {
        List<DevelopmentCard> column2 = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[1];
        if(column2 != null) {
            List<Resource> price = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[1].get(gameModel.getActivePlayer().
                    getPersonalBoard().getDevelopmentCardSlots()[1].size()-1).getProductionPriceResource();
            gameModel.getActivePlayer().getPersonalBoard().addToStrongbox(productionFromColumn2);
        }
    }

    /**
     * adds the result of the production in the strongbox, called at the end of all production activation
     * @param gameModel
     */
    public void addProductionResourcesColumn3(GameModel gameModel) {
        List<DevelopmentCard> column3 = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[2];
        if(column3 != null) {
            List<Resource> price = gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[2].get(gameModel.getActivePlayer().
                    getPersonalBoard().getDevelopmentCardSlots()[2].size()-1).getProductionPriceResource();
            gameModel.getActivePlayer().getPersonalBoard().addToStrongbox(productionFromColumn3);
        }
    }

    /**
     * adds the result of the production in the strongbox, called at the end of all production activation
     * @param gameModel
     */
    public void addProductionResourceBaseProduction(GameModel gameModel) {
        gameModel.getActivePlayer().getPersonalBoard().addToStrongbox(baseProductionResource.get(0));
    }

    /**
     * adds the result of the production in the strongbox, called at the end of all production activation
     * @param gameModel
     */
    public void addProductionResourceLeaderCard1(GameModel gameModel) {
        if(!gameModel.getActivePlayer().getLeaderCardList().isEmpty()) {
            if(gameModel.getActivePlayer().getLeaderCardList().size() >= 1) {
                if(gameModel.getActivePlayer().getLeaderCardList().get(0) instanceof ExtraProductionLeader) {
                    ExtraProductionLeader extra = (ExtraProductionLeader) gameModel.getActivePlayer().getLeaderCardList().get(0);
                    gameModel.getActivePlayer().getPersonalBoard().addToStrongbox(extra.getProducedResource());
                }
            }
        }
    }

    /**
     * adds the result of the production in the strongbox, called at the end of all production activation
     * @param gameModel
     */
    public void addProductionResourceLeaderCard2(GameModel gameModel) {
        if(!gameModel.getActivePlayer().getLeaderCardList().isEmpty()) {
            if(gameModel.getActivePlayer().getLeaderCardList().size() >= 2) {
                if(gameModel.getActivePlayer().getLeaderCardList().get(1) instanceof ExtraProductionLeader) {
                    ExtraProductionLeader extra = (ExtraProductionLeader) gameModel.getActivePlayer().getLeaderCardList().get(1);
                    gameModel.getActivePlayer().getPersonalBoard().addToStrongbox(extra.getProducedResource());
                }
            }
        }
    }

    /**
     * This method is used in activating a LeaderCard. Checks for the resources and, in case, activate the LeaderCard. NOT ITS ABILITY!!
     * @param leaderCard the leaderCard to be activate.

     * throws NotEnoughResourcesException, if the resources/DevCard are not enough throws an exception.
     */
    public void activateLeaderCard(GameModel gameModel, LeaderCard leaderCard) throws NotEnoughResourcesException {
        Player player = gameModel.getActivePlayer();
        int NUM_OF_SLOTS = 3;
        int WANTED_RESOURCE = 5;

        if(player.isActivePlayer()) {
            if(player.getLeaderCardList().get(player.getLeaderCardList().indexOf(leaderCard)).getRequiredDevCards() != null) {
                ArrayList<DevelopmentCard> devCardRequired = player.getLeaderCardList().get(player.getLeaderCardList().indexOf(leaderCard)).getRequiredDevCards();

                /*Check in every slots if there are cards of the same level and colour. If there are the required card is removed.
                At the end, if I have an empty list it means I found every required DevelopmentCard and I can proceed in activate the leaderCard.
                If you look at the code you'll notice that the double for cycle checks also for the DevCard with level of 3, which are not
                used to activate the leaderCard. I don't think is this much of a big difference, it would have been more complex to not check for them too.
                */

                for(int i = 0; i < NUM_OF_SLOTS; i ++) {
                    for(DevelopmentCard card : player.getPersonalBoard().getDevelopmentCardSlots()[i]) {
                        devCardRequired.removeIf(requiredCard -> card.getLvl() == requiredCard.getLvl() && card.getColour().equals(requiredCard.getColour()));
                    }
                }

                if(devCardRequired.size() == 0) { player.getLeaderCardList().get(player.getLeaderCardList().indexOf(leaderCard)).setActivated(); }
                else { throw new NotEnoughResourcesException(); }

            }
            else if(player.getLeaderCardList().get(player.getLeaderCardList().indexOf(leaderCard)).getRequiredResource() != null) {
                Resource resourceRequired = player.getLeaderCardList().get(player.getLeaderCardList().indexOf(leaderCard)).getRequiredResource();
                ArrayList<Integer> positionStrongbox = new ArrayList<>(5);
                int resourceFound = 0;

                /*
                Checks for 5 resources of the needed type in the Warehouse and in the StrongBox.
                First checks in the Warehouse and, if found, puts the amount in resourceFound.
                Then checks for the remaining requiredResource in the StringBox.
                 */

                resourceFound = player.getPersonalBoard().getWarehouse().numberOfResource(resourceRequired);
                for(int i = 0; i < player.getPersonalBoard().getStrongBox().size(); i ++) {
                    if(player.getPersonalBoard().getStrongBox().get(i).toString().equals(resourceRequired.toString())) { resourceFound ++; }
                }

                if( resourceFound >= WANTED_RESOURCE) {
                    player.getLeaderCardList().get(player.getLeaderCardList().indexOf(leaderCard)).setActivated();
                }
                else { throw new NotEnoughResourcesException(); }
            }
        }

        player.addVictoryPoints(player.getLeaderCardList().get(player.getLeaderCardList().indexOf(leaderCard)).getVictoryPoints());

        LeaderCardChanges change = new LeaderCardChanges(gameModel.getActivePlayer().getRoundPosition(),
                player.getLeaderCardList().indexOf(leaderCard), player.getLeaderCardList().get(player.getLeaderCardList().indexOf(leaderCard)), ACTIVATED);
        for(Controller controller : gameModel.getControllers()) { controller.notify(change); }
    }
}