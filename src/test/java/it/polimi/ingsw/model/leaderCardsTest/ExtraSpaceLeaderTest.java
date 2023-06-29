package it.polimi.ingsw.model.leaderCardsTest;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.exceptions.NotEnoughSpaceException;
import it.polimi.ingsw.model.leaderCards.ExtraSpaceLeader;
import it.polimi.ingsw.model.resources.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExtraSpaceLeaderTest {

    //String name, int victoryPoints, ArrayList<DevelopmentCard> requirements, String ability, String desc

    GameModel gameModel = new GameModel();

    private void initializeGameModel() {
        ArrayList<Player> players = new ArrayList<>(4);
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.get(0).setName("One");
        players.get(1).setName("Two");
        players.get(2).setName("Three");
        players.get(3).setName("Four");
        gameModel.setPlayerList(players);
        gameModel.initializeTheGame();
    }

    @Test
    public void testExtraSpace() {
        initializeGameModel();
        Shield shield = new Shield();

        ArrayList<String> cardPrice = new ArrayList<>();

        ExtraSpaceLeader leaderExtraProduction = new ExtraSpaceLeader("CoinStorage", 3, "discount", "Get 2 Coin additional storage.", "Coin");

        leaderExtraProduction.setActivated();

        try { leaderExtraProduction.addResource(gameModel, "", new Coin()); }
        catch(NotEnoughSpaceException e) { }

        assertNotNull(leaderExtraProduction.getExtraResources().get(0));
        assertTrue(leaderExtraProduction.getExtraResources().get(0) instanceof Coin);
    }

}
