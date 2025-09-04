package luna;

import java.time.format.DateTimeParseException;

import luna.tasks.Deadline;
import luna.tasks.Event;
import luna.tasks.Todo;

/**
 * Parses user input commands into executable actions.
 * Converts raw text input from the command line into structured
 * command objects that can be processed by the system.
 */
public class Parser {

    /**
     * Parses the given input string and returns the corresponding Command object.
     * @param input The user-input string.
     * @return A command matching the user input.
     * @throws LunaException if user-input was invalid .
     */
    public static Command parse(String input) throws DateTimeParseException, LunaException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String detail = parts.length > 1 ? parts[1] : "";

        if (detail.equals("")) {
            throw new LunaException.EmptyInputException("description of task cannot be empty!!!!!!");
        }

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
                return new AddCommand(new Todo(detail, false));

            case "deadline":
                String[] dParts = detail.split(" /by ");
                return new AddCommand(new Deadline(dParts[0], false, dParts[1]));

            case "event":
                String[] eParts = detail.split(" /from ");
                String[] duration = eParts[1].split(" /to ");
                return new AddCommand(new Event(eParts[0], false, duration[0], duration[1]));

            case "find":
                return new FindCommand(detail);

            default:
                return new InvalidCommand();
            }
        } catch (DateTimeParseException e) {
            throw new LunaException("Error during parsing: " + e.getMessage());
        }
    }
}
