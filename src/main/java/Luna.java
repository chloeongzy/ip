import java.util.Scanner;

public class Luna {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Luna\nWhat can I do for you?";
        String exit = "Bye. Hope to see you again soon!";

        System.out.println(greeting);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else {
                System.out.println(input);
            }
        }

        sc.close();

    }
}
