package it.polimi.ingsw.model.leaderCards;

/**
 * Leader Card with discount ability
 */
public class DiscountLeader extends LeaderCard {
    /**
     * Constructor of LeaderCard class.
     * @param name card name
     * @param victoryPoints victory points
     * @param ability ability name
     * @param desc description
     */
    public DiscountLeader(String name, int victoryPoints, String ability, String desc, String resource) {
        super(name, victoryPoints, ability, desc, resource);
    }
}
