package it.polimi.ingsw.model;

import it.polimi.ingsw.model.colourMarble.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.leaderCards.*;
import it.polimi.ingsw.model.resources.*;

import static it.polimi.ingsw.Variables.*;


/**
 * Class used to create all the basic components that every player will see, use or interact from the beginning of the game.
 */

public class GameInitialization {

    private ArrayList<Player> playerList;
    static int numberOfPlayers;
    private final int NUMBER_OF_LEVEL = 3;
    private final int NUMBER_OF_COLOUR = 4;
    private final int NUMBER_OF_CARDS_PACK = 4;


    /**
     * Class Constructor
     */
    public GameInitialization() {
        playerList = new ArrayList<>(4);
        numberOfPlayers = 0;
    }


    /**
     * Method used to create an arraylist with all the nickname players that enter the game
     */

    public void setPlayers(ArrayList<Player> playerList) {
        for(int i = 0; i < playerList.size(); i ++) {
            playerList.get(i).setRoundPosition(i);
            if(i == 0) { playerList.get(i).isActivePlayer(true); }
        }
    }


    /**
     * Method used to generate from Json file a list of LeaderCards.
     * It should be private but it's now public for tests.
     */
    public ArrayList<LeaderCard> generateLeaderCards() {
        Gson gson = new Gson();
        ArrayList<LeaderCard> leaderCardList = new ArrayList<>();

        Reader reader = new InputStreamReader(this.getClass().getResourceAsStream(LEADER_CARDS_PATH), StandardCharsets.UTF_8);
        ArrayList<LeaderCardParameters> parameterLeaderCardList = gson.fromJson(reader, new TypeToken<ArrayList<LeaderCardParameters>>(){ }.getType());

        Collections.shuffle(parameterLeaderCardList);

        LeaderCard leaderCard;
        for(LeaderCardParameters card : parameterLeaderCardList) {
            card.setRequirementsFromStringToDevCards();
            switch (card.getAbility()) {
                case DISCOUNT:
                    leaderCard = new DiscountLeader(card.getName(), card.getVictoryPoints(), card.getAbility(), card.getDesc(), card.getResourceString());
                    leaderCard.setRequiredDevCards(card.getDevCardRequired());
                    leaderCardList.add(leaderCard);
                    break;
                case ADDITIONAL_SPACE:
                    leaderCard = new ExtraSpaceLeader(card.getName(), card.getVictoryPoints(), card.getAbility(), card.getDesc(), card.getResourceString());
                    leaderCard.setRequiredResource(card.getRequiredResource());
                    leaderCardList.add(leaderCard);
                    break;
                case WHITE_MARBLE_CONVERT:
                    leaderCard = new WhiteMarbleLeader(card.getName(), card.getVictoryPoints(), card.getAbility(), card.getDesc(), card.getResourceString());
                    leaderCard.setRequiredDevCards(card.getDevCardRequired());
                    leaderCardList.add(leaderCard);
                    break;
                case NEW_PRODUCTION:
                    leaderCard = new ExtraProductionLeader(card.getName(), card.getVictoryPoints(), card.getAbility(), card.getDesc(), card.getResourceString());
                    leaderCard.setRequiredDevCards(card.getDevCardRequired());
                    leaderCardList.add(leaderCard);
                    break;
            }
        }

        return leaderCardList;
    }


    /**
     * Method used in splitting the leaderCards to the players.
     * @param playerList
     */
    public void splitLeaderCard(List<Player> playerList) {
        ArrayList<LeaderCard> leaderCards = generateLeaderCards();
        ArrayList<LeaderCard> playerLeaderCards = new ArrayList<>(4);

        int ind = 0;
        for(Player player : playerList) {
            for(int i = 0; i < 4; i ++) {
                playerLeaderCards.add(leaderCards.get(ind));
                ind ++;
            }
            player.setLeaderCards(playerLeaderCards);
            playerLeaderCards.clear();
        }
    }

    /**
     * Initialize the marketMarbleStructure and give the shuffled ArrayList to the GameModel, which will put it in a constructor method for marketMarbleStructure.
     * @return the shuffled list.
     */
    public ArrayList<Marble> initializeMarbleMarketStructure() {
        ArrayList<Marble> marbleList = new ArrayList<>(13);
        for(int i = 0; i < 2; i ++) {
            marbleList.add(new White());
            marbleList.add(new White());
            marbleList.add(new Purple());
            marbleList.add(new Yellow());
            marbleList.add(new Grey());
            marbleList.add(new Blue());
        }
        marbleList.add(new Red());

        Collections.shuffle(marbleList);
        return marbleList;
    }

    /**
     * Parse the developmentCardList in json file.
     * Create the initial instance of the developmentCardMarket and return it to the gameModel.
     * @return the instance of the developmentCardMarket to the gameModel.
     */
    public DevelopmentCardMarket setDevelopmentCardMarket() {
        ArrayList<ProductionDevelopmentCard>[][] devCardStructure = new ArrayList[NUMBER_OF_LEVEL][NUMBER_OF_COLOUR];

        Gson gson = new Gson();

        try {
            Reader reader = new InputStreamReader(this.getClass().getResourceAsStream(DEVELOPMENT_CARDS_PATH), StandardCharsets.UTF_8);
            ArrayList<ProductionDevelopmentCard> devCards = gson.fromJson(reader, new TypeToken<ArrayList<ProductionDevelopmentCard>>() { }.getType());


            reader.close();

            for(DevelopmentCard card : devCards){
                ArrayList<Resource> resources = new ArrayList<>();
                for(String priceResource : card.getCardPrice()){
                    switch (priceResource) {
                        case "Coin":
                            resources.add(new Coin());
                            break;
                        case "Servant":
                            resources.add(new Servant());
                            break;
                        case "Shield":
                            resources.add(new Shield());
                            break;
                        case "Stone":
                            resources.add(new Stone());
                            break;
                    }
                }
                card.setCardPrice(resources);
            }

            for(DevelopmentCard card : devCards) {
                ArrayList<Resource> resources = new ArrayList<>();
                for(String productionResource : card.getProductionPrice()) {
                    switch (productionResource) {
                        case "Coin":
                            resources.add(new Coin());
                            break;
                        case "Servant":
                            resources.add(new Servant());
                            break;
                        case "Shield":
                            resources.add(new Shield());
                            break;
                        case "Stone":
                            resources.add(new Stone());
                            break;
                    }
                }
                card.setProductionPriceResource(resources);
            }


            int i = 0;

            for(int level = 0; level < NUMBER_OF_LEVEL; level ++) {
                for(int colour = 0; colour < NUMBER_OF_COLOUR; colour ++) {
                    devCardStructure[level][colour] = new ArrayList<>(NUMBER_OF_CARDS_PACK);
                    for(int numCard = i * NUMBER_OF_CARDS_PACK ; numCard < i * NUMBER_OF_CARDS_PACK + NUMBER_OF_CARDS_PACK; numCard ++) {
                        devCardStructure[level][colour].add(devCards.get(numCard));
                    }
                    i ++;
                    Collections.shuffle(devCardStructure[level][colour]);
                }
            }

        }
        catch (IOException e) { System.out.println("File Not Found"); }

        DevelopmentCardMarket devCardMarket = new DevelopmentCardMarket(devCardStructure);
        return devCardMarket;
    }
}

