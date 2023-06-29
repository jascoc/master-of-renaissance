package it.polimi.ingsw.model;

import it.polimi.ingsw.changes.*;
import it.polimi.ingsw.model.tokens.ActionToken;
import it.polimi.ingsw.network.Parser;
import it.polimi.ingsw.network.server.Controller;
import it.polimi.ingsw.network.server.Lobby;
import it.polimi.ingsw.network.server.LobbyManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static it.polimi.ingsw.Variables.*;
import static java.lang.Thread.sleep;

/**
 * GameModel class. It handles all the instance of the game.
 */

public class GameModel {

    private ArrayList<Player> playerList;
    private MarketMarbleStructure marketMarbleStructure;
    private DevelopmentCardMarket developmentCardMarket;
    private Round round;
    //private ResourceSupply resourceSupply = new ResourceSupply();
    private TrackTiles[] faithTrack;
    private GameInitialization initializedGame;
    private SinglePlayer singlePlayer;
    private Lobby lobby;

    private boolean isSinglePlayerMode = false;
    private boolean isFinalRounds;
    private ArrayList<Controller> controllers;

    private Timer timerDisconnections;
    private final long delay = 5000; //Default value 45000, 5000 used for tests.

    /**
     * GameModel constructor.
     * Sets a FaithTrack of 25 TrackTiles and gets the playerList directly from the GameInitialization.
     */
    public GameModel() {
        round = new Round();
        controllers = new ArrayList<>();
        faithTrack = new TrackTiles[25];
        for(int i = 0; i < 25; i ++) faithTrack[i] = new TrackTiles(i);
    }

    /**
     * Initialize the game. Must call it at the start of every game.
     * Sets the marketMarbleStructure.
     * Sets the playerList.
     */
    public void initializeTheGame() {
        initializedGame = new GameInitialization();
        initializedGame.setPlayers(playerList);
        initializedGame.splitLeaderCard(playerList);

        this.marketMarbleStructure = new MarketMarbleStructure(initializedGame.initializeMarbleMarketStructure(), this);
        this.developmentCardMarket = initializedGame.setDevelopmentCardMarket();

        throwGameStartedChange();
    }

    /**
     * Used to notify the model if a change happens
     */
    private void throwGameStartedChange() {
        GameStartedChanges change = new GameStartedChanges(-1, developmentCardMarket.getAllCardsAsList(),
                marketMarbleStructure.getMarbleMarketAsList(), playerList, isSinglePlayerMode);
        for(Controller controller : controllers) { controller.notify(change); }
    }

    /**
     * Returns the list of controllers of each player.
     */
    public ArrayList<Controller> getControllers() { return this.controllers; }

    /**
     * Set Player List.
     * @param playerList got from GameInitialization class.
     */
    public void setPlayerList(ArrayList<Player> playerList) { this.playerList = playerList; }


    /**
     * Set the Round.
     * @param round gotten Round.
     */
    public void setRound(Round round) { this.round = round; }

    /**
     * Get DevelopmentCardMarket.
     * @return DevelopmentCardMarket's instance.
     */
    public DevelopmentCardMarket getDevelopmentCardMarket() { return developmentCardMarket; }

    /**
     * Get MarketMarbleStructure.
     * @return MarketMarbleStructure's instance.
     */
    public MarketMarbleStructure getMarketMarbleStructure() { return marketMarbleStructure; }

    /**
     * Get a Tile in the Faith Track.
     * @param position is the wanted position.
     * @return the Tile related to the parameter position.
     */
    public TrackTiles getFaithTrack(int position) { return faithTrack[position]; }

    /**
     * Get PlayerList.
     * @return Player List.
     */
    public ArrayList<Player> getPlayerList() { return this.playerList; }

    /**
     * Get the Round.
     * @return Round.
     */
    public Round getRound() { return this.round; }

    /**
     * Move on FaithTrack of *movement* spaces.
     */
    public void moveFaithTrack(int movement, Player player) {
        for(int i = 0; i < movement; i ++) {
            if(player.getFaithTrackPosition() == 24) { return; }
            player.increasePlayerFaithTrackPosition();
            if(faithTrack[player.getFaithTrackPosition()].isPopeSpace()
                    && !faithTrack[player.getFaithTrackPosition()].isPopeSpaceAlreadyActivated()) {
                faithTrack[player.getFaithTrackPosition()].setPopeSpaceAlreadyActivated();
                activePopeFavorTiles();
            }
        }

        ArrayList<Integer> faithTrackPositions = new ArrayList<>(4);
        for(Player p : playerList) { faithTrackPositions.add(p.getFaithTrackPosition()); }

        FaithTrackChanges change = new FaithTrackChanges(getActivePlayer().getRoundPosition(), faithTrackPositions);
        for(Controller controller : controllers) { controller.notify(change); }
    }

