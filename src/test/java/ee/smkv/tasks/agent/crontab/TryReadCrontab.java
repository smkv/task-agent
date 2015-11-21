package ee.smkv.tasks.agent.crontab;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;

public class TryReadCrontab {

    public static void main(String[] args) throws IOException, ParseException {
        Crontab crontab = new Crontab();


        System.out.println(StringUtils.repeat('=' , 50));
        System.out.println(crontab.getContent());
        System.out.println(StringUtils.repeat('=' , 50));
        for (Task task : crontab.getTasks()) {
            System.out.println(task);
            System.out.println("Next execution: " + task.getTrigger().nextExecutionDate());
        }
        System.out.println(StringUtils.repeat('=' , 50));


    }
}
