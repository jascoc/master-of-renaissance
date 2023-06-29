package it.polimi.ingsw.network.client.CLI;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.colourMarble.*;
import it.polimi.ingsw.model.leaderCards.*;
import it.polimi.ingsw.network.client.Minimal.*;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.Variables.*;

/**
 * This class represent CLI view
 */
public class CLIPrints {
    /**
     * Turn a Resource to a Ascii String
     */
    private static String resourceToString(Resource resource) {
        String stringedResource = new String();
        switch (resource.toString()) {
            case STONE:
                stringedResource = CLIColours.ANSI_WHITE + "S" + CLIColours.ANSI_RESET;
                break;
            case SHIELD:
                stringedResource = CLIColours.ANSI_BLUE + "S" + CLIColours.ANSI_RESET;
                break;
            case SERVANT:
                stringedResource = CLIColours.ANSI_PURPLE + "S" + CLIColours.ANSI_RESET;
                break;
            case COIN:
                stringedResource = CLIColours.ANSI_YELLOW + "C" + CLIColours.ANSI_RESET;
                break;
        }
        return stringedResource;
    }

    /**
     * Turn a String Resource to a Ascii String
     * @param resource
     * @return
     */
    private static String resourceStringToString(String resource) {
        String stringedResource = new String();
        switch (resource) {
            case STONE:
                stringedResource = CLIColours.ANSI_WHITE + "S" + CLIColours.ANSI_RESET;
                break;
            case SHIELD:
                stringedResource = CLIColours.ANSI_BLUE + "S" + CLIColours.ANSI_RESET;
                break;
            case SERVANT:
                stringedResource = CLIColours.ANSI_PURPLE + "S" + CLIColours.ANSI_RESET;
                break;
            case COIN:
                stringedResource = CLIColours.ANSI_YELLOW + "C" + CLIColours.ANSI_RESET;
                break;
            case RED_CROSS:
                stringedResource = CLIColours.ANSI_RED + "+" + CLIColours.ANSI_RESET;
                break;
            case GREEN:
                stringedResource = CLIColours.ANSI_GREEN + "Green" + CLIColours.ANSI_RESET;
                break;
            case PURPLE:
                stringedResource = CLIColours.ANSI_PURPLE + "Purple" + CLIColours.ANSI_RESET;
                break;
            case BLUE:
                stringedResource = CLIColours.ANSI_BLUE + "Blue" + CLIColours.ANSI_RESET;
                break;
            case YELLOW:
                stringedResource = CLIColours.ANSI_YELLOW + "Yellow" + CLIColours.ANSI_RESET;
                break;
        }
        return stringedResource;
    }

    /**
     * generate the faith track in the CLI
     * @param playerPosition
     */
    public static void faithTrackGenerator(int playerPosition) {
        String[] faithTrack = new String[25];
        for (int i=0; i<25; i++) {
            if (i==5 || i==6 || i==7 || i==12 || i==13 || i==14 || i==15 || i==19 || i==20 || i==21 || i==22 || i==23){
                faithTrack[i]=CLIColours.ANSI_YELLOW+"[ ]";
                if(i==playerPosition)
                    faithTrack[i]=CLIColours.ANSI_YELLOW+"["+CLIColours.ANSI_RESET+"X"+CLIColours.ANSI_YELLOW+"]";
            }
            else if (i==8 || i==16 || i==24) {
                faithTrack[i]=CLIColours.ANSI_RED+"[ ]";
                if(i==playerPosition)
                    faithTrack[i]=CLIColours.ANSI_RED+"["+CLIColours.ANSI_RESET+"X"+CLIColours.ANSI_RED+"]";
            }
            else {
                faithTrack[i]=CLIColours.ANSI_WHITE+"[ ]";
                if(i==playerPosition)
                    faithTrack[i]=CLIColours.ANSI_WHITE+"["+CLIColours.ANSI_RESET+"X"+CLIColours.ANSI_WHITE+"]";
            }
        }
        faithTrackPrinter(faithTrack);
    }

    /**
     * print the faith track in the CLI
     * @param faithTrack
     */
    private static void faithTrackPrinter(String[] faithTrack) {
        System.out.println("faith track");
        String printedFaithTrack = "";
        for (int i=0; i<25; i++) { printedFaithTrack += faithTrack[i]; }
        System.out.println(printedFaithTrack+CLIColours.ANSI_RESET);
    }

