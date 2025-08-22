import java.util.Scanner;
import java.util.ArrayList;

public class Luna {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> currList = new ArrayList<>();

        String greeting = " Hello! I'm Luna\n What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";

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
            } else {
                currList.add(input);
                System.out.println(" added: " + input);
            }
        }

        sc.close();

    }
}
