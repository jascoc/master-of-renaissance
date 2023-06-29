package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.commands.MoveResourceCommand;
import it.polimi.ingsw.network.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveResourcesMessage extends Message {

    public String oldPosition;
    public String newPosition;


    public MoveResourcesMessage(String oldPosition, String newPosition) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    @Override
    public void activeMessage(GameModel gameModel) {
        MoveResourceCommand command = new MoveResourceCommand();
        command.executeCommand(oldPosition, newPosition, gameModel);
    }
}