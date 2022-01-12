package fr.uphf.tp;

import java.io.IOException;
import java.util.Set;

import fr.uphf.tp.model.Room;
import fr.uphf.tp.model.Seat;
import fr.uphf.tp.solver.GreedySolver;
import fr.uphf.tp.solver.HeuristicSolver;
import fr.uphf.tp.solver.LocalSearchSolver;
import fr.uphf.tp.solver.Solver;
import fr.uphf.tp.util.Benchmark;
import fr.uphf.tp.util.LoaderUtils;

/**
 * Class Java Main
 *
 * @author Valérian BAL
 *
 */
public class Main {
    private static final int DEFAULT_DISTANCE = 200;

    public static void main(String[] args) throws IOException {
        Room room = LoaderUtils.fromFile("instance.txt");

        // Test solver

        Solver solver = new GreedySolver();
        Set<Seat> occupiedSeats = solver.solve(room, DEFAULT_DISTANCE);

        System.out.println("Faisable ? : " + solver.isFeasible(occupiedSeats, DEFAULT_DISTANCE));
        System.out.println(occupiedSeats.size() + " sièges occupés : " + occupiedSeats);

        room.displayRoom(occupiedSeats);

        Solver solver2 = new LocalSearchSolver(2000, 500);
        Set<Seat> occupiedSeats2 = solver2.solve(room, DEFAULT_DISTANCE);

        System.out.println("Faisable ? : " + solver2.isFeasible(occupiedSeats2, DEFAULT_DISTANCE));
        System.out.println(occupiedSeats2.size() + " sièges occupés : " + occupiedSeats2);

        room.displayRoom(occupiedSeats2);

        // Genetic

        Benchmark.displayBenchmark(Benchmark.benchmark(room, new GreedySolver()));
        Benchmark.displayBenchmark(Benchmark.benchmark(room, new LocalSearchSolver(2000, 500)));
        Benchmark.displayBenchmark(Benchmark.benchmark_heuristic(room, new HeuristicSolver()));
    }
}
