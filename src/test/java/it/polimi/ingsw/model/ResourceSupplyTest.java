package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.ResourceSupply;
import it.polimi.ingsw.model.resources.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is totally useless, we didn't use the resource supply in our project.
 */
public class ResourceSupplyTest {

    private ResourceSupply resourceSupply = new ResourceSupply();

    /**
     * Almost useless test. Used to see if the getResource method returns the wanted resource.
     */
    @Test
    public void tryGetWantedResource(){
        Resource wantedResource = resourceSupply.getResource(new Coin());
        assertTrue(wantedResource instanceof Coin);

        wantedResource = resourceSupply.getResource(new Servant());
        assertTrue(wantedResource instanceof Servant);

        wantedResource = resourceSupply.getResource(new Shield());
        assertTrue(wantedResource instanceof Shield);

        wantedResource = resourceSupply.getResource(new Stone());
        assertTrue(wantedResource instanceof Stone);
    }

}
