package it.polimi.ingsw.network.client.Minimal;

import java.util.ArrayList;
import java.util.List;

/**
 * Minimal class are simplified version, with only getter and setters
 * of the classes in the model, they are used in the client to not have
 * any side effect
 */
public class SinglePlayerMinimal extends PlayerMinimal {

    private String token;

    /**
     * Constructor
     */
    public SinglePlayerMinimal() { }

    /**
     * Getter and Setter
     */
    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }


}
