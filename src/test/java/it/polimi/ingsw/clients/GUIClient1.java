package it.polimi.ingsw.clients;

import it.polimi.ingsw.network.client.GUI.GUIHandler;
import it.polimi.ingsw.network.client.GUI.GUIView;
import it.polimi.ingsw.network.client.IOClient;
import java.io.IOException;
import java.net.Socket;

import static it.polimi.ingsw.Variables.*;
/*
public class GUIClient1 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(LOCALHOST, 1234);
            //IOClient IOClient = new IOClient(socket);
            GUIHandler guiHandler = new GUIHandler(socket);
            GUIView guiView = new GUIView();

            Thread thread = new Thread(() -> {
                Thread t = guiHandler.createReadingThread();
                t.start();
                try { t.join(); }
                catch (InterruptedException e) { e.printStackTrace(); }
            });
            thread.start();

            JavafxMain.main(args[]);

            try { thread.join(); }
            catch (InterruptedException ignored) { }
        }
        catch (IOException ignored) { }
    }
}*/