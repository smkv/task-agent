package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class StepTriggerEntryTest {

    @Test
    public void testAcceptEven() throws Exception {
        StepTriggerEntry triggerEntry = new StepTriggerEntry(new RangeTriggerEntry(0, 10), 2);
        assertTrue(triggerEntry.accept(0));
        assertFalse(triggerEntry.accept(1));
        assertTrue(triggerEntry.accept(2));
        assertFalse(triggerEntry.accept(3));
        assertTrue(triggerEntry.accept(4));
        assertFalse(triggerEntry.accept(5));
    }
    @Test
    public void testAcceptOdd() throws Exception {
        StepTriggerEntry triggerEntry = new StepTriggerEntry(new RangeTriggerEntry(1, 10), 2);
        assertFalse(triggerEntry.accept(0));
        assertTrue(triggerEntry.accept(1));
        assertFalse(triggerEntry.accept(2));
        assertTrue(triggerEntry.accept(3));
        assertFalse(triggerEntry.accept(4));
        assertTrue(triggerEntry.accept(5));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("0-10/2" , new StepTriggerEntry(new RangeTriggerEntry(0, 10), 2).toString());
        assertEquals("*/2" , new StepTriggerEntry(new AnyTriggerEntry(0, 10), 2).toString());
    }
}