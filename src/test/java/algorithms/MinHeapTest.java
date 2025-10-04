package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    void testInsertAndExtractSingleElement() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(42);
        assertEquals(1, heap.getSize());

        int min = heap.extractMin();
        assertEquals(42, min);
        assertEquals(0, heap.getSize());
    }

    @Test
    void testInsertMultipleElements() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(5);
        heap.insert(3);
        heap.insert(7);

        assertEquals(3, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(7, heap.extractMin());
    }

    @Test
    void testExtractFromEmptyHeap() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(10, tracker);

        assertThrows(Exception.class, heap::extractMin);
    }

    @Test
    void testWithDuplicates() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(4);
        heap.insert(4);
        heap.insert(4);

        assertEquals(4, heap.extractMin());
        assertEquals(4, heap.extractMin());
        assertEquals(4, heap.extractMin());
    }

    @Test
    void testHeapGrowth() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(2, tracker);

        for (int i = 0; i < 100; i++) {
            heap.insert(i);
        }

        assertEquals(100, heap.getSize());
        assertEquals(0, heap.extractMin());
        assertEquals(1, heap.extractMin());
    }
}
