package fr.uphf.tp.solver;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import fr.uphf.tp.model.Room;
import fr.uphf.tp.model.Seat;

/**
 * Class Java GreedySetSolver
 * 
 * @author Antoine BALIEU
 *
 */
public class GreedySolver extends Solver {

    /**
     * Constructeur de la class GreedySetSolver
     */
    public GreedySolver() {
    }

    @Override
    /**
     * Fonction qui permet d'optimiser l'occupation des sièges de la pièce du
     * solveur par la méthode glouton
     * 
     * @return Set<Seat> l'ensemble des sièges occupés
     */
    public Set<Seat> solve(Room room, int distance) {

        // Déclaration de l'ensemble de sièges que l'on va retourner qui sera trié par
        // l'id des sièges
        Set<Seat> occupiedSeats = new TreeSet<>(Comparator.comparing(Seat::getId));

        // Pour chaque siège présent dans la pièce
        for (Seat seat : room.getSeats()) {
            // On initialise le booléen qui va nous indiquer si on peut occuper le siège ou
            // non
            boolean nearOccupied = false;

            // Pour chaque siège occupés de l'ensemble des sièges occupés
            for (Seat occupiedSeat : occupiedSeats) {
                // Si la distance entre le siège courant et le siège occupé ne respecte pas la
                // distance minimale alors
                if (seat.distanceTo(occupiedSeat) < distance) {
                    // On indique qu'il y a un siège occupé pas loin donc on ne peut pas occuper
                    // celui-ci
                    nearOccupied = true;
                    break;
                }
            }

            // Si aucun siège voisin n'est occupé on peut occupé le siège courant
            if (!nearOccupied) {
                // On ajoute le siège à la liste des sièges occupés
                occupiedSeats.add(seat);
            }
        }

        // On retourne la liste des sièges occupés
        return occupiedSeats;
    }

}