package it.polimi.ingsw.model.colourMarble;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.resources.Stone;

/**
 * This class implements grey the marble
 */
public class Grey extends Marble {

    private Stone stone = new Stone();

    /**
     * Override in grey class of convertToResources method from Marble abstract class.
     */
    @Override
    public Resource convertToResources (GameModel gameModel){ return this.stone.getResource(); }

    @Override
    public String toString() {
        return "Gray";
    }
}
