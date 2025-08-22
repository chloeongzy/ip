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
            //Handling exception for when no input is given
            if (input.isEmpty()) {
                try {
                    throw new LunaException.EmptyInputException("Yes? How can I help you? :)");
                } catch (LunaException.EmptyInputException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

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
                } else {
                    //handling exception for when invalid index is given
                    try {
                        throw new LunaException.InvalidTaskNumberException("Give me a valid number please!!");
                    } catch (LunaException.InvalidTaskNumberException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }  else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < currList.size()) {
                    currList.get(index).unmark();
                    System.out.println(" Oh no :( I've marked this task as not done yet:");
                    System.out.println("  " + currList.get(index));
                } else {
                    //handling exception for when invalid index is given
                    try {
                        throw new LunaException.InvalidTaskNumberException("Give me a valid number please!!");
                    } catch (LunaException.InvalidTaskNumberException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.split(" ", 2); // split into 2 parts first
                String completeDescription = parts.length > 1 ? parts[1] : "";
                String description = completeDescription.split(" by ")[0];
                String by = completeDescription.isEmpty() ? "" : completeDescription.split(" by ")[1];
                if (description.isEmpty()) {
                    try {
                        throw new LunaException.EmptyTaskDescriptionException("deadline for what???");
                    } catch (LunaException.EmptyTaskDescriptionException e) {
                        System.out.println(e.getMessage());
                        continue; // skip to next loop iteration
                    }
                }
                Deadline newTask = new Deadline(description, by);
                currList.add(newTask);
                System.out.println(" okay! I've added this task:");
                System.out.println("  " + newTask);
                System.out.println(" Looks like you have " + currList.size() + " tasks in the list...");
            } else if (input.startsWith("event ")) {
                String[] parts = input.split(" ", 2); //split into 2 parts first
                String completeDescription = parts.length > 1 ? parts[1] : "";
                String description = completeDescription.split(" /from ")[0];
                String duration = completeDescription.isEmpty() ? "" : completeDescription.split(" /from ")[1];
                String from = duration.split(" /to ")[0];
                String to = duration.isEmpty() ? "" : duration.split(" /to ")[1];
                if (description.isEmpty()) {
                    try {
                        throw new LunaException.EmptyTaskDescriptionException("what event do you have?");
                    } catch (LunaException.EmptyTaskDescriptionException e) {
                        System.out.println(e.getMessage());
                        continue; // skip to next loop iteration
                    }
                }

                Event newTask = new Event(description, from, to);
                currList.add(newTask);
                System.out.println(" okay! I've added this task:");
                System.out.println("  " + newTask);
                System.out.println(" Looks like you have " + currList.size() + " tasks in the list...");
            } else if (input.startsWith("todo ")) {
                String[] parts = input.split(" ");
                String description = (parts.length > 1) ? parts[1].trim() : "";

                if (description.isEmpty()) {
                    try {
                        throw new LunaException.EmptyTaskDescriptionException("what do you need to do???");
                    } catch (LunaException.EmptyTaskDescriptionException e) {
                        System.out.println(e.getMessage());
                        continue; // skip to next loop iteration
                    }
                }
                Todo newTask = new Todo(description);
                currList.add(newTask);
                System.out.println(" okay! I've added this task:");
                System.out.println("  " + newTask);
                System.out.println(" Looks like you have " + currList.size() + " tasks in the list...");
            } else {
                try {
                    throw new LunaException.InvalidCommandException("Sorry I don't know what that means :(");
                } catch (LunaException.InvalidCommandException e) {
                    System.out.println(e.getMessage());
                }
            }

        }

        sc.close();

    }
}
