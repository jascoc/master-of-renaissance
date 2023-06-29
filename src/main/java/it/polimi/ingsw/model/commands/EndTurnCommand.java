package it.polimi.ingsw.model.commands;

import it.polimi.ingsw.model.GameModel;

/**
 * This class implements the command to notify the end of the round
 */
public class EndTurnCommand extends Command {

    /**
     * Used to end the current player turn and sets the next active player when the actual message arrives
     * @param gameModel game model
     */
    public void executeCommand(GameModel gameModel) { gameModel.setNextActivePLayer(); }

}
