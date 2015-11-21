package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriggerEntryParserTest {

    @Test
    public void testParse() throws Exception {
        TriggerEntryParser parser = new TriggerEntryParser();
        assertEquals("*", parser.parse("*", 0, 10).toString());
        assertEquals("1", parser.parse("1", 0, 10).toString());
        assertEquals("*/2", parser.parse("*/2", 0, 10).toString());
        assertEquals("1-10/2", parser.parse("1-10/2", 0, 10).toString());
        assertEquals("1,10/2", parser.parse("1,10/2", 0, 10).toString());
    }
}