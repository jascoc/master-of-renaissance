package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.commands.EndTurnCommand;

public class EndTurnMessage extends Message {

    public void activeMessage(GameModel gameModel) {
        EndTurnCommand command = new EndTurnCommand();
        command.executeCommand(gameModel);
    }

}
