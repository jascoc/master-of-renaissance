package it.polimi.ingsw.model.colourMarble;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.resources.Shield;

/**
 * This class implements the blue marble
 */
public class Blue extends Marble {

    private Shield shield = new Shield();

    /**
     * Override in blue class of convertToResources method from Marble abstract class.
     */
    @Override
    public Resource convertToResources(GameModel gameModel) { return this.shield.getResource(); }

    @Override
    public String toString() {
        return "Blue";
    }

}
