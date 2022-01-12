package Room.Sector;

import Room.Sector.Seat.Seat;

/**
 * Class Java Sector 1
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
        initialization();
    }


    /**
     * Constructor with 1 parameter
     *
     * @param matrix matrix[8][3]
     */
    public S1(Seat[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Initialize the matrix
     */
    public void initialization() {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<4;j++) {
                matrix[i][j] = new Seat();
            }
        }
    }

    /**
     * Setter of the matrix of the Sector 1
     *
     * @param matrix matrix[8][4]
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
     * Show the matrix Sector 1
     *
     * @param line current line int
     */
    public void displaySector(int line){
        if(line < 8)
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
