package ee.smkv.tasks.agent.crontab;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void testAcceptAny() throws Exception {
        Trigger trigger = new Trigger("*", "*", "*", "*", "*");
        assertTrue(trigger.accept(new Date()));
    }

    @Test
    public void testAcceptFixed() throws Exception {
        Trigger trigger = new Trigger("30", "16", "15", "11", "0");
        assertTrue(trigger.accept(parseDate("15.11.2015 16:30")));
    }


    @Test
    public void testAcceptFixedWeek() throws Exception {
        Trigger trigger;

        trigger = new Trigger("*", "*", "*", "*", "0");
        assertTrue(trigger.accept(parseDate("15.11.2015 16:30")));

        trigger = new Trigger("*", "*", "*", "*", "7");
        assertTrue(trigger.accept(parseDate("15.11.2015 16:30")));

        trigger = new Trigger("*", "*", "*", "*", "1");
        assertTrue(trigger.accept(parseDate("16.11.2015 16:30")));

        trigger = new Trigger("*", "*", "*", "*", "2");
        assertTrue(trigger.accept(parseDate("17.11.2015 16:30")));

        trigger = new Trigger("*", "*", "*", "*", "3");
        assertTrue(trigger.accept(parseDate("18.11.2015 16:30")));

        trigger = new Trigger("*", "*", "*", "*", "4");
        assertTrue(trigger.accept(parseDate("19.11.2015 16:30")));

        trigger = new Trigger("*", "*", "*", "*", "5");
        assertTrue(trigger.accept(parseDate("20.11.2015 16:30")));

        trigger = new Trigger("*", "*", "*", "*", "6");
        assertTrue(trigger.accept(parseDate("21.11.2015 16:30")));
    }


    @Test
    public void testNextExecutionDate() throws Exception {

        Trigger trigger;

        trigger = new Trigger("*", "*", "*", "*", "*");
        assertEquals("15.11.2015 16:30", formatDate(trigger.nextExecutionDate(parseDate("15.11.2015 16:30"))));

        trigger = new Trigger("30", "16", "15", "11", "0");
        assertEquals("15.11.2015 16:30", formatDate(trigger.nextExecutionDate(parseDate("15.11.2015 16:30"))));

        trigger = new Trigger("30", "16", "16", "11", "1");
        assertEquals("16.11.2015 16:30", formatDate(trigger.nextExecutionDate(parseDate("15.11.2015 16:30"))));

    }

    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(date);
    }

    private String formatDate(Date date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);
    }
}