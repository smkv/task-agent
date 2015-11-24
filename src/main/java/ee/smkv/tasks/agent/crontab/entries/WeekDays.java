package ee.smkv.tasks.agent.crontab.entries;

public enum  WeekDays implements Alias {
    SUN, MON, TUE, WED, THU, FRI, SAT;

    @Override
    public int getNumber() {
        return ordinal();
    }
}
