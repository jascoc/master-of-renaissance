package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.Server;

public class RunServer {

    private static final Integer ServerPort = 1234;

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                Server server = new Server();
                server.runServer(ServerPort);
            }
        }.start();
    }
}
