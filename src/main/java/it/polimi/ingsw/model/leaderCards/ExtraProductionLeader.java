package it.polimi.ingsw.model.leaderCards;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import java.util.ArrayList;

import static it.polimi.ingsw.Variables.ADDITIONAL_SPACE;

/**
 * Leader Card with extra production ability
 */

public class ExtraProductionLeader extends LeaderCard {

    private Resource wantedResource;
    private ArrayList<Resource> resource = new ArrayList<>(1);

    /**
     * Constructor of LeaderCard class.
     * @param name card name
     * @param victoryPoints victory points
     * @param ability ability name
     * @param desc description of the ability
     */
    public ExtraProductionLeader(String name, int victoryPoints, String ability, String desc, String resource) {
        super(name, victoryPoints, desc, ability, resource);
    }

    /**
     * Activate the production if the requirements matches
     * @param gameModel
     * @param resource
     * @throws NotEnoughResourcesException
     */
    public void activeProduction(GameModel gameModel, Resource resource) throws NotEnoughResourcesException {
        this.resource.clear();

        Player player = gameModel.getActivePlayer();
        boolean check = false;

        if(getActivated()) {
            if (player.getPersonalBoard().getWarehouse().findResourceByName(this.getResourceUsed())) {
                player.getPersonalBoard().getWarehouse().removeResourceByName(this.getResourceUsed(), gameModel);
                check = true;
            }
            if(player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0).getAbility().equals(ADDITIONAL_SPACE) &&
                        player.getLeaderCardList().get(0).getResourceString().equals(getResourceString()) && !check) {

                    ExtraSpaceLeader leader0 = (ExtraSpaceLeader) player.getLeaderCardList().get(0);
                    if(leader0.removeResource(gameModel)) { check = true; }
                }
            }
            if (player.getLeaderCardList().get(1).getAbility().equals(ADDITIONAL_SPACE) &&
                    player.getLeaderCardList().get(1).getResourceString().equals(getResourceString()) && !check) {

                ExtraSpaceLeader leader1 = (ExtraSpaceLeader) player.getLeaderCardList().get(1);
                if(leader1.removeResource(gameModel)) { check = true; }
            }
            exitStrongbox:
            if (!check) {
                for (Resource res : player.getPersonalBoard().getStrongBox()) {
                    if (res.toString().equals(getResourceString())) {
                        player.getPersonalBoard().getStrongBox().remove(res);
                        check = true;
                        break exitStrongbox;
                    }
                }
            }
        }

        if(!check) { throw new NotEnoughResourcesException(); }

        gameModel.moveFaithTrack(1, player);
        this.resource.add(resource);
    }

    /**
     * Gets the resource produced
     * @return resource produced
     */
    public ArrayList<Resource> getProducedResource() { return this.resource; }
}

