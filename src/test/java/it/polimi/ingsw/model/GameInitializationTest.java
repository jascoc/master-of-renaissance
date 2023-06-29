package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.model.DevelopmentCardMarket;
import it.polimi.ingsw.model.GameInitialization;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import org.junit.jupiter.api.Test;

public class GameInitializationTest {

    private GameModel gameModel = new GameModel();
    private GameInitialization initializeGame = new GameInitialization();

    @Test
    public void tryLeaderCardParser(){
        ArrayList<LeaderCard> leaderCards = initializeGame.generateLeaderCards();
        leaderCards.forEach(System.out::println);
    }


    /**
     * IT'S WORKINGGGGGGG
     */
    @Test
    public void tryDevelopmentCardParser(){
        DevelopmentCardMarket developmentCardMarket = initializeGame.setDevelopmentCardMarket();
    }

}
