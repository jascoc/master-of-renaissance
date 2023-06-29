package it.polimi.ingsw.network.client.Minimal;

import java.util.ArrayList;

/**
 * Minimal class are simplified version, with only getter and setters
 * of the classes in the model, they are used in the client to not have
 * any side effect
 */
public class MarbleMarketMinimal {

    private String[][] marketMarbleStructure;
    private String outMarble;

    /**
     * Constructor
     */
    public MarbleMarketMinimal() { marketMarbleStructure = new String[3][4]; }

    /**
     * Constructor
     * @param marbleMarketString marble market in String
     */
    public MarbleMarketMinimal(ArrayList<String> marbleMarketString) {
        marketMarbleStructure = new String[3][4];
        int punt = 0;
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 4; j ++) {
                marketMarbleStructure[i][j] = marbleMarketString.get(punt);
                punt ++;
            }
        }
        outMarble = marbleMarketString.get(punt);
    }

    /**
     * Getters and Setters
     */
    public void setMarketMarbleStructure(String[][] marketMarbleStructure) { this.marketMarbleStructure = marketMarbleStructure; }

    public void setOutMarble(String outMarble) { this.outMarble = outMarble; }

    public String[][] getMarketMarbleStructure() { return marketMarbleStructure; }

    public String getMarbleFromMarket(int row, int column) { return marketMarbleStructure[row][column]; }

    public String getOutMarble() { return outMarble; }

}
