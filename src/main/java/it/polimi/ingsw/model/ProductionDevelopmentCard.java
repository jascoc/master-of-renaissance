package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.model.leaderCards.ExtraSpaceLeader;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;


/**
 * The concrete part of the decorator where i finally implements the methods
 */
public class ProductionDevelopmentCard extends DevelopmentCardDecorator{

    /**
     * Constructor of DevCards, used in ProductionDevelopmentCard.
     * @param lvl card lvl
     * @param colour card colour
     * @param cardPrice card price
     * @param victoryPoints card victory points
     * @param productionPrice production price
     */
    public ProductionDevelopmentCard(int lvl, String colour, ArrayList<String> cardPrice, int victoryPoints, ArrayList<String> productionPrice) {
        super(lvl, colour, cardPrice, victoryPoints, productionPrice);
    }

    /**
     * Constructor
     * @param lvl card lvl
     * @param colour card colour
     * @param cardPrice card price
     * @param victoryPoints card victory points
     * @param productionPrice production price
     * @param production the resource produced
     */
    public ProductionDevelopmentCard(int lvl, String colour, ArrayList<String> cardPrice, int victoryPoints, ArrayList<String> productionPrice, ArrayList<String> production) {
        super(lvl, colour, cardPrice, victoryPoints, productionPrice, production);
    }


    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * these methods do the overloading for the production,
     * so based on the signature and the victoryPoints that the card gives they return the resource that the production actually produces
     */
    public List<Resource> activeProduction(GameModel gameModel, Shield shield) {
        Player player = gameModel.getActivePlayer();
        Boolean activatable = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();
        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield, gameModel);
            activatable = true;
        }
        if(!activatable) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable = true;
                        }
                    }
                    if (!activatable) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable = true;
                        }
                    }
                    if (!activatable) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 12:
                development.add(new Stone());
                development.add(new Servant());
                development.add(new Servant());
                development.add(new Servant());
                break;
            case 11:
                development.add(new Servant());
                gameModel.moveFaithTrack(3, player);
                break;
            case 8:
                development.add(new Coin());
                development.add(new Coin());
                gameModel.moveFaithTrack(1, player);

                break;
            case 5:
                gameModel.moveFaithTrack(2, player);

                break;
            case 2:
                development.add(new Coin());
                break;
            case 1:
                gameModel.moveFaithTrack(1, player);
                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable)
            return development;
        else
            return new ArrayList<>();

    }

    public List<Resource> activeProduction(GameModel gameModel, Servant servant){
        Player player = gameModel.getActivePlayer();
        Boolean activatable = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();
        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant, gameModel);
            activatable=true;
        }
        if(!activatable) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable = true;
                        }
                    }
                    if(!activatable) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable = true;
                        }
                    }
                    if(!activatable) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable=true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 12:
                development.add(new Coin());
                development.add(new Shield());
                development.add(new Shield());
                development.add(new Shield());
                break;
            case 11:
                development.add(new Coin());
                gameModel.moveFaithTrack(3, player);

                break;
            case 8:
                development.add(new Stone());
                development.add(new Stone());
                gameModel.moveFaithTrack(1, player);

                break;
            case 5:
                gameModel.moveFaithTrack(2, player);

                break;
            case 2:
                development.add(new Stone());
                break;
            case 1:
                gameModel.moveFaithTrack(1, player);

                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable)
            return development;
        else
            return new ArrayList<Resource>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Coin coin){
        Player player = gameModel.getActivePlayer();
        Boolean activatable = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();
        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin, gameModel);
            activatable =true;
        }
        if(!activatable) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable = true;
                        }
                    }
                    if (!activatable) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable = true;
                        }
                    }
                    if (!activatable) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 12:
                development.add(new Servant());
                development.add(new Stone());
                development.add(new Stone());
                development.add(new Stone());
                break;
            case 11:
                development.add(new Stone());
                gameModel.moveFaithTrack(3, player);

                break;
            case 8:
                development.add(new Shield());
                development.add(new Shield());
                gameModel.moveFaithTrack(1, player);

                break;
            case 5:
                gameModel.moveFaithTrack(2, player);

                break;
            case 2:
                development.add(new Shield());
                break;
            case 1:
                gameModel.moveFaithTrack(1, player);

                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable)
            return development;
        else
            return new ArrayList<Resource>();

    }

    public List<Resource> activeProduction(GameModel gameModel, Stone stone){
        Player player = gameModel.getActivePlayer();
        Boolean activatable = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone, gameModel);
            activatable = true;
        }
        if(!activatable) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable = true;
                        }
                    }
                    if (!activatable) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable = true;
                        }
                    }
                    if (!activatable) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==stone.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 12:
                development.add(new Shield());
                development.add(new Coin());
                development.add(new Coin());
                development.add(new Coin());
                break;
            case 11:
                development.add(new Shield());
                gameModel.moveFaithTrack(3, player);

                break;
            case 8:
                development.add(new Servant());
                development.add(new Servant());
                gameModel.moveFaithTrack(1, player);

                break;
            case 5:
                gameModel.moveFaithTrack(2, player);

                break;
            case 2:
                development.add(new Servant());
                break;
            case 1:
                gameModel.moveFaithTrack(1, player);

                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable)
            return development;
        else
            return new ArrayList<Resource>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Stone stone, Servant servant){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;

        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==stone.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        if(getVictoryPoints()==10){
            development.add(new Shield());
            development.add(new Shield());
            development.add(new Coin());
            development.add(new Coin());
            gameModel.moveFaithTrack(1, player);
            
        }
        else if (getVictoryPoints() == 4){
            development.add(new Coin());
            development.add(new Coin());
            gameModel.moveFaithTrack(1, player);
            
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Coin coin, Shield shield){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        if(getVictoryPoints()==10){
            development.add(new Servant());
            development.add(new Servant());
            development.add(new Stone());
            development.add(new Stone());
            gameModel.moveFaithTrack(1, player);
            
        }
        else if (getVictoryPoints()==4){
            development.add(new Stone());
            development.add(new Stone());
            gameModel.moveFaithTrack(1, player);
            
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Stone stone, Shield shield){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==stone.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 10:
                development.add(new Coin());
                development.add(new Coin());
                development.add(new Servant());
                development.add(new Servant());
                gameModel.moveFaithTrack(1, player);

                break;
            case 6:
                development.add(new Coin());
                development.add(new Coin());
                development.add(new Coin());
                break;
            case 4:
                development.add(new Servant());
                development.add(new Servant());
                gameModel.moveFaithTrack(1, player);

                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Coin coin, Servant servant){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 10:
                development.add(new Stone());
                development.add(new Stone());
                development.add(new Shield());
                development.add(new Shield());
                gameModel.moveFaithTrack(1, player);

                break;
            case 6:
                development.add(new Shield());
                development.add(new Shield());
                development.add(new Shield());
                break;
            case 4:
                development.add(new Shield());
                development.add(new Shield());
                gameModel.moveFaithTrack(1, player);

                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Shield shield1, Shield shield2){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield1)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield1, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield1.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield1.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield1.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield1.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield1.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield2)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield2, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield2.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield2.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield2.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield2.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield2.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 9:
                development.add(new Servant());
                development.add(new Servant());
                development.add(new Servant());
                gameModel.moveFaithTrack(2, player);

                break;
            case 7:
                development.add(new Servant());
                development.add(new Servant());
                gameModel.moveFaithTrack(2, player);

                break;
            case 3:
                development.add(new Coin());
                development.add(new Servant());
                development.add(new Stone());
                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Servant servant1, Servant servant2){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant1)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant1, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant1.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant1.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant1.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant1.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant1.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant2)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant2, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant2.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant2.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2 && false) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant2.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant2.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant2.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 9:
                development.add(new Shield());
                development.add(new Shield());
                development.add(new Shield());
                gameModel.moveFaithTrack(2, player);

                break;
            case 7:
                development.add(new Shield());
                development.add(new Shield());
                gameModel.moveFaithTrack(2, player);

                break;
            case 3:
                development.add(new Coin());
                development.add(new Shield());
                development.add(new Stone());
                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Stone stone1, Stone stone2){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone1)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone1, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone1.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone1.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2 && false) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone1.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone1.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==stone1.toString()){
                    player.getPersonalBoard().getStrongBox().remove(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone2)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone2, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone2.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone2.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone2.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone2.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==stone2.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 9:
                development.add(new Coin());
                development.add(new Coin());
                development.add(new Coin());
                gameModel.moveFaithTrack(2, player);

                break;
            case 7:
                development.add(new Coin());
                development.add(new Coin());
                gameModel.moveFaithTrack(2, player);

                break;
            case 3:
                development.add(new Coin());
                development.add(new Servant());
                development.add(new Shield());
                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Coin coin1, Coin coin2){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin1)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin1, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin1.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin1.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin1.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin1.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin1.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin2)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin2, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin2.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin2.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2 && false) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin2.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin2.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin2.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 9:
                development.add(new Stone());
                development.add(new Stone());
                development.add(new Stone());
                gameModel.moveFaithTrack(2, player);

                break;
            case 7:
                development.add(new Stone());
                development.add(new Stone());
                gameModel.moveFaithTrack(2, player);

                break;
            case 3:
                development.add(new Shield());
                development.add(new Servant());
                development.add(new Stone());
                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Shield shield, Servant servant){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        if(getVictoryPoints()==6){
            development.add(new Stone());
            development.add(new Stone());
            development.add(new Stone());
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Coin coin, Stone stone){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==stone.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        if(getVictoryPoints()==6){
            development.add(new Servant());
            development.add(new Servant());
            development.add(new Servant());
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    /**
     * writing the same method but with the signature inverted (eg. from Coin coin, Stone stone to Stone stone, Coin coin)
     */
    public List<Resource> activeProduction(GameModel gameModel, Servant servant, Stone stone){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==stone.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        if(getVictoryPoints()==10){
            development.add(new Shield());
            development.add(new Shield());
            development.add(new Coin());
            development.add(new Coin());
            gameModel.moveFaithTrack(1, player);
            
        }
        else if (getVictoryPoints()==4){
            development.add(new Coin());
            development.add(new Coin());
            gameModel.moveFaithTrack(1, player);
            
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Shield shield, Coin coin){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        if(getVictoryPoints()==10){
            development.add(new Servant());
            development.add(new Servant());
            development.add(new Stone());
            development.add(new Stone());
            gameModel.moveFaithTrack(1, player);
            
        }
        else if (getVictoryPoints()==4){
            development.add(new Stone());
            development.add(new Stone());
            gameModel.moveFaithTrack(1, player);
            
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Shield shield, Stone stone) {
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for (Player p : gameModel.getPlayerList()) {
            if (p.isActivePlayer()) {
                player = p;
            }
        }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone)) {
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone, gameModel);
            activatable1 = true;
        }
        if (!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if (!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if (!activatable1) {
            for (Resource resource : player.getPersonalBoard().getStrongBox()) {
                if (resource.toString() == stone.toString()) {
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield, gameModel);
            activatable2 = false;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = false;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 10:
                development.add(new Coin());
                development.add(new Coin());
                development.add(new Servant());
                development.add(new Servant());
                gameModel.moveFaithTrack(1, player);

                break;
            case 6:
                development.add(new Coin());
                development.add(new Coin());
                development.add(new Coin());
                break;
            case 4:
                development.add(new Servant());
                development.add(new Servant());
                gameModel.moveFaithTrack(1, player);

                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Servant servant, Coin coin){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        switch (getVictoryPoints()) {
            case 10:
                development.add(new Stone());
                development.add(new Stone());
                development.add(new Shield());
                development.add(new Shield());
                gameModel.moveFaithTrack(1, player);
                break;
            case 6:
                development.add(new Shield());
                development.add(new Shield());
                development.add(new Shield());
                break;
            case 4:
                development.add(new Shield());
                development.add(new Shield());
                gameModel.moveFaithTrack(1, player);
                break;
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Servant servant, Shield shield){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }

        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(shield)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(shield, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == shield.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == shield.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==shield.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(servant)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(servant, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == servant.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == servant.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==servant.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        if(getVictoryPoints()==6){
            development.add(new Stone());
            development.add(new Stone());
            development.add(new Stone());
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }

    public List<Resource> activeProduction(GameModel gameModel, Stone stone, Coin coin){
        Player player = gameModel.getActivePlayer();
        Boolean activatable1 = false;
        Boolean activatable2 = false;
        for(Player p : gameModel.getPlayerList()) { if(p.isActivePlayer()) { player = p; } }
        
        List<Resource> development = new ArrayList<>();

        if (player.getPersonalBoard().getWarehouse().findResourceByName(stone)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(stone, gameModel);
            activatable1 = true;
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == stone.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable1 = true;
                        }
                    }
                    if (!activatable1) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == stone.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable1 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable1) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==stone.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable1 = true;
                    break;
                }
            }
        }
        if (player.getPersonalBoard().getWarehouse().findResourceByName(coin)){
            player.getPersonalBoard().getWarehouse().removeResourceByName(coin, gameModel);
            activatable2 = true;
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() >= 1) {
                if (player.getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(0)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            if (player.getLeaderCardList().size() == 2) {
                if (player.getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                    if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0) != null) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(0).toString() == coin.toString()) {
                            ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(0);
                            activatable2 = true;
                        }
                    }
                    if (!activatable2) {
                        if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1) != null) {
                            if (((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().get(1).toString() == coin.toString()) {
                                ((ExtraSpaceLeader) player.getLeaderCardList().get(1)).getExtraResources().remove(1);
                                activatable2 = true;
                            }
                        }
                    }
                }
            }
        }
        if(!activatable2) {
            for (Resource resource: player.getPersonalBoard().getStrongBox()) {
                if(resource.toString()==coin.toString()){
                    player.getPersonalBoard().removeFromStrongbox(resource);
                    activatable2 = true;
                    break;
                }
            }
        }
        if(getVictoryPoints()==6){
            development.add(new Servant());
            development.add(new Servant());
            development.add(new Servant());
        }
       // player.getPersonalBoard().getStrongBox().addAll(development);
        if(activatable1&&activatable2)
            return  development;
        else
            return new ArrayList<>();
    }
}