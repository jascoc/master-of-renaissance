package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.commands.RoundZeroCommand;
import it.polimi.ingsw.network.client.Minimal.LeaderCardMinimal;
import it.polimi.ingsw.network.messages.Message;

import java.util.ArrayList;

public class RoundZeroMessage extends Message {

    private ArrayList<LeaderCardMinimal> leaderCards;
    private ArrayList<String> resource;
    private String playerName;
    private int playerRoundPosition;

    public RoundZeroMessage(ArrayList<LeaderCardMinimal> leaderCards, ArrayList<String> resource, String playerName, int playerRoundPosition) {
        this.leaderCards = leaderCards;
        this.resource = resource;
        this.playerName = playerName;
        this.playerRoundPosition = playerRoundPosition;
    }

    public ArrayList<LeaderCardMinimal> getLeaderCards() { return leaderCards; }

    public void setLeaderCards(ArrayList<LeaderCardMinimal> leaderCards) { this.leaderCards = leaderCards; }

    public ArrayList<String> getResource() { return resource; }

    public void setResource(ArrayList<String> resource) { this.resource = resource; }

    public int getPlayerRoundPosition() { return playerRoundPosition; }

    public void setPlayerRoundPosition(int playerRoundPosition) { this.playerRoundPosition = playerRoundPosition; }

    @Override
    public void activeMessage(GameModel gameModel) {
        RoundZeroCommand command = new RoundZeroCommand();
        command.executeCommand(this, gameModel, playerName);
    }

}
