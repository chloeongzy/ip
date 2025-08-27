package luna;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Luna {
    private TaskList taskList;
    private Ui ui;

    public Luna() {
        ui = new Ui();
        taskList = new TaskList();
    }

    public void run() {
        ui.greeting();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = sc.nextLine();
            try {
                Command c = Parser.parse(input);
                c.execute(taskList, ui);
                if (c instanceof ExitCommand) {
                    isExit = true;
                }
            } catch (DateTimeParseException | LunaException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Luna().run();
    }

}

