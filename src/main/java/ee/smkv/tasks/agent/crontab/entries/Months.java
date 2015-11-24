package ee.smkv.tasks.agent.crontab.entries;

public enum Months implements Alias {
    JAN, FEB, MAR, APR, MAY, JUNE, JULE, AUG, SEPT, OCT, NOV, DEC;

    @Override
    public int getNumber() {
        return ordinal() + 1;
    }
}
