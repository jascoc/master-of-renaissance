package it.polimi.ingsw.model.colourMarble;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.resources.uselessResources.RedCross;

/**
 * This class implements the red marble
 */
public class Red extends Marble {

    private RedCross RedCross = new RedCross();

    /**
     * Override in red class of convertToResources method from Marble abstract class, Red Marble moves the faithTrack.
     */
    @Override
    public Resource convertToResources(GameModel gameModel) {
            gameModel.moveFaithTrack(1, gameModel.getActivePlayer());
            //gameModel.moveFaithTrack(24, gameModel.getActivePlayer()); //FOR QUICK TESTS.
            return this.RedCross;
    }

    @Override
    public String toString() { return "Red"; }
}