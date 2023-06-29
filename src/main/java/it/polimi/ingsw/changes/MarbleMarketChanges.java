package it.polimi.ingsw.changes;

import java.util.ArrayList;
import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of the marble market
 */
public class MarbleMarketChanges extends Changes {

    private ArrayList<String> marbleMarket;

    public MarbleMarketChanges(int playerRoundNumber, ArrayList<String> marbleMarket) {
        super(playerRoundNumber, MARBLE_MARKET_CHANGES);
        this.marbleMarket = marbleMarket;
    }

    public ArrayList<String> getMarbleMarket() { return marbleMarket; }

}
