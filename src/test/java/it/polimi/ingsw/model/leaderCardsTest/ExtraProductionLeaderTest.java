package it.polimi.ingsw.model.leaderCardsTest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.leaderCards.*;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Shield;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class ExtraProductionLeaderTest {

    LeaderCard leader0;
    GameModel gameModel = new GameModel();
    Player player = new Player();

    private void fillGameModel(){
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
        gameModel.getInitializedGame().splitLeaderCard(gameModel.getPlayerList());
        player = gameModel.getActivePlayer();
        leader0 = new ExtraProductionLeader("CoinProduction", 1, "newProduction", "Ciao", "Coin");
        LeaderCard leader1 = new ExtraProductionLeader("StoneProduction", 2, "newProduction", "Ciao1", "Stone");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>(2);
        leaderCards.add(leader0);
        leaderCards.add(leader1);
        player.setLeaderCards(leaderCards);
    }

    @RepeatedTest(1)
    public void testNotEnoughResources() {
        fillGameModel();
        assertEquals("One", player.getName());
        //assertTrue(player.getLeaderCardList().get(0) instanceof ExtraProductionLeader);

        try { ((ExtraProductionLeader) leader0).activeProduction(gameModel, new Shield()); }
        catch (NotEnoughResourcesException e) { }


        assertEquals(0, player.getFaithTrackPosition());
    }


    @RepeatedTest(1)
    public void testWithResources(){
        fillGameModel();
        assertEquals("One", player.getName());
        //assertTrue(player.getLeaderCardList().get(0) instanceof ExtraProductionLeader);

        player.getPersonalBoard().getWarehouse().addResourceTop(gameModel, new Coin());
        player.getLeaderCardList().get(0).setActivated();

        try { ((ExtraProductionLeader) leader0).activeProduction(gameModel, new Shield()); }
        catch (NotEnoughResourcesException e) { }

        //assertTrue(((ExtraProductionLeader) leader0).getProducedResource().get(0) instanceof Shield);
        assertEquals(0, player.getFaithTrackPosition());
    }

    @Test
    public void leaderCardAbilityTest(){
        Gson gson = new Gson();
        ArrayList<LeaderCard> leaderCardList = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/resources/leaderCards.json"));
            ArrayList<LeaderCardParameters> parameterLeaderCardList = gson.fromJson(reader, new TypeToken<ArrayList<LeaderCardParameters>>() { }.getType());

            Collections.shuffle(parameterLeaderCardList);

            LeaderCard leaderCard;
            for(LeaderCardParameters card : parameterLeaderCardList){
                card.setRequirementsFromStringToDevCards();
                switch (card.getAbility()){
                    case "discount":
                        leaderCard = new DiscountLeader(card.getName(), card.getVictoryPoints(), card.getAbility(), card.getDesc(), card.getResourceString());
                        leaderCard.setRequiredDevCards(card.getDevCardRequired());
                        leaderCardList.add(leaderCard);
                        break;
                    case "additionalSpace":
                        leaderCard = new ExtraSpaceLeader(card.getName(), card.getVictoryPoints(), card.getAbility(), card.getDesc(), card.getResourceString());
                        leaderCard.setRequiredResource(card.getRequiredResource());
                        leaderCardList.add(leaderCard);
                        break;
                    case "whiteMarbleConvert":
                        leaderCard = new WhiteMarbleLeader(card.getName(), card.getVictoryPoints(), card.getAbility(), card.getDesc(), card.getResourceString());
                        leaderCard.setRequiredDevCards(card.getDevCardRequired());
                        leaderCardList.add(leaderCard);
                        break;
                    case "newProduction":
                        leaderCard = new ExtraProductionLeader(card.getName(), card.getVictoryPoints(), card.getAbility(), card.getDesc(), card.getResourceString());
                        leaderCard.setRequiredDevCards(card.getDevCardRequired());
                        leaderCardList.add(leaderCard);
                        System.out.println(leaderCard.getAbility());
                        break;
                }
            }
        }
        catch(IOException ignored) { }
    }

}
