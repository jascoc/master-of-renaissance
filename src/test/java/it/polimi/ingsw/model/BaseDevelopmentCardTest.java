package it.polimi.ingsw.model;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.network.server.Controller;
import org.junit.jupiter.api.Test;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseDevelopmentCardTest {

    private Stone stone = new Stone();
    private Servant servant = new Servant();
    private Coin coin = new Coin();
    private Shield shield = new Shield();
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
    public void activeBaseProductionTest(){
        fillGameModel();
        Stone stone1 = stone.getResource();
        Shield shield1 = shield.getResource();
        Coin coin1 = coin.getResource();

        gameModel.getPlayerList().get(0).isActivePlayer(true);
        gameModel.getPlayerList().get(0).getPersonalBoard().getStrongBox().add(stone1);
        gameModel.getPlayerList().get(0).getPersonalBoard().getStrongBox().add(shield1);
        gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse().addResourceBottom(gameModel, coin1);
        assertEquals(coin1,gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse().getBottom()[0]);
        assertTrue(gameModel.getPlayerList().get(0).getPersonalBoard().getStrongBox().contains(stone1));
        assertTrue(gameModel.getPlayerList().get(0).getPersonalBoard().getStrongBox().contains(shield1));

        try {
            gameModel.getPlayerList().get(0).getPersonalBoard().getBaseDevelopmentCard().activeBaseProduction(gameModel.getPlayerList().get(0),stone1,coin1,servant.getResource(),gameModel);
        } catch (NotEnoughResourcesException e){}
        assertEquals(null,gameModel.getPlayerList().get(0).getPersonalBoard().getWarehouse().getBottom()[0]);
        assertEquals(false,gameModel.getPlayerList().get(0).getPersonalBoard().getStrongBox().contains(stone1));
    }
}
