package fr.uphf.tp.util;

import java.util.Set;

import fr.uphf.tp.model.Room;
import fr.uphf.tp.model.Seat;
import fr.uphf.tp.solver.HeuristicSolver;
import fr.uphf.tp.solver.Solver;

public class Benchmark {

    private static final int ITERATIONS = 1000;
    private static final int[] distances = { 100, 120, 140, 160, 180, 200 };

    public static long[][] benchmark(Room room, Solver solver) {
        long[][] timings = new long[distances.length][2];

        for (int i = 0; i < distances.length; ++i) {
            long maxSeats = 0;
            long start = System.nanoTime();

            for (int j = 0; j < ITERATIONS; ++j) {
                Set<Seat> seats = solver.solve(room, distances[i]);

                if (seats.size() > maxSeats) {
                    maxSeats = seats.size();
                }
            }

            long end = System.nanoTime();

            timings[i][0] = (end - start) / ITERATIONS;
            timings[i][1] = maxSeats;
        }

        return timings;
    }

    public static long[][] benchmark_heuristic(Room room, HeuristicSolver solver) {
        long[][] timings = new long[distances.length][2];

        for (int i = 0; i < distances.length; ++i) {
            long maxSeats = 0;
            long start = System.nanoTime();

            for (int j = 0; j < ITERATIONS; ++j) {
                solver.genetic(room, distances[i], 50, 5, 5);
                Set<Seat> seats = solver.solve(room, distances[i]);

                if (seats.size() > maxSeats) {
                    maxSeats = seats.size();
                }
            }

            long end = System.nanoTime();

            timings[i][0] = (end - start) / ITERATIONS;
            timings[i][1] = maxSeats;
        }

        return timings;
    }

    public static void displayBenchmark(long[][] timings) {
        for (int i = 0; i < timings.length; ++i) {
            System.out.println(distances[i] + "cm : " + timings[i][0] + "ns, seats = " + timings[i][1]);
        }
    }
}
