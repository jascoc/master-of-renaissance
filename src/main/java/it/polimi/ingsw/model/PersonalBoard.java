package it.polimi.ingsw.model;

import it.polimi.ingsw.changes.ErrorChanges;
import it.polimi.ingsw.changes.PlayerDevelopmentCardChanges;
import it.polimi.ingsw.changes.StrongboxChanges;
import it.polimi.ingsw.model.resources.*;
import it.polimi.ingsw.network.server.Controller;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.Variables.FULL_SLOTS_ERROR;

/**
 * This class describes the board that each player has and where they play on.
 */
public class PersonalBoard {

    private ArrayList<Resource> strongBox;
    private Warehouse warehouse;
    private ArrayList<DevelopmentCard>[] developmentCardSlots;
    private BaseDevelopmentCard baseDevelopmentCard;
    private ArrayList<DevelopmentCard> column1;
    private ArrayList<DevelopmentCard> column2;
    private ArrayList<DevelopmentCard> column3;
    private Player player;

    /**
     * Constructor
     * @param player player
     */
    public PersonalBoard(Player player) {
        this.player = player;
        column1 = new ArrayList<>();
        column2 = new ArrayList<>();
        column3 = new ArrayList<>();
        strongBox = new ArrayList<>();
        warehouse = new Warehouse(player);
        baseDevelopmentCard = new BaseDevelopmentCard();
        developmentCardSlots = new ArrayList[3];
        developmentCardSlots[0] = column1;
        developmentCardSlots[1] = column2;
        developmentCardSlots[2] = column3;
    }

    /**
     * @return warehouse
     */
    public Warehouse getWarehouse() { return warehouse; }

    /**
     * @return strongbox
     */
    public ArrayList<Resource> getStrongBox() { return strongBox; }

    /**
     * a kind of override of the remove to use throwStrongboxChange
     * @param i
     */
    public void removeFromStrongbox(int i) {
        strongBox.remove(i);
        throwStrongboxChange();
    }

    /**
     * a kind of override of the remove to use throwStrongboxChange
     * @param res
     */
    public void removeFromStrongbox(Resource res) {
        strongBox.remove(res);
        throwStrongboxChange();
    }

    /**
     * a kind of override of the remove to use throwStrongboxChange
     * @param resources
     */
    public void addToStrongbox(List<Resource> resources) {
        strongBox.addAll(resources);
        throwStrongboxChange();
    }

    /**
     * a kind of override of the remove to use throwStrongboxChange
     * @param resources
     */
    public void addToStrongbox(Resource resources) {
        strongBox.add(resources);
        throwStrongboxChange();
    }

    /**
     * used to notify the model when a changes happens to the strongbox
     */
    private void throwStrongboxChange() {
        StrongboxChanges change = new StrongboxChanges(player.getRoundPosition(), strongBox);
        for(Controller controller : player.getController().getGameModel().getControllers()) { controller.notify(change); }
    }

    /**
     * getters
     */
    public BaseDevelopmentCard getBaseDevelopmentCard() { return baseDevelopmentCard; }

    public ArrayList<DevelopmentCard>[] getDevelopmentCardSlots() { return developmentCardSlots; }

    public DevelopmentCard getDevelopmentCard(int punt) { return developmentCardSlots[punt].get(0); }

    public void setBaseDevelopmentCard(BaseDevelopmentCard baseDevelopmentCard) { this.baseDevelopmentCard = baseDevelopmentCard; }

