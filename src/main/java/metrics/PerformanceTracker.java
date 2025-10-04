package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long accesses = 0;
    private long allocations = 0;

    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }
    public void incAccesses() { accesses++; }
    public void incAllocations() { allocations++; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getAccesses() { return accesses; }
    public long getAllocations() { return allocations; }

    public void reset() {
        comparisons = swaps = accesses = allocations = 0;
    }
}