    /**
     * Searching for players that are in the right reportSection when someone pass on the right Pope space.
     */
    private void activePopeFavorTiles() {
        TrackTiles.setPopeTilesReached();
        for(Player p : playerList) {
            forCyclePlayer:
            for(int i = 0; i < 3; i++) {
                if(faithTrack[p.getFaithTrackPosition()].isReportSection(i) && TrackTiles.getPopeTilesReached() - 1 == i) {
                    p.addVictoryPoints(i + 2);
                    break forCyclePlayer;
                }
            }
        }
    }

    /**
     * Used at the end of a Round to set the next active Player (in order).
     * If the game should end, this method will handle it automatically.
     */
    public void setNextActivePLayer() {
        if(!isFinalRounds) { checkForFinalRounds(); }
        boolean ended = false;

        if(!isSinglePlayerMode) {
            Player player = null;
            for (Player p : playerList) { if (p.isActivePlayer()) { player = p; break; } }

            //assert player != null; //player can't be null!! This is just for safe...
            player.isActivePlayer(false);
            if(player.getRoundPosition() != playerList.size() - 1) { playerList.get(player.getRoundPosition()+1).isActivePlayer(true); }
            else if(player.getRoundPosition() ==  playerList.size() - 1) {
                if(!isFinalRounds) { playerList.get(0).isActivePlayer(true); }
                else { ended = true; }
            }

            if(!ended) {
                EndTurnChanges change = new EndTurnChanges(player.getRoundPosition(), player.getName());
                for(Controller controller : controllers) { controller.notify(change); }
            }
            else {
                HashMap<String, Integer> pointsHashMap = createHashMapPoints();
                WinnerChanges change = new WinnerChanges(player.getRoundPosition(), WINNER, pointsHashMap, getWinner());
                for(Controller controller : controllers) { controller.notify(change); }
                LobbyManager.getLobbyInstance().removeLobby(this);
            }
        }
        else {
            if(isFinalRounds) {
                SinglePlayerWinnerChanges change = new SinglePlayerWinnerChanges(getActivePlayer().getRoundPosition(), SINGLE_PLAYER_WINNER,
                        createHashMapPoints(), false);
                for(Controller controller : controllers) { controller.notify(change); }
            }
            else {
                ActionToken token = singlePlayer.getActionTokens().get(singlePlayer.getActionTokens().size() - 1);
                singlePlayer.activeActionToken(this);

                if(checkForSinglePlayerWinner()) {
                    SinglePlayerWinnerChanges change = new SinglePlayerWinnerChanges(getActivePlayer().getRoundPosition(), SINGLE_PLAYER_WINNER,
                            createHashMapPoints(), true);
                    for(Controller controller : controllers) { controller.notify(change); }
                }
                else {
                    EndSinglePlayerTurnChanges change = new EndSinglePlayerTurnChanges(0,
                            token, singlePlayer.getFaithTrackPosition());
                    for(Controller controller : controllers) { controller.notify(change); }
                }
            }
        }

        if(getActivePlayer().getDisconnected()) { handleDisconnectedPlayer(); }
    }

    /**
     * Check if we got into the final rounds.
     */
    private void checkForFinalRounds() {
        for(Player player : playerList) {
            if(player.getPersonalBoard().getSumDevelopmentCard() == 7 || player.getFaithTrackPosition() == 24) {
                isFinalRounds = true;
                if(playerList.size() > 1) {
                    LastRoundsChanges change = new LastRoundsChanges(player.getRoundPosition(), player.getName());
                    for(Controller controller : controllers) { controller.notify(change); }
                }
            }
        }
    }

    /**
     * Check if Lorenzo the magnificent has won the game.
     */
    public boolean checkForSinglePlayerWinner() {
        int tot = 0;
        for(int j = 0; j < 4; j ++) {
            for(int i = 0; i < 3; i ++) { if(developmentCardMarket.getDevelopmentCardList(i, j).size() == 0) { tot ++; } }
            if(tot == 3) { return true; }
            tot = 0;
        }
        return (singlePlayer.getFaithTrackPosition() == 24);
    }

    /**
     * Create the hashmap player/points after the game is ended.
     */
    private HashMap<String, Integer> createHashMapPoints() {
        addPointsForResources();
        addPointsForFaithTrackPosition();

        HashMap<String, Integer> playerPointsHashMap = new HashMap<>();
        for(Player player : playerList) { playerPointsHashMap.put(player.getName(), player.getVictoryPoints()); }
        return playerPointsHashMap;
    }

    /** For each player adds a number of victory points equal to his resources / 5. */
    private void addPointsForResources() {
        for(Player player : playerList) { player.addVictoryPoints(player.getPersonalBoard().getNumResource() / 5); }
    }

