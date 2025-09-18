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

    private static final String EMPTY_TASK_ERROR = " Error: description of task cannot be empty!";

    /**
     * Parses the given input string and returns the corresponding Command object.
     * @param input The user-input string.
     * @return A command matching the user input.
     * @throws LunaException if user-input was invalid .
     */
    public static Command parse(String input) throws DateTimeParseException, LunaException {
        assert input != null && !input.trim().isEmpty();
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String detail = parts.length > 1 ? parts[1] : "";

        try {
            switch (command) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new MarkCommand(Integer.parseInt(detail) - 1);

            case "unmark":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new UnmarkCommand(Integer.parseInt(detail) - 1);

            case "delete":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new DeleteCommand(Integer.parseInt(detail) - 1);

            case "todo":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new AddCommand(new Todo(detail, false));

            case "deadline":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                String[] dParts = detail.split(" /by ");
                assert dParts.length > 1;
                return new AddCommand(new Deadline(dParts[0], false, dParts[1]));

            case "event":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                String[] eParts = detail.split(" /from ");
                assert eParts.length > 1;
                String[] duration = eParts[1].split(" /to ");
                assert duration.length > 1;
                return new AddCommand(new Event(eParts[0], false, duration[0], duration[1]));

            case "find":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new FindCommand(detail);

            case "tag":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                String[] tParts = detail.split(" ");
                assert tParts.length > 1;
                int index = Integer.parseInt(tParts[0]) - 1;
                String[] tags = tParts[1].split(", ");
                return new TagCommand(index, tags);

            default:
                return new InvalidCommand();
            }
        } catch (DateTimeParseException e) {
            throw new LunaException("Error during parsing: " + e.getMessage());
        }
    }
}
