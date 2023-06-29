package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.model.resources.*;


/**
 * is Card sub-class and implements the development cards
 */
public abstract class DevelopmentCard extends Card {

    private int lvl;
    private String colour;

    private ArrayList<String> cardPrice;
    private ArrayList<Resource> cardPriceResource = new ArrayList<>(8);

    private ArrayList<String> productionPrice;
    private ArrayList<Resource> productionPriceResource = new ArrayList<>(2);

    private ArrayList<String> production = new ArrayList<>();

    /**
     * Constructor of DevCards, used in ProductionDevelopmentCard.
     * @param lvl card lvl
     * @param colour card colour
     * @param cardPrice card price
     * @param victoryPoints card victory points
     * @param productionPrice production price
     */
    public DevelopmentCard(int lvl, String colour, ArrayList<String> cardPrice, int victoryPoints, ArrayList<String> productionPrice) {
        this.lvl = lvl;
        this.colour = colour;
        this.cardPrice = cardPrice;
        this.productionPrice = productionPrice;
        setVictoryPoints(victoryPoints);
    }

    /**
     * Constructor
     * @param lvl card lvl
     * @param colour card colour
     * @param cardPrice card price
     * @param victoryPoints card victory points
     * @param productionPrice production price
     * @param production the resource produced
     */
    public DevelopmentCard(int lvl, String colour, ArrayList<String> cardPrice, int victoryPoints, ArrayList<String> productionPrice, ArrayList<String> production) {
        this.lvl = lvl;
        this.colour = colour;
        this.cardPrice = cardPrice;
        this.productionPrice = productionPrice;
        setVictoryPoints(victoryPoints);
        this.production = production;
    }

    /**
     * toString override.
     */
    @Override
    public String toString() {
        return "DevelopmentCard { " +
                "lvl = " + lvl +
                ", colour = '" + colour + '\'' +
                ", cardPriceResource = " + cardPriceResource +
                ", productionPriceResource = " + productionPriceResource +
                ", victoryPoints = " + getVictoryPoints() +
                '}';
    }

    /**
     * Getters
     */
    public String getColour() {
        return colour;
    }

    public int getLvl() {
        return lvl;
    }

    public ArrayList<Resource> getCardPriceResources() { return cardPriceResource; }

    public ArrayList<String> getCardPrice() { return cardPrice; }

    public ArrayList<Resource> getProductionPriceResource() { return productionPriceResource; }

    public ArrayList<String> getProductionPrice() { return productionPrice; }

    public ArrayList<String> getProduction() { return production; }

    /**
     * Setters
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setCardPrice(ArrayList<Resource> cardPrice) { this.cardPriceResource = cardPrice; }

    public void setProductionPriceResource(ArrayList<Resource> productionPriceResource) { this.productionPriceResource = productionPriceResource; }


    /**
     * following the Decorator pattern creating abstract methods that will be override by its subclass
     * @return
     */

    abstract List<Resource> activeProduction(GameModel gameModel, Shield shield);

    abstract List<Resource> activeProduction(GameModel gameModel,Servant servant);

    abstract List<Resource> activeProduction(GameModel gameModel,Coin coin);

    abstract List<Resource> activeProduction(GameModel gameModel,Stone stone);

    abstract List<Resource> activeProduction(GameModel gameModel,Stone stone,Servant servant);

    abstract List<Resource> activeProduction(GameModel gameModel,Coin coin,Shield shield);

    abstract List<Resource> activeProduction(GameModel gameModel,Stone stone,Shield shield);

    abstract List<Resource> activeProduction(GameModel gameModel,Coin coin,Servant servant);

    abstract List<Resource> activeProduction(GameModel gameModel,Shield shield1,Shield shield2);

    abstract List<Resource> activeProduction(GameModel gameModel,Servant servant1,Servant servant2);

    abstract List<Resource> activeProduction(GameModel gameModel,Stone stone1,Stone stone2);

    abstract List<Resource> activeProduction(GameModel gameModel,Coin coin1,Coin coin2);

    abstract List<Resource> activeProduction(GameModel gameModel, Shield shield, Servant servant);

    abstract List<Resource> activeProduction(GameModel gameModel, Coin coin, Stone stone);

    /**
     * writing the same method but with the signature inverted (eg. from Coin coin, Stone stone to Stone stone, Coin coin)
     */
    abstract List<Resource> activeProduction(GameModel gameModel, Servant servant, Stone stone);

    abstract List<Resource> activeProduction(GameModel gameModel, Shield shield, Coin coin);

    abstract List<Resource> activeProduction(GameModel gameModel, Shield shield, Stone stone);

    abstract List<Resource> activeProduction(GameModel gameModel, Servant servant, Coin coin);

    abstract List<Resource> activeProduction(GameModel gameModel, Servant servant, Shield shield);

    abstract List<Resource> activeProduction(GameModel gameModel, Stone stone, Coin coin);


}