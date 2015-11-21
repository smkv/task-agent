package ee.smkv.tasks.agent.crontab.entries;

public class AnyTriggerEntry implements TriggerEntry {

    @Override
    public boolean accept(int number) {
        return true;
    }

    public String toString(){
        return "*";
    }
}
