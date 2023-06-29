package it.polimi.ingsw.changes;

import static it.polimi.ingsw.Variables.VICTORY_POINTS_CHANGES;

public class VictoryPointsChanges extends Changes {

    private int victoryPoints;

    public VictoryPointsChanges(int playerRoundNumber, int victoryPoints) {
        super(playerRoundNumber, VICTORY_POINTS_CHANGES);
        this.victoryPoints = victoryPoints;
    }

    public int getVictoryPoints() { return victoryPoints; }

    public void setVictoryPoints(int victoryPoints) { this.victoryPoints = victoryPoints; }

}
