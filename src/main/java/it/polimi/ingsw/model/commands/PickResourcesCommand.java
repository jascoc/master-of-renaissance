package it.polimi.ingsw.model.commands;

import it.polimi.ingsw.changes.ErrorChanges;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.exceptions.NotEnoughSpaceException;
import it.polimi.ingsw.model.leaderCards.ExtraSpaceLeader;
import it.polimi.ingsw.model.resources.*;
import it.polimi.ingsw.network.messages.PickResourcesMessage;

import static it.polimi.ingsw.Variables.*;

import java.util.ArrayList;

/**
 * This class implements the command to notify the picking of a marble
 */
public class PickResourcesCommand extends Command {

    /**
     * Used to choose the column or the row from the marble market when the actual message arrives
     * @param message message from the client
     * @param gameModel game model
     */
    public void executeCommand(PickResourcesMessage message, GameModel gameModel) {
        ArrayList<Resource> resources = new ArrayList<>();

        if(message.getChosenCoords().equals(ROW)) { resources = gameModel.getMarketMarbleStructure().chooseRow(message.getCoordsNumber(), gameModel); }
        else if(message.getChosenCoords().equals(COLUMN)) { resources = gameModel.getMarketMarbleStructure().chooseColumn(message.getCoordsNumber(), gameModel); }
        else { System.err.println("ERROR: CHOSEN COORDS NOT VALID."); } //Checked already in client, this is just for safe.

        //System.out.println("Resources " + resources);
        resources.removeIf((res) -> res.toString().equals(WHITE) || res.toString().equals(RED_CROSS));
        //System.out.println("Resources without red and white " + resources);

        for(int i = 0; i < resources.size(); i ++) {
            switch (message.getResourceWarehouse().get(i)) {
                case TOP:
                    gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceTop(gameModel, resources.get(i));
                    break;
                case MIDDLE:
                    gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceMiddle(gameModel, resources.get(i));
                    break;
                case BOTTOM:
                    gameModel.getActivePlayer().getPersonalBoard().getWarehouse().addResourceBottom(gameModel, resources.get(i));
                    break;
                case L1:
                    if(gameModel.getActivePlayer().getLeaderCardList().get(0) instanceof ExtraSpaceLeader) {
                        ExtraSpaceLeader leader = (ExtraSpaceLeader) gameModel.getActivePlayer().getLeaderCardList().get(0);
                        try { leader.addResource(gameModel, message.getResourceWarehouse().get(i), resources.get(i)); }
                        catch (NotEnoughSpaceException e) { }
                    }
                    else {
                        gameModel.getActivePlayer().getController().notify(new ErrorChanges(-1, "Leader 1 is not a Extra Space Leader!!", NOT_EXTRA_SPACE));
                    }
                    break;
                case L2:
                    if(gameModel.getActivePlayer().getLeaderCardList().get(1) instanceof ExtraSpaceLeader) {
                        ExtraSpaceLeader leader = (ExtraSpaceLeader) gameModel.getActivePlayer().getLeaderCardList().get(1);
                        try { leader.addResource(gameModel, message.getResourceWarehouse().get(i), resources.get(i)); }
                        catch (NotEnoughSpaceException e) { }
                    }
                    else {
                        gameModel.getActivePlayer().getController().notify(new ErrorChanges(-1, "Leader 2 is not a Extra Space Leader!!", NOT_EXTRA_SPACE));
                    }
                    break;
                default: gameModel.getActivePlayer().getController().notify(new ErrorChanges(gameModel.getActivePlayer().getRoundPosition(), "Wrong marble positioning!!", WRONG_MARBLE_POSITIONING));
            }
        }
    }
}