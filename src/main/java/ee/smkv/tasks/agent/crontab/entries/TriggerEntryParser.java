package ee.smkv.tasks.agent.crontab.entries;

import org.apache.commons.lang3.StringUtils;

public class TriggerEntryParser {


    public TriggerEntry parse(String value, int min, int max) {
        if (StringUtils.contains(value, ',')) {
            String[] values = StringUtils.split(value, ',');
            TriggerEntry[] entries = new TriggerEntry[values.length];
            for (int i = 0; i < values.length; i++) {
                entries[i] = parse(values[i], min, max);
            }
            return new ArrayTriggerEntry(entries);
        } else {
            if (StringUtils.contains(value, '/')) {
                String[] split = StringUtils.split(value, "/", 2);
                return new ModTriggerEntry(parse(split[0], min, max), parseInt(split[1], min, max));
            } else if (StringUtils.contains(value, '-')) {
                String[] split = StringUtils.split(value, "-", 2);
                return new RangeTriggerEntry(parseInt(split[0], min, max), parseInt(split[1], min, max));
            } else if (StringUtils.equals(value, "*")) {
                return new AnyTriggerEntry();
            } else {
                return new FixedTriggerEntry(parseInt(value, min, max));
            }
        }
    }

    private Integer parseInt(String s, int min, int max) {
        Integer integer = Integer.valueOf(s);
        if (integer < min || integer > max) {
            throw new IllegalArgumentException(String.format("Number  must be in range %d-%d but actual is %d", min, max, integer));
        }
        return integer;
    }

}
