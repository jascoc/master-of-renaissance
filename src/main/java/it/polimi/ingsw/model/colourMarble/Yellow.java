package it.polimi.ingsw.model.colourMarble;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.resources.Coin;

/**
 * This class implements the yellow marble
 */
public class Yellow extends Marble {

    private Coin coin = new Coin();

    /**
     * Override in yellow class of convertToResources method from Marble abstract class.
     */
    @Override
    public Resource convertToResources (GameModel gameModel){ return this.coin.getResource(); }

    @Override
    public String toString() {
        return "Yellow";
    }
}
