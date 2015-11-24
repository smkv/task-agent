package ee.smkv.tasks.agent.crontab;

import ee.smkv.tasks.agent.crontab.entries.Months;
import ee.smkv.tasks.agent.crontab.entries.WeekDays;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CronLineParser {

    private static final String TRIGGER_ENTRY_PATTERN = "([0-9*/\\-,]+)";
    private static final String MONTH_TRIGGER_ENTRY_PATTERN = String.format("([0-9*/\\-,]+|%s)", StringUtils.join(Months.values(), '|'));
    private static final String WEEK_TRIGGER_ENTRY_PATTERN = String.format("([0-9*/\\-,]+|%s)", StringUtils.join(WeekDays.values(), '|'));
    private static final String COMMAND_PATTERN = "(.+)";

    private static final Pattern CRONTAB_TASK_PATTERN = Pattern.compile(
            String.format("\\s*%s\\s+%s\\s+%s\\s+%s\\s+%s\\s+%s",
                    TRIGGER_ENTRY_PATTERN,  // minutes
                    TRIGGER_ENTRY_PATTERN,  // hours
                    TRIGGER_ENTRY_PATTERN,  // days
                    MONTH_TRIGGER_ENTRY_PATTERN,  // months
                    WEEK_TRIGGER_ENTRY_PATTERN,  // weeks
                    COMMAND_PATTERN
            )
    );
    private static final int GROUP_MINUTE = 1;
    private static final int GROUP_HOUR = 2;
    private static final int GROUP_DAY = 3;
    private static final int GROUP_MONTH = 4;
    private static final int GROUP_WEEK = 5;
    private static final int GROUP_COMMAND = 6;


    public boolean isTask(String line) {
        return line != null && CRONTAB_TASK_PATTERN.matcher(line).matches();
    }

    public Task parse(String line) throws ParseException {
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

}
