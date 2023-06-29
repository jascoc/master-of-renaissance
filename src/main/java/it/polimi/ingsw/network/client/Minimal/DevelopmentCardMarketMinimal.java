package it.polimi.ingsw.network.client.Minimal;

import java.util.ArrayList;

/**
 * Minimal class are simplified version, with only getter and setters
 * of the classes in the model, they are used in the client to not have
 * any side effect
 */
public class DevelopmentCardMarketMinimal {

    private ArrayList<DevelopmentCardMinimal>[][] developmentCardMarket = new ArrayList[3][4];

    /**
     * Constructor
     */
    public DevelopmentCardMarketMinimal() {
        for(int i = 0; i < 3; i ++) {
            for (int j = 0; j < 4; j ++) {
                developmentCardMarket[i][j] = new ArrayList<>(4);
            }
        }
    }

    /**
     * Constructor
     * @param developmentCardMarket development card market
     */
    public DevelopmentCardMarketMinimal(ArrayList<DevelopmentCardMinimal>[][] developmentCardMarket) {
        this.developmentCardMarket = developmentCardMarket;
    }

    /**
     * Constructor
     * @param devCardsMinimal development card minimal
     */
    public DevelopmentCardMarketMinimal(ArrayList<DevelopmentCardMinimal> devCardsMinimal) {
        int punt = 0;
        for(int i = 0; i < 3; i ++) {
            for (int j = 0; j < 4; j ++) {
                developmentCardMarket[i][j] = new ArrayList<>(4);
                for(int k = 0; k < 4; k ++) {
                    if(devCardsMinimal.size() != 0) {
                        developmentCardMarket[i][j].add(devCardsMinimal.get(punt));
                        punt ++;
                    }
                }
            }
        }
    }

    /**
     * Getters and Setters
     */
    public void setDevelopmentCardMarket(ArrayList<DevelopmentCardMinimal>[][] developmentCardMarket) {
        this.developmentCardMarket = developmentCardMarket;
    }

    public void addCardToMarket(int colour, int level, DevelopmentCardMinimal devCard) {
       developmentCardMarket[colour][level].add(0, devCard);
    }

    public ArrayList<DevelopmentCardMinimal>[][] getDevelopmentCardMarket() {
        return developmentCardMarket;
    }

    public DevelopmentCardMinimal getCard(int colour, int level) {
        return developmentCardMarket[colour][level].get(0);
    }

    public DevelopmentCardMinimal getCard(int colour, int level, int position) {
        return developmentCardMarket[colour][level].get(position);
    }

    public int howMuchCardsLeft(int colour, int level) {
        return developmentCardMarket[colour][level].size();
    }

    public ArrayList<DevelopmentCardMinimal> getList(int colour, int level) {
        return developmentCardMarket[colour][level];
    }
}
