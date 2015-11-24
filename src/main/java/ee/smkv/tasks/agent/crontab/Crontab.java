package ee.smkv.tasks.agent.crontab;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Crontab {
    public String getContent() throws IOException {
        return IOUtils.toString(getProcessBuilder("crontab -l").start().getInputStream());
    }

    public List<Task> getTasks() throws IOException, ParseException {
        CronLineParser parser = new CronLineParser();
        String content = getContent();
        StringTokenizer tokenizer = new StringTokenizer(content,"\n");

        ArrayList<Task> tasks = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String line = tokenizer.nextToken();
            if (parser.isTask(line)) {
                tasks.add(parser.parse(line));
            }
        }
        return tasks;
    }

    public void setContent(String content) throws IOException {
        File tempFile = File.createTempFile("crontab", ".txt");
        FileUtils.write(tempFile, content);
        getProcessBuilder("crontab " + tempFile.getAbsolutePath()).start();
        tempFile.delete();
    }

    private ProcessBuilder getProcessBuilder(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
        processBuilder.redirectErrorStream(true);
        return processBuilder;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        Crontab crontab = new Crontab();
        System.out.println(crontab.getTasks());
    }
}
