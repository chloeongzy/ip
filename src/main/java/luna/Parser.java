package luna;

import luna.tasks.*;
import luna.LunaException;

import java.time.format.DateTimeParseException;


public class Parser {

    public static Command parse(String input) throws DateTimeParseException, LunaException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String detail = parts.length > 1 ? parts[1] : "";

        try {
            switch (command) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(Integer.parseInt(detail) - 1);

            case "unmark":
                return new UnmarkCommand(Integer.parseInt(detail) - 1);

            case "delete":
                return new DeleteCommand(Integer.parseInt(detail) - 1);

            case "todo":
                return new AddCommand(new Todo(detail));

            case "deadline":
                String[] dParts = detail.split(" /by ");
                return new AddCommand(new Deadline(dParts[0], dParts[1]));

            case "event":
                String[] eParts = detail.split(" /from ");
                String[] duration = eParts[1].split(" /to ");
                return new AddCommand(new Event(eParts[0], duration[0], duration[1]));

            default:
                return new InvalidCommand();
            }
        } catch (DateTimeParseException e) {
            throw new LunaException("Error during parsing: " + e.getMessage());
        }
    }
}
