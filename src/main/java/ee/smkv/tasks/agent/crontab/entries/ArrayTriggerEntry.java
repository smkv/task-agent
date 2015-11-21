package ee.smkv.tasks.agent.crontab.entries;

import org.apache.commons.lang3.StringUtils;

public class ArrayTriggerEntry implements TriggerEntry {

    private final TriggerEntry[] entries;

    public ArrayTriggerEntry(TriggerEntry... entries) {
        this.entries = entries;
    }

    @Override
    public boolean accept(int number) {
        for (TriggerEntry entry : entries) {
            if (entry.accept(number)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return StringUtils.join(entries, ',');
    }
}
