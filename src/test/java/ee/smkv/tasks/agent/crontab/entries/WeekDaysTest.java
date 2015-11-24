package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeekDaysTest {

    @Test
    public void testGetNumber() throws Exception {
        assertEquals(0 , WeekDays.SUN.getNumber());
        assertEquals(1 , WeekDays.MON.getNumber());
        assertEquals(2 , WeekDays.TUE.getNumber());
        assertEquals(3 , WeekDays.WED.getNumber());
        assertEquals(4 , WeekDays.THU.getNumber());
        assertEquals(5 , WeekDays.FRI.getNumber());
        assertEquals(6 , WeekDays.SAT.getNumber());
    }
}