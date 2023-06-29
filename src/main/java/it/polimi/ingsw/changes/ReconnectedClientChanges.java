package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.network.client.Minimal.DevelopmentCardMinimal;
import it.polimi.ingsw.network.client.Minimal.LeaderCardMinimal;
import it.polimi.ingsw.network.client.Minimal.PlayerMinimal;
import it.polimi.ingsw.network.client.Minimal.WarehouseMinimal;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.RECONNECTED_CLIENT;

/**
 * this Class is used to notify the view of the evolution of the reconnection
 */
public class ReconnectedClientChanges extends Changes {

    private ArrayList<DevelopmentCardMinimal> devCardMarket;
    private ArrayList<DevelopmentCardMinimal>[][] devCardsMarketStructure;
    private ArrayList<String> marbleMarket;
    private ArrayList<PlayerMinimal> players;
    private int singlePlayerFaithPosition;
    private boolean isSinglePlayer;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param isSinglePlayer single player mode
     * @param devCardMarket development card market
     * @param marbleMarket marble market
     * @param singlePlayerFaithPosition single player faith track position
     */
    public ReconnectedClientChanges(int playerRoundNumber, boolean isSinglePlayer, ArrayList<DevelopmentCard> devCardMarket,
                                    ArrayList<String> marbleMarket, int singlePlayerFaithPosition) {
        super(playerRoundNumber, RECONNECTED_CLIENT);
        this.isSinglePlayer = isSinglePlayer;
        this.singlePlayerFaithPosition = singlePlayerFaithPosition;
        players = new ArrayList<>();
    }

    /**
     * Converts player into a minimal one
     * @param playerRoundNumber position in the round
     * @param name nickname
     * @param playerFaithPosition faith track position
     * @param leaderCards leader card list as a string
     * @param warehouse warehouse as a list of string
     * @param strongbox strongbox as a list of string
     * @param devCards development card
     * @param isActive current playing player
     */
    public void setPlayerMinimal(int playerRoundNumber, String name, int playerFaithPosition,
                                 ArrayList<LeaderCard> leaderCards, ArrayList<String> warehouse, ArrayList<String> strongbox,
                                 ArrayList<DevelopmentCard>[] devCards, boolean isActive) {
        PlayerMinimal player = new PlayerMinimal();
        player.setPlayerName(name);
        player.setPlayerRoundNumber(playerRoundNumber);
        player.setActive(isActive);
        player.setPlayerFaithPosition(playerFaithPosition);

        player.setLeaderCards(createLeaderCardsMinimal(leaderCards));
        player.setWarehouse(createWarehouseMinimal(warehouse));
        player.setStrongbox(strongbox);
        player.setDevelopmentCards(createDevCardsMinimal(devCards));

        players.add(player);
    }

    /**
     * Converts
     * @param warehouse form Warehouse type into
     * @return Minimal one
     */
    private WarehouseMinimal createWarehouseMinimal(ArrayList<String> warehouse) {
        WarehouseMinimal warehouseMinimal = new WarehouseMinimal();
        ArrayList<String> middle = new ArrayList<>();
        ArrayList<String> bottom = new ArrayList<>();
        middle.add(warehouse.get(1));
        middle.add(warehouse.get(2));
        bottom.add(warehouse.get(3));
        bottom.add(warehouse.get(4));
        bottom.add(warehouse.get(5));

        warehouseMinimal.setTop(warehouse.get(0));
        warehouseMinimal.setMiddle(middle);
        warehouseMinimal.setBottom(bottom);

        return warehouseMinimal;
    }

    /**
     * Converts
     * @param leaderCards from LeaderCard type into
     * @return Minimal one
     */
    private ArrayList<LeaderCardMinimal> createLeaderCardsMinimal(ArrayList<LeaderCard> leaderCards) {
        ArrayList<LeaderCardMinimal> leaderCardsMinimal = new ArrayList<>();
        String reqResource = null;
        ArrayList<DevelopmentCardMinimal> devCards = null;
        for(LeaderCard card : leaderCards) {
            if(card.getRequiredResource() != null) { reqResource = card.getRequiredResource().toString(); }
            if(card.getRequiredDevCards() != null) { devCards = convertDevCardsToMinimal(card.getRequiredDevCards()); }

            LeaderCardMinimal newLeaderMinimal = new LeaderCardMinimal(card.getAbility(), card.getResourceUsed().toString(), card.getVictoryPoints(),
                    reqResource, devCards);
            if(card.getActivated()) { newLeaderMinimal.setActivated(true); }
            leaderCardsMinimal.add(newLeaderMinimal);
        }
        return leaderCardsMinimal;
    }

    /**
     * Converts
     * @param devCards from DevelopmentCard type into
     * @return Minimal one
     */
    private ArrayList<DevelopmentCardMinimal>[] createDevCardsMinimal(ArrayList<DevelopmentCard>[] devCards) {
        ArrayList<DevelopmentCardMinimal>[] devCardsMinimal = new ArrayList[3];
        for(int i = 0; i < 3; i ++) { devCardsMinimal[i] = convertDevCardsToMinimal(devCards[i]); }
        return devCardsMinimal;
    }

    /**
     * Initialize
     * @param devCardMarket
     */
    public void setDevCardMarket(ArrayList<DevelopmentCard>[][] devCardMarket) {
        devCardsMarketStructure = new ArrayList[3][4];
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 4; j ++) {
                devCardsMarketStructure[i][j] = new ArrayList<>(convertDevCardsToMinimal(devCardMarket[i][j]));
            }
        }
    }

    /**
     * Converts
     * @param devCards array list of arrays into
     * @return array list
     */
    private ArrayList<DevelopmentCardMinimal> convertDevCardsToMinimal(ArrayList<DevelopmentCard> devCards) {
        ArrayList<DevelopmentCardMinimal> devCardMinimals = new ArrayList<>();
        for(DevelopmentCard card : devCards) {
            devCardMinimals.add(new DevelopmentCardMinimal(card.getLvl(), card.getColour(), card.getCardPrice(), card.getVictoryPoints(),
                    card.getProductionPrice(), card.getProduction()));
        }
        return devCardMinimals;
    }

    /**
     * Getters and Setters
     */
    public ArrayList<String> getMarbleMarket() { return marbleMarket; }

    public void setMarbleMarket(ArrayList<String> marbleMarket) { this.marbleMarket = marbleMarket; }

    public ArrayList<PlayerMinimal> getPlayers() { return players; }

    public void setPlayers(ArrayList<PlayerMinimal> players) { this.players = players; }

    public boolean isSinglePlayer() { return isSinglePlayer; }

    public void setSinglePlayer(boolean singlePlayer) { isSinglePlayer = singlePlayer; }

    public ArrayList<DevelopmentCardMinimal>[][] getDevCardsMarketStructure() { return devCardsMarketStructure; }

    public void setDevCardsMarketStructure(ArrayList<DevelopmentCardMinimal>[][] devCardsMarketStructure) {
        this.devCardsMarketStructure = devCardsMarketStructure;
    }

    public ArrayList<DevelopmentCardMinimal> getDevCardMarket() { return devCardMarket; }

    public void setDevCardMarket(ArrayList<DevelopmentCard> devCardMarket) { this.devCardMarket = convertDevCardsToMinimal(devCardMarket); }

    public int getSinglePlayerFaithPosition() { return singlePlayerFaithPosition; }

    public void setSinglePlayerFaithPosition(int singlePlayerFaithPosition) { this.singlePlayerFaithPosition = singlePlayerFaithPosition; }

}