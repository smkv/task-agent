package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnyTriggerEntryTest {

    @Test
    public void testAccept() throws Exception {
        assertTrue(new AnyTriggerEntry().accept(0));
        assertTrue(new AnyTriggerEntry().accept(1));
        assertTrue(new AnyTriggerEntry().accept(-1));
        assertTrue(new AnyTriggerEntry().accept(-1000));
        assertTrue(new AnyTriggerEntry().accept(+1000));
        assertTrue(new AnyTriggerEntry().accept(Integer.MAX_VALUE));
        assertTrue(new AnyTriggerEntry().accept(Integer.MIN_VALUE));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("*", new AnyTriggerEntry().toString());
    }
}