package ee.smkv.tasks.agent.crontab.entries;

public interface TriggerEntry {
    boolean accept(int number);
}
