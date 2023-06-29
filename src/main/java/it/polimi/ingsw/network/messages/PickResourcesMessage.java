package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.commands.PickResourcesCommand;
import it.polimi.ingsw.network.messages.Message;

import java.util.ArrayList;

public class PickResourcesMessage extends Message {

    private String chosenCoords;
    private int coordsNumber;
    private ArrayList<String> resourceWarehouse;

    public PickResourcesMessage(String chosenCoords, int coordsNumber, ArrayList<String> resourceWarehouse) {
        this.chosenCoords = chosenCoords;
        this.coordsNumber = coordsNumber;
        this.resourceWarehouse = resourceWarehouse;
    }

    public String getChosenCoords() { return chosenCoords; }

    public void setChosenCoords(String chosenCoords) { this.chosenCoords = chosenCoords; }

    public int getCoordsNumber() { return coordsNumber; }

    public void setCoordsNumber(int coordsNumber) { this.coordsNumber = coordsNumber; }

    public ArrayList<String> getResourceWarehouse() { return resourceWarehouse; }

    public void setResourceWarehouse(ArrayList<String> resourceWarehouse) { this.resourceWarehouse = resourceWarehouse; }

    /**
     * Change the message parameters in data used as input for methods. In here we change the current model state.
     */
    @Override
    public void activeMessage(GameModel gameModel) {
        PickResourcesCommand command = new PickResourcesCommand();
        command.executeCommand(this, gameModel);
    }
}
