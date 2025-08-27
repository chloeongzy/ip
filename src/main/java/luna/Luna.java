package luna;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Luna {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    public Luna(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load(), storage);
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            taskList = new TaskList(new ArrayList<>(), storage);
        }

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
        new Luna("data/luna.txt").run();
    }

}

