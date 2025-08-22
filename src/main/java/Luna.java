import java.util.Scanner;
import java.util.ArrayList;

public class Luna {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> currList = new ArrayList<>();

        String greeting = " Hello! I'm Luna\n What can I do for you?";
        String exit = "Byebyeee~~ Hope to see you again soon!";

        System.out.println(greeting);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < currList.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + currList.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < currList.size()) {
                    currList.get(index).mark();
                    System.out.println(" Yayyy!! I've marked this task as done: ");
                    System.out.println("  " + currList.get(index));
                }
            }  else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < currList.size()) {
                    currList.get(index).unmark();
                    System.out.println(" Oh no :( I've marked this task as not done yet: ");
                    System.out.println("  " + currList.get(index));
                }
            } else {
                Task newTask = new Task(input);
                currList.add(newTask);
                System.out.println(" added: " + input);
            }
        }

        sc.close();

    }
}
