package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModTriggerEntryTest {

    @Test
    public void testAccept() throws Exception {
        assertTrue(new ModTriggerEntry(new AnyTriggerEntry(), 2).accept(0));
        assertTrue(new ModTriggerEntry(new AnyTriggerEntry(), 2).accept(2));
        assertTrue(new ModTriggerEntry(new AnyTriggerEntry(), 2).accept(4));
        assertTrue(new ModTriggerEntry(new AnyTriggerEntry(), 2).accept(6));

        assertFalse(new ModTriggerEntry(new AnyTriggerEntry(), 2).accept(3));
        assertFalse(new ModTriggerEntry(new AnyTriggerEntry(), 2).accept(5));
        assertFalse(new ModTriggerEntry(new AnyTriggerEntry(), 2).accept(7));

        assertTrue(new ModTriggerEntry(new RangeTriggerEntry(4, 4), 2).accept(4));
        assertFalse(new ModTriggerEntry(new RangeTriggerEntry(2, 3), 2).accept(4));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("*/2", new ModTriggerEntry(new AnyTriggerEntry(), 2).toString());
        assertEquals("0-59/2", new ModTriggerEntry(new RangeTriggerEntry(0, 59), 2).toString());
    }
}