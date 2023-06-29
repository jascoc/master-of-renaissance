package it.polimi.ingsw.model.colourMarble;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.resources.Servant;

/**
 * This class implements the purple marble
 */
public class Purple extends Marble {

    private Servant servant = new Servant();

    /**
     * Override in purple class of convertToResources method from Marble abstract class.
     */
    @Override
    public Resource convertToResources (GameModel gameModel){ return this.servant.getResource(); }

    @Override
    public String toString() {
        return "Purple";
    }
}
