package it.polimi.ingsw.model.exceptions;

/**
 * OutOfFaithTrackException is thrown when someone goes out of the Faith Track.
 */
public class OutOfFaithTrackException extends Exception {

    /**
     * This constructor tells you're out of the FaithTrack.
     */
    public OutOfFaithTrackException() {
        System.out.println("You're gone out of the Faith Track!");
    }

    /**
     * This constructor tells you're out of the FaithTrack and the position you're on.
     *
     * @param position is the current position.
     */
    public OutOfFaithTrackException(int position) {
        System.out.println("You're in the " + position + " tile, you're out of the Faith Track!");
    }
}
