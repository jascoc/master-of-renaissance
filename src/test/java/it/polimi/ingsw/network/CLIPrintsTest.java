package it.polimi.ingsw.network;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.tokens.ActionToken;
import it.polimi.ingsw.model.tokens.BlueActionToken;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.network.client.CLI.CLIPrints;
import it.polimi.ingsw.network.client.CLI.CLIMessage;
import it.polimi.ingsw.network.client.Minimal.LeaderCardMinimal;
import it.polimi.ingsw.network.client.Minimal.MarbleMarketMinimal;
import it.polimi.ingsw.network.client.Minimal.PlayerMinimal;
import it.polimi.ingsw.network.client.Minimal.WarehouseMinimal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static it.polimi.ingsw.Variables.*;

public class CLIPrintsTest {

    @Test
    public void cliTest() {
        System.out.println(CLIMessage.TITLE);
        System.out.println(CLIMessage.RESOURCE_HELP_MESSAGE);
        ActionToken actionToken = new BlueActionToken();
        System.out.println(actionToken);
        Warehouse warehouse = new Warehouse();
        PersonalBoard personalBoard = new PersonalBoard(new Player());
        ArrayList<String> cardPrice = new ArrayList<>();
        ArrayList<String> productionPrice = new ArrayList<>();
        ArrayList<String> production = new ArrayList<>();
        cardPrice.add("Stone");
        cardPrice.add("Shield");
        productionPrice.add("Coin");
        productionPrice.add("Servant");
        production.add("RedCross");
        production.add("RedCross");
        production.add("RedCross");
        DevelopmentCard developmentCard = new ProductionDevelopmentCard(2, "Red", cardPrice, 12,productionPrice, production);
        CLIPrints.faithTrackGenerator(15);
        //CLI.warehouseGenerator(warehouse);
        //CLI.strongboxGenerator(personalBoard.getStrongBox());
        personalBoard.getStrongBox().add(new Coin());
        personalBoard.getStrongBox().add(new Coin());
        personalBoard.getStrongBox().add(new Servant());
        //CLI.strongboxGenerator(personalBoard.getStrongBox());
        personalBoard.getStrongBox().remove(1);
        //CLI.strongboxGenerator(personalBoard.getStrongBox());
        //CLI.developmentCardGenerator(developmentCard);
    }

    @Test
    public void marketMarbleTest() {
        ArrayList<String> marbles = new ArrayList<>();
        for(int i = 0; i < 13; i ++) { marbles.add("Blue"); }

        PlayerMinimal player = new PlayerMinimal();
        ArrayList<LeaderCardMinimal> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCardMinimal(ADDITIONAL_SPACE, COIN, true, false));
        player.setLeaderCards(leaderCards);

        MarbleMarketMinimal marketMinimal = new MarbleMarketMinimal(marbles);
        System.out.println(marketMinimal.getMarbleFromMarket(1,2));
        CLIPrints.marketMarbleGenerator(marketMinimal, player);
        ArrayList<String> middle = new ArrayList<>();
        middle.add("Shield");
        middle.add("Shield");
        WarehouseMinimal warehouseMinimal = new WarehouseMinimal();
        warehouseMinimal.setTop("Coin");
        warehouseMinimal.setMiddle(middle);
        CLIPrints.warehouseGenerator(warehouseMinimal);
        LeaderCardMinimal leaderCardMinimal = new LeaderCardMinimal("additionalSpace","Coin", 12,"Shield",null);
        CLIPrints.leaderCardGenerator(leaderCardMinimal);
    }
}
