package it.polimi.ingsw.network.server;

import it.polimi.ingsw.changes.ConnectionPlayerChanges;

import java.io.*;
import java.net.Socket;

import static it.polimi.ingsw.Variables.PLAYER_CONNECTED;
import static it.polimi.ingsw.Variables.PLAYER_DISCONNECTED;
import static java.lang.Thread.sleep;

public class Handler {

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private BufferedReader dataInputStream;
    private Controller controller;
    private boolean endGame;

    public Handler(Socket socket, Controller controller) throws IOException {
        this.socket = socket;
        dataInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.controller = controller;
        endGame = false;
    }

    public Socket getSocket() { return socket; }

    public void setEndGame(boolean endGame) { this.endGame = endGame; }

    public void handle() {
        Thread reading = createReadingThread();
        reading.start();

        try { reading.join(); }
        catch (InterruptedException ignored) { }
    }


    public Thread createReadingThread() {
        return new Thread(() -> {
            while (!endGame) {
                try {
                    String message = dataInputStream.readLine();
                    if(message != null) { controller.giveInputToModel(message); }
                }
                catch (IOException e) {
                    controller.getPlayer().isDisconnected(true);
                    controller.getGameModel().getControllers().remove(controller);
                    controller.getGameModel().handleDisconnectedPlayer();

                    ConnectionPlayerChanges change = new ConnectionPlayerChanges(-1, controller.getPlayer().getName(), PLAYER_DISCONNECTED);
                    for(Controller controller : controller.getGameModel().getControllers()) { controller.notify(change); }

                    endGame = true;
                }
            }
        });
    }


    public void sendMessage(String message) {
        try { dataOutputStream.writeUTF(message); }
        catch (IOException ignored) { }
    }
}