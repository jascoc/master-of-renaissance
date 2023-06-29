package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;
import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of the strongbox
 */
public class StrongboxChanges extends Changes {

    private ArrayList<String> resourcesInBox;

    /**
     * Constructor
     * @param playerRoundNumber player round position
     * @param resourcesInBox resources in the strongbox
     */
    public StrongboxChanges(int playerRoundNumber, ArrayList<Resource> resourcesInBox) {
        super(playerRoundNumber, STRONGBOX_CHANGES);
        this.resourcesInBox = strongboxToMinimal(resourcesInBox);
    }

    /**
     * Converts
     * @param strongbox from Resource type into
     * @return String
     */
    private ArrayList<String> strongboxToMinimal(ArrayList<Resource> strongbox) {
        ArrayList<String> strongboxMinimal = new ArrayList<>();
        for (Resource resource: strongbox) { strongboxMinimal.add(resource.toString()); }
        return strongboxMinimal;
    }

    /**
     * Getter
     */
    public ArrayList<String> getResourcesInBox() { return resourcesInBox; }

}
