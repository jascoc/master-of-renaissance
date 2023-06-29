package it.polimi.ingsw.model;

import it.polimi.ingsw.network.server.Controller;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WarehouseTest {
    private Servant servant = new Servant();
    private Shield shield = new Shield();
    private Coin coin = new Coin();
    private Stone stone = new Stone();
    private List<String> names = new ArrayList<>();
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

    @Test
    public void warehouseTest(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        warehouse.addResourceTop(gameModel, servant.getResource());
        String[][] stringWarehouse = new String[3][3];
        assertEquals("Servant",warehouse.getTop()[0].toString());
        //CLI.warehouseGenerator(warehouse);

        warehouse.addResourceMiddle(gameModel, servant.getResource());
        assertEquals(null,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getMiddle()[1]);
        assertEquals(1,gameModel.getPlayerList().get(1).getFaithTrackPosition());

        warehouse.addResourceMiddle(gameModel, coin.getResource());
        assertEquals("Coin",warehouse.getMiddle()[0].toString());
        assertEquals(null,warehouse.getMiddle()[1]);

        warehouse.addResourceMiddle(gameModel, stone.getResource());
        assertEquals(null,warehouse.getMiddle()[1]);

        warehouse.addResourceMiddle(gameModel, coin.getResource());
        assertEquals("Coin",warehouse.getMiddle()[1].toString());

        warehouse.addResourceBottom(gameModel, coin.getResource());
        assertEquals(null,warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getBottom()[1]);
        assertEquals(null,warehouse.getBottom()[2]);

        warehouse.addResourceBottom(gameModel, shield.getResource());
        assertEquals("Shield",warehouse.getBottom()[0].toString());
        assertEquals(null,warehouse.getBottom()[1]);
        assertEquals(null,warehouse.getBottom()[2]);

        warehouse.setBottom(shield.getResource());//this method is used only for testing
        warehouse.addResourceBottom(gameModel, shield.getResource());
        assertEquals("Shield",warehouse.getBottom()[0].toString());
        assertEquals("Shield",warehouse.getBottom()[1].toString());
        assertEquals("Shield",warehouse.getBottom()[2].toString());

        warehouse.addResourceBottom(gameModel, servant.getResource());
        warehouse.addResourceBottom(gameModel, servant.getResource());
        warehouse.addResourceBottom(gameModel, servant.getResource());
        assertEquals("Shield",warehouse.getBottom()[0].toString());
        assertEquals("Shield",warehouse.getBottom()[1].toString());
        assertEquals("Shield",warehouse.getBottom()[2].toString());

        warehouse.removeResource(warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getBottom()[0]);

        warehouse.addResourceBottom(gameModel, servant.getResource());
        assertEquals(null,warehouse.getBottom()[0]);

        warehouse.addResourceBottom(gameModel, shield.getResource());
        assertEquals("Shield",warehouse.getBottom()[0].toString());
        //CLI.warehouseGenerator(warehouse);

        warehouse.removeResourceByName(new Shield(), gameModel);
        //CLI.warehouseGenerator(warehouse);


    }

    @Test
    public void moveToTopTest(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Servant servant = new Servant();
        Shield shield = new Shield();
        Coin coin = new Coin();
        Stone stone = new Stone();
        String[][] stringWarehouse = new String[3][3];

        warehouse.addResourceBottom(gameModel,shield);
        assertEquals(shield,warehouse.getBottom()[0]);
        //CLI.warehouseGenerator(warehouse);


        /**
         * case where top has nothing and i am moving a resource from bottom to top
         */
        warehouse.moveToTop(shield);
        assertEquals(null,warehouse.getBottom()[0]);
        assertEquals(shield,warehouse.getTop()[0]);
        //CLI.warehouseGenerator(warehouse);


        warehouse.addResourceBottom(gameModel,coin);
        assertEquals(coin,warehouse.getBottom()[0]);
        //CLI.warehouseGenerator(warehouse);


        warehouse.moveToTop(coin);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(coin,warehouse.getTop()[0]);
        //CLI.warehouseGenerator(warehouse);

    }

    /**
     * case where bottom is empty
     */
    @Test
    public void moveFromTopToBottomCase1Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();

        warehouse.addResourceTop(gameModel,shield);
        assertEquals(null,warehouse.getBottom()[0]);
        assertEquals(shield,warehouse.getTop()[0]);

        warehouse.moveFromTopToBottom(shield);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getTop()[0]);

    }

    /**
     * case where bottom has 1 resource
     */
    @Test
    public void moveFromTopToBottomCase2Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Coin coin = new Coin();

        warehouse.addResourceTop(gameModel,shield);
        warehouse.addResourceBottom(gameModel,coin);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(shield,warehouse.getTop()[0]);

        warehouse.moveFromTopToBottom(shield);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(coin,warehouse.getTop()[0]);

    }
    /**
     * case where bottom has 2 resources and middle is empty
     */
    @Test
    public void moveFromTopToBottomCase3Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Coin coin = new Coin();
        Coin coin2 = new Coin();

        warehouse.addResourceTop(gameModel,shield);
        warehouse.addResourceBottom(gameModel,coin);
        warehouse.addResourceBottom(gameModel,coin2);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(coin2,warehouse.getBottom()[1]);
        assertEquals(shield,warehouse.getTop()[0]);

        warehouse.moveFromTopToBottom(shield);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(coin2,warehouse.getMiddle()[1]);
        assertEquals(null,warehouse.getTop()[0]);
    }

    /**
     * case where middle has 1 resource and bottom is empty
     */
    @Test
    public void moveMiddleTopToBottomCase1Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();


        warehouse.addResourceMiddle(gameModel,shield);
        assertEquals(null,warehouse.getBottom()[0]);
        assertEquals(shield,warehouse.getMiddle()[0]);

        warehouse.moveFromMiddleToBottom(shield);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getMiddle()[0]);

    }
    /**
     * case where middle has 1 resource and bottom has 1 resource
     */
    @Test
    public void moveFromMiddleToBottomCase2Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Coin coin = new Coin();

        warehouse.addResourceMiddle(gameModel,shield);
        warehouse.addResourceBottom(gameModel,coin);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(shield,warehouse.getMiddle()[0]);

        warehouse.moveFromMiddleToBottom(shield);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(coin,warehouse.getMiddle()[0]);
    }

    /**
     * case where middle has 1 resource and bottom has 2 resources and middle is empty
     */
    @Test
    public void moveFromMiddleToBottomCase3Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Coin coin = new Coin();
        Coin coin2 = new Coin();

        warehouse.addResourceMiddle(gameModel,shield);
        warehouse.addResourceBottom(gameModel,coin);
        warehouse.addResourceBottom(gameModel,coin2);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(coin2,warehouse.getBottom()[1]);
        assertEquals(shield,warehouse.getMiddle()[0]);

        warehouse.moveFromMiddleToBottom(shield);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(coin2,warehouse.getMiddle()[1]);
    }

    /**
     * case where middle has 2 resource and bottom is empty
     */
    @Test
    public void moveMiddleTopToBottomCase4Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Shield shield1 = new Shield();

        warehouse.addResourceMiddle(gameModel,shield);
        warehouse.addResourceMiddle(gameModel,shield1);
        assertEquals(null,warehouse.getBottom()[0]);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield1,warehouse.getMiddle()[1]);

        warehouse.moveFromMiddleToBottom(warehouse.getMiddle());
        assertEquals(null,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getMiddle()[1]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[1]);
    }

    /**
     * case where middle has 2 resource and bottom has 1 resource
     */
    @Test
    public void moveFromMiddleToBottomCase5Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Shield shield1 = new Shield();
        Coin coin1 = new Coin();
        Coin coin2 = new Coin();

        warehouse.addResourceMiddle(gameModel,shield);
        warehouse.addResourceMiddle(gameModel,shield1);
        warehouse.addResourceBottom(gameModel,coin1);
        warehouse.addResourceBottom(gameModel,coin2);
        warehouse.removeResource(coin1);
        assertEquals(null,warehouse.getBottom()[0]);
        assertEquals(coin2,warehouse.getBottom()[1]);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield1,warehouse.getMiddle()[1]);

        warehouse.moveFromMiddleToBottom(warehouse.getMiddle());
        assertEquals(coin2,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getMiddle()[1]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[1]);
    }

    /**
     * case where middle has 2 resource and bottom has 2 resources and middle is empty
     */
    @Test
    public void moveFromMiddleToBottomCase6Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Shield shield1 = new Shield();
        Shield shield2 = new Shield();
        Coin coin = new Coin();
        Coin coin2 = new Coin();

        warehouse.addResourceMiddle(gameModel,shield);
        warehouse.addResourceMiddle(gameModel,shield1);
        warehouse.addResourceBottom(gameModel,coin);
        warehouse.addResourceBottom(gameModel,coin2);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(coin2,warehouse.getBottom()[1]);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield1,warehouse.getMiddle()[1]);

        warehouse.moveFromMiddleToBottom(warehouse.getMiddle());
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[1]);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(coin2,warehouse.getMiddle()[1]);

        warehouse.addResourceBottom(gameModel, shield2);
        warehouse.removeResource(shield1);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getBottom()[1]);
        assertEquals(shield2,warehouse.getBottom()[2]);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(coin2,warehouse.getMiddle()[1]);

        warehouse.moveFromMiddleToBottom(warehouse.getMiddle());
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(coin2,warehouse.getBottom()[1]);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield2,warehouse.getMiddle()[1]);
    }

    /**
     * case where middle is empty
     */
    @Test
    public void moveFromTopToMiddleCase1Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();

        warehouse.addResourceTop(gameModel,shield);
        assertEquals(null,warehouse.getMiddle()[0]);
        assertEquals(shield,warehouse.getTop()[0]);

        warehouse.moveFromTopToMiddle(shield);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getTop()[0]);

    }

    /**
     * case where middle has 1 resource and then 2
     */
    @Test
    public void moveFromTopToMiddleCase2Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Shield shield1 = new Shield();
        Coin coin = new Coin();

        warehouse.addResourceTop(gameModel,shield);
        warehouse.addResourceMiddle(gameModel,coin);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(shield,warehouse.getTop()[0]);

        warehouse.moveFromTopToMiddle(shield);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(coin,warehouse.getTop()[0]);

        warehouse.addResourceTop(gameModel,shield1);
        warehouse.addResourceBottom(gameModel,shield1);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(coin,warehouse.getTop()[0]);
        assertEquals(null,warehouse.getBottom()[0]);

        warehouse.addResourceMiddle(gameModel,shield1);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield1,warehouse.getMiddle()[1]);
        assertEquals(coin,warehouse.getTop()[0]);
        assertEquals(null,warehouse.getBottom()[0]);

        warehouse.moveFromTopToMiddle(coin);
        assertEquals(null,warehouse.getTop()[0]);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getMiddle()[1]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[1]);
    }

    /**
     * case when bottom has 1 resource
     */
    @Test
    public void moveFromBottomToMiddleCase1Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Shield shield1 = new Shield();
        Shield shield2 = new Shield();
        Coin coin = new Coin();

        warehouse.addResourceBottom(gameModel, coin);
        assertEquals(coin,warehouse.getBottom()[0]);

        /**
         * case middle empty
         */
        warehouse.moveFromBottomToMiddle(coin);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getBottom()[0]);

        /**
         * case middle has 1 resource
         */
        warehouse.addResourceBottom(gameModel, shield);
        assertEquals(shield,warehouse.getBottom()[0]);

        warehouse.moveFromBottomToMiddle(shield);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(shield,warehouse.getMiddle()[0]);

        /**
         * case middle has 2 resource
         */
        warehouse.addResourceMiddle(gameModel,shield1);
        warehouse.moveFromBottomToMiddle(coin);
        assertEquals(null,warehouse.getTop()[0]);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getMiddle()[1]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[1]);
        assertEquals(null,warehouse.getBottom()[2]);

        warehouse.addResourceBottom(gameModel,shield2);
        assertEquals(shield2,warehouse.getBottom()[2]);

        warehouse.moveFromBottomToMiddle(coin);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[1]);
        assertEquals(shield2,warehouse.getBottom()[2]);

        warehouse.moveFromBottomToMiddle(shield);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[1]);
        assertEquals(shield2,warehouse.getBottom()[2]);
    }

    /**
     * case when bottom has 2 resource
     */
    @Test
    public void moveFromBottomToMiddleCase2Test(){
        fillGameModel();
        gameModel.getPlayerList().get(0).isActivePlayer(true);
        assertEquals(true,gameModel.getPlayerList().get(0).isActivePlayer());
        assertEquals(false,gameModel.getPlayerList().get(1).isActivePlayer());

        Warehouse warehouse = gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse();
        Shield shield = new Shield();
        Shield shield1 = new Shield();
        Shield shield2 = new Shield();
        Coin coin = new Coin();
        Coin coin1 = new Coin();
        Coin coin2 = new Coin();

        /**
         * case middle empty
         */
        warehouse.addResourceBottom(gameModel,shield);
        warehouse.addResourceBottom(gameModel,shield1);
        warehouse.addResourceBottom(gameModel,shield2);
        warehouse.removeResource(shield1);
        assertEquals(null,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getMiddle()[1]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getBottom()[1]);
        assertEquals(shield2,warehouse.getBottom()[2]);

        warehouse.moveFromBottomToMiddle(warehouse.getBottom());
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield2,warehouse.getMiddle()[1]);
        assertEquals(null,warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getBottom()[1]);
        assertEquals(null,warehouse.getBottom()[2]);

        /**
         * case middle has 1 resource
         */
        warehouse.moveFromMiddleToBottom(warehouse.getMiddle());
        warehouse.addResourceBottom(gameModel,shield1);
        assertEquals(null,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getMiddle()[1]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[2]);
        assertEquals(shield2,warehouse.getBottom()[1]);

        warehouse.removeResource(shield2);
        warehouse.addResourceMiddle(gameModel,coin);
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(null,warehouse.getMiddle()[1]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[2]);
        assertEquals(null,warehouse.getBottom()[1]);

        warehouse.moveFromBottomToMiddle(warehouse.getBottom());
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield1,warehouse.getMiddle()[1]);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getBottom()[1]);
        assertEquals(null,warehouse.getBottom()[2]);

        /**
         * case when middle has 2 resources
         */
        warehouse.addResourceBottom(gameModel,coin1);
        warehouse.addResourceBottom(gameModel,coin2);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield1,warehouse.getMiddle()[1]);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(coin1,warehouse.getBottom()[1]);
        assertEquals(coin2,warehouse.getBottom()[2]);

        warehouse.removeResource(coin1);
        assertEquals(shield,warehouse.getMiddle()[0]);
        assertEquals(shield1,warehouse.getMiddle()[1]);
        assertEquals(coin,warehouse.getBottom()[0]);
        assertEquals(null,warehouse.getBottom()[1]);
        assertEquals(coin2,warehouse.getBottom()[2]);

        warehouse.moveFromBottomToMiddle(warehouse.getBottom());
        assertEquals(coin,warehouse.getMiddle()[0]);
        assertEquals(coin2,warehouse.getMiddle()[1]);
        assertEquals(shield,warehouse.getBottom()[0]);
        assertEquals(shield1,warehouse.getBottom()[1]);
        assertEquals(null,warehouse.getBottom()[2]);
    }
}
