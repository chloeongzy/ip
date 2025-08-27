package luna;

import luna.tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task t) {
        taskList.add(t);
        System.out.println(" Okay! I've added this task:");
        System.out.println("  " + t);
        System.out.println(" Looks like you have " + taskList.size() + " tasks in the list...");
    }

    public void deleteTask(int index) {
        Task t = taskList.remove(index);
        System.out.println(" Okay! I've removed this task:");
        System.out.println("  " + t);
        System.out.println(" Looks like you have " + taskList.size() + " tasks in the list...");
    }

    public void markTask(int index) {
        Task t = taskList.get(index);
        t.mark();
        System.out.println(" Yay!! I've marked this task as done:");
        System.out.println("  " + t);
    }

    public void unmarkTask(int index) {
        Task t = taskList.get(index);
        t.unmark();
        System.out.println(" Oh no :( I've marked this task as not done yet:");
        System.out.println("  " + t);
    }
}
