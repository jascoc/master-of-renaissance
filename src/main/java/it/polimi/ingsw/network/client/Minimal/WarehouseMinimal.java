package it.polimi.ingsw.network.client.Minimal;

import java.util.ArrayList;

/**
 * Minimal class are simplified version, with only getter and setters
 * of the classes in the model, they are used in the client to not have
 * any side effect
 */
public class WarehouseMinimal {

    private String top;
    private ArrayList<String> middle;
    private ArrayList<String> bottom;

    /**
     * Constructor
     */
    public WarehouseMinimal() {
        middle = new ArrayList<>(2);
        bottom = new ArrayList<>(3);
    }

    /**
     * Getters and Setters
     */
    public String getTop() { return top; }

    public void setTop(String top) { this.top = top; }

    public ArrayList<String> getMiddle() { return middle; }

    public void setMiddle(ArrayList<String> middle) { this.middle = middle; }

    public ArrayList<String> getBottom() { return bottom; }

    public void setBottom(ArrayList<String> bottom) { this.bottom = bottom; }

    public void addMiddle(String res) { if(middle.size() < 2) { middle.add(res); } }

    public void addBottom(String res) { if(bottom.size() < 3) { bottom.add(res); } }

}