    /**
     * generate the market marble in the CLI
     * @param marbleStructure
     * @param player
     */
    public static void marketMarbleGenerator(MarbleMarketMinimal marbleStructure, PlayerMinimal player) {
        String[][] stringMarbleStructure = new String[3][4];
        String leaderChangedMarble = null;

        for(LeaderCardMinimal card : player.getLeaderCards()) {
            if(card.getActivated() && card.getAbility().equals(WHITE_MARBLE_CONVERT)) {
                leaderChangedMarble = changeMarbleColour(card.getResourceUsed());
            }
        }

        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 4; j ++) {
                switch (marbleStructure.getMarketMarbleStructure()[i][j]) {
                    case WHITE:
                        if(leaderChangedMarble == null) { stringMarbleStructure[i][j] = "[O]"; }
                        else { stringMarbleStructure[i][j] = "[" + leaderChangedMarble + "O" + CLIColours.ANSI_RESET + "]"; }
                        break;
                    case BLUE:
                        stringMarbleStructure[i][j] = "[" + CLIColours.ANSI_BLUE + "O" + CLIColours.ANSI_RESET + "]";
                        break;
                    case YELLOW:
                        stringMarbleStructure[i][j] = "[" + CLIColours.ANSI_YELLOW + "O" + CLIColours.ANSI_RESET + "]";
                        break;
                    case RED:
                        stringMarbleStructure[i][j] = "[" + CLIColours.ANSI_RED + "O" + CLIColours.ANSI_RESET + "]";
                        break;
                    case PURPLE:
                        stringMarbleStructure[i][j] = "[" + CLIColours.ANSI_PURPLE + "O" + CLIColours.ANSI_RESET + "]";
                        break;
                    case GREY:
                        stringMarbleStructure[i][j] = "[" + CLIColours.ANSI_WHITE + "O" + CLIColours.ANSI_RESET + "]";
                        break;
                }
            }
        }
        switch (marbleStructure.getOutMarble()) {
            case WHITE:
                System.out.println(CLIColours.GREEN_UNDERLINED + "out marble:" + CLIColours.ANSI_RESET + " [O]");
                break;
            case "Blue":
                System.out.println(CLIColours.GREEN_UNDERLINED + "out marble:" + CLIColours.ANSI_RESET + " [" + CLIColours.ANSI_BLUE + "O" + CLIColours.ANSI_RESET + "]");
                break;
            case "Yellow":
                System.out.println(CLIColours.GREEN_UNDERLINED + "out marble:" + CLIColours.ANSI_RESET + " [" + CLIColours.ANSI_YELLOW + "O" + CLIColours.ANSI_RESET + "]");
                break;
            case "Red":
                System.out.println(CLIColours.GREEN_UNDERLINED + "out marble:" + CLIColours.ANSI_RESET + " [" + CLIColours.ANSI_RED + "O" + CLIColours.ANSI_RESET + "]");
                break;
            case "Purple":
                System.out.println(CLIColours.GREEN_UNDERLINED + "out marble:" + CLIColours.ANSI_RESET + " [" + CLIColours.ANSI_PURPLE + "O" + CLIColours.ANSI_RESET + "]");
                break;
            case "Grey":
                System.out.println(CLIColours.GREEN_UNDERLINED + "out marble:" + CLIColours.ANSI_RESET + " [" + CLIColours.ANSI_WHITE + "O" + CLIColours.ANSI_RESET + "]");
                break;
        }
        marketMarblePrinter(stringMarbleStructure);
    }

    /**
     * turn String into colored String
     * @param resourceLeader
     * @return
     */
    private static String changeMarbleColour(String resourceLeader) {
        switch(resourceLeader) {
            case COIN:
                return CLIColours.ANSI_YELLOW;
            case SERVANT:
                return CLIColours.ANSI_PURPLE;
            case SHIELD:
                return CLIColours.ANSI_BLUE;
            case STONE:
                return CLIColours.ANSI_WHITE;
            default: return null;
        }
    }

    /**
     * prints the market marble
     * @param stringMarbleStructure
     */
    private static void marketMarblePrinter(String[][] stringMarbleStructure) {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 4; j ++) { System.out.print(stringMarbleStructure[i][j]); }
            System.out.println();
        }
    }


    /**
     * generate and prints the warehouse
     * @param warehouse
     */
    public static void warehouseGenerator(WarehouseMinimal warehouse) {
        String[][] stringWarehouse = new String[3][3];
        for (int i=0; i<3; i++) {
            for (int j = 0; j <= i; j++) {
                if (i==0) {
                    if(warehouse.getTop()!=null) {
                        switch (warehouse.getTop()) {
                            case STONE:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_WHITE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case SHIELD:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_BLUE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case SERVANT:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_PURPLE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case COIN:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_YELLOW + "C" + CLIColours.ANSI_RESET + "]";
                                break;
                            default:
                                stringWarehouse[i][j] = "[ ]";
                                break;
                        }
                    }
                    else
                        stringWarehouse[i][j] = "[ ]";
                }
                else if(i==1) {
                    if(warehouse.getMiddle().size() > j) {
                        switch (warehouse.getMiddle().get(j)) {
                            case STONE:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_WHITE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case SHIELD:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_BLUE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case SERVANT:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_PURPLE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case COIN:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_YELLOW + "C" + CLIColours.ANSI_RESET + "]";
                                break;
                            default:
                                stringWarehouse[i][j] = "[ ]";
                                break;
                        }
                    }
                    else
                        stringWarehouse[i][j] = "[ ]";
                }
                else if(i==2) {
                    if(warehouse.getBottom().size() > j) {
                        switch (warehouse.getBottom().get(j)) {
                            case STONE:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_WHITE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case SHIELD:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_BLUE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case SERVANT:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_PURPLE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case COIN:
                                stringWarehouse[i][j] = "[" + CLIColours.ANSI_YELLOW + "C" + CLIColours.ANSI_RESET + "]";
                                break;
                            default:
                                stringWarehouse[i][j] = "[ ]";
                                break;
                        }
                    }
                    else
                        stringWarehouse[i][j] = "[ ]";
                }
            }
        }
        warehousePrinter(stringWarehouse);
    }

    /**
     * prints the warehouse
     * @param warehouse
     */
    private static void warehousePrinter(String[][] warehouse) {
        System.out.println("warehouse");
        String top = "";
        String middle = "";
        String bottom = "";
        for (int i=0; i<3; i++) {
            for (int j = 0; j <= i; j++) {
                if(i==0)
                    top = warehouse[i][j];
                else if(i==1)
                    middle += warehouse[i][j];
                else if(i==2)
                    bottom += warehouse[i][j];
            }
        }
        System.out.println("  "+top);
        System.out.println(" "+middle);
        System.out.print(bottom);
    }

    /**
     * generate and prints the warehouse
     * @param strongbox
     */
    public static void strongboxGenerator(List<String> strongbox) {
        List<String> stringStrongbox = new ArrayList<>();
        stringStrongbox.clear();
        for (int i=0; i<strongbox.size(); i++) {
            if(strongbox.get(i)!=null) {
                switch (strongbox.get(i)) {
                    case STONE:
                        stringStrongbox.add("[" + CLIColours.ANSI_WHITE + "S" + CLIColours.ANSI_RESET + "]");
                        break;
                    case SHIELD:
                        stringStrongbox.add("[" + CLIColours.ANSI_BLUE + "S" + CLIColours.ANSI_RESET + "]");
                        break;
                    case SERVANT:
                        stringStrongbox.add("[" + CLIColours.ANSI_PURPLE + "S" + CLIColours.ANSI_RESET + "]");
                        break;
                    case COIN:
                        stringStrongbox.add("[" + CLIColours.ANSI_YELLOW + "C" + CLIColours.ANSI_RESET + "]");
                        break;
                }
            }
        }
        strongboxPrinter(stringStrongbox);
    }

    /**
     * prints the warehouse
     * @param stringStrongbox
     */
    private static void strongboxPrinter(List<String> stringStrongbox) {
        System.out.print(" strongbox:");
        String strongboxPrinter = "";
        if(stringStrongbox.size()==0)
            System.out.println(" [Empty]");
        else {
            for (String resource : stringStrongbox) {
                strongboxPrinter += resource;
            }
            System.out.println( strongboxPrinter);
        }
    }

    /**
     * generates and prints a development card
     * @param developmentCard
     */
    public static void developmentCardGenerator(DevelopmentCardMinimal developmentCard) {
        String price = "";
        String productionPrice = "";
        String production = "";
        for (String resource : developmentCard.getCardPrice()) {
            price += resourceStringToString(resource) + " ";
        }
        for (String resource : developmentCard.getProductionPrice()) {
            productionPrice += resourceStringToString(resource) + " ";
        }
        for (String resource : developmentCard.getProduction()) {
            production += resourceStringToString(resource) + " ";
        }
        //System.out.println(CLIColours.ANSI_CYAN+"-----------------------------------------------");
        System.out.print(CLIColours.ANSI_WHITE_BACKGROUND+CLIColours.RED_UNDERLINED+"dev card"+CLIColours.ANSI_RESET+CLIColours.ANSI_CYAN);
        System.out.print("[lv:" + developmentCard.getLvl() + " " + resourceStringToString(developmentCard.getColour())+CLIColours.ANSI_CYAN + "][victoryPoints: " + developmentCard.getVictoryPoints());
        System.out.print("][cardPrice: "+ price);
        System.out.print(CLIColours.ANSI_CYAN+"][production:" + productionPrice + CLIColours.ANSI_CYAN + "->" + production + "]");
        //System.out.println("-----------------------------------------------"+CLIColours.ANSI_RESET);
    }

    /**
     * generates and prints the leader card
     * @param leaderCard
     */
    public static void leaderCardGenerator(LeaderCardMinimal leaderCard) {
        System.out.println("\nleader card: ");
        String requirement = "";

        if (leaderCard.getAbility().equals("additionalSpace")) { requirement = resourceStringToString(leaderCard.getRequiredResource()) + " x5"; }
        else {
            for (DevelopmentCardMinimal developmentCard : leaderCard.getRequiredDevCards()) {
                requirement += "[Development Card: Lvl = " + developmentCard.getLvl() + ", Colour = " + developmentCard.getColour() + "]";
            }
        }
        System.out.println(CLIColours.ANSI_BLUE + leaderCard.getAbility() + CLIColours.ANSI_RESET + "\nVictory Points: " +
                leaderCard.getVictoryPoints());
        System.out.print(CLIColours.BLUE_UNDERLINED + "requirements:" + CLIColours.ANSI_RESET + " " + requirement);

        if(leaderCard.getActivated()) { System.out.println(CLIColours.ANSI_GREEN + "\nActive" + CLIColours.ANSI_RESET); }
        else { System.out.println(CLIColours.ANSI_RED + "\nNot active" + CLIColours.ANSI_RESET); }

        switch (leaderCard.getAbility().toString()) {
            case "additionalSpace":
                System.out.print("can contain only: " + resourceStringToString(leaderCard.getResourceUsed()) +"\t");
                String[] extraSpace = new String[2];
                extraSpace[0] = "[ ]";
                extraSpace[1] = "[ ]";
                if(leaderCard.getDeposit().isEmpty()) { System.out.println("[ ][ ]"); }
                else {
                    for(int i=0; i< leaderCard.getDeposit().size() ; i++) {
                        switch (leaderCard.getDeposit().get(i)) {
                            case STONE:
                                extraSpace[i] = "[" + CLIColours.ANSI_WHITE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case SHIELD:
                                extraSpace[i] = "[" + CLIColours.ANSI_BLUE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case SERVANT:
                                extraSpace[i] = "[" + CLIColours.ANSI_PURPLE + "S" + CLIColours.ANSI_RESET + "]";
                                break;
                            case COIN:
                                extraSpace[i] = "[" + CLIColours.ANSI_YELLOW + "C" + CLIColours.ANSI_RESET + "]";
                                break;
                        }
                    }
                    String printer = "";
                    for(int i = 0; i < 2 ; i ++) { printer += extraSpace[i]; }
                    System.out.println(printer);
                }
                break;
            case "whiteMarbleConvert":
                System.out.println("O -> " + resourceStringToString(leaderCard.getResourceUsed()));
                break;
            case "discount":
                System.out.println("discount of " + resourceStringToString(leaderCard.getResourceUsed()));
                break;
            case "Add a new production using a Servant.":
                String production1 = resourceStringToString("Servant") + " " + resourceStringToString("RedCross");
                System.out.println("production: " + resourceStringToString(leaderCard.getResourceUsed()) + " -> " + production1);
                break;
            case "Add a new production using a Coin.":
                String production2 = resourceStringToString("Coin") + " " + resourceStringToString("RedCross");
                System.out.println("production: " + resourceStringToString(leaderCard.getResourceUsed()) + " -> " + production2);
                break;
            case "Add a new production using a Stone.":
                String production3 = resourceStringToString("Stone") + " " + resourceStringToString("RedCross");
                System.out.println("production: " + resourceStringToString(leaderCard.getResourceUsed()) + " -> " + production3);
                break;
            case "Add a new production using a Shield.":
                String production4 = resourceStringToString("Shield") + " " + resourceStringToString("RedCross");
                System.out.println("production: " + resourceStringToString(leaderCard.getResourceUsed()) + " -> " + production4);
                break;
        }
    }

    /**
     * generates and prints the development card slots showing every card
     * @param player
     */
    public static void developmentCardSlotsGenerator(PlayerMinimal player) {
        for(int i = 0; i<3 ; i++) {
            System.out.println(CLIColours.ANSI_YELLOW+"\n=====================================================================================================================================================================================================");
            System.out.println("SLOT: "+ (i+1) +CLIColours.ANSI_RESET);
            if(player.getDevelopmentCards()[i].isEmpty()) {
                System.out.println(CLIColours.ANSI_RED+" IS EMPTY"+CLIColours.ANSI_RESET);
            } else {
                for (DevelopmentCardMinimal devCard: player.getDevelopmentCards()[i]) {
                    developmentCardGenerator(devCard);
                }
            }
        }
    }

    /**
     * generates and prints the development card market showing every card on the top
     * @param developmentCardMarket
     */
    public static void developmentCardMarketGenerator(DevelopmentCardMarketMinimal developmentCardMarket) {
        System.out.println(CLIColours.CYAN_UNDERLINED+"DEV MARKET"+CLIColours.ANSI_RESET+CLIColours.ANSI_CYAN);
        for(int i=0; i<3; i++) {
            for(int j=0; j<4; j++) {
                String price = "";
                String productionPrice = "";
                String production = "";
                if (!developmentCardMarket.getList(i,j).isEmpty()) {
                    for (String resource : developmentCardMarket.getList(i,j).get(0).getCardPrice()) {
                        price += resourceStringToString(resource) + " ";
                    }
                    for (String resource : developmentCardMarket.getList(i,j).get(0).getProductionPrice()) {
                        productionPrice += resourceStringToString(resource) + " ";
                    }
                    for (String resource : developmentCardMarket.getList(i,j).get(0).getProduction()) {
                        production += resourceStringToString(resource) + " ";
                    }
                    System.out.print(CLIColours.ANSI_WHITE_BACKGROUND + CLIColours.RED_UNDERLINED + "dev card" + CLIColours.ANSI_RESET + CLIColours.ANSI_CYAN);
                    System.out.print("[lv:" + developmentCardMarket.getList(i,j).get(0).getLvl() + " " + resourceStringToString(developmentCardMarket.getList(i,j).get(0).getColour())+ CLIColours.ANSI_CYAN + "][victoryPoints: " + developmentCardMarket.getList(i,j).get(0).getVictoryPoints());
                    System.out.print("][cardPrice: " + price);
                    System.out.print(CLIColours.ANSI_CYAN + "][production:" + productionPrice + CLIColours.ANSI_CYAN + "->" + production + "]\t");
                }
                else {
                    System.out.print(CLIColours.ANSI_RED+"[THE CARDS HERE HAVE BEEN ALL SOLD/DISCARDED]\t");
                }
            }
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    /**
     * generate and prints the complete personal board for each player
     * @param players
     * @param thisPlayer
     */
    public static void personalBoardGenerator(List<PlayerMinimal> players, PlayerMinimal thisPlayer) {
        //Single player never added to player list
        for (PlayerMinimal player: players) {
            System.out.println("Player: " + player.getPlayerName()+ ". Round position: " + player.getPlayerRoundNumber() +
                    "\nVictory Points: " + player.getVictoryPoints());
            if (player.isActive()) { System.out.println("IS NOW PLAYING"); }
            else { System.out.println("NOT HIS TURN"); }
            faithTrackGenerator(player.getPlayerFaithPosition());
            warehouseGenerator(player.getWarehouse());
            strongboxGenerator(player.getStrongbox());
            developmentCardSlotsGenerator(player);
            for (LeaderCardMinimal leader: player.getLeaderCards()) {
                if(!leader.getActivated() && !player.getPlayerName().equals(thisPlayer.getPlayerName()))
                    System.out.println("LEADER CARD NOT ACTIVE YET");
                else { leaderCardGenerator(leader); }
            }
            System.out.println();
        }
    }

    /**
     * generate and prints the complete personal board for a player
     * @param player
     */
    public static void personalBoardGenerator(PlayerMinimal player) {
        //single player non viene mai aggiunto alla lista dei giocatori
            System.out.println("Player: " + player.getPlayerName()+ " round position: " + player.getPlayerRoundNumber());
            if (player.isActive()) { System.out.println("IS ACTIVE"); }
            else { System.out.println("IS NOT ACTIVE"); }

            faithTrackGenerator(player.getPlayerFaithPosition());
            warehouseGenerator(player.getWarehouse());
            strongboxGenerator(player.getStrongbox());
            developmentCardSlotsGenerator(player);
            //System.out.println(player.getLeaderCards());
            for (LeaderCardMinimal leader: player.getLeaderCards()) {
                if(!leader.getActivated() && !player.isActive()) {
                    System.out.println("LEADER CARD NOT ACTIVE YET");
                }
                else { leaderCardGenerator(leader); }
            }
            System.out.println();
    }

    /**
     * generate the full gameboard
     * @param marketMinimal
     * @param developmentCardMarketMinimal
     */
    public static void gameBoardGenerator(MarbleMarketMinimal marketMinimal, DevelopmentCardMarketMinimal developmentCardMarketMinimal) {
        marketMarbleGenerator(marketMinimal, new PlayerMinimal());
        developmentCardMarketGenerator(developmentCardMarketMinimal);
    }

    /**
     * message that describe the workflow of the rounds
     * @param players
     */
    public static void playerMessageGenerator(List<Player> players) {
        for (Player p : players) {
            if(p.isWinner()){
                System.out.println(p.getName() +" is the winner!");
            }
            if(p.getDisconnected()) {
                System.out.println(p.getName() + " is disconnected");
            }
            if(p.isActivePlayer()) {
                System.out.println("Now is " +CLIColours.RED_UNDERLINED+p.getName()+"'s turn");
            }
        }
    }

    /**
     * generates and prints the single player game board
     * @param singlePlayer
     */
    public static void singlePlayerGenerator (SinglePlayerMinimal singlePlayer) {
        System.out.println(LORENZO_THE_MAGNIFICENT + " player board: ");
        if(singlePlayer.getToken() == null) { System.out.println("He hasn't played nothing yet!"); }
        else { System.out.println("Last token played: " + singlePlayer.getToken()); }
        faithTrackGenerator(singlePlayer.getPlayerFaithPosition());
    }

    /**
     * print the base production
     */
    public static void baseProductionGenerator() {
        System.out.println(CLIColours.BLUE_UNDERLINED + "Every player has a base production witch can be activated during the action!" + CLIColours.ANSI_RESET );
        System.out.println("You can turn 2 resources into 1 resource of your choice except for RedCross");
    }

    /**
     * prints all the command help usable by the player
     */
    public static void generateHelpTable() {
        System.out.println("This is the help table, here are listed all the commands you need to play: ");
        System.out.println("For more info about a specific action type help + action_name.");
        System.out.println("ROUND ZERO ACTIONS:");
        System.out.println("chooseResource - if you're not the first player you can choose 1 or 2 resources;");
        System.out.println("discardLeader - discard 2 of 4 leader to start the game.");
        System.out.println("GAME ACTIONS:");
        System.out.println("show - print on video what you need;");
        System.out.println("action pickMarbles - get marbles form the marble market;");
        System.out.println("action buyDevCard - buy a development card from the dev card market;");
        System.out.println("action produce - activate the production of a dev card / leader card;");
        System.out.println("move - move resources in the warehouse;");
        System.out.println("leader - activate/discard a leadercard;");
        System.out.println("endTurn - to end your turn;");
        System.out.println("quit - to quit the game.");
        System.out.println(CLIColours.ANSI_CYAN + "Remember, numbers are in \"code\" mode, meaning the first row, for example, needs to be specified as 0, and so on." + CLIColours.ANSI_RESET);
    }

    /**
     * prints all the command show usable by the player
     */
    public static void generateHelpShow() {
        System.out.println("HELP -> SHOW COMMAND");
        System.out.println(CLIColours.ANSI_PURPLE + "show - plots your player board;" + CLIColours.ANSI_RESET);
        System.out.println(CLIColours.ANSI_PURPLE + "show player player_name - plots the specified player's player board;" + CLIColours.ANSI_RESET);
        System.out.println(CLIColours.ANSI_PURPLE + "show devCardMarket- plots the development card market;" + CLIColours.ANSI_RESET);
        System.out.println(CLIColours.ANSI_PURPLE + "show marbleMarket - plots the marble market." + CLIColours.ANSI_RESET);
    }

    /**
     * prints all the command pick usable by the player
     */
    public static void generateHelpPickMarbles() {
        System.out.println("HELP -> PICK MARBLES COMMAND");
        System.out.println("action pickMarbles [row/column] num_of_coordinate [TOP/MIDDLE/BOTTOM]");
        System.out.println("EXAMPLE:\nI want to pick marbles from row 1 and I get (in order) red blue yellow blue. The message will be:");
        System.out.println(CLIColours.ANSI_PURPLE + "action pickMarbles row 1 middle top middle" + CLIColours.ANSI_RESET);
        System.out.println("to get one coin in warehouse.top and two shield in warehouse.middle.");
        System.out.println("Remember, you can make only one main action in a turn!");
    }

    /**
     * prints all the command helpBuy usable by the player
     */
    public static void generateHelpBuyDevCard() {
        System.out.println("HELP -> BUY DEV CARD COMMAND");
        System.out.println("action buyDevCard num_row num_column num_position_personalBoard");
        System.out.println("EXAMPLE:\nI want to buy the dev card in row 1, column 2 and put it in my second slot of the personalBoard. The message will be:");
        System.out.println(CLIColours.ANSI_PURPLE + "action buyDevCard 1 2 1" + CLIColours.ANSI_RESET);
        System.out.println("Remember, you can make only one main action in a turn!");
    }

    /**
     * prints all the command helpProduce usable by the player
     */
    public static void generateHelpProduce() {
        System.out.println("HELP -> PRODUCE COMMAND");
        System.out.println("action produce [b + d1 + d2 + d2 + l1 + l2]");
        System.out.println("b for baseProduction, d for development card production and l for leader card production.");
        System.out.println("EXAMPLE:\nI want to activate the production of my first leaderCard and of the second and third dev cards. The message will be:");
        System.out.println(CLIColours.ANSI_PURPLE + "action produce d2, d3, l1" + CLIColours.ANSI_RESET);
        System.out.println("Remember, you can make only one main action in a turn!");
    }

    /**
     * prints all the command helpMove usable by the player
     */
    public static void generateHelpMove() {
        System.out.println("HELP -> MOVE COMMAND");
        System.out.println(CLIColours.ANSI_PURPLE + "move [TOP/MIDDLE/BOTTOM] to [TOP/MIDDLE/BOTTOM]" + CLIColours.ANSI_RESET);
        System.out.println("Move one or more resource(s) in the warehouse.");
    }

    /**
     * prints all the command helpLeader usable by the player
     */
    public static void generateHelpLeadercard() {
        System.out.println("HELP -> LEADERCARD COMMAND");
        System.out.println(CLIColours.ANSI_PURPLE + "leader [ACTIVATE/DISCARD] num_of_leaderCard" + CLIColours.ANSI_RESET);
        System.out.println("Num_of_leaderCard is either 0 or 1.");
    }

    /**
     * prints all the command helpChoose usable by the player
     */
    public static void generateHelpChooseResource() {
        System.out.println("HELP -> CHOOSE RESOURCE COMMAND");
        System.out.println(CLIColours.ANSI_PURPLE + "chooseResource [CO/SE/SH/ST/ANY]" + CLIColours.ANSI_RESET);
        System.out.println("ANY generate a casual resource.");
        System.out.println("Remember, if you're the fourth player you need to choose 2 resources by typing this command 2 times.");
    }

    /**
     * prints all the command helpDiscard usable by the player
     */
    public static void generateHelpDiscardLeader() {
        System.out.println("HELP -> DISCARD LEADERCARD COMMAND");
        System.out.println(CLIColours.ANSI_PURPLE + "discardLeader num_of_leader" + CLIColours.ANSI_RESET);
        System.out.println("Num_of_leaderCard is a number between 0 and 3.");
    }
}
