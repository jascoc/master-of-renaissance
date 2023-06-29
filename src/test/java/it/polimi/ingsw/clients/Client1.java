package it.polimi.ingsw.clients;

import it.polimi.ingsw.network.client.IOClient;
import java.io.IOException;
import java.net.Socket;

import static it.polimi.ingsw.Variables.*;

public class Client1 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(LOCALHOST, 1234);
            IOClient IOClient = new IOClient(socket, 0);

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