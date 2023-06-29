package it.polimi.ingsw.model;

/**
 * Abstract class Marble to model marbles used in the market structure.
 */
public abstract class Marble {

    /**
     * This method will be Overloaded in subclasses methods
     */
    public abstract Resource convertToResources(GameModel gameModel);
}
