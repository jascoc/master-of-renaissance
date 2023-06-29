package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.network.client.Minimal.DevelopmentCardMinimal;
import it.polimi.ingsw.network.client.Minimal.LeaderCardMinimal;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of the leader card
 */
public class LeaderCardChanges extends Changes {

    private int leaderCardInvolved;
    private LeaderCardMinimal leaderCard;
    private String whatHappened;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param leaderCardInvolved num of leader cards
     * @param leaderCard leader card
     * @param whatHappened event log
     */
    public LeaderCardChanges(int playerRoundNumber, int leaderCardInvolved, LeaderCard leaderCard, String whatHappened) {
        super(playerRoundNumber, LEADER_CARD_CHANGES);
        this.leaderCardInvolved = leaderCardInvolved;
        this.leaderCard = transformLeaderCard(leaderCard);
        this.leaderCard.setDiscarded(true);
        this.whatHappened = whatHappened;
    }

    /**
     * Transform a LeaderCard into a LeaderCardMinimal.
     */
    private LeaderCardMinimal transformLeaderCard(LeaderCard card) {
        String reqResource = null;
        ArrayList<DevelopmentCardMinimal> devCards = null;
            if(card.getRequiredResource() != null) { reqResource = card.getRequiredResource().toString(); }
            if(card.getRequiredDevCards() != null) { devCards = devCardToMinimals(card.getRequiredDevCards()); }
            return new LeaderCardMinimal(card.getAbility(), card.getResourceUsed().toString(), card.getVictoryPoints(),
                    reqResource, devCards);
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
     * Getters and Setters
     */
    public int getLeaderCardInvolved() { return leaderCardInvolved; }

    public LeaderCardMinimal getLeaderCard() { return leaderCard; }

    public void setLeaderCard(LeaderCardMinimal leaderCard) { this.leaderCard = leaderCard; }

    public String getWhatHappened() { return whatHappened; }

    public void setWhatHappened(String whatHappened) { this.whatHappened = whatHappened; }
}
