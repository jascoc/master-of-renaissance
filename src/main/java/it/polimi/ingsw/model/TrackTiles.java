package it.polimi.ingsw.model;

/**
 * TrackTiles class is used to make actions on the FaithTrack
 */
public class TrackTiles {

    private static int popeTilesReached;

    private int position;
    private boolean isOccupied = false;
    private boolean popeSpace = false;
    private boolean[] reportSection = {false, false, false};
    private boolean popeSpaceAlreadyActivated = false;


    /**
     * Constructor trackTiles create a new tile for the faith track.
     * Exception outOfFaithTrack, already caught, if I'm going out of the FaithTrack.
     */
    public TrackTiles (int position) {
        this.position = position;
        if((position != 0) && ((position % 8) == 0))
            popeSpace = true;
        if(position >= 5 && position <= 8)
            reportSection[0] = true;
        else if(position >= 12 && position <= 16)
            reportSection[1] = true;
        else if(position >= 19)
            reportSection[2] = true;
        popeTilesReached = 0;
    }

    /**
     * Method SetIsOccupied gets isOccupied.
     * @return true if this tile is occupied.
     */
    public boolean getIsOccupied() { return isOccupied; }

    /**
     * Method SetIsOccupied sets isOccupied.
     */
    public void setIsOccupied() { this.isOccupied = true; }

    /**
     * Method isPopeSpace returns popeSpace.
     * @return true if this tile is a pope space.
     */
    public boolean isPopeSpace() { return popeSpace; }

    /**
     * Method isReportSection returns reportSection[N].
     * @param punt is the number of the wanted report section.
     * @return true if this tile is a report section[N].
     */
    public boolean isReportSection(int punt) { return reportSection[punt]; }

    /**
     * Method get position. Used in tests.
     * @return the current position of this tile.
     */
    public int getPosition() { return position; }

    /**
     * Get state of popeSpaceAlreadyActivated.
     * @return true only if this tiles is a popeSpaceTile and if this popeSpace has been activated.
     */
    public boolean isPopeSpaceAlreadyActivated() { return popeSpaceAlreadyActivated; }

    /**
     * Get the number of times a pope space is activated. It cannot be more than 3 times.
     * @return number of time a pope space has been already activated.
     */
    public static int getPopeTilesReached() { return popeTilesReached; }

    /**
     * Increment by one the number of pope position activated. Called when a pope space has been activated.
     */
    public static void setPopeTilesReached() { popeTilesReached ++; }

    /**
     * Sets true if the popeSpace gets activated by a player.
     * A tile can be activated if only it's a PopeSpace tile.
     */
    public void setPopeSpaceAlreadyActivated() { if(popeSpace) { this.popeSpaceAlreadyActivated = true; } }
}