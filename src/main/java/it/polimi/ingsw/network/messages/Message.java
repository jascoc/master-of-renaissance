package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.exceptions.CantBuyThisCardException;
import it.polimi.ingsw.model.exceptions.ErrorInMessageException;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.network.server.Controller;

public class Message {

    public void activeMessage(GameModel gameModel) throws NotEnoughResourcesException, CantBuyThisCardException, ErrorInMessageException { }

    public void activeMessage(Controller controller) { }
}