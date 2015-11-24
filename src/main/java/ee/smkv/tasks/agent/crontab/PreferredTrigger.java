package ee.smkv.tasks.agent.crontab;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PreferredTrigger extends Trigger {

    private final String synonym;
    public static final Map<String, Trigger> PREFERENCES = Collections.unmodifiableMap(
            new HashMap<String, Trigger>() {{
                PREFERENCES.put("@yearly", new PreferredTrigger("@yearly", "0", "0", "1", "1", "*"));
                PREFERENCES.put("@annually", new PreferredTrigger("@annually", "0", "0", "1", "1", "*"));
                PREFERENCES.put("@monthly", new PreferredTrigger("@monthly", "0", "0", "1", "*", "*"));
                PREFERENCES.put("@weekly", new PreferredTrigger("@weekly", "0", "0", "*", "*", "0"));
                PREFERENCES.put("@daily", new PreferredTrigger("@daily", "0", "0", "*", "*", "*"));
                PREFERENCES.put("@hourly", new PreferredTrigger("@hourly", "0", "*", "*", "*", "*"));
                PREFERENCES.put("@reboot", new PreferredTrigger("@hourly", "*", "*", "*", "*", "*") {
                    @Override
                    public boolean accept(Date date) {
                        return false;
                    }
                });
            }}
    );

    public PreferredTrigger(String synonym, String minutes, String hours, String days, String months, String weeks) {
        super(minutes, hours, days, months, weeks);
        this.synonym = synonym;
    }

    @Override
    public String toString() {
        return synonym;
    }
}
