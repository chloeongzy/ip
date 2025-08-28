package luna;

import luna.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages a list of tasks.
 * Methods for adding, deleting, marking, and unmarking tasks.
 * Saves changes to persistent storage after each modification.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    /**
     * Constructs a TaskList with the given list of tasks and storage.
     *
     * @param taskList The initial list of tasks.
     * @param storage The Storage object used to save tasks to disk.
     */
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
