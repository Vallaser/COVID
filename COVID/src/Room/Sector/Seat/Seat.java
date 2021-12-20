package Room.Sector.Seat;

public class Seat {
    private static int counter = 1;
    private int identifier = 0;

    /**
     * Constructor without parameters
     */
    public Seat() {
        identifier = counter++;
    }

    /**
     * Constructor with 1 parameter
     *
     * @param identifier unique identifier of the seat
     */
    public Seat(int identifier) {
        this.identifier = identifier;
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
}
