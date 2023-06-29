package it.polimi.ingsw.model.commands;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.network.messages.SingleMessageFromClient;

/**
 * This class implements the command to notify the command from the client
 */
public class SingleCommandFromClient extends Command {

    /**
     * Used as a broadcast command that notify the model
     * @param message
     * @param gameModel
     */
    public void executeCommand(SingleMessageFromClient message, GameModel gameModel) {
        String broadcastCommunication = message.getBroadcastCommunication().toLowerCase();

        System.out.println("Received: " + broadcastCommunication);
        //System.out.println("Active Player in position : " + gameModel.getActivePlayer().getFaithTrackPosition());
        //gameModel.moveFaithTrack(5, gameModel.getActivePlayer());

        switch (broadcastCommunication) {
            default :
                System.out.println("BroadcastCommunicationGoesBrrrrr");
        }
    }
}
