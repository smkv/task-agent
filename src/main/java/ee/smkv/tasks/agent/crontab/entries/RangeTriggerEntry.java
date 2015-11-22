package ee.smkv.tasks.agent.crontab.entries;

public class RangeTriggerEntry implements TriggerEntry {

    private final int min, max;

    public RangeTriggerEntry(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public boolean accept(int number) {
        return number >= min && number <= max;
    }

    public String toString() {
        return String.format("%d-%d", min, max);
    }
}
