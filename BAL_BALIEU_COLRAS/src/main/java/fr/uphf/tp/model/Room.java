package fr.uphf.tp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class Java Room
 *
 * @author Valérian BAL
 */
public class Room {

    private final Set<Seat> seats = new TreeSet<>(Comparator.comparing(Seat::getId)); // Set of Seat of the room

    /**
     * Constructeur de la class Room
     * 
     * @param seats Set de sièges à ajouter à la pièce
     */
    public Room(Set<Seat> seats) {
        this.seats.addAll(seats);
    }

    /**
     * Getter de l'attribut seats
     * 
     * @return seats l'ensemble des sièges de la pièce
     */
    public Set<Seat> getSeats() {
        return seats;
    }

    /**
     * Display de la Pièce
     * 
     * @param occupiedSeats Set<Seat> liste des sièges occupés
     */
    public void displayRoom(Set<Seat> occupiedSeats) {

        List<Seat> orderedSeats = new ArrayList<Seat>(getSeats());

        Collections.sort(orderedSeats, Comparator.comparing(Seat::getId));

        int startX = 0;
        int startY = -1;
        int cptLigne = 0;
        int cptColonne = 0;
        int oldX = -1;

        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i = 1; i < orderedSeats.size(); i++) {
            cptLigne++;
            int tempoX = orderedSeats.get(i).getX();
            if (tempoX != startX) {
                startX = tempoX;
                oldX = cptLigne;

                if (startY == -1)
                    startY = cptLigne;
                else if (startY != cptLigne) {
                    startY = cptLigne;
                    oldX--;
                    hashMap.put(oldX, cptColonne);
                    cptColonne = 0;

                }
                cptColonne++;
                cptLigne = 0;
            }
        }
        hashMap.put(oldX, ++cptColonne);

        int tabSeats[] = new int[this.getSeats().size()];
        Arrays.fill(tabSeats, 0);
        for (Seat s : occupiedSeats) {
            tabSeats[s.getId() - 1] = 1;
        }

        int cpt = 0;
        for (var h : hashMap.entrySet()) {
            System.out.println();
            for (int i = 0; i < h.getValue(); i++) {
                for (int j = 0; j < h.getKey(); j++) {
                    System.out.print(tabSeats[cpt] + " ");
                    cpt++;
                }
                System.out.println();
            }
        }

        Arrays.fill(tabSeats, 0);

        System.out.println();
    }
}
