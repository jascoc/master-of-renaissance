package it.polimi.ingsw.network.client;

import it.polimi.ingsw.changes.*;
import it.polimi.ingsw.model.TrackTiles;
import it.polimi.ingsw.network.Parser;
import it.polimi.ingsw.network.client.CLI.CLIPrints;
import it.polimi.ingsw.network.client.Minimal.*;
import it.polimi.ingsw.network.messages.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

import static it.polimi.ingsw.Variables.*;
import static it.polimi.ingsw.network.client.CLI.CLIColours.*;
import static java.lang.System.exit;

public class IOClient {

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
    private Socket socket;

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
    private boolean roundZeroDone;

    private ArrayList<String> resourcesChosen;
    private ArrayList<LeaderCardMinimal> leaderCardDiscarded;
    private HashMap<String, Integer> playersWaiting;

    public IOClient(Socket socket, int type) throws IOException {
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
        roundZeroDone = false;
    }


    /**
     * Principal method of client. Contains all the calls of the others method and handle all the client logic of the round.
     * START GAME > ROUND ZERO > STARTING OF FIRST ROUND > (CYCLE):
     * START ROUND > MAKE DECISION ON ACTION TO MAKE (ACTION, LEADER CARDS) > (IF YOU WANT MOVE RES, SHOW OTHERS THINGS) > ENDTURN
     *
     */
    public void startGame() throws IOException {
        //STARTING GAME. MESSAGE > (TITLE + INSTRUCTION FOR THE SETUP MESSAGE);
        System.out.println("WELCOME TO MASTER OF RENAISSANCE!");
        System.out.println("For starting the game use the setup message (Setup UserName NumOfPlayers)");

        //Setup message in order to register to the game lobby.
        startPlayingWithSetupMessage();

        //While the game is not started if I write something it returns an error message.
        while(!gameStarted) {
            if(inputFromUser.ready()) {
                inputFromUser.readLine();
                System.out.println("The game is not started yet. Wait for all the players to connect!");
            }
        }

        //Round zero is the first thing to do when the game starts. Even if it's not my turn. It will be done one time only.
        if(!roundZeroDone) { doRoundZero(); }

        if(thisPlayer != activePlayer) { System.out.println(ANSI_GREEN + activePlayer.getPlayerName() + " has started his turn." + ANSI_RESET); }
        else { System.out.println(ANSI_GREEN_BACKGROUND + "It's your turn!" + ANSI_RESET); }

        //Game started;
        while(!gameEnded) {
            mutex = 0;

            //While is not my turn I can't do any action except HELP and SHOW.
            while(!isMyTurn && !gameEnded) {
                if(inputFromUser.ready()) {
                    userInput = inputFromUser.readLine();
                    if(userInput.split("\\s")[0].equals(HELP) || userInput.split("\\s")[0].equals(SHOW)) {
                        System.out.println(userInput.split("\\s")[0]);
                        handleUserInput(userInput);
                    }
                    else { System.out.println("You can't do this action now!! It's not your turn!"); }
                }
            }

            //When finally is my turn I can do whatever I want while my turn is not ended.
            while(isMyTurn && !gameEnded) {
                if(inputFromUser.ready()) {
                    userInput = inputFromUser.readLine();
                    handleUserInput(userInput);
                }
            }

            while(mutex == 0) { System.out.print(""); }
        }
    }

