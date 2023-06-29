package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.commands.ActiveProductionCommand;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.network.messages.Message;
import java.util.ArrayList;

public class ActiveProductionMessage extends Message {
    /**
     * Update message with wanted resource and resourced use for baseDEV and leaderCardProductions.
     */
    private String wantedResourceBase;
    private String wantedResourceL1;
    private String wantedResourceL2;
    private ArrayList<String> usedResource;
    private ArrayList<String> chosenProduction;

    public ActiveProductionMessage(String wantedResourceBase, String wantedResourceL1, String wantedResourceL2, ArrayList<String> usedResource, ArrayList<String> chosenProduction) {
        this.wantedResourceBase = wantedResourceBase;
        this.wantedResourceL1 = wantedResourceL1;
        this.wantedResourceL2 = wantedResourceL2;
        this.usedResource = usedResource;
        this.chosenProduction = chosenProduction;
    }

    public String getWantedResourceBase() { return wantedResourceBase; }

    public void setWantedResourceBase(String wantedResourceBase) { this.wantedResourceBase = wantedResourceBase; }

    public String getWantedResourceL1() { return wantedResourceL1; }

    public void setWantedResourceL1(String wantedResourceL1) { this.wantedResourceL1 = wantedResourceL1; }

    public String getWantedResourceL2() { return wantedResourceL2; }

    public void setWantedResourceL2(String wantedResourceL2) { this.wantedResourceL2 = wantedResourceL2; }

    public ArrayList<String> getUsedResource() { return usedResource; }

    public void setUsedResource(ArrayList<String> usedResource) { this.usedResource = usedResource; }

    public ArrayList<String> getChosenProduction() { return chosenProduction; }

    public void setChosenProduction(ArrayList<String> chosenProduction) { this.chosenProduction = chosenProduction; }

    @Override
    public void activeMessage(GameModel gameModel) throws NotEnoughResourcesException {
        ActiveProductionCommand command = new ActiveProductionCommand();
        command.executeCommand(this, gameModel);
    }
}
