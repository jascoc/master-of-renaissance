package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.network.client.Minimal.DevelopmentCardMinimal;
import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the development card change
 */
public class DevelopmentCardStructureChanges extends Changes {

    private int row;
    private int column;
    private DevelopmentCardMinimal devCardTaken;

    /**
     * constructor
     * @param playerRoundNumber the position of the player in the round
     * @param row in the market
     * @param column in the market
     * @param devCardTaken the card bought
     */
    public DevelopmentCardStructureChanges(int playerRoundNumber, int row, int column, DevelopmentCard devCardTaken) {
        super(playerRoundNumber, DEVELOPMENT_CARD_MARKET_CHANGES);
        this.row = row;
        this.column = column;
        this.devCardTaken = developmentCardToMinimal(devCardTaken);
    }

    /**
     * initialize the a MINIMAL OBJECT
     * @param dev development card
     * @return development card minimal which is a development card with only setters and getters
     */
    private DevelopmentCardMinimal developmentCardToMinimal(DevelopmentCard dev) {
        return new DevelopmentCardMinimal(dev.getLvl(),dev.getColour(),dev.getCardPrice(),dev.getVictoryPoints(), dev.getProductionPrice(), dev.getProduction());
    }

    /**
     * Getters and Setters
     */
    public int getRow() { return row; }

    public void setRow(int row) { this.row = row; }

    public int getColumn() { return column; }

    public void setColumn(int column) { this.column = column; }

    public DevelopmentCardMinimal getDevCardTaken() { return devCardTaken; }

    public void setDevCardTaken(DevelopmentCardMinimal devCardTaken) { this.devCardTaken = devCardTaken; }
}
