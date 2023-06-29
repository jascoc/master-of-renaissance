package it.polimi.ingsw.network.server;

import it.polimi.ingsw.SetTestAdvantages;
import it.polimi.ingsw.changes.ConnectionPlayerChanges;
import it.polimi.ingsw.changes.NickNameTakenChanges;
import it.polimi.ingsw.changes.PlayerWaitingChanges;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.Variables.*;

public class LobbyManager {

    private static ArrayList<Lobby> lobbies;
    private static LobbyManager lobbyInstance = null;
    private static HashMap<String, Player> playerNicknameHashMap;

    private LobbyManager() { }

    /**
     * LobbyManager is a singleton class.
     */
    public static LobbyManager getLobbyInstance() {
        if(lobbyInstance == null) { lobbyInstance = new LobbyManager(); }
        return lobbyInstance;
    }

    public void setLobbyInstance() {
        lobbies = new ArrayList<>();
        playerNicknameHashMap = new HashMap<>();
    }

    /**
     * Add Player method handle a new connection. If the player is disconnected starts the reconnection, if his name is already taken it send a message
     * to the client, if it's all correct the player is added to a lobby, and if the lobby doesn't exists it creates it.
     */
    public void addPlayer(Player player, int numberOfPlayers) {
        if(playerNicknameHashMap.containsKey(player.getName())) {
            if(playerNicknameHashMap.get(player.getName()).getDisconnected()) {
                player.getController().notify(new ConnectionPlayerChanges(-1, player.getName(), PLAYER_CONNECTED));
                Lobby playerLobby = getLobbyOfPlayer(player);
                playerNicknameHashMap.get(player.getName()).isDisconnected(false);
                reconnectPlayer(player, playerLobby);
            }
            else { player.getController().notify(new NickNameTakenChanges(-1)); }
        }
        else {
            playerNicknameHashMap.put(player.getName(), player);

            if(numberOfPlayers == 1) { createSinglePlayerGame(player); }
            else {
                if(lobbies.size() == 0) {
                    lobbies.add(new Lobby(player, numberOfPlayers));
                    player.getController().notify(new PlayerWaitingChanges(0, player.getName()));
                    return;
                }
                for (Lobby l: lobbies) {
                    if(!l.isStartedGame()) {
                        if(l.getPlayers().size() < l.getNumberOfPlayers() && l.getNumberOfPlayers() == numberOfPlayers) {
                            l.getPlayers().add(player);

                            for(Player p : l.getPlayers()) {
                                p.getController().notify(new PlayerWaitingChanges(l.getPlayers().size() - 1, player.getName()));
                            }

                            if(l.getPlayers().size() == l.getNumberOfPlayers())
                                createTheGame(l);
                            return;
                        }
                    }
                }
                lobbies.add(new Lobby(player, numberOfPlayers));
                player.getController().notify(new PlayerWaitingChanges(0, player.getName()));
            }
        }
    }

    /**
     * Initialize a standard game for this lobby.
     */
    private void createTheGame(Lobby lobby) {
        GameModel gameModel = new GameModel();
        gameModel.setPlayerList(lobby.getPlayers());
        SetTestAdvantages adv = new SetTestAdvantages();

        for(Player player : gameModel.getPlayerList()) {
            player.getController().setGameModel(gameModel);
            gameModel.getControllers().add(player.getController());
        }
        gameModel.initializeTheGame();

        //TODO Delete comments below to get initial advantages while playing. Used for tests.
        //for(Player player : gameModel.getPlayerList()) { adv.addAdvantages(gameModel, player); }


        gameModel.setLobby(lobby);
        lobby.setGameModel(gameModel);
        lobby.setStartedGame(true);
    }

    /**
     * Initialize a singlePlayer game.
     */
    private void createSinglePlayerGame(Player player) {
        GameModel gameModel = new GameModel();
        gameModel.setSinglePlayerMode(true);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        gameModel.setPlayerList(players);
        player.getController().setGameModel(gameModel);
        gameModel.getControllers().add(player.getController());

        Lobby lobby = new Lobby(player, 1);
        lobbies.add(lobby);
        lobby.setGameModel(gameModel);
        gameModel.setLobby(lobby);
        gameModel.initializeTheGame();

        lobby.setStartedGame(true);
    }

    /**
     * Gets the playerNicknameHashmap.
     */
    public HashMap<String, Player> getPlayerNicknameHashMap() { return getPlayerNicknameHashMap(); }

    /**
     * Handle the player reconnection.
     */
    private void reconnectPlayer(Player newPlayer, Lobby lobby) {
        GameModel gameModel = lobby.getGameModel();
        gameModel.getTimerDisconnections().cancel();

        ConnectionPlayerChanges change = new ConnectionPlayerChanges(-1, newPlayer.getName(), PLAYER_CONNECTED);
        for(Controller controller : gameModel.getControllers()) { controller.notify(change); }

        gameModel.addController(newPlayer.getController());
        newPlayer.getController().setGameModel(gameModel);
        gameModel.sendReconnectionChange(newPlayer.getController());
    }

    private Lobby getLobbyOfPlayer(Player newPlayer) {
        for(Lobby lobby : lobbies) { for(Player player : lobby.getPlayers()) { if(player.getName().equals(newPlayer.getName())) { return lobby; } } }
        return null;
    }

    public void removeLobby(GameModel gameModel) {
        for(Player player : gameModel.getPlayerList()) { playerNicknameHashMap.remove(player.getName()); }
        lobbies.removeIf((lobby) -> lobby.getGameModel() == gameModel);
    }
}