    /**
     * Used to store the development card into the personal board
     * @param developmentCard
     * @param column
     * @param gameModel
     */
    public void storeDevelopmentCard(DevelopmentCard developmentCard, int column, GameModel gameModel) {
        if (developmentCard.getLvl() == 1) {
            if (developmentCardSlots[0].isEmpty() && column == 0) {
                developmentCardSlots[0].add(developmentCard);
            }
            else if(developmentCardSlots[1].isEmpty() && column == 1) {
                developmentCardSlots[1].add(developmentCard);
            }
            else if(developmentCardSlots[2].isEmpty() && column == 2) {
                developmentCardSlots[2].add(developmentCard);
            }
            else {
                for(Controller controller : gameModel.getControllers()) {
                    controller.notify(new ErrorChanges(gameModel.getActivePlayer().getRoundPosition(), " Lvl 1 slots are full", FULL_SLOTS_ERROR));
                }
            }
        }
        else if (developmentCard.getLvl()==2) {
            if (developmentCardSlots[0].size()==1 && column == 0) {
                developmentCardSlots[0].add(developmentCard);
            }
            else if(developmentCardSlots[1].size()==1 && column == 1) {
                developmentCardSlots[1].add(developmentCard);
            }
            else if(developmentCardSlots[2].size()==1 && column == 2) {
                developmentCardSlots[2].add(developmentCard);
            }
            else {
                for(Controller controller : gameModel.getControllers()) {
                    controller.notify(new ErrorChanges(gameModel.getActivePlayer().getRoundPosition(), "Lvl 2 slots are full or you don't have enough lvl 1 card yet", FULL_SLOTS_ERROR));
                }
            }
        }
        else if (developmentCard.getLvl() == 3) {
            if (developmentCardSlots[0].size() == 2 && column == 0) {
                developmentCardSlots[0].add(developmentCard);
            }
            else if(developmentCardSlots[1].size() == 2 && column == 1) {
                developmentCardSlots[1].add(developmentCard);
            }
            else if(developmentCardSlots[2].size() == 2 && column == 2) {
                developmentCardSlots[2].add(developmentCard);
            }
            else {
                for(Controller controller : gameModel.getControllers()) {
                    controller.notify(new ErrorChanges(gameModel.getActivePlayer().getRoundPosition(), "Lvl 3 slots are full or you don't have enough lvl 2 card yet", FULL_SLOTS_ERROR));
                }
            }
        }

            PlayerDevelopmentCardChanges changes = new PlayerDevelopmentCardChanges(gameModel.getActivePlayer().getRoundPosition(), column, developmentCard);
            for(Controller controller : gameModel.getControllers()) {controller.notify(changes);}
    }

    /**
     * Delete the parameter resource from the Strongbox.
     * The resource deleted is the first found from the start of the List.
     */
    //public void deleteResourceFromStrongbox(Resource wantedResource) { strongBox.remove(strongBox.indexOf(wantedResource)); }

    /**
     * Returns the index of a specified resource found in the Strongbox. If not found returns -1.
     */
    public int searchForResourceInStrongbox(Resource resource) { return strongBox.indexOf(resource); }

    /**
     * @return the number of development card in the personal board
     */
    public int getSumDevelopmentCard() {
        int sumColumn1= 0;
        int sumColumn2 = 0 ;
        int sumColumn3 = 0 ;

        for(DevelopmentCard d : column1) { if(d != null) { sumColumn1++; } }
        for(DevelopmentCard d : column2) { if(d != null) { sumColumn2++; } }
        for(DevelopmentCard d : column3) { if(d != null) { sumColumn3++; } }

        return sumColumn1+sumColumn2+sumColumn3;
    }

    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }

    public void setStrongBox(ArrayList<Resource> strongBox) { this.strongBox = strongBox; }

    /**
     * Search for a resource in the strongBox using the toString() function.
     */
    public boolean findInStrongBoxByName(Resource resource) {
        for(Resource res : strongBox) {
            if(res.toString().equals(resource.toString())){ return true; }
        }
        return false;
    }

    /**
     * Converts the strong box into
     * @return a array of string
     */
    public ArrayList<String> strongBoxToString() {
        ArrayList<String> strongBoxString = new ArrayList<>();
        for(Resource res : strongBox) { strongBoxString.add(res.toString()); }
        return strongBoxString;
    }

    /**
     * @return number of resources
     */
    public int getNumResource() {
        int numResource = 0;
        numResource += strongBox.size();
        numResource += warehouse.numberOfResourceInWareHouse();
        return  numResource;
    }

    /**
     * @param columnN
     * @return development cards that are in the column chosen
     */
    public ArrayList<DevelopmentCard> getDevCardsInColumnN(int columnN) {
        switch(columnN) {
            case 0:
                return column1;
            case 1:
                return column2;
            case 2:
                return column3;
            default:
                return new ArrayList<>();
        }
    }
}





