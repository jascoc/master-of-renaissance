package it.polimi.ingsw;

import it.polimi.ingsw.changes.LeaderCardChanges;
import it.polimi.ingsw.changes.StrongboxChanges;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;
import it.polimi.ingsw.network.server.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static it.polimi.ingsw.Variables.*;

/**
 * Class used to add advantages to player in order to test the game quickly.
 */
public class SetTestAdvantages {

    private ArrayList<Integer> num = new ArrayList<>();

    public void addAdvantages(GameModel gameModel, Player player) {
        for(int i = 0; i < 10; i ++) {
            player.getPersonalBoard().getStrongBox().add(new Coin());
            player.getPersonalBoard().getStrongBox().add(new Servant());
            player.getPersonalBoard().getStrongBox().add(new Shield());
            player.getPersonalBoard().getStrongBox().add(new Stone());
        }
        StrongboxChanges changeStrongbox = new StrongboxChanges(player.getRoundPosition(), player.getPersonalBoard().getStrongBox());
        for(Controller controller : gameModel.getControllers()) { controller.notify(changeStrongbox); }

        for(LeaderCard card : player.getLeaderCardList()) {
            card.setActivated();
            LeaderCardChanges changeLeader = new LeaderCardChanges(player.getRoundPosition(),
                    player.getLeaderCardList().indexOf(card), player.getLeaderCardList().get(player.getLeaderCardList().indexOf(card)), ACTIVATED);
            for (Controller controller : gameModel.getControllers()) {
                controller.notify(changeLeader);
            }
        }

    }
}
