package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnyTriggerEntryTest {

    @Test
    public void testAccept() throws Exception {
        int min = 0;
        int max = 59;
        assertTrue(new AnyTriggerEntry(min, max).accept(0));
        assertTrue(new AnyTriggerEntry(min, max).accept(1));
        assertFalse(new AnyTriggerEntry(min, max).accept(-1));
        assertFalse(new AnyTriggerEntry(min, max).accept(-1000));
        assertFalse(new AnyTriggerEntry(min, max).accept(+1000));
        assertFalse(new AnyTriggerEntry(min, max).accept(Integer.MAX_VALUE));
        assertFalse(new AnyTriggerEntry(min, max).accept(Integer.MIN_VALUE));
    }

    @Test
    public void testToString() throws Exception {
        int min = 0;
        int max = 59;
        assertEquals("*", new AnyTriggerEntry(min, max).toString());
    }
}