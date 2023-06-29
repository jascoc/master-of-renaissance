package it.polimi.ingsw.model.colourMarble;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.resources.uselessResources.UselessWhite;

/**
 * This class implements the white marble
 */
public class White extends Marble {

    /**
     * Override in white class of convertToResources method from Marble abstract class, White Marbles do nothing.
     */
    @Override
    public Resource convertToResources(GameModel gameModel) {
        return new UselessWhite();
    }

    @Override
    public String toString() {
        return "White";
    }
}
