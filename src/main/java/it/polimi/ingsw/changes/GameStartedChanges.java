package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.network.client.Minimal.*;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the start of the game
 */
public class GameStartedChanges extends Changes {

    private ArrayList<PlayerMinimal> players;
    private DevelopmentCardMarketMinimal devCardMarket;
    private MarbleMarketMinimal marbleMarket;
    private boolean isSinglePlayerMode;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param players list of players
     * @param devCardMarket development card minimal
     * @param marbleMarket marble market minimal
     */
    public GameStartedChanges(int playerRoundNumber, ArrayList<PlayerMinimal> players,
                              DevelopmentCardMarketMinimal devCardMarket, MarbleMarketMinimal marbleMarket) {
        super(playerRoundNumber, GAME_STARTED);
        this.players = players;
        this.devCardMarket = devCardMarket;
        this.marbleMarket = marbleMarket;
    }

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param devCardMarket development market
     * @param marbleMarket marble market
     * @param players players list
     * @param isSinglePlayer if is single player mode
     */
    public GameStartedChanges(int playerRoundNumber, ArrayList<DevelopmentCard> devCardMarket, ArrayList<Marble> marbleMarket,
                              ArrayList<Player> players, boolean isSinglePlayer) {
        super(playerRoundNumber, GAME_STARTED);
        this.devCardMarket = createDevCardStructure(devCardMarket);
        this.marbleMarket = createMarbleMarket(marbleMarket);
        this.players = createPlayerMinimalList(players);
        this.isSinglePlayerMode = isSinglePlayer;
    }

    /**
     * Create a DevelopmentCardMarketMinimal out of an ArrayList of DevelopmentCards.
     */
    private DevelopmentCardMarketMinimal createDevCardStructure(ArrayList<DevelopmentCard> devCardMarket) {
        return new DevelopmentCardMarketMinimal(devCardToMinimals(devCardMarket));
    }

    /**
     * Create a MarbleMarketMinimal out of an ArrayList of Strings.
     */
    private MarbleMarketMinimal createMarbleMarket(ArrayList<Marble> marbleMarket) {
        ArrayList<String> marbleMarketString = new ArrayList<>(13);
        for(Marble marble : marbleMarket) {
            marbleMarketString.add(marble.toString());
        }
        return new MarbleMarketMinimal(marbleMarketString);
    }

    /**
     * Create a PlayerMinimal ArrayList out of a Player ArrayList.
     */
    private ArrayList<PlayerMinimal> createPlayerMinimalList(ArrayList<Player> players) {
        ArrayList<PlayerMinimal> playersMinimal = new ArrayList<>();
        for(Player player : players) {
            playersMinimal.add(new PlayerMinimal(player.getName(), player.getRoundPosition(), transformLeaderCards(player.getLeaderCardList()), player.isActivePlayer()));
        }
        return playersMinimal;
    }

    /**
     * Transform an ArrayList of LeaderCard in an ArrayList of LeaderCardMinimal.
     */
    private ArrayList<LeaderCardMinimal> transformLeaderCards(ArrayList<LeaderCard> leaderCards) {
        ArrayList<LeaderCardMinimal> leaderCardsMinimal = new ArrayList<>();
        String reqResource = null;
        ArrayList<DevelopmentCardMinimal> devCards = null;
        for(LeaderCard card : leaderCards) {
            if(card.getRequiredResource() != null) { reqResource = card.getRequiredResource().toString(); }
            if(card.getRequiredDevCards() != null) { devCards = devCardToMinimals(card.getRequiredDevCards()); }

                leaderCardsMinimal.add(new LeaderCardMinimal(card.getAbility(), card.getResourceUsed().toString(), card.getVictoryPoints(),
                    reqResource, devCards));
        }
        return leaderCardsMinimal;
    }

    /**
     * Transform an ArrayList of DevelopmentCards in an ArrayList of DevelopmentCardMinimal.
     */
    private ArrayList<DevelopmentCardMinimal> devCardToMinimals(ArrayList<DevelopmentCard> devCards) {
        ArrayList<DevelopmentCardMinimal> devCardMinimals = new ArrayList<>();
        for(DevelopmentCard card : devCards) {
            devCardMinimals.add(new DevelopmentCardMinimal(card.getLvl(), card.getColour(), card.getCardPrice(), card.getVictoryPoints(),
                    card.getProductionPrice(), card.getProduction()));
        }
        return devCardMinimals;
    }


    /**
     * Setter e Getter.
     */
    public ArrayList<PlayerMinimal> getPlayers() { return players; }

    public void setPlayers(ArrayList<PlayerMinimal> players) { this.players = players; }

    public DevelopmentCardMarketMinimal getDevCardMarket() { return devCardMarket; }

    public void setDevCardMarket(DevelopmentCardMarketMinimal devCardMarket) { this.devCardMarket = devCardMarket; }

    public MarbleMarketMinimal getMarbleMarket() { return marbleMarket; }

    public void setMarbleMarket(MarbleMarketMinimal marbleMarket) { this.marbleMarket = marbleMarket; }

    public boolean isSinglePlayerMode() { return isSinglePlayerMode; }

    public void setSinglePlayerMode(boolean singlePlayerMode) { isSinglePlayerMode = singlePlayerMode; }

}