package it.polimi.ingsw.model;

/**
 * this class is the super class of any card type class
 */
public class Card {

    private int victoryPoints;

    /**
     * Getter
     */
    public int getVictoryPoints() { return victoryPoints; }

    /**
     * Setter
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }
}