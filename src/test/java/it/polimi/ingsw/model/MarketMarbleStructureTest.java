package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import it.polimi.ingsw.model.colourMarble.*;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.network.server.Controller;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the market marble methods.
 */
public class MarketMarbleStructureTest {

    GameModel gameModel = new GameModel();
    MarketMarbleStructure market;
    ArrayList<Marble> arrayMarble = new ArrayList<>(13);
    ArrayList<String> playerList = new ArrayList<>(4);

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


    /**
     * Just a method to quickly fill my array, in the game will be initialized randomly in the GameInitialization class.
     */
    private void fillArray() {
        arrayMarble.add(new White());
        arrayMarble.add(new White());
        arrayMarble.add(new White());
        arrayMarble.add(new White());
        arrayMarble.add(new Blue());
        arrayMarble.add(new Blue());
        arrayMarble.add(new Yellow());
        arrayMarble.add(new Yellow());
        arrayMarble.add(new Purple());
        arrayMarble.add(new Purple());
        arrayMarble.add(new Red());
        arrayMarble.add(new Grey());
        arrayMarble.add(new Grey());
    }

    /**
     * Testing the methods chooseRow and chooseColumn.
     * I made direct assumption on the OutMarble and some of the resources in a certain position,
     * but when the marbleStructure will be initialized randomly this will not work anymore.
     */
    @Test
    public void getMarketStructure() {
        fillGameModel();
        fillArray();
        market = new MarketMarbleStructure(arrayMarble, gameModel);
        ArrayList<Resource> getResources;

        assertTrue(market.getOutMarble() instanceof Grey);
        assertTrue(market.getMarbleFromStructure(0, 2) instanceof White);

        market.printMarket();
        System.out.println("OutMarble: " + market.getOutMarble() + "\n");
        System.out.print("chooseRow(1)\n");
        getResources = market.chooseRow(1, gameModel);
        assertTrue(market.getMarbleFromStructure(0, 2) instanceof White);
        System.out.print(getResources);
        System.out.print("\n | \n V \n");

        market.printMarket();
        System.out.println("OutMarble: " + market.getOutMarble() + "\n");
        System.out.print("chooseColumn(0)\n");
        getResources = market.chooseColumn(0, gameModel);
        assertTrue(market.getMarbleFromStructure(0, 2) instanceof White);
        System.out.print(getResources);
        System.out.print("\n | \n V \n");

        market.printMarket();
        System.out.println("OutMarble: " + market.getOutMarble() + "\n");
        System.out.print("chooseRow(0)\n");
        getResources = market.chooseRow(0, gameModel);
        assertTrue(market.getMarbleFromStructure(0, 2) instanceof White);
        System.out.print(getResources);
        System.out.print("\n | \n V \n");

        market.printMarket();
        System.out.println("OutMarble: " + market.getOutMarble() + "\n");
        System.out.print("chooseRow(0)\n");
        getResources = market.chooseRow(0, gameModel);
        assertTrue(market.getMarbleFromStructure(0, 2) instanceof Yellow);
        System.out.print(getResources);
        System.out.print("\n | \n V \n");

        market.printMarket();
        System.out.println("OutMarble: " + market.getOutMarble() + "\n");
        System.out.print("chooseColumn(2)\n");
        getResources = market.chooseColumn(2, gameModel);
        assertTrue(market.getMarbleFromStructure(0, 2) instanceof White);
        System.out.print(getResources);
        System.out.print("\n | \n V \n");

        market.printMarket();
        System.out.println("OutMarble: " + market.getOutMarble() + "\n");
        System.out.println("chooseColumn(2)");
        getResources = market.chooseColumn(2, gameModel);
        assertTrue(market.getMarbleFromStructure(0, 2) instanceof Red);
        System.out.print(getResources);
        System.out.print("\n | \n V \n");

        market.printMarket();
        System.out.println("OutMarble: " + market.getOutMarble());
    }


    /**
     * Testing that the methods chooseRow and chooseColumn are correct.
     * I can't make explicit assertion because I will not know the initial positioning of the marbles in the marbleStructure.
     * So I make use of toString methods and check directly that the outputs are correct.
     */
    @Test
    public void convertTest() {
        fillGameModel();
        fillArray();

        market = new MarketMarbleStructure(arrayMarble, gameModel);
        ArrayList<Resource> resources = new ArrayList<>(4);

        resources = market.chooseRow(0, gameModel);
        resources = market.chooseColumn(3, gameModel);
        resources = market.chooseRow(1, gameModel);
        resources = market.chooseColumn(2, gameModel);
        resources = market.chooseColumn(1, gameModel);
        resources = market.chooseRow(1, gameModel);

        resources.forEach((res) -> System.out.println(res.toString()));
    }

    /**
     * Testing the sorting method Collection.sort() and the comparator method Comparator.comparing().
     */
    @Test
    public void convertTest1() {
        fillGameModel();
        fillArray();

        market = new MarketMarbleStructure(arrayMarble, gameModel);
        ArrayList<Resource> resources = new ArrayList<>(4);

        resources = market.chooseColumn(0, gameModel);
        resources = market.chooseColumn(2, gameModel);
        resources = market.chooseRow(1, gameModel);

        resources.forEach((res) -> System.out.println(res.toString()));

        Collections.sort(resources, Comparator.comparing(Object::toString));

        resources.forEach((res) -> System.out.println(res.toString()));

    }

    @Test
    public void convertTest3() {
        fillGameModel();
        fillArray();

        market = new MarketMarbleStructure(arrayMarble, gameModel);
        ArrayList<Resource> resources = new ArrayList<>(4);

        resources = market.chooseRow(2, gameModel);

        resources.forEach((res) -> System.out.println(res.toString()));
    }


    @Deprecated
    public void checkGetResourcesRedCross() {
        fillGameModel();
        fillArray();
        fillGameModel();
        market = gameModel.getMarketMarbleStructure();

        market.getMarbleFromStructure(2, 2).convertToResources(gameModel);
        assertEquals(1, gameModel.getPlayerList().get(0).getFaithTrackPosition());
    }

    @Test
    public void testGetResourcesWhenLeaderCardWhiteMarbleActivated() {
        fillGameModel();
        gameModel.getInitializedGame().splitLeaderCard(gameModel.getPlayerList());

        for(Player player : gameModel.getPlayerList()) {
            for(LeaderCard card : player.getLeaderCardList()){
                if(card.getAbility().equals("whiteMarbleConvert")){
                    card.setActivated();
                    card.setChosenToTrue();
                    ArrayList<Resource> resourceList = gameModel.getMarketMarbleStructure().chooseRow(2, gameModel);
                    System.out.println(resourceList + "\n");
                }
            }
        }
    }
}