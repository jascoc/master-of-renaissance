package it.polimi.ingsw.changes;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.resources.*;

import java.util.ArrayList;

import static it.polimi.ingsw.Variables.*;

/**
 * this Class is used to notify the view of the evolution of Extra Space Leader card
 */
public class ExtraSpaceLeaderChanges extends Changes {

    private ArrayList<String> resources;
    private String leader;

    /**
     * Constructor
     * @param playerRoundNumber position in the round
     * @param leader leader card
     * @param resources resource used
     */
    public ExtraSpaceLeaderChanges(int playerRoundNumber, String leader, ArrayList<Resource> resources) {
        super(playerRoundNumber, EXTRA_SPACE_LEADER);
        this.leader = leader;
        this.resources = convertResources(resources);
    }

    /**
     * Getters and Setters
     */
    public String getLeader() { return leader; }

    public void setLeader(String leader) { this.leader = leader; }

    public ArrayList<String> getResources() { return resources; }

    public void setResources(ArrayList<String> resources) { this.resources = resources; }

    /**
     * Converts
     * @param resource from Resource type to
     * @return String type
     */
    private ArrayList<String> convertResources(ArrayList<Resource> resource) {
        ArrayList<String> resources = new ArrayList<>();
        for(Resource res : resource) {
            resources.add(res.toString());
        }
        return resources;
    }
}
