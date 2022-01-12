package Room.Sector.Seat;

/**
 * Class Java Seat
 *
 * @author Valérian BAL
 * @version 1.0
 */
public class Seat {
    private static int counter = 1;
    private int identifier = 0;
    private boolean occupied = false;

    /**
     * Constructor without parameters
     */
    public Seat() {
        identifier = counter++;
    }

    /**
     * Constructor with 1 parameter
     *
     * @param occupied if the seat is occupied (boolean)
     */
    public Seat(boolean occupied) {
        identifier = counter++;
        this.occupied = occupied;
    }


    /**
     * Setter of the boolean of the seat
     *
     * @param occupied if is occupied
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Getter of the counter of the number of seats
     *
     * @return the counetr of the number of seats
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Getter of the unique identifier of the Seat
     *
     * @return the unique identifier of the Seat
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Getter of the boolean of the seat
     *
     * @return if is occupied
     */
    public boolean isOccupied() {
        return occupied;
    }
}
