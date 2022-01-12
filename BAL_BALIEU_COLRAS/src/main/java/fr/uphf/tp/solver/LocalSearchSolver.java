package fr.uphf.tp.solver;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import fr.uphf.tp.model.Room;
import fr.uphf.tp.model.Seat;

/**
 * Class Java LocalSearchSetSolver
 * @author Antoine BALIEU
 *
 */
public class LocalSearchSolver extends GreedySolver {
	
    private static final Random RANDOM = new Random(); //Object Random that allows you to generate random numbers

    private final int maxIterations; //Maximum number of iterations
    private final int maxTime; //Maximum time that the function can take to run

    /**
     * Constructeur de la class LocalSearchSetSolver
     * @param room pièce à optimiser les sièges
     * @param maxIterations nombre maximal d'itérations
     * @param maxTime temps maximal
     */
    public LocalSearchSolver(int maxIterations, int maxTime) {
        this.maxIterations = maxIterations;
        this.maxTime = maxTime;
    }

    /**
     * Fonction qui permet d'échanger l'état occupé/libre entre deux sièges et met à jour les sièges à coté
     * @param occupiedSeats Ensemble des sièges occupés
     * @param seatToRemove Siège occupé à supprimer
     * @param seatToAdd Siège libre à ajouter
     * @return le nouvel ensemble des sièges occupés
     */
    public Set<Seat> swap(Room room, int distance, Set<Seat> occupiedSeats, Seat seatToRemove, Seat seatToAdd) {
    	
    	//On déclare le nouvel ensemble des sièges occupés (triés selon l'id des sièges)
        Set<Seat> newOccupiedSeats = new TreeSet<>(Comparator.comparing(Seat::getId));
        
        
        newOccupiedSeats.addAll(occupiedSeats); //On ajoute les sièges de l'ancien ensemble
        newOccupiedSeats.remove(seatToRemove); //On retire le siège à supprimer 
        newOccupiedSeats.add(seatToAdd); //On ajoute le siège à ajouter

        //On déclare un itérateur pour parcourir le nouvel ensemble des sièges occupés
        Iterator<Seat> iterator = newOccupiedSeats.iterator();
        while (iterator.hasNext()) { //Tant qu'il y a un siège suivant
            Seat seat = iterator.next(); //On récupère le siège suivant

            //Si la distance entre le siège suivant et le siège que l'on à ajouter ne respecte pas les distance alors
            if (seat.distanceTo(seatToAdd) < distance) {
                iterator.remove(); //On retire le siège de l'ensemble des sièges occupés
            }
        }
        
        //Pour chaque siège de la pièce
        for (Seat seat : room.getSeats()) {
        	//Si le siège est le siège que l'on a supprimer on passe
            if (seat == seatToRemove) {
                continue;
            }

            //On initialise le booléen qui va nous indiquer si on peut occuper le siège ou non
            boolean nearOccupied = false;
            
            //Pour chaque siège occupés de l'ensemble des sièges occupés
            for (Seat occupiedSeat : newOccupiedSeats) {
            	//Si la distance entre le siège courant et le siège occupé ne respecte pas la distance minimale alors
                if (seat.distanceTo(occupiedSeat) < distance) {
                	//On indique qu'il y a un siège occupé pas loin donc on ne peut pas occuper celui-ci
                    nearOccupied = true;
                    break;
                }
            }
            
            //Si aucun siège voisin n'est occupé on peut occupé le siège courant
            if (!nearOccupied) {
            	//On ajoute le siège à la liste des sièges occupés
            	newOccupiedSeats.add(seat);
            }
        }

	    //On retourne la liste des sièges occupés
	    return newOccupiedSeats;

    }

    @Override
    /**
     * Fonction qui retourne l'ensemble presque optimale des sièges occupés de la pièce
     * @return Set<Seat> ensemble des sièges occupés
     */
    public Set<Seat> solve(Room room, int distance) {
    	
    	//On récupère l'ensemble des sièges occupés de la pièce
        Set<Seat> occupiedSeats = super.solve(room, distance);

        int iteration = 0; //On initialise le compteur d'itération
        Instant start = Instant.now(); //On initialise le temps de départ
        Instant maxInstant = start.plus(maxTime, ChronoUnit.MILLIS); //On déclare le temps maximal

        //Tant que le nombre d'itération ou le temps ne dépasse les limites définis
        while (iteration <= maxIterations && Instant.now().isBefore(maxInstant)) {
        	
        	//On choisis au hasard un siège occupé parmi la liste des sièges occupés
            Seat randomSeat = occupiedSeats.stream()
                    .skip(RANDOM.nextInt(occupiedSeats.size() - 1))
                    .findAny().orElseThrow();

            //On choisis au hasard un siège de la pièce
            Seat freeSeat = room.getSeats().stream()
                    .skip(RANDOM.nextInt(room.getSeats().size() - 1))
                    .filter(s -> !occupiedSeats.contains(s))
                    .findAny().orElseThrow();

            //On récupère le nouvel ensemble des sièges occupés de la pièce résultant du changement entre les deux sièges
            Set<Seat> newOccupiedSeats = this.swap(room, distance, occupiedSeats, randomSeat, freeSeat);

            //Si le nouvel ensemble est plus grand que celui actuel
            if (newOccupiedSeats.size() > occupiedSeats.size()) {
                occupiedSeats.clear(); //L'ancien ensemble reçoit le nouvel ensemble des sièges occupés
                occupiedSeats.addAll(newOccupiedSeats);
            }

            iteration++; //On incrémente le nombre d'itérations
        }

        return occupiedSeats; //On retourne l'ensemble des sièges occupés de la pièce
    }
}
