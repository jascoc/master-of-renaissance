package it.polimi.ingsw.model;

import java.util.*;

import it.polimi.ingsw.model.commands.PickResourcesCommand;
import it.polimi.ingsw.network.messages.PickResourcesMessage;
import it.polimi.ingsw.network.server.Controller;
import it.polimi.ingsw.network.server.LobbyManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {

    GameModel gameModel = new GameModel();
    Random random = new Random();

    @RepeatedTest(150)
    public void tryMoveColumnAndRows(){
        initializeStructure();

        gameModel.getMarketMarbleStructure().printMarket();
        gameModel.getMarketMarbleStructure().chooseRow(2, gameModel);
        gameModel.getMarketMarbleStructure().printMarket();
        gameModel.getMarketMarbleStructure().chooseColumn(0, gameModel);
        gameModel.getMarketMarbleStructure().printMarket();
        System.out.println(gameModel.getMarketMarbleStructure().getOutMarble().toString());

    }


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
    @Deprecated
    public void tryMove() {
        initializeStructure();
        for(Player p : gameModel.getPlayerList()) {
            assertEquals(0, p.getVictoryPoints());
            System.out.println("Player " + p.getName() + " is active? " + p.isActivePlayer());
        }
        System.out.println();

        ArrayList<Resource> resourceArray = new ArrayList<>(4);

        int randomNumber = 0;

        for(Player p : gameModel.getPlayerList()){
            System.out.println("Player " + p.getName() + " is active? " + p.isActivePlayer());
            randomNumber = random.nextInt(3);
            resourceArray = gameModel.getMarketMarbleStructure().chooseRow(randomNumber, gameModel);
            System.out.print("Player " + p.getName() + ": has choose row number " + randomNumber +
                    " and has taken " + resourceArray + " Resources.\nNow he's in position: " +
                    p.getFaithTrackPosition());
            gameModel.setNextActivePLayer();
            System.out.println(". He's active now? " + p.isActivePlayer() + "\n");
            gameModel.setNextActivePLayer();
        }
    }


    @RepeatedTest(150)
    public void moveFaithTrackTest() {
        initializeStructure();

        int[] playerMovement = new int[4];
        playerMovement[0] = 5;
        playerMovement[1] = 12;
        playerMovement[2] = 8;
        playerMovement[3] = 16;

        for(Player player : gameModel.getPlayerList()) { gameModel.moveFaithTrack(playerMovement[player.getRoundPosition()], player); }

        //assertEquals(9, gameModel.getActivePlayer().getVictoryPoints());
        assertEquals(2, gameModel.getPlayerList().get(0).getVictoryPoints());
        assertEquals(5, gameModel.getPlayerList().get(1).getVictoryPoints());
        assertEquals(0, gameModel.getPlayerList().get(2).getVictoryPoints());
        assertEquals(3, gameModel.getPlayerList().get(3).getVictoryPoints());
    }


    //@RepeatedTest(150)
    @Deprecated
    public void finalGame(){
        Scanner in = new Scanner(System.in);
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

        for(Player player : gameModel.getPlayerList()){
            int leader1 = random.nextInt(3);
            int leader2 = random.nextInt(3);
            while(leader2 == leader1){ leader2 = random.nextInt(3); }

            gameModel.getRound().discardLeaderCard(player, player.getLeaderCardList().get(leader1), player.getLeaderCardList().get(leader2));
        }

        ArrayList<Resource> resourceList;

        int times = 0;
        while(times < 1) {
            for (Player player : gameModel.getPlayerList()) {
                assertTrue(player.isActivePlayer());
                int num = random.nextInt(3);
                resourceList = gameModel.getMarketMarbleStructure().chooseRow(num, gameModel);
                ArrayList<Resource> iterator = new ArrayList<>(resourceList);
                for (Resource res : iterator) {
                    if (res.toString().equals("RedCross")) {
                        //assertEquals(1, player.getFaithTrackPosition());
                        resourceList.remove(res);
                    } else if (res.toString().equals("White")) {
                        resourceList.remove(res);
                    }
                }

                for(Resource res : resourceList){
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Warehouse:");
                    System.out.println("" + Arrays.toString(player.getPersonalBoard().getWarehouse().getTop()) + "");
                    System.out.println(Arrays.toString(player.getPersonalBoard().getWarehouse().getMiddle()));
                    System.out.println(Arrays.toString(player.getPersonalBoard().getWarehouse().getBottom()));

                    System.out.println("You have: " + res +".\nWhere do you want to put it in the warehouse?\n");
                    player.getPersonalBoard().getWarehouse().chooseLocation(gameModel, res, 0);
                }

                System.out.println("Resource taken: " + iterator);
                System.out.println("Player position: " + player.getFaithTrackPosition() + "\n");

                //
                gameModel.setNextActivePLayer();
            }
            times ++;
        }
    }


    @RepeatedTest(150)
    public void testIfRedCrossWorksFine() {
        initializeStructure();
        ArrayList<Resource> resources = gameModel.getMarketMarbleStructure().chooseRow(0, gameModel);
        System.out.println(resources);

        resources.forEach((res) -> {
            if(res.toString().equals("Red")) { assertEquals(1, gameModel.getActivePlayer().getFaithTrackPosition()); }
        });
    }

    //@RepeatedTest(1)
    public void testIfPlayerMoveCorrectly() {
        initializeStructure();
        ArrayList<String> resInWarehouse = new ArrayList<>();
        ArrayList<Marble> res = gameModel.getMarketMarbleStructure().getMarbleMarketAsList();
        ArrayList<String> resString = new ArrayList<>();
        for(Marble m : res) { resString.add(m.toString()); }
        System.out.println(res);

        /*
        res.removeIf((resource) -> resource.toString().equals(WHITE) || resource.toString().equals(RED_CROSS));

        if(res.get(0).toString().equals(res.get(1).toString())) {
            resInWarehouse.add("middle");
            resInWarehouse.add("middle");
            resInWarehouse.add("top");
        }
        else if(res.get(0).toString().equals(res.get(2).toString())) {
            resInWarehouse.add("middle");
            resInWarehouse.add("top");
            resInWarehouse.add("middle");
        }
        else if(res.get(1).toString().equals(res.get(2).toString())) {
            resInWarehouse.add("top");
            resInWarehouse.add("middle");
            resInWarehouse.add("middle");
        }
        else if(res.get(0).toString().equals(res.get(1).toString()) && res.get(0).toString().equals(res.get(2).toString())) {
            resInWarehouse.add("bottom");
            resInWarehouse.add("bottom");
            resInWarehouse.add("bottom");
        }
        else {
            resInWarehouse.add("top");
            resInWarehouse.add("middle");
            resInWarehouse.add("bottom");
        }
        */

        resInWarehouse.add("top");
        resInWarehouse.add("middle");
        resInWarehouse.add("bottom");

        PickResourcesMessage message = new PickResourcesMessage("row", 0, resInWarehouse);
        PickResourcesCommand command = new PickResourcesCommand();
        command.executeCommand(message, gameModel);
        System.out.println(gameModel.getActivePlayer().getPersonalBoard().getWarehouse().getResourcesString());

        for(Player player : gameModel.getPlayerList()) {
            if(player.isActivePlayer() && resString.contains("RedCross")) {
                assertEquals(1, player.getFaithTrackPosition());
            }
            else {
                if(res.get(0).toString().equals(res.get(1).toString()) || res.get(0).toString().equals(res.get(2).toString())
                || (res.get(0).toString().equals(res.get(1).toString()) && res.get(0).toString().equals(res.get(2).toString()))) {
                    assertEquals(1, player.getFaithTrackPosition());
                }
            }
        }
    }
}