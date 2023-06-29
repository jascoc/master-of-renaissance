package it.polimi.ingsw.model.leaderCards;

import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.resources.*;

import java.util.ArrayList;

/**
 * Leader Card
 */
public abstract class LeaderCard extends Card {

    private String name;
    private String ability;
    private String resource;
    private String desc;
    private boolean activated = false;

    private boolean chosen = true; //Set to false if the player has two identical LeaderCard that conflicts. The chosen one has chosen true, the other has chosen to false.

    private Resource resourceUsed; //The resource used by the leaderCard. It's one different resource for each LeaderCard of the same ability.

    private Resource requiredResource; //Resource needed to activate the LeaderCard in case its an "ADDITIONALSPACE" LeaderCard
    private ArrayList<DevelopmentCard> requiredDevCards;


    /**
     * Empty constructor of LeaderCard class.
     */
    public LeaderCard() { }

    /**
     * Constructor of LeaderCard class.
     */
    public LeaderCard(String name, int victoryPoints, String ability, String desc, String resource) {
        this.name = name;
        super.setVictoryPoints(victoryPoints);
        this.ability = ability;
        this.desc = desc;
        this.resource = resource;

        switch (resource) {
            case "Shield":
                resourceUsed = new Shield();
                break;
            case "Stone":
                resourceUsed = new Stone();
                break;
            case "Servant":
                resourceUsed = new Servant();
                break;
            case "Coin":
                resourceUsed = new Coin();
                break;
        }
    }

    /**
     * Getter e Setter Name.
     */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /**
     * Getter e Setter Victory Points.
     */
    public int getVictoryPoints() { return super.getVictoryPoints(); }
    public void setVictoryPoints(int victoryPoints) {  super.setVictoryPoints(victoryPoints); }


    /**
     * Setter required resources.
     */
    public Resource getRequiredResource() { return requiredResource; }

    /**
     * Getter required resources.
     */
    public void setRequiredResource(Resource requiredResource) { this.requiredResource = requiredResource; }

    /**
     * Getter required devCards.
     */
    public ArrayList<DevelopmentCard> getRequiredDevCards() { return requiredDevCards; }

    /**
     * Setter required devCards.
     */
    public void setRequiredDevCards(ArrayList<DevelopmentCard> developmentCard) { this.requiredDevCards = developmentCard; }

    /**
     * Getter Description.
     */
    public String getDesc() { return desc; }

    /**
     * Setter Description.
     */
    public void setDesc(String desc) { this.desc = desc; }

    /**
     * Override of toString. Used in tests.
     */
    @Override
    public String toString() { return "LeaderCard: " + name + ", Ability: " + ability + ". Resource: " + resourceUsed + ". Is active? " + activated
            + ", DevCardRequired: " + requiredDevCards + ", ResourceRequired: " + requiredResource; }

    /**
     * Activate the Leader Card.
     */
    public void setActivated() { activated = true; }

    /**
     * Get the status of activation of Leader Card.
     */
    public boolean getActivated() { return activated; }

    /**
     * Get ability.
     */
    public String getAbility() { return ability; }

    /**
     * Get UsedResource.
     */
    public Resource getResourceUsed() { return resourceUsed; }

    /**
     * Getters
     */
    public String getResourceString() { return resource; }

    public boolean getChosen() { return chosen; }

    /**
     * Setters
     */
    public void setChosenToFalse() { chosen = false; }
    public void setChosenToTrue() { chosen = true; }


}