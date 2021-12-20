package Room.Sector;

import Room.Sector.Seat.Seat;

/**
 * Class Java Sector 2
 *
 * @author Valérian BAL
 * @version 1.0
 */
public class S2 extends Sector {
    private Seat matrix[][] = new Seat[9][3];

    /**
     * Constructor without parameters
     */
    public S2() {
        initialization();
    }

    /**
     * Consctructor with 1 parameter
     *
     * @param identifier unique identifier of the Room.Room.Sector
     */
    public S2(int identifier) {
        super(identifier);
        initialization();
    }

    /**
     * Initialize the matrix
     */
    public void initialization() {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<3;j++) {
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
        if(line < 9)
        {
            for(int j=0;j<3;j++)
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
            for(int j=0;j<3;j++)
            {
                System.out.print("  ");
            }
        }
    }
}
