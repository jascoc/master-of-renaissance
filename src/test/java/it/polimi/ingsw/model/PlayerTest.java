package it.polimi.ingsw.model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TrackTiles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    Player player_1 = new Player();
    Player player_2 = new Player();
    /**
     * this method tests the movement of the player on the faith track
     */
    @Test
    public void moveFaithTrackTest(){
        for(int i = 0; i < 24; i++){
            player_1.increasePlayerFaithTrackPosition();
        }
        assertEquals(24,player_1.getFaithTrackPosition());
    }

    /**
     * this method tests the correct evolution of the track tiles
     */
    @Test
    public void activePopeFavorTailsTest(){
        TrackTiles trackTiles = new TrackTiles(player_2.getFaithTrackPosition());

            for(int i = 0; i < 8; i++){ player_2.increasePlayerFaithTrackPosition(); }

            for(int i = 0; i < 5; i++){ player_1.increasePlayerFaithTrackPosition(); }

        assertEquals(8,player_2.getFaithTrackPosition());
        assertEquals(5,player_1.getFaithTrackPosition());

        /*assertEquals(2, player_1.getVictoryPoints());
        assertEquals(2, player_1.getVictoryPoints());
        assertEquals(0, player_1.getVictoryPoints());
        assertEquals(0, player_1.getVictoryPoints());
        assertEquals(0, player_1.getVictoryPoints());
        assertEquals(0, player_1.getVictoryPoints());*/

            for(int i = 0; i < 8; i++){ player_2.increasePlayerFaithTrackPosition(); }

        assertEquals(16,player_2.getFaithTrackPosition());
        assertEquals(5,player_1.getFaithTrackPosition());

        /*assertEquals(2, player_1.getVictoryPoints());
        assertEquals(2, player_1.getVictoryPoints());
        assertEquals(0, player_1.getVictoryPoints());
        assertEquals(3, player_1.getVictoryPoints());
        assertEquals(0, player_1.getVictoryPoints());
        assertEquals(0, player_1.getVictoryPoints());*/

            for(int i = 0; i < 8; i++){ player_2.increasePlayerFaithTrackPosition(); }

            for(int i = 0; i < 16; i++){ player_1.increasePlayerFaithTrackPosition(); }

        assertEquals(24,player_2.getFaithTrackPosition());
        assertEquals(21,player_1.getFaithTrackPosition());

        /*assertEquals(2, player_1.getVictoryPoints());
        assertEquals(2, player_1.getVictoryPoints());
        assertEquals(0, player_1.getVictoryPoints());
        assertEquals(3, player_1.getVictoryPoints());
        assertEquals(4, player_1.getVictoryPoints());
        assertEquals(4, player_1.getVictoryPoints());*/
    }
}
