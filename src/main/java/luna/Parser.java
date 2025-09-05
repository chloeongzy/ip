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

        try {
            switch (command) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException("description of task cannot be empty!!!!!!");
                }
                return new MarkCommand(Integer.parseInt(detail) - 1);

            case "unmark":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException("description of task cannot be empty!!!!!!");
                }
                return new UnmarkCommand(Integer.parseInt(detail) - 1);

            case "delete":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException("description of task cannot be empty!!!!!!");
                }
                return new DeleteCommand(Integer.parseInt(detail) - 1);

            case "todo":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException("description of task cannot be empty!!!!!!");
                }
                return new AddCommand(new Todo(detail, false));

            case "deadline":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException("description of task cannot be empty!!!!!!");
                }
                String[] dParts = detail.split(" /by ");
                return new AddCommand(new Deadline(dParts[0], false, dParts[1]));

            case "event":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException("description of task cannot be empty!!!!!!");
                }
                String[] eParts = detail.split(" /from ");
                String[] duration = eParts[1].split(" /to ");
                return new AddCommand(new Event(eParts[0], false, duration[0], duration[1]));

            case "find":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException("description of task cannot be empty!!!!!!");
                }
                return new FindCommand(detail);

            default:
                return new InvalidCommand();
            }
        } catch (DateTimeParseException e) {
            throw new LunaException("Error during parsing: " + e.getMessage());
        }
    }
}
