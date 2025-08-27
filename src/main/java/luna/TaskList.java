package luna;

import luna.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;

    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task t) {
        taskList.add(t);
        System.out.println(" Okay! I've added this task:");
        System.out.println("  " + t);
        System.out.println(" Looks like you have " + taskList.size() + " tasks in the list...");
        saveTasks();
    }

    public void deleteTask(int index) {
        Task t = taskList.remove(index);
        System.out.println(" Okay! I've removed this task:");
        System.out.println("  " + t);
        System.out.println(" Looks like you have " + taskList.size() + " tasks in the list...");
        saveTasks();
    }

    public void markTask(int index) {
        Task t = taskList.get(index);
        t.mark();
        System.out.println(" Yay!! I've marked this task as done:");
        System.out.println("  " + t);
        saveTasks();
    }

    public void unmarkTask(int index) {
        Task t = taskList.get(index);
        t.unmark();
        System.out.println(" Oh no :( I've marked this task as not done yet:");
        System.out.println("  " + t);
        saveTasks();
    }

    private void saveTasks() {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