    /** For each player adds a number of victory points based on his position in the faithTrack. */
    private void addPointsForFaithTrackPosition() {
        for(Player player : playerList) {
            switch (player.getFaithTrackPosition()) {
                case 3:
                case 4:
                case 5:
                    player.addVictoryPoints(1);
                    break;
                case 6:
                case 7:
                case 8:
                    player.addVictoryPoints(2);
                    break;
                case 9:
                case 10:
                case 11:
                    player.addVictoryPoints(4);
                    break;
                case 12:
                case 13:
                case 14:
                    player.addVictoryPoints(6);
                    break;
                case 15:
                case 16:
                case 17:
                    player.addVictoryPoints(9);
                    break;
                case 18:
                case 19:
                case 20:
                    player.addVictoryPoints(12);
                    break;
                case 21:
                case 22:
                case 23:
                    player.addVictoryPoints(16);
                    break;
                case 24:
                    player.addVictoryPoints(20);
                    break;
                default: player.addVictoryPoints(0);
            }
        }
    }

    /** Check who's the winner. */
    public String getWinner() {
        int victoryPointsMax = -1;
        Player player = new Player();
        for(Player p : playerList) {
            if(p.getVictoryPoints() > victoryPointsMax) {
                victoryPointsMax = p.getVictoryPoints();
                player = p;
            }
            else if(p.getVictoryPoints() == victoryPointsMax && p.getPersonalBoard().getNumResource() > player.getPersonalBoard().getNumResource()) {
                player = p;
            }
        }
        return player.getName();
    }

    /**
     * Returns the current active player.
     */
    public Player getActivePlayer() {
        Player player = new Player();
        for(Player p : playerList) { if(p.isActivePlayer()) { player = p; } }
        return player;
    }

    /**
     * Get initializedGame.
     */
    public GameInitialization getInitializedGame() { return initializedGame; }

    /**
     * Get singlePlayer.
     */
    public SinglePlayer getSinglePlayer() { return singlePlayer; }

    /**
     * Get isSinglePlayerMode.
     */
    public boolean isSinglePlayerMode() { return isSinglePlayerMode; }

    /**
     * Set isSinglePlayerMode.
     */
    public void setSinglePlayerMode(boolean singlePlayerMode) {
        isSinglePlayerMode = singlePlayerMode;
        singlePlayer = new SinglePlayer();
    }

    /**
     * Gets the disconnectedPlayerTimer.
     */
    public Timer getTimerDisconnections() { return timerDisconnections; }

    /**
     * Sets the disconnectedPlayerTimer.
     */
    public void setTimerDisconnections(Timer timerDisconnections) { this.timerDisconnections = timerDisconnections; }

    /**
     * Returns the lobby associated to this gameModel.
     */
    public Lobby getLobby() { return lobby; }

    /**
     * Set the lobby for this gameModel.
     */
    public void setLobby(Lobby lobby) { this.lobby = lobby; }

    /**
     * Add a controller in the controllers list.
     */
    public void addController(Controller controller) {
        if(controllers.size() == (playerList.size() - 1)) { controllers.add(controller); }
        else { System.out.println("ERROR GM 308"); }
    }

    /**
     * When a player reconnects to the game this method update his client with all the actions made since the start of the game.
     */
    public void sendReconnectionChange(Controller controller) {
        int pos = -1;
        if(isSinglePlayerMode) { pos = singlePlayer.getFaithTrackPosition(); }

        ReconnectedClientChanges change = new ReconnectedClientChanges(0, isSinglePlayerMode,
                developmentCardMarket.getAllCardsAsList(), marketMarbleStructure.getMarbleMarketString(), pos);

        change.setDevCardMarket(developmentCardMarket.getDevCardStructure());
        change.setMarbleMarket(marketMarbleStructure.getMarbleMarketString());

        for(Player player : playerList) {
            change.setPlayerMinimal(player.getRoundPosition(), player.getName(), player.getFaithTrackPosition(), player.getLeaderCardList(),
                    player.getPersonalBoard().getWarehouse().getResourcesString(), player.getPersonalBoard().strongBoxToString(),
                    player.getPersonalBoard().getDevelopmentCardSlots(), player.isActivePlayer());
        }

        //System.out.println("RECONNECTION MESSAGE: " + Parser.getInstance().serializeChanges(change));
        controller.notify(change);
    }

    /**
     * Sets a timer to handle disconnected players. If the timer runs out before the player reconnects, the player jumps his turn.
     */
    public void handleDisconnectedPlayer() {
        timerDisconnections = new Timer("DisconnectedPlayer");
        TimerTask task = new TimerTask() { public void run() { setNextActivePLayer(); } };
        timerDisconnections.schedule(task, this.delay);
    }
}