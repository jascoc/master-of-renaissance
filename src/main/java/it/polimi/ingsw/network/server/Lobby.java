package it.polimi.ingsw.network.server;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import java.util.ArrayList;

public class Lobby {

    private ArrayList<Player> players;
    private int numberOfPlayers;
    private boolean startedGame = false;
    private GameModel gameModel;

    public Lobby(Player player, int numberOfPlayers) {
        players = new ArrayList<>();
        players.add(player);
        this.numberOfPlayers = numberOfPlayers;
    }

    public ArrayList<Player> getPlayers() { return players; }

    public int getNumberOfPlayers() { return numberOfPlayers; }

    public boolean isStartedGame() { return startedGame; }

    public void setStartedGame(boolean startedGame) { this.startedGame = startedGame; }

    public GameModel getGameModel() { return gameModel; }

    public void setGameModel(GameModel gameModel) { this.gameModel = gameModel; }

}
