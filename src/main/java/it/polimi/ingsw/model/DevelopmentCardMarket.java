package it.polimi.ingsw.model;

import it.polimi.ingsw.changes.DevelopmentCardStructureChanges;
import it.polimi.ingsw.model.exceptions.CantBuyThisCardException;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.leaderCards.ExtraSpaceLeader;
import it.polimi.ingsw.network.server.Controller;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;

/**
 * This class represents development card market
 */
public class DevelopmentCardMarket {

    private ArrayList<ProductionDevelopmentCard>[][] devCardStructure;

    /**
     * Constructor of this class.
     * @param devCardStructure is the mixed up structure.
     */
    public DevelopmentCardMarket(ArrayList <ProductionDevelopmentCard>[][] devCardStructure) { this.devCardStructure = devCardStructure; }

    /**
     * Method used for buying a developmentCard. It first check if the player has enough resources and then proceed in remove the card
     * from the developmentMarket and give it to the player.
     * @param row is the row in which the developmentCard is.
     * @param column is the column in which the developmentCard is.
     * @throws NotEnoughResourcesException .
     */
    public void buyDevelopmentCard(GameModel gameModel, int row, int column, int positionDevCard) throws NotEnoughResourcesException, CantBuyThisCardException {
        Player player = gameModel.getActivePlayer();

        //Check if the currentPlayer can buy the devCard basing on his current DevCards levels.
        if(!player.getPersonalBoard().getDevelopmentCardSlots()[positionDevCard].isEmpty()) {
            if(devCardStructure[row][column].get(0).getLvl() > player.getPersonalBoard().getDevelopmentCardSlots()[positionDevCard].get(0).getLvl() + 1) { throw new CantBuyThisCardException(); }
        }

        ArrayList<Resource> resourceWanted = new ArrayList<>(devCardStructure[row][column].get(0).getCardPriceResources());

        ArrayList<Resource> iterator = new ArrayList<>(resourceWanted);

        boolean check;

        firstDiscount:
        if(player.getLeaderCardList().size() >= 1 ) {
            if(player.getLeaderCardList().get(0).getAbility().equals(DISCOUNT) && player.getLeaderCardList().get(0).getActivated()) {
                for(Resource res : iterator) {
                    if(res.toString().equals(player.getLeaderCardList().get(0).getResourceString())) {
                        resourceWanted.remove(res);
                        break firstDiscount;
                    }
                }
            }
        }

        secondDiscount:
        if(player.getLeaderCardList().size() == 2) {
            if(player.getLeaderCardList().get(1).getAbility().equals(DISCOUNT) && player.getLeaderCardList().get(0).getActivated()) {
                for(Resource res : iterator) {
                    if(res.toString().equals(player.getLeaderCardList().get(1).getResourceString())) {
                        resourceWanted.remove(res);
                        break secondDiscount;
                    }
                }
            }
        }

        ArrayList<Resource> priceList = new ArrayList<>(resourceWanted);

        ArrayList<String> resInWarehouse = new ArrayList<>(6);
        for(Resource res : gameModel.getActivePlayer().getPersonalBoard().getWarehouse().warehouseToList()) { if(res != null) { resInWarehouse.add(res.toString());} }

        for(Resource resource : priceList) {
            check = false;
            if(resInWarehouse.contains(resource.toString())) {
                if(resourceWanted.contains(resource)) {
                    resourceWanted.remove(resource);
                    resInWarehouse.remove(resource.toString());
                    check = true;
                }
            }
            if(player.getLeaderCardList().size() >= 1) {
                if(player.getLeaderCardList().get(0).getAbility().equals(ADDITIONAL_SPACE) && player.getLeaderCardList().get(0).getResourceString().equals(resource.toString()) && !check) {
                    ExtraSpaceLeader leader0 = (ExtraSpaceLeader) player.getLeaderCardList().get(0);
                    if(!leader0.getExtraResources().isEmpty()) { check = leader0.removeResource(gameModel); }
                }
            }
            if(player.getLeaderCardList().size() == 2) {
                if(player.getLeaderCardList().get(1).getAbility().equals(ADDITIONAL_SPACE) && player.getLeaderCardList().get(1).getResourceString().equals(resource.toString()) && !check){
                    ExtraSpaceLeader leader1 = (ExtraSpaceLeader) player.getLeaderCardList().get(1);
                    if(!leader1.getExtraResources().isEmpty()) { check = leader1.removeResource(gameModel); }
                }
            }
            exitStrongbox:
            if(!check) {
                for(Resource res : player.getPersonalBoard().getStrongBox()) { if(res.toString().equals(resource.toString())){ resourceWanted.remove(resource); break exitStrongbox; } }
            }
        }

        if(!resourceWanted.isEmpty()) { throw new NotEnoughResourcesException(); }
        else{
            resourceWanted = new ArrayList<>(priceList);
            for(Resource resource : resourceWanted) {
                if(player.getPersonalBoard().getWarehouse().findResourceByName(resource)) { player.getPersonalBoard().getWarehouse().removeResourceByName(resource, gameModel); }
                else {
                    boolean done = false;
                    for(int i = 0; i < player.getPersonalBoard().getStrongBox().size() && !done; i ++) {
                        if(player.getPersonalBoard().getStrongBox().get(i).toString().equals(resource.toString())) {
                            player.getPersonalBoard().removeFromStrongbox(i);
                            done = true;
                        }
                    }
                }
            }
        }

        player.getPersonalBoard().storeDevelopmentCard(devCardStructure[row][column].get(0), positionDevCard, gameModel);
        player.addVictoryPoints(devCardStructure[row][column].get(0).getVictoryPoints());

        DevelopmentCardStructureChanges changes = new DevelopmentCardStructureChanges(gameModel.getActivePlayer().getRoundPosition(), row, column, getCardFromStructure(row,column));
        for (Controller controller : gameModel.getControllers()) { controller.notify(changes); }

        devCardStructure[row][column].remove(0);
    }


    /**
     * Method for getting the top card of the chosen matrix' cell. Used by the view to get the top cards to show and in tests.
     * @param row the row wanted.
     * @param column the column wanted.
     * @return the development card that's in devCardStructure[row][column].
     */
    public DevelopmentCard getCardFromStructure(int row, int column) { return devCardStructure[row][column].get(0); }

    /**
     * Get list from parameters.
     */
    public ArrayList<ProductionDevelopmentCard> getDevelopmentCardList(int row, int column) { return devCardStructure[row][column]; }

    /**
     * Puts all the card from the market into a List
     * @return List of Development Card
     */
    public ArrayList<DevelopmentCard> getAllCardsAsList() {
        ArrayList<DevelopmentCard> developmentCards = new ArrayList<>(48);
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 4; j ++) {
                developmentCards.addAll(devCardStructure[i][j]);
            }
        }
        return developmentCards;
    }

    /**
     * Creates the structure of Development card market
     * @return matrix of Array list
     */
    public ArrayList<DevelopmentCard>[][] getDevCardStructure() {
        ArrayList<DevelopmentCard>[][] developmentCards = new ArrayList[3][4];
        for(int i = 0; i < 3; i ++) { for(int j = 0; j < 4; j ++) { developmentCards[i][j] = new ArrayList<>(devCardStructure[i][j]); } }
        return developmentCards;
    }

}