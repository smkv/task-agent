package ee.smkv.tasks.agent.crontab.entries;

public class AnyTriggerEntry extends RangeTriggerEntry {

    public AnyTriggerEntry(int min, int max) {
        super(min , max);
    }


    public String toString(){
        return "*";
    }
}
