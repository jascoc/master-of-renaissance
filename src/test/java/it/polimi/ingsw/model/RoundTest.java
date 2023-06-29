package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;
import it.polimi.ingsw.network.server.Controller;
import it.polimi.ingsw.network.server.LobbyManager;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;
import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {

    GameModel gameModel = new GameModel();

    private void initializeStructure() {
        ArrayList<Player> players = new ArrayList<>(4);
        LobbyManager.getLobbyInstance().setLobbyInstance();
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

    //@RepeatedTest(150)
    @Deprecated //because now leaderCards are shuffled in the ArrayList and I don't know anymore what leader card there will be in activePlayer
                // position 0. Anyway the test worked fine before that change.
    public void activateLeaderCardTest() {
        initializeStructure();
        ArrayList<Resource> strongbox1 = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            strongbox1.add(new Coin());
            strongbox1.add(new Servant());
            strongbox1.add(new Shield());
            strongbox1.add(new Stone());
        }

        LeaderCard card = null;

        exit:
        for(int i = 0; i < 4; i ++) {
            for(int j = 0; j < 4; j ++) {
                if(gameModel.getActivePlayer().getLeaderCardList().get(j).getAbility().equals(ADDITIONAL_SPACE)) {
                    card = gameModel.getActivePlayer().getLeaderCardList().get(j);
                    break exit;
                }
            }
            gameModel.setNextActivePLayer();
        }

        System.out.println(card);

        ArrayList<String> required = new ArrayList<>();

        DevelopmentCard cardGreen = new ProductionDevelopmentCard(1, GREEN, required, 0, required);
        DevelopmentCard cardPurple = new ProductionDevelopmentCard(1, PURPLE, required, 0, required);
        DevelopmentCard cardBlue = new ProductionDevelopmentCard(1, BLUE, required, 0, required);
        DevelopmentCard cardYellow = new ProductionDevelopmentCard(1, YELLOW, required, 0, required);


        gameModel.getActivePlayer().getPersonalBoard().addToStrongbox(strongbox1);
        gameModel.getActivePlayer().getPersonalBoard().storeDevelopmentCard(cardGreen, 0, gameModel);
        gameModel.getActivePlayer().getPersonalBoard().storeDevelopmentCard(cardPurple, 1, gameModel);
        gameModel.getActivePlayer().getPersonalBoard().storeDevelopmentCard(cardBlue, 2, gameModel);

        try { gameModel.getRound().activateLeaderCard(gameModel, card); }
        catch (NotEnoughResourcesException e) { System.out.println(e); }

        assertTrue(card.getActivated());
    }
}