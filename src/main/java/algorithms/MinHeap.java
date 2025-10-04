package algorithms;

import metrics.PerformanceTracker;
import java.util.*;

public class MinHeap {
    private int[] heap;
    private int size;
    private final PerformanceTracker tracker;

    public MinHeap(int capacity, PerformanceTracker tracker) {
        heap = new int[capacity];
        this.tracker = tracker;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void insert(int key) {
        if (size == heap.length) heap = Arrays.copyOf(heap, Math.max(1, heap.length * 2));
        heap[size] = key;
        tracker.alloc(); tracker.access();
        heapifyUp(size++);
    }

    public int peekMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return heap[0];
    }

    public int extractMin() {
        if (isEmpty()) throw new NoSuchElementException();
        int min = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return min;
    }

    public void decreaseKey(int i, int newVal) {
        if (i >= size || newVal > heap[i]) throw new IllegalArgumentException();
        heap[i] = newVal; tracker.access();
        heapifyUp(i);
    }

    public static MinHeap merge(MinHeap a, MinHeap b) {
        PerformanceTracker tr = a.tracker;
        int[] merged = Arrays.copyOf(a.heap, a.size + b.size);
        System.arraycopy(b.heap, 0, merged, a.size, b.size);
        MinHeap h = new MinHeap(merged.length, tr);
        h.heap = merged; h.size = merged.length; tr.alloc();
        for (int i = h.size / 2 - 1; i >= 0; i--) h.heapifyDown(i);
        return h;
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i)); tracker.comp();
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int s = i, l = left(i), r = right(i);
        if (l < size && heap[l] < heap[s]) s = l;
        if (r < size && heap[r] < heap[s]) s = r;
        if (s != i) { swap(i, s); heapifyDown(s); }
    }

    private void swap(int i, int j) {
        int tmp = heap[i]; heap[i] = heap[j]; heap[j] = tmp;
        tracker.swap();
    }
}
