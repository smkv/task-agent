package ee.smkv.tasks.agent.crontab;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private final Trigger trigger;
    private final String command;

    public Task(Trigger trigger, String command) {
        this.trigger = trigger;
        this.command = command;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public String getCommand() {
        return command;
    }

    public Date nextExecutionDate() {
        return getTrigger().nextExecutionDate();
    }

    public Date nextExecutionDate(Date after) {
        return getTrigger().nextExecutionDate(after);
    }

    public Calendar nextExecutionDate(Calendar after) {
        return getTrigger().nextExecutionDate(after);
    }

    @Override
    public String toString() {
        return String.format("%s %s", trigger, command);
    }
}
