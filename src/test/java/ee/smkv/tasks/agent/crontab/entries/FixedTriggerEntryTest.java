package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class FixedTriggerEntryTest {

    @Test
    public void testAccept() throws Exception {
        assertTrue(new FixedTriggerEntry(1).accept(1));
        assertTrue(new FixedTriggerEntry(0).accept(0));

        assertFalse(new FixedTriggerEntry(0).accept(1));
        assertFalse(new FixedTriggerEntry(1).accept(0));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("1", new FixedTriggerEntry(1).toString());
    }
}