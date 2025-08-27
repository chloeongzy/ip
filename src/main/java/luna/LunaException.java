package luna;

public class LunaException extends Exception {
    public LunaException(String message) {
        super(message);
    }

    // Exception for invalid commands
    public static class InvalidCommandException extends LunaException {
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    // Exception for invalid task number for mark/unmark commands
    public static class InvalidTaskNumberException extends LunaException {
        public InvalidTaskNumberException(String message) {
            super(message);
        }
    }

    // Exception for empty input
    public static class EmptyInputException extends LunaException {
        public EmptyInputException(String message) {
            super(message);
        }
    }

    // Exception for invalid task description
    public static class EmptyTaskDescriptionException extends LunaException {
        public EmptyTaskDescriptionException(String message) {
            super(message);
        }
    }
}
