package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.leaderCards.ExtraSpaceLeader;

/**
 * This Class represents a Base Development Card
 */
public class BaseDevelopmentCard {

    /**
     * Remove first from warehouse then in leader card extra space if there is any and then in strongbox.
     */
    public Resource activeBaseProduction(Player player, Resource resource1, Resource resource2, Resource resource3, GameModel gameModel) throws NotEnoughResourcesException {
        boolean activatable1 = false;
        boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        activatable1 = isActivatable(player, resource1, gameModel, activatable1);
        activatable2 = isActivatable(player, resource2, gameModel, activatable2);

        if(activatable1 && activatable2) { return resource3; }
        else { throw new NotEnoughResourcesException(); }
    }

    /**
     * sets the boolean true if the requirements to active the base production are matched
     * @param player player name
     * @param resource1 resource
     * @param gameModel game model
     * @param activatable1 if activatable
     * @return activatable1
     */
    private boolean isActivatable(Player player, Resource resource1, GameModel gameModel, boolean activatable1) {
        if (player.getPersonalBoard().getWarehouse().findResourceByName(resource1)) {
            player.getPersonalBoard().getWarehouse().removeResourceByName(resource1, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().size() >= 1) {
                        if(((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString().equals(resource1.toString())) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                                activatable1 = true;
                            }
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().size() == 2) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                                if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString().equals(resource1.toString())) {
                                    ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                    activatable1 = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().size() >= 1) {
                        if(((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString().equals(resource1.toString())) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                                activatable1 = true;
                            }
                        }
                    }
                    if (!activatable1) {
                        if(((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().size() == 2) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                                if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString().equals(resource1.toString())) {
                                    ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                    activatable1 = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString().equals(resource1.toString())){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        return activatable1;
    }
}
