package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;

import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int size = 10000;
        int trials = 5;

        Random rand = new Random();

        System.out.println("Benchmark MinHeap (size=" + size + ", trials=" + trials + ")");

        for (int t = 1; t <= trials; t++) {
            PerformanceTracker tracker = new PerformanceTracker();
            MinHeap heap = new MinHeap(size, tracker);

            long start = System.nanoTime();
            // Insert
            for (int i = 0; i < size; i++) {
                heap.insert(rand.nextInt());
            }
            // Extract all
            while (heap.getSize() > 0) {
                heap.extractMin();
            }
            long end = System.nanoTime();

            long timeMs = (end - start) / 1_000_000;

            System.out.printf(
                    "Trial %d: time=%d ms, Comparisons=%d, Swaps=%d, Accesses=%d, Allocs=%d%n",
                    t, timeMs, tracker.getComparisons(), tracker.getSwaps(),
                    tracker.getAccesses(), tracker.getAllocations()
            );
        }
    }
}

