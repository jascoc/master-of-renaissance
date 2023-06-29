package it.polimi.ingsw.model.leaderCards;

import it.polimi.ingsw.changes.ErrorChanges;
import it.polimi.ingsw.changes.ExtraSpaceLeaderChanges;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.exceptions.NotEnoughSpaceException;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;
import it.polimi.ingsw.network.server.Controller;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;
import static it.polimi.ingsw.Variables.STONE;

/**
 * Leader Card with extra space ability
 */
public class ExtraSpaceLeader extends LeaderCard {

    ArrayList<Resource> extraResources;

    /**
     * Constructor of ExtraSpaceLeader.
     * @param name card name
     * @param victoryPoints victory points
     * @param ability ability name
     * @param desc ability description
     */
    public ExtraSpaceLeader(String name, int victoryPoints, String ability, String desc, String resource) {
        super(name, victoryPoints, ability, desc, resource);
        extraResources = new ArrayList<>(2);
    }

    /**
     * Add a resource in the first slot, if is full put it in the second slot.
     * @throws NotEnoughSpaceException if both the slots are full.
     */
    public void addResource(GameModel gameModel, String leader, Resource storableResource) throws NotEnoughSpaceException {
        if (getActivated()) {
            if (storableResource.toString().equals(getResourceUsed().toString())) {
                if (extraResources.size() <= 2) { extraResources.add(storableResource); }
                else throw new NotEnoughSpaceException();
            }
        }
        for(Controller controller : gameModel.getControllers()) {
            controller.notify(new ExtraSpaceLeaderChanges(gameModel.getActivePlayer().getRoundPosition(), leader, extraResources));
        }
    }

    /**
     * Remove the requested resource from this spaces.
     * If both are full, take it from the second slot, else take it from the first slot.
     * If there are not resources in the card, return null or a new Exception.
     * @return the needed resource.
     */
    public boolean removeResource(GameModel gameModel) {
        if(extraResources.size() == 0)  { return false; }
        extraResources.remove(0);
        for(Controller controller : gameModel.getControllers()) {
            controller.notify(new ExtraSpaceLeaderChanges(gameModel.getActivePlayer().getRoundPosition(), getResourceString(), extraResources));
        }
        return true;
    }

    /**
     * getter
     * @return Array list of the resource
     */
    public ArrayList<Resource> getExtraResources() { return extraResources; }

    /**
     * Converts
     * @param resource String to
     * @return Resource class object
     */
    private Resource convertResources(String resource) {
        switch(resource) {
            case COIN:
                return new Coin();
            case SERVANT:
                return new Servant();
            case SHIELD:
                return new Shield();
            case STONE:
                return new Stone();
            default:
                return new Coin();
        }
    }

}