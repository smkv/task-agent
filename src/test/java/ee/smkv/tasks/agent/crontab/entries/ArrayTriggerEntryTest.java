package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayTriggerEntryTest {

    @Test
    public void testAccept() throws Exception {
        ArrayTriggerEntry arrayTriggerEntry = new ArrayTriggerEntry(
                new FixedTriggerEntry(1),
                new FixedTriggerEntry(2),
                new FixedTriggerEntry(7)
        );
        assertTrue(arrayTriggerEntry.accept(1));
        assertTrue(arrayTriggerEntry.accept(2));
        assertTrue(arrayTriggerEntry.accept(7));

        assertFalse(arrayTriggerEntry.accept(0));
        assertFalse(arrayTriggerEntry.accept(3));
        assertFalse(arrayTriggerEntry.accept(5));
        assertFalse(arrayTriggerEntry.accept(6));
    }

    @Test
    public void testToString() throws Exception {
        ArrayTriggerEntry arrayTriggerEntry = new ArrayTriggerEntry(
                new FixedTriggerEntry(1),
                new FixedTriggerEntry(2),
                new FixedTriggerEntry(7)
        );
        assertEquals("1,2,7", arrayTriggerEntry.toString());
    }
}