package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.commands.SingleCommandFromClient;
import it.polimi.ingsw.network.messages.Message;

public class SingleMessageFromClient extends Message {

    private String broadcastCommunication;

    public SingleMessageFromClient(String broadcastCommunication) { this.broadcastCommunication = broadcastCommunication; }

    public String getBroadcastCommunication() { return broadcastCommunication; }

    @Override
    public void activeMessage(GameModel gameModel) {
        SingleCommandFromClient command = new SingleCommandFromClient();
        command.executeCommand(this, gameModel);
    }
}

//{"type":"SingleMessageFromClient","broadcastCommunication":"Ciao"}