package it.polimi.ingsw.network;

import it.polimi.ingsw.network.client.CLI.ClientStarter;
import it.polimi.ingsw.network.server.Server;

import java.util.Locale;

import static it.polimi.ingsw.Variables.*;

/**
 * Main App, from here we could start all the game, giving it the right parameters while executing the jar.
 * Execution example: java -jar AM14.jar [server/cliclient/guiclient] [port to which connect] [IP to connect (if clients)]
 */
public class MainApp {

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(args));
        ClientStarter clientStarter;
        switch (args[0].toLowerCase(Locale.ROOT)) {
            case SERVER:
                new Thread() {
                    @Override
                    public void run() {
                        Server server = new Server();
                        server.runServer(Integer.parseInt(args[1]));
                    }
                }.start();
                break;
            case CLI_CLIENT:
                clientStarter = new ClientStarter(args[2], Integer.parseInt(args[1]), 0);
                clientStarter.startClient();
                break;
            case GUI_CLIENT:
                clientStarter = new ClientStarter(args[2], Integer.parseInt(args[1]), 1);
                clientStarter.startClient();
                break;
        }

    }
}

/*
System.out.println("Type \"server\" to launch the server. Type \"cliClient\" to launch the CLI or \"guiClient\" to launch the GUI.");

        Scanner scanner = new Scanner(System.in);
        String whatToLaunch = scanner.nextLine();

        if(whatToLaunch.toLowerCase(Locale.ROOT).equals(SERVER)) {  }
        else if(whatToLaunch.toLowerCase(Locale.ROOT).equals(CLI_CLIENT)) {  }
        else if(whatToLaunch.toLowerCase(Locale.ROOT).equals(GUI_CLIENT)) {  }
 */
