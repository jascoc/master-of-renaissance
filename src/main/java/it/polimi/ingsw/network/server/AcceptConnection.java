package it.polimi.ingsw.network.server;

import it.polimi.ingsw.model.Player;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptConnection {

    public void acceptConnection(int ServerPort) throws IOException {
        ServerSocket serverSocket = new ServerSocket(ServerPort);
        System.out.println("SERVER ONLINE\n");

        LobbyManager lobbyManager = LobbyManager.getLobbyInstance();
        lobbyManager.setLobbyInstance();

        try { manageAcceptance(serverSocket); }
        catch (IOException e) { serverSocket.close(); }
    }

    private void manageAcceptance(ServerSocket serverSocket) throws IOException {
        while(true) {
            Socket socket = serverSocket.accept();

            System.out.println("CONNECTION ACCEPTED!");
            Player player = new Player();
            Controller controller = new Controller();
            player.setController(controller);
            controller.setPlayer(player);
            controller.setHandler(socket);

            new Thread(() -> {
                Thread t = createThreadHandle(controller.getHandler());
                t.start();
                try { t.join(); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }).start();
        }
    }

    private Thread createThreadHandle(Handler handler) { return new Thread(handler::handle); }

}