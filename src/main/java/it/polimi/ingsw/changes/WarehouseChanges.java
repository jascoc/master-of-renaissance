package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.Warehouse;
import it.polimi.ingsw.network.client.Minimal.WarehouseMinimal;

import java.util.ArrayList;
import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of the warehouse
 */
public class WarehouseChanges extends Changes {

    private ArrayList<String> resourcesInWareHouse;
    private WarehouseMinimal warehouseMinimal ;
    private String playerName;

    /**
     * Constructor
     * @param playerRoundNumber player round position
     * @param resourcesInWareHouse resource in warehouse
     * @param playerName player name
     */
    public WarehouseChanges(int playerRoundNumber, ArrayList<String> resourcesInWareHouse, String playerName) {
        super(playerRoundNumber, WAREHOUSE_CHANGES);
        this.resourcesInWareHouse = resourcesInWareHouse;
        this.warehouseMinimal = warehouseToMinimal(resourcesInWareHouse);
        this.playerName = playerName;
    }

    /**
     * Converts
     * @param resourcesInWareHouse from Resource type into
     * @return Minimal one
     */
    private WarehouseMinimal warehouseToMinimal (ArrayList<String> resourcesInWareHouse){
        WarehouseMinimal warehouseMinimal = new WarehouseMinimal();
        warehouseMinimal.setTop(resourcesInWareHouse.get(0));
        warehouseMinimal.getMiddle().add(resourcesInWareHouse.get(1));
        warehouseMinimal.getMiddle().add(resourcesInWareHouse.get(2));
        warehouseMinimal.getBottom().add(resourcesInWareHouse.get(3));
        warehouseMinimal.getBottom().add(resourcesInWareHouse.get(4));
        warehouseMinimal.getBottom().add(resourcesInWareHouse.get(5));
        return warehouseMinimal;
    }

    /**
     * Getter and Setter
     */
    public ArrayList<String> getResourcesInWareHouse() { return resourcesInWareHouse; }

    public String getPlayerName() { return playerName; }

    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public WarehouseMinimal getWarehouseMinimal() { return warehouseMinimal; }

    public void setWarehouseMinimal(WarehouseMinimal warehouseMinimal) { this.warehouseMinimal = warehouseMinimal; }

}
