package it.polimi.ingsw.model;
import it.polimi.ingsw.model.tokens.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.LORENZO_THE_MAGNIFICENT;
import static org.junit.jupiter.api.Assertions.*;
public class SinglePlayerTest {

    GameModel gameModel;

    public void fillGameModel() {
        gameModel = new GameModel();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        gameModel.setPlayerList(players);
        gameModel.initializeTheGame();

        gameModel.setSinglePlayerMode(true);
    }

    /**
     * Testing the initialization of the Single Player
     */
    //@Test
    @Deprecated //This test works fine when the first token is no the moveAndShuffle (as it should be).
    public void tokensListTest() {
        fillGameModel();
        System.out.println(gameModel.getSinglePlayer().getActionTokens());
        assertEquals(7,gameModel.getSinglePlayer().getActionTokens().size());
        gameModel.getSinglePlayer().activeActionToken(gameModel);
        System.out.println(gameModel.getSinglePlayer().getActionTokens());
        assertEquals(6,gameModel.getSinglePlayer().getActionTokens().size());
        gameModel.getSinglePlayer().activeActionToken(gameModel);
        assertEquals(5,gameModel.getSinglePlayer().getActionTokens().size());
        System.out.println(gameModel.getSinglePlayer().getActionTokens());
    }

    /**
     * Testing colored tokens of the Single Player
     */
    @Test
    public void colorTokenTest() {
        fillGameModel();

        BlueActionToken blueActionToken = new BlueActionToken();
        YellowActionToken yellowActionToken = new YellowActionToken();

        assertEquals(4,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0,3).size());
        yellowActionToken.activeToken(gameModel);
        assertEquals(2,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0,3).size());
        yellowActionToken.activeToken(gameModel);
        assertEquals(0,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0,3).size());
        yellowActionToken.activeToken(gameModel);
        assertEquals(2,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(1,3).size());
        yellowActionToken.activeToken(gameModel);
        assertEquals(0,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(1,3).size());

        assertEquals(4,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0,2).size());
        blueActionToken.activeToken(gameModel);
        assertEquals(2,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0,2).size());
        blueActionToken.activeToken(gameModel);
        assertEquals(0,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(0,2).size());
        blueActionToken.activeToken(gameModel);
        assertEquals(2,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(1,2).size());
        //blueActionToken.activeToken(gameModel);
        //assertEquals(0,gameModel.getDevelopmentCardMarket().getDevelopmentCardList(1,2).size());
        /*CLI.developmentCardMarketGenerator(gameModel.getDevelopmentCardMarket());
        CLI.marketMarbleGenerator(gameModel.getMarketMarbleStructure());*/
    }

    /**
     * Testing movement token of the Single Player
     */
    @Test
    public void moveAndShuffleActionToken(){
        fillGameModel();
        ActionToken actionToken = new MoveAndShuffleActionToken();
        //gameModel.getSinglePlayer().setActionTokens();
        System.out.println(gameModel.getSinglePlayer().getActionTokens());
        assertEquals(0,gameModel.getSinglePlayer().getFaithTrackPosition());
        actionToken.activeToken(gameModel);
        System.out.println(gameModel.getSinglePlayer().getActionTokens());
        assertEquals(1,gameModel.getSinglePlayer().getFaithTrackPosition());
    }

    @Test
    public void moveActionToken(){
        fillGameModel();
        ActionToken actionToken = new MoveActionToken();
        //method that does nothing, just to see if test works
        //gameModel.getSinglePlayer().setActionTokens();
        assertEquals(0,gameModel.getSinglePlayer().getFaithTrackPosition());
        actionToken.activeToken(gameModel);
        assertEquals(2,gameModel.getSinglePlayer().getFaithTrackPosition());
    }
}
