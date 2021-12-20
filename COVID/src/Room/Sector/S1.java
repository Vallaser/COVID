package Room.Sector;

import Room.Sector.Seat.Seat;

/**
 * Class Java Room.Room.Sector 1
 *
 * @author Valérian BAL
 * @version 1.0
 */
public class S1 extends Sector {
    private Seat matrix[][] = new Seat[8][4];

    /**
     * Constructor without parameters
     */
    public S1() {
    }

    /**
     * Consctructor with 1 parameter
     *
     * @param identifier unique identifier of the Room.Room.Sector
     */
    public S1(int identifier) {
        super(identifier);
    }

    /**
     * Getter matrix of the sector
     *
     * @return matrix of the sector
     */
    public Seat[][] getMatrix() {
        return matrix;
    }
}
