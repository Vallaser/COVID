import Room.Room;
import Room.Sector.S1;
import Room.Sector.S2;
import Room.Sector.S3;

/**
 * Class Java Main
 *
 * @author Valérian BAL
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        //Creation of the first test
        S1 s1 = new S1();
        S2 s2 = new S2();
        S3 s3 = new S3();
        Room room1 = new Room();
        room1.addSector(s1);
        room1.addSector(s2);
        room1.addSector(s3);
    }
}
