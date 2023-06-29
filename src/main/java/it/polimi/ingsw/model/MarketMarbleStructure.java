package it.polimi.ingsw.model;

import it.polimi.ingsw.changes.MarbleMarketChanges;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.network.server.Controller;

import java.util.ArrayList;

/**
 * Market Marble structure class.
 */
public class MarketMarbleStructure {

    private Marble[][] marketMarbleStructure;
    private Marble outMarble;
    private Marble tempMarble;
    private GameModel gameModel;

    /**
     * Constructor of MarketMarbleStructure, needs to be modified by taking the market directly from GameInitialization class.
     */
    public MarketMarbleStructure(ArrayList<Marble> marbleArray, GameModel gameModel) {
        this.marketMarbleStructure = new Marble[3][4];
        this.gameModel = gameModel;
        int punt = 0;
        for(int row = 0; row < 3; row ++){
            for(int col = 0; col < 4; col ++){
                marketMarbleStructure[row][col] = marbleArray.get(punt);
                punt ++;
            }
        }
        outMarble = marbleArray.get(12);
    }

    /**
     * This method will probably be used only in tests.
     * @param row, column
     * @return Marble contained in marketMarbleStructure[row][column].
     */
    public Marble getMarbleFromStructure(int row, int column) { return marketMarbleStructure[row][column]; }

    /**
     * This method will probably be used only in tests.
     * @return outMarble.
     */
    public Marble getOutMarble() { return outMarble; }


    /**
     * Method that changes the Marbles in a single row. LeaderCardVersion.
     * @return a list, with the Resources contained in that row before the change.
     */
    public ArrayList<Resource> chooseRow(int row, GameModel gameModel) {
        ArrayList<Marble> marbleList = new ArrayList<>(4);
        for (int column = 0; column < 4; column++) { marbleList.add(marketMarbleStructure[row][column]); }

        tempMarble = outMarble;
        outMarble = marketMarbleStructure[row][3];
        for (int column = 2; column >= 0; column--)
            marketMarbleStructure[row][column + 1] = marketMarbleStructure[row][column];
        marketMarbleStructure[row][0] = tempMarble;

        ArrayList<Resource> resourceList = new ArrayList<>(4);
        marbleList.forEach((marble) -> resourceList.add(marble.convertToResources(gameModel)));

        //When I'll have 2 whiteToResource LeaderCards active I ask the player which one he wants to use, and the other one' attribute CHOSEN go to false.
        for(LeaderCard card : gameModel.getActivePlayer().getLeaderCardList()) {
            if(card.getChosen() && card.getAbility().equals("whiteMarbleConvert") && card.getActivated()) {
                for(int i = 0; i < 4; i ++) {
                    if(resourceList.get(i).toString().equals("White")) { resourceList.set(i, card.getResourceUsed().getResource()); }
                }
            }
        }
        throwMarketMarbleChanges();
        return resourceList;
    }


    /**
     * Method that changes the Marbles in a single column. LeaderCardVersion.
     * @return a list, with the Resources contained in that column before the change.
     */
    public ArrayList<Resource> chooseColumn(int column, GameModel gameModel) {
        ArrayList<Marble> marbleList = new ArrayList<>(3);
        for(int row = 0; row < 3; row ++) { marbleList.add(marketMarbleStructure[row][column]); }

        tempMarble = outMarble;
        outMarble = marketMarbleStructure[2][column];
        for(int row = 1; row >= 0; row --)
            marketMarbleStructure[row + 1][column] = marketMarbleStructure[row][column];
        marketMarbleStructure[0][column] = tempMarble;

        ArrayList<Resource> resourceList = new ArrayList<>(3);
        marbleList.forEach((marble) -> resourceList.add(marble.convertToResources(gameModel)));

        //When I'll have 2 whiteToResource LeaderCards I ask the player which one he wants to use, and the other one' attribute CHOSEN go to false.
        for(LeaderCard card : gameModel.getActivePlayer().getLeaderCardList()) {
            if(card.getChosen() && card.getAbility().equals("whiteMarbleConvert") && card.getActivated()) {
                for(int i = 0; i < 3; i ++) {
                    if(resourceList.get(i).toString().equals("White")) { resourceList.set(i, card.getResourceUsed().getResource()); }
                }
            }
        }
        throwMarketMarbleChanges();
        return resourceList;
    }


    /**
     * Print the current set of market. Used in tests.
     */
    public void printMarket() {
        for(int i = 0; i < 3; i ++) {
            for(int j = 0 ; j < 4; j ++) { System.out.print(marketMarbleStructure[i][j].toString() + " "); }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Puts the marbles from the marble market into a list
     * @return list of marbles
     */
    public ArrayList<Marble> getMarbleMarketAsList() {
        ArrayList<Marble> marbleAsList = new ArrayList<>(13);
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 4; j ++) {
                marbleAsList.add(marketMarbleStructure[i][j]);
            }
        }
        marbleAsList.add(outMarble);

        return marbleAsList;
    }

    /**
     * Converts Marbles from the market to Strings
     * @return
     */
    public ArrayList<String> getMarbleMarketString() {
        ArrayList<String> marbleMarket = new ArrayList<>();
        for(Marble marble : getMarbleMarketAsList()) {
            marbleMarket.add(marble.toString());
        }
        return marbleMarket;
    }

    /**
     * Used to notify the model if a changes in the market happens
     */
    private void throwMarketMarbleChanges() {
        MarbleMarketChanges change = new MarbleMarketChanges(gameModel.getActivePlayer().getRoundPosition(),
                gameModel.getMarketMarbleStructure().getMarbleMarketString());
        for(Controller controller : gameModel.getControllers()) { controller.notify(change); }
    }
}
