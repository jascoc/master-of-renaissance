package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.tokens.ActionToken;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of Single Player
 */
public class EndSinglePlayerTurnChanges extends Changes {

    private ArrayList<String> tokens;
    private String token;
    private int faithTrackPosition;

    /**
     * Ends the single player round
     * @param playerRoundNumber the position of the player in the round
     * @param tokens list of action token
     */
    public EndSinglePlayerTurnChanges(int playerRoundNumber, ArrayList<ActionToken> tokens) {
        super(playerRoundNumber, END_SINGLE_PLAYER_TURN);
        this.tokens = convertTokens(tokens);
    }

    /**
     * Ends the single player round
     * @param playerRoundNumber the position of the player in the round
     * @param token action token
     * @param faithTrackPosition the position in the faith track
     */
    public EndSinglePlayerTurnChanges(int playerRoundNumber, ActionToken token, int faithTrackPosition) {
        super(playerRoundNumber, END_SINGLE_PLAYER_TURN);
        this.token = token.toString();
        this.faithTrackPosition = faithTrackPosition;
    }

    /**
     * Converts
     * @param tokens from ActionToken to
     * @return a list of String
     */
    private ArrayList<String> convertTokens(ArrayList<ActionToken> tokens) {
        ArrayList<String> tokensString = new ArrayList<>();
        for(ActionToken token : tokens) { tokensString.add(token.toString()); }
        return tokensString;
    }

    /**
     * Getters and Setters
     */
    public ArrayList<String> getTokens() { return tokens; }

    public void setTokens(ArrayList<String> tokens) { this.tokens = tokens; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public int getFaithTrackPosition() { return faithTrackPosition; }

    public void setFaithTrackPosition(int faithTrackPosition) { this.faithTrackPosition = faithTrackPosition; }
}
