package ee.smkv.tasks.agent.crontab.entries;

import org.junit.Test;

import static org.junit.Assert.*;

public class MonthsTest {

    @Test
    public void testGetNumber() throws Exception {
         assertEquals(1, Months.JAN.getNumber());
         assertEquals(2, Months.FEB.getNumber());
         assertEquals(3, Months.MAR.getNumber());
         assertEquals(4, Months.APR.getNumber());
         assertEquals(5, Months.MAY.getNumber());
         assertEquals(6, Months.JUNE.getNumber());
         assertEquals(7, Months.JULE.getNumber());
         assertEquals(8, Months.AUG.getNumber());
         assertEquals(9, Months.SEPT.getNumber());
         assertEquals(10, Months.OCT.getNumber());
         assertEquals(11, Months.NOV.getNumber());
         assertEquals(12, Months.DEC.getNumber());
    }
}