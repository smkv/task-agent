package ee.smkv.tasks.agent.crontab.entries;

public class FixedTriggerEntry implements TriggerEntry {
    private final int fixed;

    public FixedTriggerEntry(int fixed) {
        this.fixed = fixed;
    }

    @Override
    public boolean accept(int number) {
        return number == fixed;
    }

    public String toString(){
        return String.valueOf(fixed);
    }

}
