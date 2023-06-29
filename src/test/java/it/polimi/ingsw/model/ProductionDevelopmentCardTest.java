package it.polimi.ingsw.model;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.server.Controller;
import org.junit.jupiter.api.Test;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductionDevelopmentCardTest {
    private Servant servant = new Servant();
    private Shield shield = new Shield();
    private Coin coin = new Coin();
    private Stone stone = new Stone();
    private GameModel gameModel = new GameModel();

    /**
     * test the old version of active production, not useful anymore
     */
    /*@Test
    public void activeProductionTest(){

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        gameModel.setPlayerList(players);



        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        DevelopmentCard developmentCard = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard.setVictoryPoints(12);
        assertEquals(4,developmentCard.activeProduction(gameModel, new Shield()).size());
        assertEquals("Servant",developmentCard.activeProduction(gameModel, shield.getResource()).get(3).toString());
        assertEquals("Servant",developmentCard.activeProduction(gameModel, shield.getResource()).get(2).toString());
        assertEquals("Servant",developmentCard.activeProduction(gameModel, shield.getResource()).get(1).toString());
        assertEquals("Stone",developmentCard.activeProduction(gameModel, shield.getResource()).get(0).toString());

        DevelopmentCard developmentCard1 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard1.setVictoryPoints(12);
        assertEquals(4,developmentCard.activeProduction(gameModel, servant.getResource()).size());
        assertEquals("Shield",developmentCard1.activeProduction(gameModel, servant.getResource()).get(3).toString());
        assertEquals("Shield",developmentCard1.activeProduction(gameModel, servant.getResource()).get(2).toString());
        assertEquals("Shield",developmentCard1.activeProduction(gameModel, servant.getResource()).get(1).toString());
        assertEquals("Coin",developmentCard1.activeProduction(gameModel, servant.getResource()).get(0).toString());

        DevelopmentCard developmentCard2 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard2.setVictoryPoints(11);
        List<Resource> production = developmentCard2.activeProduction(gameModel, shield.getResource());
        assertEquals(1,production.size());
        assertEquals("Servant",production.get(0).toString());
        assertEquals(3, player.getFaithTrackPosition());

        DevelopmentCard developmentCard3 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard3.setVictoryPoints(11);
        production = developmentCard3.activeProduction(gameModel, servant.getResource());
        assertEquals(1,production.size());
        assertEquals("Coin",production.get(0).toString());
        assertEquals(6, player.getFaithTrackPosition());

        DevelopmentCard developmentCard4 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard4.setVictoryPoints(12);
        assertEquals(4,developmentCard4.activeProduction(gameModel, coin.getResource()).size());
        assertEquals("Stone",developmentCard4.activeProduction(gameModel, coin.getResource()).get(3).toString());
        assertEquals("Stone",developmentCard4.activeProduction(gameModel, coin.getResource()).get(2).toString());
        assertEquals("Stone",developmentCard4.activeProduction(gameModel, coin.getResource()).get(1).toString());
        assertEquals("Servant",developmentCard4.activeProduction(gameModel, coin.getResource()).get(0).toString());

        DevelopmentCard developmentCard5 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard5.setVictoryPoints(12);
        assertEquals(4,developmentCard5.activeProduction(gameModel, stone.getResource()).size());
        assertEquals("Coin",developmentCard5.activeProduction(gameModel, stone.getResource()).get(3).toString());
        assertEquals("Coin",developmentCard5.activeProduction(gameModel, stone.getResource()).get(2).toString());
        assertEquals("Coin",developmentCard5.activeProduction(gameModel, stone.getResource()).get(1).toString());
        assertEquals("Shield",developmentCard5.activeProduction(gameModel, stone.getResource()).get(0).toString());

        DevelopmentCard developmentCard6 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard6.setVictoryPoints(11);
        production = developmentCard6.activeProduction(gameModel, coin.getResource());
        assertEquals(1,production.size());
        assertEquals("Stone",production.get(0).toString());
        assertEquals(9, player.getFaithTrackPosition());

        DevelopmentCard developmentCard7 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard7.setVictoryPoints(11);
        production = developmentCard7.activeProduction(gameModel, stone.getResource());
        assertEquals(1,production.size());
        assertEquals("Shield",production.get(0).toString());
        assertEquals(12, player.getFaithTrackPosition());

        DevelopmentCard developmentCard8 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard8.setVictoryPoints(10);
        production = developmentCard8.activeProduction(gameModel, stone.getResource(), servant.getResource());
        assertEquals(4,production.size());
        assertEquals("Shield",production.get(0).toString());
        assertEquals("Shield",production.get(1).toString());
        assertEquals("Coin",production.get(2).toString());
        assertEquals("Coin",production.get(3).toString());
        assertEquals(13, player.getFaithTrackPosition());

        DevelopmentCard developmentCard9 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        developmentCard9.setVictoryPoints(1);
        production = developmentCard9.activeProduction(gameModel, servant.getResource());
        assertEquals(0,production.size());
        assertEquals(14, player.getFaithTrackPosition());
    }*/


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
     * testing the correct work flow of the method active production
     */
    @Test
    public void lastTest(){
        fillGameModel();
        ArrayList<Resource> price = new ArrayList<>();
        ArrayList<String> cardPrice = new ArrayList<>();
        price.add(coin.getResource());
        cardPrice.add("Coin");

        gameModel.getPlayerList().get(0).isActivePlayer(true);
        gameModel.getActivePlayer().getPersonalBoard().getStrongBox().add(coin.getResource());
        gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceTop(gameModel,coin.getResource());
        gameModel.getActivePlayer().setController(new Controller());


        DevelopmentCard developmentCard = new ProductionDevelopmentCard(1,"Blue",cardPrice,12,cardPrice);
        developmentCard.setProductionPriceResource(price);
        assertEquals(gameModel.getPlayerList().get(0),gameModel.getActivePlayer());
        gameModel.getActivePlayer().getPersonalBoard().storeDevelopmentCard(developmentCard,0, gameModel);
        assertEquals(developmentCard,gameModel.getActivePlayer().getPersonalBoard().getDevelopmentCardSlots()[0].get(0));
        List<Resource>development = gameModel.getRound().chooseProductionColumn(gameModel, price, 0);
        assertNull(gameModel.getActivePlayer().getPersonalBoard().getWarehouse().getTop()[0]);
        assertEquals("Coin",gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(0).toString());
        assertEquals("Servant",development.get(0).toString());
        assertEquals("Stone",development.get(1).toString());
        assertEquals("Stone",development.get(2).toString());
        assertEquals("Stone",development.get(3).toString());
        // don't work here because network is needed
        /*gameModel.getRound().addProductionResourcesColumn1(gameModel);
        assertEquals("Coin",gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(0).toString());
        assertEquals("Servant",gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(1).toString());
        assertEquals("Stone",gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(2).toString());
        assertEquals("Stone",gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(3).toString());
        assertEquals("Stone",gameModel.getActivePlayer().getPersonalBoard().getStrongBox().get(4).toString());*/

    }
}
