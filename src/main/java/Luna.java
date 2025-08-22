import java.util.Scanner;
import java.util.ArrayList;

public class Luna {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> currList = new ArrayList<>();

        String greeting = " Hello! I'm Luna\n What can I do for you?";
        String exit = "Bye~~ Hope to see you again soon!";

        System.out.println(greeting);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                System.out.println(" Here are the tasks in your list!!");
                for (int i = 0; i < currList.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + currList.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < currList.size()) {
                    currList.get(index).mark();
                    System.out.println(" Yay!! I've marked this task as done:");
                    System.out.println("  " + currList.get(index));
                }
            }  else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < currList.size()) {
                    currList.get(index).unmark();
                    System.out.println(" Oh no :( I've marked this task as not done yet:");
                    System.out.println("  " + currList.get(index));
                }
            } else if (input.startsWith("deadline ")) {
                String description = input.split(" ")[1];
                String by = input.split(" /by ")[1];
                Deadline newTask = new Deadline(description, by);
                currList.add(newTask);
                System.out.println(" okay! I've added this task:");
                System.out.println("  " + newTask);
                System.out.println(" Looks like you have " + currList.size() + " tasks in the list...");
            } else if (input.startsWith("event ")) {
                String description = input.split(" ")[1];
                String from = input.split(" /from ")[1].split(" /to ")[0];
                String to = input.split(" /from ")[1].split(" /to ")[1];
                Event newTask = new Event(description, from, to);
                currList.add(newTask);
                System.out.println(" okay! I've added this task:");
                System.out.println("  " + newTask);
                System.out.println(" Looks like you have " + currList.size() + " tasks in the list...");
            } else if (input.startsWith("todo ")) {
                String description = input.split(" ")[1];
                Todo newTask = new Todo(description);
                currList.add(newTask);
                System.out.println(" okay! I've added this task:");
                System.out.println("  " + newTask);
                System.out.println(" Looks like you have " + currList.size() + " tasks in the list...");
            }
        }

        sc.close();

    }
}
