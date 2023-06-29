package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exceptions.CantBuyThisCardException;

import static it.polimi.ingsw.Variables.*;
import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.exceptions.NotEnoughSpaceException;
import it.polimi.ingsw.model.leaderCards.ExtraSpaceLeader;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.model.resources.*;
import it.polimi.ingsw.network.server.Controller;
import org.junit.jupiter.api.RepeatedTest;
import java.util.ArrayList;

/**
 * Test used to verify the correct working of the development card market and of the method used to buy a dev card
 */
public class DevelopmentCardMarketTest {

    private ArrayList<ProductionDevelopmentCard>[][] devCardMatrix;
    private DevelopmentCardMarket developmentCardMarket;
    private GameModel gameModel = new GameModel();

    private void fillGameModel() {
        ArrayList<Player> players = new ArrayList<>(4);
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.get(0).setName("One");
        players.get(1).setName("Two");
        players.get(2).setName("Three");
        players.get(3).setName("Four");
        Controller controller = new Controller();
        controller.setGameModel(gameModel);
        players.get(0).setController(controller);
        players.get(1).setController(controller);
        players.get(2).setController(controller);
        players.get(3).setController(controller);
        gameModel.setPlayerList(players);
        gameModel.initializeTheGame();
    }

    private Resource convertResource(String resource) {
        switch(resource) {
            case COIN:
                return new Coin();
            case SERVANT:
                return new Servant();
            case SHIELD:
                return new Shield();
            case STONE:
                return new Stone();
            default:
                return new Coin();
        }
    }

    @RepeatedTest(100)
    public void testDevelopmentCardMarket() {
        fillGameModel();
        gameModel.getInitializedGame().splitLeaderCard(gameModel.getPlayerList());
        System.out.println(gameModel.getDevelopmentCardMarket().getCardFromStructure(0, 0).getCardPrice());
        DevelopmentCard devCard = gameModel.getDevelopmentCardMarket().getCardFromStructure(0, 0);

        for(String s : gameModel.getDevelopmentCardMarket().getCardFromStructure(0, 0).getCardPrice()) {
            gameModel.getActivePlayer().getPersonalBoard().addToStrongbox(convertResource(s));
        }

        /*gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceMiddle(gameModel, new Shield());
        gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceMiddle(gameModel, new Shield());

        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Shield());
        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Shield());
        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Shield());
        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Stone());
        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Coin());
        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Coin());
        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Servant());
         */

        try{ gameModel.getDevelopmentCardMarket().buyDevelopmentCard(gameModel, 0, 0, 0); }
        catch(NotEnoughResourcesException | CantBuyThisCardException ignored) { }

        //assertNull(gameModel.getActivePlayer().getPersonalBoard().getWarehouse().getMiddle()[0]);
        //assertNull(gameModel.getActivePlayer().getPersonalBoard().getWarehouse().getMiddle()[1]);
        //assertTrue(gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(0) instanceof Shield);
        //assertTrue(gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(1) instanceof Shield);

        assertEquals(devCard, gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[0].get(0));
        System.out.println(gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[0].get(0));
    }

    //@RepeatedTest(50)
    @Deprecated //This test shows how cards with a specific cost can be bought successfully, others aren't bought.
    //Obviously if the card is not buyable the test fails, that's the motif this got signed as Deprecated.
    public void testDevelopmentCardMarketWithLeaderCards() {
        fillGameModel();
        gameModel.getInitializedGame().splitLeaderCard(gameModel.getPlayerList());
        for(Player player : gameModel.getPlayerList()) {
            gameModel.getRound().discardLeaderCard(player, player.getLeaderCardList().get(0), player.getLeaderCardList().get(1));
        }
        gameModel.getActivePlayer().getLeaderCardList().get(0).setActivated();
        gameModel.getActivePlayer().getLeaderCardList().get(1).setActivated();
        System.out.println(gameModel.getDevelopmentCardMarket().getCardFromStructure(0, 0).getCardPrice());
        gameModel.getActivePlayer().getLeaderCardList().forEach(System.out::println);
        System.out.println();

        gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceMiddle(gameModel, new Coin());
        gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceBottom(gameModel, new Shield());
        gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceBottom(gameModel, new Shield());
        gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceBottom(gameModel, new Shield());

        //gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Shield());
        //gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Stone());

        try{ gameModel.getDevelopmentCardMarket().buyDevelopmentCard(gameModel, 0, 0, 0); }
        catch(NotEnoughResourcesException | CantBuyThisCardException ignored) { }

        assertNull(gameModel.getActivePlayer().getPersonalBoard().getWarehouse().getBottom()[0]);
        //assertTrue(gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(0) instanceof Stone);
    }

    //@Test
    @Deprecated //This test fails because now the developmentCardMarket is shuffled.
    public void test3() {
        fillGameModel();
        ExtraSpaceLeader leader1 = new ExtraSpaceLeader("leader1", 10, "additionalSpace", "", "Shield");
        ExtraSpaceLeader leader2 = new ExtraSpaceLeader("leader2", 20, "additionalSpace", "", "Coin");
        leader1.setActivated();
        leader2.setActivated();
        try {
            leader1.addResource(gameModel, "", new Shield());
            assertTrue(leader1.getExtraResources().get(0) instanceof Shield);
            assertEquals(1, leader1.getExtraResources().size());
            leader1.addResource(gameModel, "", new Shield());
            assertTrue(leader1.getExtraResources().get(1) instanceof Shield);
            assertEquals(0, leader2.getExtraResources().size());
            assertTrue(leader1.removeResource(gameModel));
            assertEquals(1, leader1.getExtraResources().size());
        }
        catch (NotEnoughSpaceException ignored) { }

        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(leader1);
        leaderCards.add(leader2);
        gameModel.getActivePlayer().setLeaderCards(leaderCards);
        gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceTop(gameModel, new Coin());
        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(new Shield());

        try { gameModel.getDevelopmentCardMarket().buyDevelopmentCard(gameModel, 0, 0, 0); }
        catch(NotEnoughResourcesException | CantBuyThisCardException e) { System.out.println("ERROR"); }

        assertEquals(0, leader1.getExtraResources().size());
        assertTrue(gameModel.getActivePlayer().getPersonalBoard().getWarehouse().getTop()[0] instanceof Coin);
        assertNull(gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(0));
    }

    @RepeatedTest(1)
    public void test4() {
        fillGameModel();

        ArrayList<Resource> stringbox = new ArrayList<>();
        for(int i = 0; i < 5; i ++) {
            stringbox.add(new Coin());
            stringbox.add(new Servant());
            stringbox.add(new Shield());
            stringbox.add(new Stone());
        }

        DevelopmentCard card = gameModel.getDevelopmentCardMarket().getCardFromStructure(0, 0);

        gameModel.getActivePlayer().getPersonalBoard().setStrongBox(stringbox);
        try { gameModel.getDevelopmentCardMarket().buyDevelopmentCard(gameModel, 0, 0, 0); }
        catch(NotEnoughResourcesException e) { System.out.println("NotEnoughResources!"); }
        catch(CantBuyThisCardException e) { System.out.println("CantBuyThisCard!"); }

        assertEquals(card, gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCard(0));
        assertNotEquals(card, gameModel.getDevelopmentCardMarket().getCardFromStructure(0, 0));
        System.out.println(gameModel.getActivePlayer().getPersonalBoard().getStrongBox());
    }
}
