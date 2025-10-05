package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {
    @Test
    void testInsertAndExtractMin() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(5, tracker);
        heap.insert(10);
        heap.insert(3);
        heap.insert(5);
        assertEquals(3, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.extractMin());
    }

    @Test
    void testDecreaseKey() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(5, tracker);
        heap.insert(20);
        heap.insert(15);
        heap.insert(30);
        heap.decreaseKey(2, 5);
        assertEquals(5, heap.extractMin());
    }

    @Test
    void testMerge() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap h1 = new MinHeap(5, tracker);
        MinHeap h2 = new MinHeap(5, tracker);
        h1.insert(10);
        h1.insert(2);
        h2.insert(5);
        h2.insert(1);
        MinHeap merged = MinHeap.merge(h1, h2);
        assertEquals(1, merged.extractMin());
        assertEquals(2, merged.extractMin());
    }

    @Test
    void testEdgeCases() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(2, tracker);
        assertThrows(Exception.class, heap::extractMin);
        heap.insert(42);
        assertEquals(42, heap.peekMin());
    }
}
