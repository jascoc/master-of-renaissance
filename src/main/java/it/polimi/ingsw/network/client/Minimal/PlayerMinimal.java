package it.polimi.ingsw.network.client.Minimal;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TrackTiles;

import java.util.ArrayList;

/**
 * Minimal class are simplified version, with only getter and setters
 * of the classes in the model, they are used in the client to not have
 * any side effect
 */
public class PlayerMinimal {

    private String playerName;
    private int playerRoundNumber;
    private int victoryPoints;

    private ArrayList<LeaderCardMinimal> leaderCards;
    private ArrayList<DevelopmentCardMinimal>[] developmentCards;
    private WarehouseMinimal warehouse;
    private ArrayList<String> strongbox;
    private int playerFaithPosition;
    private boolean active;
    private boolean singlePlayer;

    /**
     * Empty Constructor
     */
    public PlayerMinimal() { }

    /**
     * Constructor
     * @param playerName player nickname
     * @param playerRoundNumber player position in the round
     * @param leaderCards leader card minimal list
     * @param isActive current playing player
     */
    public PlayerMinimal(String playerName, int playerRoundNumber, ArrayList<LeaderCardMinimal> leaderCards, boolean isActive) {
        this.playerName = playerName;
        this.playerRoundNumber = playerRoundNumber;
        this.leaderCards = leaderCards;
        this.warehouse = new WarehouseMinimal();
        this.strongbox = new ArrayList<>();
        this.developmentCards = new ArrayList[3];
        for(int i = 0; i < 3; i ++) { this.developmentCards[i] = new ArrayList<>(3); }
        this.active = isActive;
    }

    /**
     * Getters and Setters
     */
    public PlayerMinimal(String playerName) { this.playerName = playerName; }

    public String getPlayerName() { return playerName; }

    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public ArrayList<LeaderCardMinimal> getLeaderCards() { return leaderCards; }

    public WarehouseMinimal getWarehouse() { return warehouse; }

    public ArrayList<String> getStrongbox() { return strongbox; }

    public int getPlayerFaithPosition() { return playerFaithPosition; }

    public void setPlayerFaithPosition(int playerFaithPosition) { this.playerFaithPosition = playerFaithPosition; }

    public int getPlayerRoundNumber() { return playerRoundNumber; }

    public void setPlayerRoundNumber(int playerRoundNumber) { this.playerRoundNumber = playerRoundNumber; }

    public ArrayList<DevelopmentCardMinimal>[] getDevelopmentCards() { return developmentCards; }

    public void setDevelopmentCards(ArrayList<DevelopmentCardMinimal>[] developmentCards) { this.developmentCards = developmentCards; }

    public ArrayList<DevelopmentCardMinimal> getDevelopmentCardList(int columnWanted) { return this.developmentCards[columnWanted]; }

    public void setStrongbox(ArrayList<String> strongbox) { this.strongbox = strongbox; }

    public void setLeaderCards(ArrayList<LeaderCardMinimal> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public void setWarehouse(WarehouseMinimal warehouse) { this.warehouse = warehouse; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSinglePlayer() { return singlePlayer; }

    public void setSinglePlayer(boolean singlePlayer) { this.singlePlayer = singlePlayer; }

    public int getVictoryPoints() { return victoryPoints; }

    public void setVictoryPoints(int victoryPoints) {this.victoryPoints = victoryPoints; }

    @Override
    public String toString() {
        return "Name: " + playerName + ", roundNumber: " + playerRoundNumber + "isActive? " + isActive();
    }
}
