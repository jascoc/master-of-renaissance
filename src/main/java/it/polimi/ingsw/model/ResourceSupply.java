package it.polimi.ingsw.model;

import it.polimi.ingsw.model.resources.*;

import java.util.ArrayList;

/**
 * This class is a Resource hub which contains infinite resources. This is why is quite useless.
 */
public class ResourceSupply {

    private final ArrayList<Resource> resourceStructure = new ArrayList<>(4);
    private final Coin coin = new Coin();
    private final Servant servant = new Servant();
    private final Shield shield = new Shield();
    private final Stone stone = new Stone();

    /**
     * The constructor fill the List with the 4 type of resources, quite useless if resources are unlimited.
     */
    public ResourceSupply (){
        this.resourceStructure.add(0, coin.getResource());
        this.resourceStructure.add(1, servant.getResource());
        this.resourceStructure.add(2, shield.getResource());
        this.resourceStructure.add(3, stone.getResource());
    }

    /**
     * Getter of ResourceStructure.
     */
    public ArrayList<Resource> getResourceStructure(){ return resourceStructure; }

    /**
     * This method is quite useless, is just for future changes, but for now resources are infinite.
     * @return the needed resource.
     */
    public Resource getResource(Resource neededResource){
        return neededResource;
    }
}