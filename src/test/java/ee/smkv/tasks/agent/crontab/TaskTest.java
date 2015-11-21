package ee.smkv.tasks.agent.crontab;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testParse() throws Exception {
        Task task = Task.parse("* * * * * echo 1");
        assertEquals("* * * * * echo 1", task.toString());
        assertEquals("* * * * *", task.getTrigger().toString());
        assertEquals("echo 1", task.getCommand());


    }

    @Test
    public void testParse2() throws Exception {
        Task task = Task.parse("23 */2 * * * echo \"Выполняется в 0:23, 2:23, 4:23 и т. д.\"");
        assertEquals("23 */2 * * *" , task.getTrigger().toString());
        assertEquals("echo \"Выполняется в 0:23, 2:23, 4:23 и т. д.\"" , task.getCommand());

    }
}