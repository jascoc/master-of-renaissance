package it.polimi.ingsw.network.client.CLI;

import it.polimi.ingsw.network.client.IOClient;

import java.io.IOException;
import java.net.Socket;

import static it.polimi.ingsw.Variables.LOCALHOST;

public class ClientStarter {

    private String host;
    private int portNumber;
    private it.polimi.ingsw.network.client.IOClient IOClient;

    public ClientStarter(String host, int portNumber, int type) {
        this.host = host;
        this.portNumber = portNumber;
        try {
            Socket socket = new Socket(host, portNumber);
            this.IOClient = new IOClient(socket, type);
        }
        catch (IOException ignored) { }
    }

    public void startClient() {
        try {
            Thread thread = new Thread(() -> {
                Thread t = IOClient.createReadingThread();
                t.start();
                try { t.join(); }
                catch (InterruptedException e) { e.printStackTrace(); }
            });
            thread.start();

            IOClient.startGame();

            try { thread.join(); }
            catch (InterruptedException ignored) { }
        }
        catch (IOException ignored) { }
    }
}