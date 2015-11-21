package ee.smkv.tasks.agent.crontab;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private static final Pattern CRONTAB_TASK_PATTERN = Pattern.compile("\\s*([0-9*/]+)\\s+([0-9*/]+)\\s+([0-9*/]+)\\s+([0-9*/]+)\\s+([0-9*/]+)\\s+(.+)");
    private static final int GROUP_MINUTE = 1;
    private static final int GROUP_HOUR = 2;
    private static final int GROUP_DAY = 3;
    private static final int GROUP_MONTH = 4;
    private static final int GROUP_WEEK = 5;
    private static final int GROUP_COMMAND = 6;


    private final Trigger trigger;
    private final String command;

    public Task(Trigger trigger, String command) {
        this.trigger = trigger;
        this.command = command;
    }

    public static boolean isTask(String line) {
        return line != null && CRONTAB_TASK_PATTERN.matcher(line).matches();
    }

    public static Task parse(String line) throws ParseException {
        Matcher matcher = CRONTAB_TASK_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new ParseException(String.format("Unable to parse crontab line '%s'", line), 0);
        }
        Trigger trigger = new Trigger(
                matcher.group(GROUP_MINUTE),
                matcher.group(GROUP_HOUR),
                matcher.group(GROUP_DAY),
                matcher.group(GROUP_MONTH),
                matcher.group(GROUP_WEEK)
        );
        return new Task(trigger, matcher.group(GROUP_COMMAND));
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
