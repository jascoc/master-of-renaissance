package it.polimi.ingsw.network.client.Minimal;

import java.util.ArrayList;
import static it.polimi.ingsw.Variables.*;

/**
 * Minimal class are simplified version, with only getter and setters
 * of the classes in the model, they are used in the client to not have
 * any side effect
 */
public class LeaderCardMinimal {

    private String name;
    private String ability;
    private String resourceUsed;
    private boolean activated;
    private boolean discarded;
    private String desc;
    private int victoryPoints;
    private String resource;// per abilità passive
    private ArrayList<String> deposit;

    //QUANDO LE COSTRUISCI
    private String requiredResource; //Resource needed to activate the LeaderCard in case its an "ADDITIONALSPACE" LeaderCard
    private ArrayList<DevelopmentCardMinimal> requiredDevCards;

    //DI EXTRA PRODUCTION
    private String wantedResource;
    private ArrayList<String> resources = new ArrayList<>(1);

    /**
     * Constructor
     * @param ability ability name
     * @param resourceUsed resource used
     * @param activated activation status
     * @param discarded discard status
     */
    public LeaderCardMinimal(String ability, String resourceUsed, boolean activated, boolean discarded) {
        this.ability = ability;
        this.resourceUsed = resourceUsed;
        this.activated = activated;
        this.discarded = discarded;
        if(ability.equals(ADDITIONAL_SPACE)) { deposit = new ArrayList<>(); }
    }

    /**
     * Constructor
     * @param ability ability name
     * @param resourceUsed resource used
     * @param victoryPoints victory points
     * @param requiredResource required resource
     * @param requiredDevCards required development card
     */
    public LeaderCardMinimal(String ability, String resourceUsed, int victoryPoints, String requiredResource,
                             ArrayList<DevelopmentCardMinimal> requiredDevCards) {
        this.ability = ability;
        this.resourceUsed = resourceUsed;
        this.victoryPoints = victoryPoints;
        this.requiredResource = requiredResource;
        this.requiredDevCards = requiredDevCards;
        if(ability.equals(ADDITIONAL_SPACE)) { deposit = new ArrayList<>(); }
    }

    /**
     * Getters and Setters
     */
    public String getAbility() { return ability; }

    public void setAbility(String ability) { this.ability = ability; }

    public String getResourceUsed() { return resourceUsed; }

    public void setResourceUsed(String resourceUsed) { this.resourceUsed = resourceUsed; }

    public boolean getActivated() { return activated; }

    public void setActivated(boolean activated) { this.activated = activated; }

    public boolean isDiscarded() { return discarded; }

    public void setDiscarded(boolean discarded) { this.discarded = discarded; }

    public String getRequiredResource() {
        return requiredResource;
    }

    public void setRequiredResource(String requiredResource) {
        this.requiredResource = requiredResource;
    }

    public ArrayList<DevelopmentCardMinimal> getRequiredDevCards() {
        return requiredDevCards;
    }

    public void setRequiredDevCards(ArrayList<DevelopmentCardMinimal> requiredDevCards) {
        this.requiredDevCards = requiredDevCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getWantedResource() {
        return wantedResource;
    }

    public void setWantedResource(String wantedResource) {
        this.wantedResource = wantedResource;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void setResources(ArrayList<String> resources) {
        this.resources = resources;
    }

    public ArrayList<String> getDeposit() { return this.deposit; }

    public void setDeposit(ArrayList<String> deposit) { this.deposit = deposit; }

}
