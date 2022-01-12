package fr.uphf.tp.solver;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import fr.uphf.tp.model.Room;
import fr.uphf.tp.model.Seat;

/**
 * Class Java HeuristicSolver
 *
 * @author Valérian BAL
 *
 */
public class HeuristicSolver extends Solver {

    private static final double TAUX_MUT_SEQ = 0.1;
    private static final double TAUX_MUT_POP = 0.2;

    /**
     * Constructeur de la class SetSolver
     *
     * @param room pièce du solveur
     */
    public HeuristicSolver() {
    }

    public void genetic(Room room, int distance, int taillePopulation, int kMeilleurs, int generationMax) {
        HashMap<int[], Integer> population = new HashMap<int[], Integer>();

        for (int i = 0; i < generationMax; i++) {
            init(room);
            shuffle(room, distance);
            Set<Seat> occupiedSeats = this.solve(room, distance);

            int coding[] = coding(room);

            int score = score(room, distance, occupiedSeats);

            if (population.containsKey(coding)) {
                i--;
            } else {
                population.put(coding, score);
            }
        }

        HashMap<int[], Integer> population_trie = sortValue(population);

        HashMap<int[], Integer> population_reduit = new HashMap<int[], Integer>();
        Iterator<int[]> it = population_trie.keySet().iterator();

        for (int i = 0; i < kMeilleurs; i++) {

            int[] key = it.next();
            // System.out.println(population_trie.get(key));
            population_reduit.put(key, population_trie.get(key));
        }

        it = population_reduit.keySet().iterator();

        for (int i = 0; i < kMeilleurs; i++) {

            int[] key = it.next();
            // System.out.println(population_trie.get(key));
            population_reduit.put(key, population_trie.get(key));

        }

        population_reduit = realGenetic(room, distance, population, population_reduit, taillePopulation, kMeilleurs);

        it = population_reduit.keySet().iterator();

        while (it.hasNext()) {
            int[] key = it.next();

            int[] key2 = Arrays.copyOf(key, key.length);

            decoding(room, key2);

            Set<Seat> occupiedSeats = this.solve(room, distance);

            int score = score(room, distance, occupiedSeats);
        }
    }

    /**
     * Initialise les sièges de la salle à non occupé
     */
    public void init(Room room) {
        for (Seat seat : room.getSeats()) {
            seat.setOccupied(false);
        }
    }

    /**
     * Redistribue les salles occupés
     */
    public void shuffle(Room room, int distance) {
        for (Seat seat : room.getSeats()) {
            int r = (int) (Math.random() * (distance));
            if (r > distance / 50) {
                // System.out.println("false");
                seat.setOccupied(false);
            } else {
                // System.out.println("true");
                seat.setOccupied(true);
            }
        }
    }

    /**
     * Calcul et renvoie le code unique de la salle en fonction des places utilisés
     * 
     * @return le code de la salle (int[])
     */
    public int[] coding(Room room) {
        int coding[] = new int[room.getSeats().size()];
        int i = 0;
        for (Seat seat : room.getSeats()) {
            if (seat.isOccupied()) {
                coding[i] = 1;
            } else {
                coding[i] = 0;
            }
            i++;
        }
        return coding;
    }

    /**
     * Decode le tableau en paramètre pour modifier l'état des places
     * 
     * @param coding int[] tableau du code unique de la solution
     */
    public void decoding(Room room, int coding[]) {
        int i = 0;
        for (Seat seat : room.getSeats()) {
            if (coding[i] == 0) {
                seat.setOccupied(false);
            } else {
                seat.setOccupied(true);
            }
            i++;
        }
    }

    public boolean compareCoding(HashMap<int[], Integer> population, int[] key2) {
        Iterator<int[]> it = population.keySet().iterator();
        while (it.hasNext()) {
            int[] key = it.next();
            if (compareCoding(key, key2)) {
                return true;
            }
        }
        return false;
    }

