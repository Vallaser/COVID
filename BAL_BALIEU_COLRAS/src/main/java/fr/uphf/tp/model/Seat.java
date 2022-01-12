package fr.uphf.tp.model;

/**
 * 
 * Class Java Seat
 * 
 * @author Quentin COLRAS
 * @version 1.0
 *
 */
public class Seat {
    private static int counter = 0;

    private final int id; // Identifier of the seat
    private final int x; // Position x of the seat
    private final int y; // Position y of the seat

    private boolean occupied = false;

    /**
     * Constructeur de la class Seat
     * @param id identifiant du siège
     * @param x position x du siège
     * @param y position y du siège
     */
    public Seat(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur de la class Seat
     * @param x position x du siège
     * @param y position y du siège
     */
    public Seat(int x, int y) {
        this(counter++, x, y);
    }

    /**
     * Setter de l'attribut occupied
     * @param occupied si la place est occupé
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Getter de l'attribut id
     * @return id identifiant du siège
     */
    public int getId() {
        return id;
    }

    /**
     * Getter de l'attribut x
     * @return x position x du siège
     */
    public int getX() {
        return x;
    }
    
    /**
     * Getter de l'attribut y
     * @return y position y du siège
     */
    public int getY() {
        return y;
    }


    /**
     * Getter de l'attribut occupied
     *
     * @return occupied booleen
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Fonction qui retourne la distance euclidienne entre this et other
     * @param other Siege auquel on calcul la distance
     * @return distance euclidienne
     */
    public double distanceTo(Seat other) {
        return other != null ? Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2)) : -1;
    }

    /**
     * Fonction qui retourne le String de la class Seat, utilisée pour l'affichage d'un siège
     */
    @Override
    public String toString() {
        return "Seat#" + id + "<" + x + ", " + y + ">";
    }
}
