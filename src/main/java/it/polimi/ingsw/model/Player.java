package it.polimi.ingsw.model;

import it.polimi.ingsw.changes.LeaderCardChanges;
import it.polimi.ingsw.changes.VictoryPointsChanges;
import it.polimi.ingsw.model.leaderCards.LeaderCard;
import it.polimi.ingsw.network.server.Controller;
import java.util.ArrayList;
import java.lang.Math;

import static it.polimi.ingsw.Variables.DISCARDED;

/**
 * This class implements the player.
 */
public class Player {

    private String playerName;
    private boolean activePlayer = false;
    private int roundPosition;
    private int victoryPoints;
    private PersonalBoard personalBoard;
    private boolean winner = false;
    private int faithTrackPosition;
    private ArrayList<LeaderCard> leaderCardList;
    private Controller controller;

    private boolean disconnected = false;

    public Player() {
        leaderCardList = new ArrayList<>();
        personalBoard = new PersonalBoard(this);
    }

    /**
     * @return player's name
     */
    public String getName(){
        return playerName;
    }

    /**
     * set the player's name
     * @param playerName
     */
    public void setName(String playerName){
        this.playerName = playerName;
    }

    /**
     * @return player's round position (e.g. first one to play or second one to play)
     */
    public int getRoundPosition() {
        return roundPosition;
    }

    /**
     * set the player's round position (e.g. first one to play or second one to play)
     * @param roundPosition
     */
    public void setRoundPosition(int roundPosition){ this.roundPosition = roundPosition; }

    /**
     *  generics getters and setters
     */

    public int getVictoryPoints() { return victoryPoints; }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public boolean isActivePlayer() { return activePlayer; }

    public void isActivePlayer(boolean activePlayer) { this.activePlayer = activePlayer; }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public int getFaithTrackPosition() {
        return faithTrackPosition;
    }

    public void setFaithTrackPosition(int faithTrackPosition) {
        this.faithTrackPosition = faithTrackPosition;
    }

    public PersonalBoard getPersonalBoard() { return personalBoard; }

    public void setPersonalBoard(PersonalBoard personalBoard) {
        this.personalBoard = personalBoard;
    }

    public ArrayList<LeaderCard> getLeaderCardList() { return leaderCardList; }

    /**
     * Method used for adding a certain amount of victoryPoints to this player.
     * @param amount is the amount of victoryPoints added.
     */
    public void addVictoryPoints(int amount) {
        victoryPoints += amount;
        sendVictoryPointsChange();
    }

    /**
     * Send victoryPointsChange to all clients.
     */
    private void sendVictoryPointsChange() {
        VictoryPointsChanges change = new VictoryPointsChanges(roundPosition, victoryPoints);
        for(Controller controller : controller.getGameModel().getControllers()) {
            controller.notify(change);
        }
    }

    /**
     * This method increases the faithTrackPosition.
     */
    public void increasePlayerFaithTrackPosition() { faithTrackPosition ++; }

    /**
     * Set the leaderCardList. Used in the GameInitialization and never again.
     * @param leaderCardList
     */
    public void setLeaderCards(ArrayList<LeaderCard> leaderCardList) { this.leaderCardList.addAll(leaderCardList); }

    /**
     * Method used to discard a specified leaderCard
     */
    public void discardLeaderCard(GameModel gameModel, LeaderCard leaderCard) {
        int num = leaderCardList.indexOf(leaderCard);
        leaderCardList.remove(leaderCard);

        LeaderCardChanges change = new LeaderCardChanges(roundPosition, num, leaderCard, DISCARDED);
        for(Controller controller : controller.getGameModel().getControllers()) { controller.notify(change); }
    }

    /**
     * @return this player's controller.
     */
    public Controller getController() { return controller; }

    /**
     * Set a controller for this player.
     */
    public void setController(Controller controller) { this.controller = controller;}

    /**
     * Set disconnected state.
     */
    public void isDisconnected(boolean disconnected) { this.disconnected = disconnected; }

    /**
     * @return disconnected state.
     */
    public boolean getDisconnected() { return disconnected; }
}