    public boolean compareCoding(int[] coding1, int[] coding2) {
        for (int i = 0; i < coding1.length; i++) {
            if (coding1[i] != coding2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcule et renvoie le score de la salle
     *
     * @return le score de la salle (int)
     */
    public int score(Room room, int distance, Set<Seat> occupiedSeats) {
        int score = 0;
        if (this.isFeasible(occupiedSeats, distance)) {
            for (Seat seat : room.getSeats()) {
                if (seat.isOccupied()) {
                    score++;
                }
            }
        }
        return score;
    }

    /**
     * Trieur de HashMap par ordre décroissant
     * 
     * @param map Hashmap à trier
     * @return le Hashmap trié
     */
    public static HashMap<int[], Integer> sortValue(HashMap<int[], Integer> map) {
        List<Map.Entry<int[], Integer>> list = new LinkedList<Map.Entry<int[], Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<int[], Integer>>() {
            public int compare(Map.Entry<int[], Integer> o1, Map.Entry<int[], Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<int[], Integer> map_apres = new LinkedHashMap<int[], Integer>();
        for (Map.Entry<int[], Integer> entry : list)
            map_apres.put(entry.getKey(), entry.getValue());
        return map_apres;
    }

    @Override
    public Set<Seat> solve(Room room, int distance) {
        // Déclaration de l'ensemble de sièges que l'on va retourner qui sera trié par
        // l'id des sièges
        Set<Seat> occupiedSeats = new TreeSet<>(Comparator.comparing(Seat::getId));

        // Pour chaque siège présent dans la pièce
        for (Seat seat : room.getSeats()) {

            if (seat.isOccupied()) {
                // System.out.println("test");
                occupiedSeats.add(seat);
            }
        }
        // On retourne la liste des sièges occupés
        return occupiedSeats;
    }

    public HashMap<int[], Integer> realGenetic(Room room, int distance, HashMap<int[], Integer> populationTotale,
            HashMap<int[], Integer> populationReduit, int taillePopulation, int kMeilleurs) {
        int position = 0;
        int nbPopTotale = populationTotale.size();
        boolean trouve = false;

        while (nbPopTotale < taillePopulation) {
            HashMap<int[], Integer> populationReduit2 = new HashMap<int[], Integer>(populationReduit);
            {
                Iterator<int[]> it = populationReduit.keySet().iterator();
                while (it.hasNext() && (nbPopTotale < taillePopulation)) {
                    int[] key = it.next();
                    int[] key2 = Arrays.copyOf(key, key.length);
                    if (key[position] == 0) {
                        key2[position] = 1;
                    } else {
                        key2[position] = 0;
                    }

                    if (!compareCoding(populationTotale, key2)) {

                        decoding(room, key2);
                        Set<Seat> occupiedSeats = this.solve(room, distance);

                        populationReduit2.put(key2, score(room, distance, occupiedSeats));
                        populationTotale.put(key2, score(room, distance, occupiedSeats));

                        nbPopTotale++;

                        trouve = true;
                    }
                }
            }
            if (trouve) {

                populationReduit2 = sortValue(populationReduit2);

                HashMap<int[], Integer> populationReduit3 = new HashMap<int[], Integer>();
                Iterator<int[]> it = populationReduit2.keySet().iterator();

                for (int i = 0; i < kMeilleurs; i++) {

                    int[] key = it.next();

                    populationReduit3.put(key, populationReduit2.get(key));

                }

                populationReduit2 = new HashMap<int[], Integer>(populationReduit3);
                populationReduit = new HashMap<int[], Integer>(populationReduit3);

                trouve = false;

                /*
                 * System.out.println();
                 * System.out.println("Test");
                 * Iterator<int[]> itTest = populationReduit3.keySet().iterator();
                 * while(itTest.hasNext())
                 * {
                 * int[] keyTest=itTest.next();
                 * System.out.println("Code :  "+keyTest+"     score:   "+populationReduit3.get(
                 * keyTest));
                 * }
                 */

            }

            if (position == room.getSeats().size() - 1) {
                position = 0;
            } else {
                position++;
            }
        }
        return populationReduit;
    }
}
