package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;


/**
 * Decorator for the DevelopmentCard class
 */
abstract class DevelopmentCardDecorator extends DevelopmentCard{

    /**
     * Constructor
     * @param lvl card lvl
     * @param colour card colour
     * @param cardPrice card price
     * @param victoryPoints card victory points
     * @param productionPrice production price
     */
    public DevelopmentCardDecorator(int lvl, String colour, ArrayList<String> cardPrice, int victoryPoints, ArrayList<String> productionPrice) { super(lvl, colour, cardPrice, victoryPoints, productionPrice); }

    /**
     * Constructor
     * @param lvl card lvl
     * @param colour card colour
     * @param cardPrice card price
     * @param victoryPoints card victory points
     * @param productionPrice production price
     * @param production the resource produced
     */
    public DevelopmentCardDecorator(int lvl, String colour, ArrayList<String> cardPrice, int victoryPoints, ArrayList<String> productionPrice, ArrayList<String> production) { super(lvl, colour, cardPrice, victoryPoints, productionPrice, production); }


    @Override
    public String toString() { return super.toString(); }

    abstract List<Resource> activeProduction(GameModel gameModel, Shield shield);

    abstract List<Resource> activeProduction(GameModel gameModel,Servant servant);

    abstract List<Resource> activeProduction(GameModel gameModel,Coin coin);

    abstract List<Resource> activeProduction(GameModel gameModel,Stone stone);

    abstract List<Resource> activeProduction(GameModel gameModel,Stone stone,Servant servant);

    abstract List<Resource> activeProduction(GameModel gameModel,Coin coin,Shield shield);

    abstract List<Resource> activeProduction(GameModel gameModel,Stone stone,Shield shield);

    abstract List<Resource> activeProduction(GameModel gameModel,Coin coin,Servant servant);

    abstract List<Resource> activeProduction(GameModel gameModel,Shield shield1,Shield shield2);

    abstract List<Resource> activeProduction(GameModel gameModel,Servant servant1,Servant servant2);

    abstract List<Resource> activeProduction(GameModel gameModel,Stone stone1,Stone stone2);

    abstract List<Resource> activeProduction(GameModel gameModel,Coin coin1,Coin coin2);

    abstract List<Resource> activeProduction(GameModel gameModel, Shield shield, Servant servant);

    abstract List<Resource> activeProduction(GameModel gameModel, Coin coin, Stone stone);

    /**
     * writing the same method but with the signature inverted (eg. from Coin coin, Stone stone to Stone stone, Coin coin)
     */
    abstract List<Resource> activeProduction(GameModel gameModel, Servant servant, Stone stone);

    abstract List<Resource> activeProduction(GameModel gameModel, Shield shield, Coin coin);

    abstract List<Resource> activeProduction(GameModel gameModel, Shield shield, Stone stone);

    abstract List<Resource> activeProduction(GameModel gameModel, Servant servant, Coin coin);

    abstract List<Resource> activeProduction(GameModel gameModel, Servant servant, Shield shield);

    abstract List<Resource> activeProduction(GameModel gameModel, Stone stone, Coin coin);


}


