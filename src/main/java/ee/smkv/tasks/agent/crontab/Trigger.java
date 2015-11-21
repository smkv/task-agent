package ee.smkv.tasks.agent.crontab;


import ee.smkv.tasks.agent.crontab.entries.TriggerEntry;
import ee.smkv.tasks.agent.crontab.entries.TriggerEntryParser;

import java.util.Calendar;
import java.util.Date;

public class Trigger {
    private TriggerEntry minutes;
    private TriggerEntry hours;
    private TriggerEntry days;
    private TriggerEntry months;
    private TriggerEntry weeks;

    public Trigger(String minutes, String hours, String days, String months, String weeks) {
        TriggerEntryParser parser = new TriggerEntryParser();
        this.minutes = parser.parse(minutes, 0, 59);
        this.hours = parser.parse(hours, 0, 23);
        this.days = parser.parse(days, 1, 31);
        this.months = parser.parse(months, 1, 12);
        this.weeks = parser.parse(weeks, 0, 7);
    }

    public boolean accept(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return accept(calendar);
    }

    public Date nextExecutionDate() {
        return nextExecutionDate(new Date());
    }

    public Date nextExecutionDate(Date after) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(after);
        return nextExecutionDate(calendar).getTime();
    }

    public Calendar nextExecutionDate(Calendar after) {

        after = nextExecutionDay(after);

        for (int minuteIterator = 0; minuteIterator < 24 * 60; minuteIterator++) {
            int hour = after.get(Calendar.HOUR_OF_DAY);
            int minute = after.get(Calendar.MINUTE);
            if (hours.accept(hour) && minutes.accept(minute)) {
                return after;
            }
            after.add(Calendar.MINUTE, 1);
        }

        throw new RuntimeException("Next execution time not found in a year for trigger " + this);
    }

    private Calendar nextExecutionDay(Calendar after) {
        for (int i = 0; i < 366; i++) {
            int day = after.get(Calendar.DAY_OF_MONTH);
            int month = after.get(Calendar.MONTH);
            int week = after.get(Calendar.DAY_OF_WEEK);
            if (days.accept(day) && acceptMonth(month) && acceptWeek(week)) {
                return after;
            }
            after.add(Calendar.DAY_OF_MONTH, 1);
        }
        throw new RuntimeException("Next execution day not found in a year for trigger " + this);
    }

    public boolean accept(Calendar calendar) {
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return minutes.accept(minute) &&
                hours.accept(hour) &&
                days.accept(day) &&
                acceptMonth(month) &&
                acceptWeek(week);
    }

    private boolean acceptWeek(int week) {
        week--;
        return week <= 0 ? (weeks.accept(0) || weeks.accept(7)) : weeks.accept(week);
    }

    private boolean acceptMonth(int month) {
        month++;
        return months.accept(month);
    }

    public String toString() {
        return String.format("%s %s %s %s %s", minutes, hours, days, months, weeks);
    }
}
