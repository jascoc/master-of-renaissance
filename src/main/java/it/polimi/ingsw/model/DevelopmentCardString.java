package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * This card development card String
 */
public class DevelopmentCardString {

    private String type;
    private String colour;
    private int level;
    private int amount;
    private int victoryPoints;
    private DevelopmentCard developmentCard;
    private Resource resourceRequired;
    private ArrayList<String> production;

    /**
     * Constructor
     * @param type type
     * @param colour card colour
     * @param level card level
     * @param amount card amount
     */
    public DevelopmentCardString(String type, String colour, int level, int amount) {
        this.type = type;
        this.colour = colour;
        this.level = level;
        this.amount = amount;
    }

    /**
     * Getters and setters.
     */
    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getColour() { return colour; }

    public void setColour(String colour) { this.colour = colour; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }


    public DevelopmentCard getRequiredDevCard() { return developmentCard; }

    public Resource geRequiredResource() { return resourceRequired; }




    public int getVictoryPoints() { return victoryPoints; }

    public void setVictoryPoints(int victoryPoints) { this.victoryPoints = victoryPoints; }
}
