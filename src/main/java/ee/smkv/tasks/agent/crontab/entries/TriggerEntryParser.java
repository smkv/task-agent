package ee.smkv.tasks.agent.crontab.entries;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class TriggerEntryParser {


    public static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");

    public TriggerEntry parse(String value, int min, int max, Alias... aliases) {
        if (StringUtils.contains(value, ',')) {
            return parseMultiplyEntities(value, min, max, aliases);
        }
        if (StringUtils.contains(value, '/')) {
            return parseStepTriggerEntry(value, min, max, aliases);
        }
        if (StringUtils.contains(value, '-')) {
            return parseRangeTriggerEntry(value, min, max, aliases);
        }
        if (StringUtils.equals(value, "*")) {
            return parseAnyTriggerEntry(value, min, max, aliases);
        }
        return parseFixedTriggerEntry(value, min, max, aliases);
    }

    private TriggerEntry parseFixedTriggerEntry(String value, int min, int max, Alias[] aliases) {
        return new FixedTriggerEntry(parseInt(value, min, max, aliases));
    }

    private TriggerEntry parseAnyTriggerEntry(String value, int min, int max, Alias[] aliases) {
        return new AnyTriggerEntry(min, max);
    }

    private TriggerEntry parseRangeTriggerEntry(String value, int min, int max, Alias[] aliases) {
        String[] split = StringUtils.split(value, "-", 2);
        return new RangeTriggerEntry(parseInt(split[0], min, max, aliases), parseInt(split[1], min, max, aliases));
    }

    private TriggerEntry parseStepTriggerEntry(String value, int min, int max, Alias[] aliases) {
        String[] split = StringUtils.split(value, "/", 2);
        TriggerEntry parent = parse(split[0], min, max, aliases);
        if (!(parent instanceof RangeTriggerEntry)) {
            throw new IllegalArgumentException("Step can be applied only for * or range");
        }

        RangeTriggerEntry range = (RangeTriggerEntry) parent;
        return new StepTriggerEntry(range, parseInt(split[1], min, max, aliases));
    }

    private TriggerEntry parseMultiplyEntities(String value, int min, int max, Alias[] aliases) {
        String[] values = StringUtils.split(value, ',');
        TriggerEntry[] entries = new TriggerEntry[values.length];
        for (int i = 0; i < values.length; i++) {
            entries[i] = parse(values[i], min, max, aliases);
        }
        return new ArrayTriggerEntry(entries);
    }

    private Integer parseInt(String s, int min, int max, Alias... aliases) {
        Integer integer = isNumber(s) ? Integer.valueOf(s) : findAlias(s, aliases);
        if (integer < min || integer > max) {
            throw new IllegalArgumentException(String.format("Number  must be in range %d-%d but actual is %d", min, max, integer));
        }
        return integer;
    }

    private Integer findAlias(String s, Alias[] aliases) {
        for (Alias alias : aliases) {
            if (alias.name().equalsIgnoreCase(s)) {
                return alias.getNumber();
            }
        }
        throw new IllegalArgumentException("Unsupported alias " + s + ", available: " + StringUtils.join(aliases));
    }

    private boolean isNumber(String s) {
        return NUMBER_PATTERN.matcher(s).matches();
    }

}
