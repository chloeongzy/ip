package luna;

import luna.tasks.*;
import java.util.ArrayList;
import java.io.*;

public class Storage {
    private final String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return taskList;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            taskList.add(parseTask(line));
        }
        br.close();
        return taskList;
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (type) {
        case "T":
            return new Todo(parts[2], isDone);
        case "D":
            return new Deadline(parts[2], isDone, parts[3]);
        case "E":
            return new Event(parts[2], isDone, parts[3], parts[4]);
        default:
            throw new IllegalArgumentException("Unknown task type in file: " + type);
        }
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            bw.write(task.toFileString());
            bw.newLine();
        }
        bw.close();
    }
}
