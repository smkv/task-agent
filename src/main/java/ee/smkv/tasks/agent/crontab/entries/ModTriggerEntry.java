package ee.smkv.tasks.agent.crontab.entries;

public class ModTriggerEntry implements TriggerEntry {
    private final TriggerEntry parent;
    private final int mod;

    public ModTriggerEntry(TriggerEntry parent, int mod) {
        this.parent = parent;
        this.mod = mod;
    }

    @Override
    public boolean accept(int number) {
        return parent.accept(number) && number % mod == 0;
    }

    public String toString() {
        return String.format("%s/%d", parent, mod);
    }
}
