package it.polimi.ingsw.model;

import it.polimi.ingsw.model.colourMarble.*;
import it.polimi.ingsw.model.resources.*;
import it.polimi.ingsw.model.resources.uselessResources.RedCross;
import it.polimi.ingsw.model.resources.uselessResources.UselessWhite;
import it.polimi.ingsw.network.server.Controller;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MarbleTest {

    Blue blueMarble = new Blue();
    Grey greyMarble = new Grey();
    Yellow yellowMarble = new Yellow();
    Purple purpleMarble = new Purple();
    Red redMarble = new Red();
    White whiteMarble = new White();

    /**
     * Testing of the convertToResources method.
     * I don't really know if RedCross and UselessWhite will be changed in null, but for now they're implemented like that.
     */
    @Test
    public void testMarble(){
        GameModel gameModel = new GameModel();
        ArrayList<Player> players = new ArrayList<>();
        Player player2 = new Player();
        Player player = new Player();
        Controller controller = new Controller();
        controller.setGameModel(gameModel);
        player.setController(controller);
        player2.setController(controller);
        players.add(player);
        players.add(player2);
        gameModel.setPlayerList(players);
        gameModel.initializeTheGame();

        int i = 0;
        assertTrue(blueMarble.convertToResources(gameModel) instanceof Shield);
        System.out.println(blueMarble.toString());

        assertTrue(greyMarble.convertToResources(gameModel) instanceof Stone);
        System.out.println(greyMarble.toString());

        assertTrue(yellowMarble.convertToResources(gameModel) instanceof Coin);
        System.out.println(yellowMarble.toString());

        assertTrue(purpleMarble.convertToResources(gameModel) instanceof Servant);
        System.out.println(purpleMarble.toString());

        for(Player p :gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }
        assertTrue(redMarble.convertToResources(gameModel) instanceof RedCross);
        //assertEquals(player.getFaithTrackPosition(), 1);

        assertTrue(whiteMarble.convertToResources(gameModel) instanceof UselessWhite);
    }
}
