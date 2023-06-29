package it.polimi.ingsw.network.client.Minimal;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * Minimal class are simplified version, with only getter and setters
 * of the classes in the model, they are used in the client to not have
 * any side effect
 */
public class DevelopmentCardMinimal {

    private int lvl;
    private int victoryPoints;
    private String colour;

    private ArrayList<String> cardPrice;
    private ArrayList<String> cardPriceResource = new ArrayList<>(8);

    private ArrayList<String> productionPrice;
    private ArrayList<String> productionPriceResource = new ArrayList<>(2);

    private ArrayList<String> production = new ArrayList<>();

    /**
     * Constructor
     * @param victoryPoints victory points
     * @param colour colour
     */
    public DevelopmentCardMinimal(int victoryPoints, String colour) {
        this.victoryPoints = victoryPoints;
        this.colour = colour;
    }

    /**
     * Constructor
     * @param lvl card lvl
     * @param colour card colour
     * @param cardPrice card price
     * @param victoryPoints card victory points
     * @param productionPrice production price
     * @param production production generated
     */
    public DevelopmentCardMinimal(int lvl, String colour, ArrayList<String> cardPrice, int victoryPoints, ArrayList<String> productionPrice, ArrayList<String> production) {
        this.lvl = lvl;
        this.colour = colour;
        this.cardPrice = cardPrice;
        this.productionPrice = productionPrice;
        setVictoryPoints(victoryPoints);
        this.production = production;
    }

    /**
     * Getters and Setters
     */
    public int getVictoryPoints() { return victoryPoints; }

    public void setVictoryPoints(int victoryPoints) { this.victoryPoints = victoryPoints; }

    public String getColour() { return colour; }

    public void setColour(String colour) { this.colour = colour; }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public ArrayList<String> getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(ArrayList<String> cardPrice) {
        this.cardPrice = cardPrice;
    }

    public ArrayList<String> getCardPriceResource() {
        return cardPriceResource;
    }

    public void setCardPriceResource(ArrayList<String> cardPriceResource) {
        this.cardPriceResource = cardPriceResource;
    }

    public ArrayList<String> getProductionPrice() {
        return productionPrice;
    }

    public void setProductionPrice(ArrayList<String> productionPrice) {
        this.productionPrice = productionPrice;
    }

    public ArrayList<String> getProductionPriceResource() {
        return productionPriceResource;
    }

    public void setProductionPriceResource(ArrayList<String> productionPriceResource) {
        this.productionPriceResource = productionPriceResource;
    }

    public ArrayList<String> getProduction() {
        return production;
    }

    public void setProduction(ArrayList<String> production) {
        this.production = production;
    }


}