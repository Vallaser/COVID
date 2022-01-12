package fr.uphf.tp.solver;

import java.util.List;
import java.util.Set;

import fr.uphf.tp.model.Room;
import fr.uphf.tp.model.Seat;

/**
 * Classe Solver
 * 
 * @author Antoine BALIEU
 *
 */
public abstract class Solver {

    /**
     * @param seatsSet Set de sièges occupés
     *                 Fonction qui renvoie true si les sièges sont à la bonne
     *                 distance entre eux
     */
    public boolean isFeasible(Set<Seat> seatsSet, int distance) {

        // Création d'une liste des sièges occupés pour avoir l'accès au ieme élément
        List<Seat> listOccupiedSeat = List.copyOf(seatsSet);

        int cpt = 0; // Compteur qui permet de savoir à quel sièges on est dans la liste pour ne pas
                     // tester
        // la distance plusieurs fois entre les mêmes sièges

        // Pour chaque siège de l'ensemble des sièges
        for (Seat s1 : seatsSet) {
            // Pour les sièges suivants
            for (int j = cpt + 1; j < listOccupiedSeat.size(); j++) {
                // Si la distance entre le sièges courant et le siège suivant ne respecte pas la
                // distance retourner faux
                if (s1.distanceTo(listOccupiedSeat.get(j)) < distance)
                    return false;
            }

            cpt++; // On incrémente le compteur
        }

        // Si on est arrivé jusqu'ici alors il n'y a pas d'erreur et donc on renvoie
        // vrai
        return true;
    }

    /** Fonction permettant de résoudre le problème */
    public abstract Set<Seat> solve(Room room, int distance);
}
