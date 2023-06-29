package it.polimi.ingsw.model;

import it.polimi.ingsw.network.server.Controller;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalBoardTest {
    GameModel gameModel = new GameModel();

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
    public void storePersonalBoardTest(){
        fillGameModel();
        PersonalBoard personalBoard = new PersonalBoard(new Player());

        ArrayList<String> cardPrice = new ArrayList<>(8);
        ArrayList<String> productionPrice = new ArrayList<>(2);
        DevelopmentCard developmentCard1 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        DevelopmentCard developmentCard2 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        DevelopmentCard developmentCard3 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        DevelopmentCard developmentCard4 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);
        DevelopmentCard developmentCard5 = new ProductionDevelopmentCard(0, "BLACK", cardPrice, 0, productionPrice);


        developmentCard1.setLvl(1);
        developmentCard2.setLvl(2);
        developmentCard3.setLvl(3);
        developmentCard4.setLvl(1);
        developmentCard5.setLvl(3);

        personalBoard.storeDevelopmentCard(developmentCard1,0,gameModel);
        assertEquals(developmentCard1,personalBoard.getDevelopmentCardSlots()[0].get(0));
        assertEquals(1,personalBoard.getDevelopmentCardSlots()[0].size());
        assertEquals(0,personalBoard.getDevelopmentCardSlots()[1].size());
        assertEquals(0,personalBoard.getDevelopmentCardSlots()[2].size());

        personalBoard.storeDevelopmentCard(developmentCard4,1,gameModel);
        assertEquals(developmentCard1,personalBoard.getDevelopmentCardSlots()[0].get(0));
        assertEquals(developmentCard4,personalBoard.getDevelopmentCardSlots()[1].get(0));
        assertEquals(1,personalBoard.getDevelopmentCardSlots()[0].size());
        assertEquals(1,personalBoard.getDevelopmentCardSlots()[1].size());
        assertEquals(0,personalBoard.getDevelopmentCardSlots()[2].size());

        personalBoard.storeDevelopmentCard(developmentCard2,0,gameModel);
        assertEquals(developmentCard1,personalBoard.getDevelopmentCardSlots()[0].get(0));
        assertEquals(developmentCard2,personalBoard.getDevelopmentCardSlots()[0].get(1));
        assertEquals(developmentCard4,personalBoard.getDevelopmentCardSlots()[1].get(0));
        assertEquals(2,personalBoard.getDevelopmentCardSlots()[0].size());
        assertEquals(1,personalBoard.getDevelopmentCardSlots()[1].size());
        assertEquals(0,personalBoard.getDevelopmentCardSlots()[2].size());

        personalBoard.storeDevelopmentCard(developmentCard3,0,gameModel);
        assertEquals(developmentCard1,personalBoard.getDevelopmentCardSlots()[0].get(0));
        assertEquals(developmentCard2,personalBoard.getDevelopmentCardSlots()[0].get(1));
        assertEquals(developmentCard3,personalBoard.getDevelopmentCardSlots()[0].get(2));
        assertEquals(developmentCard4,personalBoard.getDevelopmentCardSlots()[1].get(0));
        assertEquals(3,personalBoard.getDevelopmentCardSlots()[0].size());
        assertEquals(1,personalBoard.getDevelopmentCardSlots()[1].size());
        assertEquals(0,personalBoard.getDevelopmentCardSlots()[2].size());

        personalBoard.storeDevelopmentCard(developmentCard5,2,gameModel);
        assertEquals(developmentCard1,personalBoard.getDevelopmentCardSlots()[0].get(0));
        assertEquals(developmentCard2,personalBoard.getDevelopmentCardSlots()[0].get(1));
        assertEquals(developmentCard3,personalBoard.getDevelopmentCardSlots()[0].get(2));
        assertEquals(developmentCard4,personalBoard.getDevelopmentCardSlots()[1].get(0));
        assertEquals(3,personalBoard.getDevelopmentCardSlots()[0].size());
        assertEquals(1,personalBoard.getDevelopmentCardSlots()[1].size());
        assertEquals(0,personalBoard.getDevelopmentCardSlots()[2].size());
    }
}