    /**
     * Handle the setup message which is the first message that will be sent to the server in order to connect.
     */
    private void startPlayingWithSetupMessage() {
        boolean setupDone = false;
        try {
            while(!setupDone) {
                userInput = inputFromUser.readLine();
                if(userInput.split("\\s").length < 3) { System.out.println("Wrong message, try again!"); }
                else if(userInput.split("\\s")[0].toLowerCase().equals(SETUP) && userInput.split("\\s")[1] != null
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
     * Do the round Zero for all the players
     */
    private void doRoundZero() {
        System.out.println("You are player number " + (thisPlayer.getPlayerRoundNumber() + 1));
        switch(thisPlayer.getPlayerRoundNumber()) {
            case 0:
                System.out.println("Since you're the first player you can't choose extra resources.");
                times = 0;
                break;
            case 1:
                System.out.println("Since you're the second player you can choose an extra resource to start with.");
                times = 1;
                break;
            case 2:
                System.out.println("Since you're the third player you can choose an extra resource to start with.");
                times = 1;
                break;
            case 3:
                System.out.println("Since you're the fourth player you can choose "+ ANSI_RED + "two" + ANSI_RESET + " extra resources to start with.");
                times = 2;
                break;
        }

        if(times != 0) { chooseResources(times); }
        chooseLeaderCard();
    }

    /**
     * Choose resources.
     */
    private void chooseResources(int times) {
        System.out.println("Type " + CHOOSE_RESOURCE + " and CO/SE/SH/ST/ANY to choose the wanted resource. ");
        for(int i = 0; i < times; i ++) {
            if(times != 1) { System.out.println("Choose resource " + (i+1)); }
            try { userInput = inputFromUser.readLine().toLowerCase(); }
            catch (IOException ignored) { }

            if(userInput.split("\\s")[0].equals(CHOOSE_RESOURCE)) { handleUserInput(userInput); }
            else if(userInput.split("\\s")[0].equals(HELP)) { handleUserInput(userInput); i--; }
            else if(userInput.split("\\s")[0].equals(SHOW)) { handleUserInput(userInput); i--; }
            else { System.out.println("You need to choose a Resource!! Try again"); i --; }
        }
    }

    /**
     * Choose leader cards to discard.
     */
    private void chooseLeaderCard() {
        leaderCardDiscarded = new ArrayList<>(2);
        System.out.println("Now it's time to choose two out of four leader cards to discard. " +
                "Type " + DISCARD_LEADER + " + the number of the card to discard.");
        for(int i = 0; i < 2; i ++) {
            System.out.println("Discard LeaderCard " + (i+1));
            try { userInput = inputFromUser.readLine().toLowerCase(); }
            catch (IOException ignored) { }

            if(userInput.split("\\s")[0].equals(DISCARD_LEADER)) {
                if(Integer.parseInt(userInput.split("\\s")[1])  == alreadyDiscarded) {
                    System.out.println("You already discarded leaderCard number " + Integer.parseInt(userInput.split("\\s")[1]));
                    i --;
                }
                else { handleUserInput(userInput); }
            }
            else if(userInput.split("\\s")[0].equals(HELP) || userInput.split("\\s")[0].equals(SHOW)) {
                handleUserInput(userInput); i--;
            }
            else { System.out.println("You need to choose a LeaderCard!! Try again!"); i --; }
        }
        sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));
        resourcesChosen.clear();
        roundZeroDone = true;
        leaderCardDiscarded.clear();
    }


    /**
     * Handle user input, sort the message on their codes
     * and make different actions based on what message the player wants to do.
     */
    public void handleUserInput(String messageString) {
        messageString = messageString.toLowerCase();
        String[] array = messageString.split("\\s");
        ArrayList<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, array);

        switch (stringList.get(0)) {
            case HELP:
                help(stringList);
                break;
            case SHOW:
                if(stringList.size() == 1) { show(); }
                else if (stringList.size() == 2) { show(stringList.get(1)); }
                else { show(stringList.get(1), stringList.get(2)); }
                break;
            case ACTION:
                if(!actionDone) {
                    switch(stringList.get(1)) {
                        case PRODUCE:
                            actionDone = true;
                            activateProduction(stringList);
                            break;
                        case BUY_DEV_CARD:
                            actionDone = true;
                            buyDevCard(stringList);
                            break;
                        case PICK_MARBLES:
                            if((stringList.get(2).equals(ROW) && (Integer.parseInt(stringList.get(3)) >= 0 && Integer.parseInt(stringList.get(3)) <= 2))
                                    || (stringList.get(2).equals(COLUMN) && (Integer.parseInt(stringList.get(3)) >= 0 && Integer.parseInt(stringList.get(3)) <= 3))) {
                                actionDone = true;
                                pickMarbles(stringList);
                                break;
                            }
                        default:
                            System.out.println("Error in message...");
                            actionDone = false;
                            break;
                    }
                    if(actionDone) { System.out.println("DONE!"); }
                }
                else { System.out.println("You already made a main action this turn! Wait for your next turn pls."); }
                leaderActionDone = false;
                break;
            case MOVE:
                moveResources(stringList.get(1), stringList.get(3));
                System.out.println("DONE!");
                break;
            case LEADERCARD:
                if(!leaderActionDone) { leaderCard(stringList); }
                System.out.println("DONE!");
                leaderActionDone = true;
                break;
            case ENDTURN:
                if(thisPlayer.isActive()) { endTurn(); }
                break;
            case CHOOSE_RESOURCE:
                chooseAResource(stringList.get(1));
                break;
            case DISCARD_LEADER:
                chooseLeaderCard(stringList.get(1));
                break;
            case QUIT:
                quit();
                break;
            default: System.err.println("There's something wrong in your message, try again.");
        }
    }

    /**
    * Prints help table.
    */
    private void help(ArrayList<String> arrayString) {
        if(arrayString.size() == 1) { CLIPrints.generateHelpTable(); }
        else {
            switch(arrayString.get(1)) {
                case(SHOW):
                    CLIPrints.generateHelpShow();
                    break;
                case(MOVE):
                    CLIPrints.generateHelpMove();
                    break;
                case(PICK_MARBLES):
                    CLIPrints.generateHelpPickMarbles();
                    break;
                case(BUY_DEV_CARD):
                    CLIPrints.generateHelpBuyDevCard();
                    break;
                case(PRODUCE):
                    CLIPrints.generateHelpProduce();
                    break;
                case(LEADERCARD):
                    CLIPrints.generateHelpLeadercard();
                    break;
                case(CHOOSE_RESOURCE):
                    CLIPrints.generateHelpChooseResource();
                    break;
                case(DISCARD_LEADER):
                    CLIPrints.generateHelpDiscardLeader();
                    break;
            }
        }
    }

    /**
     * Show without parameters -> Shows thisPlayer playerBoard.
     */
    private void show() {
        ArrayList<PlayerMinimal> player = new ArrayList<>(1);
        player.add(thisPlayer);
        CLIPrints.personalBoardGenerator(player, thisPlayer);
    }

    /**
     * Show with one parameter -> Shows whatToShow which could be DevCardMarket or MarbleMarket or playerNameList.
     */
    private void show(String whatToShow) {

        switch(whatToShow) {
            case MARBLE_MARKET:
                CLIPrints.marketMarbleGenerator(marbleMarket, thisPlayer);
                break;
            case DEV_CARD_MARKET:
                CLIPrints.developmentCardMarketGenerator(developmentCardMarket);
                break;
            case PLAYER_LIST:
                players.forEach((player) -> {
                    if(player.isActive()) {
                        System.out.println(player.getPlayerName() + " in position " + player.getPlayerRoundNumber() + ". It's his turn.");
                    }
                    else { System.out.println(player.getPlayerName() + " in position " + player.getPlayerRoundNumber()); }
                });
                break;
            case SINGLE_PLAYER:
                if(isSinglePlayerMode) { CLIPrints.singlePlayerGenerator(singlePlayer); }
                else { System.out.println("This game is not in single player mode!"); }
                break;
            default: System.out.println("Show message is incorrect!");
        }
    }

    /**
     * Show with two parameters -> Shows playerToShow playerBoard.
     */
    private void show(String whatToShow, String playerToShow) {
        ArrayList<PlayerMinimal> player = new ArrayList<>(1);
        boolean done = false;
        for(PlayerMinimal p : players) { if(p.getPlayerName().toLowerCase().equals(playerToShow)) { player.add(p); done = true; } }
        if(!done) { System.out.println("Player " + playerToShow + " does not exists."); }
        else { CLIPrints.personalBoardGenerator(player, thisPlayer); }
    }

    /**
     * Gets production to activate and send the activation message to the server.
     */
    private void activateProduction(ArrayList<String> arrayString) {
        ArrayList<String> baseResourcePay = null;
        String resToGetBase = null;
        String resToGetL1 = null;
        String resToGetL2 = null;
        ArrayList<String> productionActivated = new ArrayList<>(6);
        for(int i = 0; i < 6; i ++) { productionActivated.add("false"); }

        for(int i = 2; i < arrayString.size(); i ++) {
            switch(arrayString.get(i)) {
                case BASE_PRODUCTION:
                    productionActivated.set(0, "true");
                    System.out.println("You have choose the BaseProduction to be activated. You first need to choose the resources to pay: ");
                    chooseResources(2);
                    baseResourcePay = resourcesChosen;
                    System.out.println("and then choose the resource to get.");
                    chooseResources(1);
                    resToGetBase = resourcesChosen.get(0);
                    break;
                case D1_PRODUCTION:
                    productionActivated.set(1, "true");
                    break;
                case D2_PRODUCTION:
                    productionActivated.set(2, "true");
                    break;
                case D3_PRODUCTION:
                    productionActivated.set(3, "true");
                    break;
                case L1_PRODUCTION:
                    productionActivated.set(4, "true");
                    System.out.println("You have choose a leaderCardProduction to be activated. You need to choose the resource to get.");
                    chooseResources(1);
                    resToGetL1 = resourcesChosen.get(0);
                    break;
                case L2_PRODUCTION:
                    productionActivated.set(5, "true");
                    System.out.println("You have choose a leaderCardProduction to be activated. You need to choose the resource to get.");
                    chooseResources(1);
                    resToGetL2 = resourcesChosen.get(0);
                    break;
                default: System.out.println("Something wrong in the productions! Check it again."); actionDone = false;
            }
        }

        ActiveProductionMessage messageActive = new ActiveProductionMessage(resToGetBase, resToGetL1, resToGetL2,
                baseResourcePay, productionActivated);

        sendMessageToServer(Parser.getInstance().serializeMessage(messageActive));
    }

    /**
     * Gets the card to buy and send the buy message to the server.
     */
    private void buyDevCard(ArrayList<String> arrayString) {
        int level = Integer.parseInt(arrayString.get(2));
        int colour = Integer.parseInt(arrayString.get(3));
        int column = Integer.parseInt(arrayString.get(4));
        BuyDevCardMessage messageBuy = new BuyDevCardMessage(level, colour, column);
        sendMessageToServer(Parser.getInstance().serializeMessage(messageBuy));
    }

    /**
     * Gets the marbles to take and send the pick message to the server.
     */
    private void pickMarbles(ArrayList<String> arrayString) {
        ArrayList<String> warehousePositions = new ArrayList<>();
        for(int i = 4; i < arrayString.size(); i ++) { warehousePositions.add(arrayString.get(i)); }
        PickResourcesMessage messagePick = new PickResourcesMessage(arrayString.get(2), Integer.parseInt(arrayString.get(3)), warehousePositions);
        sendMessageToServer(Parser.getInstance().serializeMessage(messagePick));
    }

    /**
     *
     */
    private void moveResources(String from, String to) {
        MoveResourcesMessage message = new MoveResourcesMessage(from, to);
        sendMessageToServer(Parser.getInstance().serializeMessage(message));
    }

    /**
     *
     */
    private void leaderCard(ArrayList<String> arrayString) {
        ChooseLeaderMessage message = new ChooseLeaderMessage(arrayString.get(1), Integer.parseInt(arrayString.get(2)));
        sendMessageToServer(Parser.getInstance().serializeMessage(message));
    }

    /**
     * The player wants to end his turn.
     */
    private void endTurn() { sendMessageToServer(Parser.getInstance().serializeMessage(new EndTurnMessage())); }

    /**
     * Choose resources.
     */
    private void chooseAResource(String resource) {
        switch(resource) {
            case "co":
                resourcesChosen.add(COIN);
                break;
            case "se":
                resourcesChosen.add(SERVANT);
                break;
            case "sh":
                resourcesChosen.add(SHIELD);
                break;
            case "st":
                resourcesChosen.add(STONE);
                break;
            case "any":
                resourcesChosen.add(casualResource());
                break;
        }
    }

    /**
     * Get a Random Resource in String.
     */
    private String casualResource() {
        Random r = new Random();
        int i = r.nextInt(4);
        switch(i) {
            case 0:
                return COIN;
            case 1:
                return SERVANT;
            case 2:
                return SHIELD;
            default:
                return STONE;
        }
    }

    /**
     * Choose leaderCards (to discard probably);
     */
    private void chooseLeaderCard(String leaderNumber) {
        int num = Integer.parseInt(leaderNumber);
        alreadyDiscarded = num;
        leaderCardDiscarded.add(thisPlayer.getLeaderCards().get(num));
    }

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
     * Send the serialized message to the server.
     */
    public void sendMessageToServer(String message) { toServer.println(message); }

    /**
     * Create a Thread which scans Server's inputs and calls the modifyClient method.
     */
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

    /**
     * Modify the client Minimal structure based on the changes coming from the server.
     */
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
            case VICTORY_POINTS_CHANGES:
                modifyVictoryPoints(changes);
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

    /**
     * Remove the taken card in the developmentCardMarket.
     */
    private void modifyDevelopmentMarket(Changes change) {
        DevelopmentCardStructureChanges modifier = (DevelopmentCardStructureChanges) change;
        //Same as remove the top card in the list because the card taken is always the top card.
        if(modifier.getDevCardTaken().getColour().equals(developmentCardMarket.getList(modifier.getRow(), modifier.getColumn()).get(0).getColour())
                && modifier.getDevCardTaken().getLvl() == developmentCardMarket.getList(modifier.getRow(), modifier.getColumn()).get(0).getLvl()) {
            developmentCardMarket.getList(modifier.getRow(), modifier.getColumn()).remove(0);
        }
        else { System.out.println("ERROR IOClient 641!!"); }//THIS ELSE SHOULD NEVER ACTIVATE!!
    }

    /**
     * Modify all the faithTrack for all the players.
     */
    private void modifyFaithTrack(Changes change) {
        FaithTrackChanges modifier = (FaithTrackChanges) change;
        for(int i = 0; i < players.size(); i ++) {
            players.get(i).setPlayerFaithPosition(modifier.getPlayerPositionFaithTrack().get(i));
        }
    }

    /**
     * Handle discard/activation of leaderCards.
     */
    private void modifyLeaderCards(Changes change) {
        LeaderCardChanges modifier = (LeaderCardChanges) change;
        if(modifier.getWhatHappened().equals(DISCARDED)) {
            //IF THE CARD WAS DISCARDED.
            players.get(modifier.getPlayerRoundNumber()).getLeaderCards().removeIf(card -> card.getResourceUsed().equals(modifier.getLeaderCard().getResourceUsed()) &&
                    card.getAbility().equals(modifier.getLeaderCard().getAbility()));
        }
        else if (modifier.getWhatHappened().equals(ACTIVATED)) {
            //IF THE CARD WAS NOT DISCARDED IT MEANS IT WAS ACTIVATED SO I SET IT TO ACTIVATED.
            for(LeaderCardMinimal card : players.get(modifier.getPlayerRoundNumber()).getLeaderCards()) {
                if(card.getResourceUsed().equals(modifier.getLeaderCard().getResourceUsed()) &&
                        card.getAbility().equals(modifier.getLeaderCard().getAbility())) {
                    players.get(modifier.getPlayerRoundNumber()).getLeaderCards().get(players.get(modifier.getPlayerRoundNumber()).
                            getLeaderCards().indexOf(card)).setActivated(true);
                }
            }
        }
        else { System.out.println("Error"); }
    }

    /**
     * Handles MarketMarbles modifies.
     */
    private void modifyMarbleMarket(Changes change) {
        MarbleMarketChanges modifier = (MarbleMarketChanges) change;
        marbleMarket = new MarbleMarketMinimal(modifier.getMarbleMarket());
    }

    /**
     * Modify devCards.
     */
    private void modifyPlayerDevCard(Changes change) {
        PlayerDevelopmentCardChanges modifier = (PlayerDevelopmentCardChanges) change;
        players.get(modifier.getPlayerRoundNumber()).getDevelopmentCardList(modifier.getColumnChanged()).add(modifier.getDevCardBought());
    }

    /**
     * Modify strongbox.
     */
    private void modifyStrongbox(Changes change) {
        StrongboxChanges modifier = (StrongboxChanges) change;
        players.get(modifier.getPlayerRoundNumber()).setStrongbox(modifier.getResourcesInBox());
    }

    /**
     * Modify warehouse.
     */
    private void modifyWarehouse(Changes change) {
        WarehouseChanges modifier = (WarehouseChanges) change;
        PlayerMinimal player = new PlayerMinimal();
        for(PlayerMinimal p : players) { if(p.getPlayerName().equals(modifier.getPlayerName())) { player = p; } }

        player.setWarehouse(modifier.getWarehouseMinimal());
    }

    /**
     * Modify victoryPoints.
     */
    private void modifyVictoryPoints(Changes change) {
        VictoryPointsChanges modifier = (VictoryPointsChanges) change;
        players.get(modifier.getPlayerRoundNumber()).setVictoryPoints(modifier.getVictoryPoints());
    }

    /**
     * The current player's turn is ended.
     */
    private void modifyTurn(Changes change) {
        EndTurnChanges modifier = (EndTurnChanges) change;

        activePlayer.setActive(false);
        isMyTurn = false;

        if(activePlayer.getPlayerRoundNumber() != modifier.getPlayerRoundNumber()) { System.out.println("ERROR -> 643"); }
        if(modifier.getPlayerRoundNumber() < (players.size() - 1)) { activePlayer = players.get(modifier.getPlayerRoundNumber() + 1); }
        else { activePlayer = players.get(0); }

        activePlayer.setActive(true);
        if(activePlayer == thisPlayer) { isMyTurn = true; }

        actionDone = false;
        leaderActionDone = false;

        mutex = 1;

        //When finally is my turn I can do whatever I want while my turn is not ended.
        if(thisPlayer != activePlayer) { System.out.println(ANSI_GREEN + activePlayer.getPlayerName() + " has started his turn." + ANSI_RESET); }
        else { System.out.println(ANSI_GREEN_BACKGROUND + "It's your turn!" + ANSI_RESET); }
    }

    /**
     * Starts the game.
     */
    private void modifyStartGame(Changes change) {
        GameStartedChanges modifier = (GameStartedChanges) change;

        isSinglePlayerMode = modifier.isSinglePlayerMode();
        if(isSinglePlayerMode) {
            players = modifier.getPlayers();
            thisPlayer = players.get(0);
            activePlayer = thisPlayer;
            thisPlayer.setActive(true);

            singlePlayer = new SinglePlayerMinimal();
            singlePlayer.setPlayerName(LORENZO_THE_MAGNIFICENT);
            singlePlayer.setSinglePlayer(true);
            //players.add(singlePlayer);
            isMyTurn = true;
        }
        else {
            players = modifier.getPlayers();
            for(PlayerMinimal player : players) {
                player.setSinglePlayer(false);
                if (player.isActive()) { activePlayer = player; }
                if(thisPlayer.getPlayerName().equals(player.getPlayerName())) { thisPlayer = player; }
                if(activePlayer.equals(thisPlayer)) { isMyTurn = true; }
            }
        }
        marbleMarket = modifier.getMarbleMarket();
        developmentCardMarket = modifier.getDevCardMarket();
        System.out.println("All players have connected successfully. Game starting...");

        gameStarted = true;
    }

    /**
     * Another player joined the game.
     */
    private void modifyWaitingPlayers(Changes change) {
        PlayerWaitingChanges modifier = (PlayerWaitingChanges) change;

        playersWaiting.put(modifier.getName(), modifier.getPlayerRoundNumber());
        System.out.println(modifier.getName() + " has connected. Waiting for all the players to connect.");
    }

    /**
     * If this arrives the game is ended, plot the winner.
     */
    private void modifyWinner(Changes change) {
        WinnerChanges modifier = (WinnerChanges) change;
        gameEnded = true;
        System.out.println("GAME ENDED!! THE WINNER IS " + modifier.getWinner().toUpperCase() + ".");
        System.out.println("Player points: ");
        for(String playerName : modifier.getPlayerPointsHashMap().keySet()) {
            System.out.println(playerName + " > " + modifier.getPlayerPointsHashMap().get(playerName));
        }
        System.out.println("Thank you for playing!");

        quit();
    }

    /**
     * SinglePlayer has won.
     */
    private void modifySinglePlayerWinner(Changes change) {
        SinglePlayerWinnerChanges modifier = (SinglePlayerWinnerChanges) change;

        if(modifier.hasSinglePlayerWon()) { System.out.println("GAME ENDED!! " + LORENZO_THE_MAGNIFICENT + " beat you! Try again!"); }
        else {
            System.out.println("GAME ENDED!! You won with " + modifier.getPlayerPointsHashMap().get(thisPlayer.getPlayerName())
                    + " victory points!");
        }
    }

    /**
     * Plot on screen that those are the final rounds!
     */
    private void modifyFinalRounds(Changes change) {
        LastRoundsChanges modifier = (LastRoundsChanges) change;
        System.out.println(ANSI_CYAN_BACKGROUND + modifier.getPlayer() +
                " has finished the game. We're now playing the last rounds!!" + ANSI_RESET);
    }

    /**
     * Plot an error message.
     */
    private void printErrorMessage(Changes change) {
        ErrorChanges modifier = (ErrorChanges) change;
        System.out.println("Exception " + modifier.getErrorMessage() + " has been thrown. Check if you can do this action!");

        if(modifier.getErrorCode().equals(FULL_SLOTS_ERROR) || modifier.getErrorCode().equals(WRONG_MARBLE_POSITIONING)) { actionDone = false; }
    }

    /**
     * Modify extra space leader resources.
     */
    private void extraSpaceAdd(Changes change) {
        ExtraSpaceLeaderChanges modifier = (ExtraSpaceLeaderChanges) change;
        int a;
        if(modifier.getLeader().equals(L1)) { a = 0; } else { a = 1; }
        thisPlayer.getLeaderCards().get(a).setDeposit(modifier.getResources());
    }

    /**
     * End turn for singlePlayer.
     */
    private void endSinglePlayerTurn(Changes change) {
        EndSinglePlayerTurnChanges modifier = (EndSinglePlayerTurnChanges) change;
        assert(isSinglePlayerMode); //IT SHOULD BE, THIS IS JUST IN CASE.

        activePlayer.setActive(false);
        isMyTurn = false;

        singlePlayer.setToken(modifier.getToken());
        if(singlePlayer.getToken().equals(MOVE_AND_SHUFFLE_ACTION_TOKEN)) { singlePlayer.setPlayerFaithPosition(singlePlayer.getPlayerFaithPosition() + 1); }
        else if(singlePlayer.getToken().equals(MOVE_ACTION_TOKEN)) { singlePlayer.setPlayerFaithPosition(singlePlayer.getPlayerFaithPosition() + 2); }

        //singlePlayer.setPlayerFaithPosition(modifier.getFaithTrackPosition());
        System.out.println(ANSI_CYAN + "Single player turn" + ANSI_RESET);
        CLIPrints.singlePlayerGenerator(singlePlayer);

        activePlayer.setActive(true);
        isMyTurn = true;

        actionDone = false;
        leaderActionDone = false;
        mutex = 1;

        System.out.println(ANSI_GREEN_BACKGROUND + "It's your turn!" + ANSI_RESET);
    }

    /**
     * There are connection issues.
     */
    private void plotConnectionIssues(Changes change) {
        ConnectionPlayerChanges modifier = (ConnectionPlayerChanges) change;

        if(modifier.getWhatHappened().equals(PLAYER_CONNECTED)) {
            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_PURPLE + modifier.getPlayerName() + " has reconnected to the game!" + ANSI_RESET);
        }
        else if(modifier.getWhatHappened().equals(PLAYER_DISCONNECTED)) {
            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_PURPLE + modifier.getPlayerName() +
                    " got disconnected from the game..." + ANSI_RESET);
        }
    }

    /**
     * Set all the old stuff for the reconnected player.
     */
    private void modifyAllAfterReconnection(Changes change) {
        ReconnectedClientChanges modifier = (ReconnectedClientChanges) change;

        marbleMarket = new MarbleMarketMinimal(modifier.getMarbleMarket());
        developmentCardMarket = new DevelopmentCardMarketMinimal(modifier.getDevCardsMarketStructure());

        if(modifier.isSinglePlayer()) {
            isMyTurn = true;
            isSinglePlayerMode = true;

            PlayerMinimal player = modifier.getPlayers().get(0);
            players.add(player);
            activePlayer = player;
            thisPlayer = player;

            assert(modifier.getSinglePlayerFaithPosition() != -1);
            singlePlayer = new SinglePlayerMinimal();
            singlePlayer.setPlayerFaithPosition(modifier.getSinglePlayerFaithPosition());
        }
        else {
            players = modifier.getPlayers();
            for(PlayerMinimal player : players) {
                player.setSinglePlayer(false);
                if(thisPlayer.getPlayerName().equals(player.getPlayerName())) { thisPlayer = player; }
                if (player.isActive()) {
                    activePlayer = player;
                    if(activePlayer.equals(thisPlayer)) { isMyTurn = true; }
                }
            }
        }
        gameStarted = true;
    }
}