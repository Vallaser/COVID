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
     * Constructor with 1 parameter
     *
     * @param matrix matrix[10][4]
     */
    public S3(Seat[][] matrix) {
        this.matrix = matrix;
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
     * Setter of the matrix of the Sector 3
     *
     * @param matrix matrix[10][4]
     */
    public void setMatrix(Seat[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Getter matrix of the sector
     *
     * @return matrix of the sector
     */
    public Seat[][] getMatrix() {
        return matrix;
    }


    /**
     * Show the matrix Sector 3
     *
     * @param line current line int
     */
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
