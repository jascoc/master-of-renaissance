package it.polimi.ingsw.model.leaderCards;

/**
 * Leader Card with withe marble conversion ability
 */
public class WhiteMarbleLeader extends LeaderCard {

    /**
     * Constructor of LeaderCard class.
     *
     * @param name card name
     * @param victoryPoints victory points
     * @param ability ability name
     * @param desc ability description
     */
    public WhiteMarbleLeader(String name, int victoryPoints, String ability, String desc, String resource) {
        super(name, victoryPoints, ability, desc, resource);
    }
}
