package it.polimi.ingsw.model.commands;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;

/**
 * This class implements the command to notify the movement in the faith track position
 */
public class MoveResourceCommand extends Command {

    /**
     * Used to change the position of the resources in the warehouse when the actual message arrives
     * @param oldPosition old position of the resource in the warehouse
     * @param newPosition new position of the resource in the warehouse
     * @param gameModel game model
     */
    public void executeCommand(String oldPosition, String newPosition, GameModel gameModel) {
        gameModel.getActivePlayer().getPersonalBoard().getWarehouse().moveResource(oldPosition, newPosition, gameModel);
    }
}