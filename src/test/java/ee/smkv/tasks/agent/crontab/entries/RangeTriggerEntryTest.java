package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeTriggerEntryTest {

    @Test
    public void testAccept() throws Exception {
        assertTrue(new RangeTriggerEntry(0, 100).accept(0));
        assertTrue(new RangeTriggerEntry(0, 100).accept(50));
        assertTrue(new RangeTriggerEntry(0, 100).accept(100));

        assertFalse(new RangeTriggerEntry(0, 100).accept(-1));
        assertFalse(new RangeTriggerEntry(0, 100).accept(101));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("0-100", new RangeTriggerEntry(0, 100).toString());
    }
}