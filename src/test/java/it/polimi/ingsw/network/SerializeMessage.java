package it.polimi.ingsw.network;

import it.polimi.ingsw.network.messages.SetupGameMessage;
import it.polimi.ingsw.network.messages.SingleMessageFromClient;
import org.junit.jupiter.api.Test;

public class SerializeMessage {

    @Test
    public void serialize(){
        SingleMessageFromClient message = new SingleMessageFromClient("ciao");
        SetupGameMessage messageSetup = new SetupGameMessage(2, "Mickey");
        System.out.println(Parser.getInstance().serializeMessage(messageSetup));
    }

}
