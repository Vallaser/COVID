package Room;

import Room.Sector.S1;
import Room.Sector.S2;
import Room.Sector.S3;
import Room.Sector.Seat.Seat;
import Room.Sector.Sector;
import jdk.jshell.execution.Util;

import java.io.*;
import java.nio.file.Files;
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
     * @param sectors list of sectors of the the room
     */
    public Room(List<Sector> sectors) {
        this.sectors = sectors;
        identifier = counter++;
    }

    public void readRoom(String path)
    {
        try {
            File file = new File(path);

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
            lineNumberReader.skip(Long.MAX_VALUE);
            int[][] matrix = new int[lineNumberReader.getLineNumber()][];
            int x = 0;

            while((line = br.readLine()) != null) {
                matrix[x] = new int[line.length()];
                for(int y=0;y<line.length();y++)
                {
                    if(line.charAt(y) == ' ')
                    {
                        matrix[x][y] = -1;
                    }
                    else
                    {
                        matrix[x][y] = Character.getNumericValue(line.charAt(y));
                    }
                }
                x++;
            }

            fr.close();


            /*for(int i=0;i<lineNumberReader.getLineNumber();i++)
            {
                for(int j=0;j<matrix[i].length;j++)
                {
                    if(matrix[i][j] == -1)
                    {
                        System.out.print(' ');
                    }
                    else
                    {
                        System.out.print(matrix[i][j]);
                    }
                }
                System.out.println();
            }*/

            System.out.println();
            System.out.println();
            System.out.println();

            fr = new FileReader(file);
            br = new BufferedReader(fr);
            line = br.readLine();
            String[] firstLine = line.split("     ");
            sectors = new ArrayList<>();
            int value = -2;
            int start = 0;
            int count = 0;
            for(int y=0;y<matrix[0].length;y++)
            {
                //System.out.print(matrix[0][y]);
                if(value == matrix[0][y] || (y == matrix[0].length - 1 && matrix[0][y] != -1))
                {
                    if(y == matrix[0].length - 1 && matrix[0][y] != -1)
                    {
                        y++;
                        count++;
                    }
                    else
                    {
                        count -= 1;
                        y--;
                    }
                    start = y - count;
                    if(count == 5)
                    {
                        Seat[][] matrix2 = new Seat[9][3];
                        for(int i=0;i<9;i++)
                        {
                            int k = 0;
                            for(int j=start;j<5+start;j++)
                            {
                                if(matrix[i][j] == 0)
                                {
                                    matrix2[i][k] = new Seat(false);
                                    k++;
                                }
                                else if(matrix[i][j] == 1)
                                {
                                    matrix2[i][k] = new Seat(true);
                                    k++;
                                }
                                else if(matrix[i][j] == -1)
                                {
                                }
                            }
                        }
                        S2 Sector2 =  new S2(matrix2);
                        sectors.add(Sector2);
                    }
                    else if(count == 7 && matrix[8][start] == -1)
                    {
                        Seat[][] matrix1 = new Seat[8][4];
                        for(int i=0;i<8;i++)
                        {
                            int k = 0;
                            for(int j=start;j<7+start;j++)
                            {
                                if(matrix[i][j] == 0)
                                {
                                    matrix1[i][k] = new Seat(false);
                                    k++;
                                }
                                else if(matrix[i][j] == 1)
                                {
                                    matrix1[i][k] = new Seat(true);
                                    k++;
                                }
                                else if(matrix[i][j] == -1)
                                {

                                }
                            }
                        }
                        S1 Sector1 =  new S1(matrix1);
                        sectors.add(Sector1);
                    }
                    else if(count == 7 && matrix[9][start] != -1)
                    {
                        Seat[][] matrix3 = new Seat[10][4];
                        for(int i=0;i<10;i++)
                        {
                            int k = 0;
                            for(int j=start;j<7+start;j++)
                            {
                                if(matrix[i][j] == 0)
                                {
                                    matrix3[i][k] = new Seat(false);
                                    k++;
                                }
                                else if(matrix[i][j] == 1)
                                {
                                    matrix3[i][k] = new Seat(true);
                                    k++;
                                }
                                else if(matrix[i][j] == -1)
                                {
                                }
                            }
                        }
                        S3 Sector3 =  new S3(matrix3);
                        sectors.add(Sector3);
                    }
                    count = 0;
                    value = -2;
                    if(y != matrix[0].length-1)
                    {
                        for(int k=y;k<matrix[0].length;k++)
                        {
                            if(matrix[0][k] != -1) {
                                y = k - 1;
                                break;
                            }
                        }
                    }
                    else{
                        y++;
                        System.out.println("fin : " + y);
                    }
                }
                else{
                    value = matrix[0][y];
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
