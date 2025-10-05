package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;

import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int size = args.length > 0 ? Integer.parseInt(args[0]) : 10000;
        int trials = args.length > 1 ? Integer.parseInt(args[1]) : 5;

        System.out.printf("Benchmark MinHeap (size=%d, trials=%d)%n", size, trials);

        for (int t = 1; t <= trials; t++) {
            PerformanceTracker tracker = new PerformanceTracker();
            MinHeap heap = new MinHeap(size, tracker);
            Random rnd = new Random();

            long start = System.nanoTime();
            for (int i = 0; i < size; i++) heap.insert(rnd.nextInt());
            while (!heap.isEmpty()) heap.extractMin();
            long end = System.nanoTime();

            System.out.printf("Trial %d: time=%.3f ms, %s%n",
                    t, (end - start) / 1e6, tracker);
        }
    }
}
