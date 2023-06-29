package it.polimi.ingsw.network.server;

import it.polimi.ingsw.changes.ErrorChanges;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.exceptions.CantBuyThisCardException;
import it.polimi.ingsw.model.exceptions.ErrorInMessageException;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.changes.Changes;
import it.polimi.ingsw.network.Observable;
import it.polimi.ingsw.network.Parser;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.SetupGameMessage;
import java.io.IOException;
import java.net.Socket;

import static it.polimi.ingsw.Variables.*;

public class Controller implements Observable {

    private Handler handler;
    private Player player;
    private GameModel gameModel;

    public Controller() { }

    public Handler getHandler() { return handler; }

    public void setHandler(Socket socket) throws IOException { handler = new Handler(socket, this); }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public GameModel getGameModel() { return gameModel; }

    public void setGameModel(GameModel gameModel) { this.gameModel = gameModel; }

    public void giveInputToModel(String messageString) {
        try {
            Message message = Parser.getInstance().deserializeMessage(messageString);
            if(message instanceof SetupGameMessage) { message.activeMessage(this); }
            else { message.activeMessage(gameModel); }
        }
        catch (NotEnoughResourcesException e) { createErrorChange(NOT_ENOUGH_RESOURCES); }
        catch (CantBuyThisCardException e) { createErrorChange(CANT_BUY_THIS_CARD); }
        catch (ErrorInMessageException e) { createErrorChange(ERROR_IN_MESSAGE + e.getError()); }
    }

    private void createErrorChange(String errorMessage) {
        ErrorChanges change = new ErrorChanges(player.getRoundPosition(), errorMessage, GENERAL_ERROR);
        notify(change);
    }

    @Override
    public void notify(Changes changes) {
        handler.sendMessage(Parser.getInstance().serializeChanges(changes));
        if(changes.getChangeCode().equals(WINNER) || changes.getChangeCode().equals(SINGLE_PLAYER_WINNER)) { handler.setEndGame(true); }
    }
}