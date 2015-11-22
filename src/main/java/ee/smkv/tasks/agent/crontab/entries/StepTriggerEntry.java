package ee.smkv.tasks.agent.crontab.entries;

public class StepTriggerEntry implements TriggerEntry {
    private final RangeTriggerEntry parent;
    private final int step;

    public StepTriggerEntry(RangeTriggerEntry parent, int step) {
        this.parent = parent;
        this.step = step;
    }

    @Override
    public boolean accept(int number) {
        for (int i = parent.getMin(); i <= parent.getMax(); i += step) {
            if (number == i) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return String.format("%s/%d", parent, step);
    }
}
