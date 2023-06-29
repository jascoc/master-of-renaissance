package it.polimi.ingsw.model;

import it.polimi.ingsw.model.TrackTiles;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrackTilesTest {

    /**
     * Test the right placement of the trackTiles.
     */
    @Test
    public void rightPlacement (){
        for(int i = 0; i < 30; i ++){
                TrackTiles tr1 = new TrackTiles(i);
                if((i != 0) && ((i % 8) == 0))
                    assertTrue(tr1.isPopeSpace());
            if(i >= 5 && i <= 8)
                assertTrue(tr1.isReportSection(0));
            else if(i >= 12 && i <= 16)
                assertTrue(tr1.isReportSection(1));
            else if(i >= 19 && i <= 24)
                assertTrue(tr1.isReportSection(2));

            tr1.setIsOccupied();
            assertTrue(tr1.getIsOccupied());
        }
    }

    /**
     * Some check for the reportSections.
     */
    @Test
    public void reportSection(){
        TrackTiles[] track = new TrackTiles[25];
        for(int i = 0; i < 25; i ++)
            track[i] = new TrackTiles(i);

        assertTrue(track[7].isReportSection(0));
        assertTrue(!track[7].isReportSection(1));
        assertTrue(!track[7].isReportSection(2));

        assertTrue(!track[15].isReportSection(0));
        assertTrue(track[15].isReportSection(1));
        assertTrue(!track[15].isReportSection(2));

        assertTrue(!track[22].isReportSection(0));
        assertTrue(!track[22].isReportSection(1));
        assertTrue(track[22].isReportSection(2));

        assertFalse(track[1].isReportSection(0));
        assertFalse(track[1].isReportSection(1));
        assertFalse(track[1].isReportSection(2));
    }


    /**
     * Some check for the popeSpaces.
     */
    @Test
    public void popeSpace(){
        TrackTiles[] track = new TrackTiles[25];
        for(int i = 0; i < 25; i ++)
            track[i] = new TrackTiles(i);

        assertTrue(track[8].isPopeSpace());
        assertTrue(track[16].isPopeSpace());
        assertTrue(track[24].isPopeSpace());
        assertTrue(!track[1].isPopeSpace());
        assertTrue(!track[13].isPopeSpace());
        assertTrue(!track[20].isPopeSpace());
    }
}