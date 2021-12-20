package Room.Sector;

import Room.Sector.Seat.Seat;

/**
 * Class Java Sector 3
 *
 * @author Valérian BAL
 * @version 1.0
 */
public class S3 extends Sector {
    private Seat matrix[][] = new Seat[10][4];

    /**
     * Constructor without parameters
     */
    public S3() {
        initialization();
    }

    /**
     * Consctructor with 1 parameter
     *
     * @param identifier unique identifier of the Room.Room.Sector
     */
    public S3(int identifier) {
        super(identifier);
        initialization();
    }

    /**
     * Initialize the matrix
     */
    public void initialization() {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<4;j++) {
                matrix[i][j] = new Seat();
            }
        }
    }

    /**
     * Getter matrix of the sector
     *
     * @return matrix of the sector
     */
    public Seat[][] getMatrix() {
        return matrix;
    }

    public void displaySector(int line){
        if(line < 10)
        {
            for(int j=0;j<4;j++)
            {
                if(matrix[line][j].isOccupied())
                {
                    System.out.print("1 ");
                }
                else{
                    System.out.print("0 ");
                }
            }
        }
        else {
            for(int j=0;j<4;j++)
            {
                System.out.print("  ");
            }
        }

    }
}
