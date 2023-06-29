package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.exceptions.ErrorInMessageException;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.commands.ChooseLeaderCommand;
import it.polimi.ingsw.network.client.Minimal.LeaderCardMinimal;
import it.polimi.ingsw.network.messages.Message;

public class ChooseLeaderMessage extends Message {

    private String leaderCardAction;
    private int positionLeaderCard;

    public ChooseLeaderMessage(String leaderCardAction, int positionLeaderCard) {
        this.leaderCardAction = leaderCardAction;
        this.positionLeaderCard = positionLeaderCard;
    }

    public String getLeaderCardAction() { return leaderCardAction; }

    public void setLeaderCardAction(String leaderCardAction) { this.leaderCardAction = leaderCardAction; }

    public int getPositionLeaderCard() { return positionLeaderCard; }

    public void setPositionLeaderCard(int positionLeaderCard) { this.positionLeaderCard = positionLeaderCard; }

    @Override
    public void activeMessage(GameModel gameModel) throws NotEnoughResourcesException, ErrorInMessageException {
        ChooseLeaderCommand command = new ChooseLeaderCommand();
        command.executeCommand(this, gameModel);
    }

}
