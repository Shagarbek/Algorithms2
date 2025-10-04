package algorithms;

import metrics.PerformanceTracker;
import java.util.NoSuchElementException;

public class MinHeap {
    private int[] heap;
    private int size;
    private PerformanceTracker tracker;

    public MinHeap(int capacity, PerformanceTracker tracker) {
        heap = new int[capacity];
        this.tracker = tracker;
        size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        tracker.incSwaps();
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void insert(int key) {
        if (size == heap.length) {
            int[] newHeap = new int[size * 2];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
            tracker.incAllocations();
        }
        heap[size] = key;
        tracker.incAccesses();
        int i = size;
        size++;
        while (i > 0 && heap[parent(i)] > heap[i]) {
            tracker.incComparisons();
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int extractMin() {
        if (size <= 0) throw new NoSuchElementException("Heap underflow");
        int root = heap[0];
        heap[0] = heap[size - 1];
        tracker.incAccesses();
        size--;
        heapify(0);
        return root;
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < size) {
            tracker.incComparisons();
            if (heap[l] < heap[smallest]) smallest = l;
        }
        if (r < size) {
            tracker.incComparisons();
            if (heap[r] < heap[smallest]) smallest = r;
        }
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    public int getSize() { return size; }
}

