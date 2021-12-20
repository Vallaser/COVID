package Room.Sector;

/**
 * Class Java Room.Room.Sector
 *
 * @author Valérian BAL
 * @version 1.0
 */
public class Sector {
    private static int counter = 1;
    private int identifier = 0;
    private final double distance_column = 0.5;
    private final double distance_line = 0.75;

    /**
     * Constructor without parameters
     */
    public Sector() {
        identifier = counter++;
    }

    /**
     * Constructor with 1 parameter
     *
     * @param identifier unique identifier of the sector
     */
    public Sector(int identifier) {
        this.identifier = identifier;
    }

    /**
     * Getter of the counter of the number of sectors
     *
     * @return the counetr of the number of sectors
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Getter of the unique identifier of the Room.Room.Sector
     *
     * @return the unique identifier of the Room.Room.Sector
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Getter distance of each column
     *
     * @return distance of each column
     */
    public double getDistance_column() {
        return distance_column;
    }

    /**
     * Getter distance of each column
     *
     * @return distance of each column
     */
    public double getDistance_line() {
        return distance_line;
    }

    public void displaySector() {
        System.out.println("Sector Number : " + identifier);
    }
}
