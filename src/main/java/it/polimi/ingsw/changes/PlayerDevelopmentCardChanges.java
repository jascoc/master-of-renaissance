package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.network.client.Minimal.DevelopmentCardMinimal;
import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of the development card slots in the personal board
 */
public class PlayerDevelopmentCardChanges extends Changes {

    private int columnChanged;
    private DevelopmentCardMinimal devCardBought;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param columnChanged which slot in the personal board
     * @param devCardBought development card bought
     */
    public PlayerDevelopmentCardChanges(int playerRoundNumber, int columnChanged, DevelopmentCard devCardBought) {
        super(playerRoundNumber, PLAYER_DEVELOPMENT_CARD_CHANGES);
        this.columnChanged = columnChanged;
        this.devCardBought = devCardToMinimal(devCardBought);
    }

    /**
     * Converts the development card into a minimal type
     * @param devCardBought development card bought
     * @return development card minimal
     */
    private DevelopmentCardMinimal devCardToMinimal(DevelopmentCard devCardBought) {
        return new DevelopmentCardMinimal(devCardBought.getLvl(), devCardBought.getColour(), devCardBought.getCardPrice(), devCardBought.getVictoryPoints(), devCardBought.getProductionPrice(), devCardBought.getProduction());
    }

    /**
     * Getters and Setters
     * @return
     */
    public int getColumnChanged() { return columnChanged; }

    public void setColumnChanged(int columnChanged) { this.columnChanged = columnChanged; }

    public DevelopmentCardMinimal getDevCardBought() { return devCardBought; }

    public void setDevCardBought(DevelopmentCardMinimal devCardBought) { this.devCardBought = devCardBought; }

}
