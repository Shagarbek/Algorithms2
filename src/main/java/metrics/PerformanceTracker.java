package metrics;

public class PerformanceTracker {
    private long comp, swap, access, alloc;
    public void comp() { comp++; }
    public void swap() { swap++; }
    public void access() { access++; }
    public void alloc() { alloc++; }
    public void reset() { comp = swap = access = alloc = 0; }
    @Override public String toString() {
        return String.format("Comparisons=%d, Swaps=%d, Accesses=%d, Allocs=%d", comp, swap, access, alloc);
    }
}
