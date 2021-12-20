package Room;

import Room.Sector.Sector;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Java Room
 *
 * @author Valérian BAL
 * @version 1.0
 */
public class Room {
    private static int counter = 1;
    private int identifier = 0;
    private final double distance_sector = 1.2;
    private List<Sector> sectors = new ArrayList<>();

    /**
     * Constructor without parameters
     */
    public Room() {
        identifier = counter++;
    }

    /**
     * Constructor with 1 parameter
     *
     * @param identifier unique identifier of the room
     */
    public Room(int identifier) {
        this.identifier = identifier;
    }

    /**
     * Constructor with 1 parameter
     *
     * @param sectors list of sectors of the the room
     */
    public Room(List<Sector> sectors) {
        this.sectors = sectors;
    }

    /**
     * Constructor with 2 parameters
     *
     * @param identifier unique identifier of the room
     * @param sectors list of sectors of the the room
     */
    public Room(int identifier, List<Sector> sectors) {
        this.identifier = identifier;
        this.sectors = sectors;
    }

    /**
     * Add a sector to the list of sectors
     *
     * @param newSector new Sector to the list
     */
    public void addSector(Sector newSector) {
        sectors.add(newSector);
    }

    /**
     * Delete a sector of the list of sectors
     *
     * @param number number of the sector like identifier so different to 0
     */
    public void deleteSector(int number) {
        sectors.remove(number - 1);
    }

    /**
     * Setter of the list of sectors
     * @param sectors the list of sectors
     */
    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    /**
     * Getter of the counter of the number of rooms
     *
     * @return the counetr of the number of rooms
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Getter of the unique identifier of the Room.Room
     *
     * @return the unique identifier of the Room.Room
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Getter of the distance of each sector
     * @return the distance of each sector
     */
    public double getDistance_sector() {
        return distance_sector;
    }

    /**
     * Getter of the list of sectors
     *
     * @return the list of sectors
     */
    public List<Sector> getSectors() {
        return sectors;
    }

    /**
     * Display the room
     */
    public void displayRoom() {
        System.out.println("Room Number : " + identifier);
        for(int i=0;i<10;i++)
        {
            for(Sector s : sectors)
            {
                s.displaySector(i);
                System.out.print("    ");
            }
            System.out.println();
        }
    }
}
