package it.polimi.ingsw.network.server;

import java.io.IOException;
import java.net.InetAddress;

public class Server {

    /**
     * Starts and run the server.
     */
    public void runServer(int ServerPort) {
        try {
            AcceptConnection accept = new AcceptConnection();
            System.out.println("SERVER STARTED");
            System.out.println(InetAddress.getLocalHost());
            accept.acceptConnection(ServerPort);
        }
        catch (IOException e) { }
    }
}