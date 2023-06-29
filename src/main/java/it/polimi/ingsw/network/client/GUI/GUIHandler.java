package it.polimi.ingsw.network.client.GUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import it.polimi.ingsw.changes.*;
import it.polimi.ingsw.model.TrackTiles;
import it.polimi.ingsw.network.Parser;
//import it.polimi.ingsw.network.client.CLI.CLI;
import it.polimi.ingsw.network.client.Minimal.*;
import it.polimi.ingsw.network.messages.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import it.polimi.ingsw.network.client.GUI.GUIView;

import static it.polimi.ingsw.Variables.*;
import static it.polimi.ingsw.GUIVariables.*;
import static it.polimi.ingsw.Variables.*;
import it.polimi.ingsw.model.GameInitialization;
import it.polimi.ingsw.model.GameModel;

import static it.polimi.ingsw.network.client.CLI.CLIColours.ANSI_RED;
import static java.lang.System.exit;



public class GUIHandler {
    private PlayerMinimal thisPlayer;
    private ArrayList<PlayerMinimal> players;
    private PlayerMinimal activePlayer;

    private DevelopmentCardMarketMinimal developmentCardMarket;
    private MarbleMarketMinimal marbleMarket;
    private TrackTiles[] faithTrack;

    private PrintWriter toServer;
    private DataInputStream inputFromServer;
    private BufferedReader inputFromUser;
    private String userInput;
    private String specification;
    private Socket socket;
    private GUIView guiView;

    private boolean actionDone = false;
    private boolean leaderActionDone = false;
    private boolean gameStarted = false;
    private boolean gameEnded = false;
    private boolean isMyTurn;
    private int alreadyDiscarded;
    private int times;
    private int mutex;
    private boolean isSinglePlayerMode;
    private SinglePlayerMinimal singlePlayer;

    private ArrayList<String> resourcesChosen;
    private ArrayList<LeaderCardMinimal> leaderCardDiscarded;
    private HashMap<String, Integer> playersWaiting;


    public GUIHandler(Socket socket) throws IOException {
        this.socket = socket;
        players = new ArrayList<>();
        developmentCardMarket = new DevelopmentCardMarketMinimal();
        faithTrack = new TrackTiles[25];

        this.toServer = new PrintWriter(socket.getOutputStream(), true);
        this.inputFromServer = new DataInputStream(socket.getInputStream());
        this.inputFromUser = new BufferedReader(new InputStreamReader(System.in));
        this.playersWaiting = new HashMap<>();
        this.resourcesChosen = new ArrayList<>(2);
        this.alreadyDiscarded = -1;
        this.mutex = 0;
    }





        public static void startGame(String[] args){ JavafxMain.main(args);}










    /**
     * Handle the setup message which is the first message that will be sent to the server in order to connect.
     */
    private void startPlayingWithSetupMessage() {
        boolean setupDone = false;
        try {
            while(!setupDone) {
                userInput = inputFromUser.readLine();
                if(userInput.split("\\s")[0].toLowerCase().equals(SETUP) && userInput.split("\\s")[1] != null
                        && userInput.split("\\s")[2] != null) {
                    if(Integer.parseInt(userInput.split("\\s")[2]) < 1 || Integer.parseInt(userInput.split("\\s")[2]) > 4) {
                        System.out.println("Wrong number of players. Rewrite setup message.");
                    }
                    else { handleSetupMessage(userInput); setupDone = true; }
                }
                else { System.out.println("Error! Wrong message. You need to insert a setup message to enter the game!"); }
            }
        }
        catch (IOException ignored) { }
    }

    /**
     * Send the message to the server.
     */
    public void handleSetupMessage(String messageString) {
        String[] array = messageString.split("\\s");
        List<String> messageList = new ArrayList<String>();
        Collections.addAll(messageList, array);

        thisPlayer = new PlayerMinimal(messageList.get(1));
        SetupGameMessage message = new SetupGameMessage(Integer.parseInt(messageList.get(2)), messageList.get(1));
        //System.out.println("Configuring new player.");
        sendMessageToServer(Parser.getInstance().serializeMessage(message));
    }

    /**
     * Send the serialized message to the server.
     */
    public void sendMessageToServer(String message) { toServer.println(message); }


    /**
     * Quit the game.
     */
    private void quit() {
        System.out.println("You disconnected.");
        try { this.socket.close(); }
        catch (IOException ignored) { }
        exit(0);
    }


    /**
     * Create a Thread which scans Server's inputs and calls the modifyClient method.
     */
    /*
    public Thread createReadingThread() {
        return new Thread(() -> {
            while (!gameEnded) {
                try {
                    String messageFromServer = inputFromServer.readUTF();
                    modifyClient(messageFromServer);
                }
                catch (IOException e) {
                    gameEnded = true;
                    System.out.println("Server disconnected! Quitting...");
                    exit(1);
                }
            }
        });
    }

     */






    /**
     * Modify the client Minimal structure based on the changes coming from the server.
     */
    /*
    public void modifyClient(String messageString) {
        Changes changes = Parser.getInstance().deserializeChanges(messageString);

        switch(changes.getChangeCode()) {
            case DEVELOPMENT_CARD_MARKET_CHANGES:
                modifyDevelopmentMarket(changes);
                break;
            case FAITH_TRACK_CHANGES:
                modifyFaithTrack(changes);
                break;
            case LEADER_CARD_CHANGES:
                modifyLeaderCards(changes);
                break;
            case MARBLE_MARKET_CHANGES:
                modifyMarbleMarket(changes);
                break;
            case PLAYER_DEVELOPMENT_CARD_CHANGES:
                modifyPlayerDevCard(changes);
                break;
            case STRONGBOX_CHANGES:
                modifyStrongbox(changes);
                break;
            case WAREHOUSE_CHANGES:
                modifyWarehouse(changes);
                break;
            case END_TURN:
                modifyTurn(changes);
                break;
            case PLAYER_WAITING:
                modifyWaitingPlayers(changes);
                break;
            case GAME_STARTED:
                modifyStartGame(changes);
                break;
            case WINNER:
                modifyWinner(changes);
                break;
            case FINAL_ROUNDS:
                modifyFinalRounds(changes);
                break;
            case ERROR:
                printErrorMessage(changes);
                break;
            case EXTRA_SPACE_LEADER:
                extraSpaceAdd(changes);
                break;
            case END_SINGLE_PLAYER_TURN:
                endSinglePlayerTurn(changes);
                break;
            case NICKNAME_TAKEN:
                System.out.println("This Nickname has been already taken!! Try again with another setup message!");
                startPlayingWithSetupMessage();
                break;
            case SINGLE_PLAYER_WINNER:
                modifySinglePlayerWinner(changes);
                break;
            case CONNECTION:
                plotConnectionIssues(changes);
                break;
            case RECONNECTED_CLIENT:
                modifyAllAfterReconnection(changes);
                break;
            default:
                System.out.println(ANSI_RED + "How tf did we got here??");
        }
    }
*/


    /**
     * Sets different scene depending of information.
     */
    public void execute(GUIView guiView){
        switch (specification) {
            case WAITING:
                guiView.setWaitingLobbyStage();
                break;
            case DISCARD:
                guiView.setDiscardLeaderCardsStage();
                break;
            case SEL_RESOURCES:
                guiView.setSelectionResourcesStage();
                break;
            case LAST_CANVAS:
                guiView.setLastCanvasStage();
                break;
        }
    }



}
