public class LunaException {

    // Exception for invalid commands
    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    // Exception for invalid task number for mark/unmark commands
    public static class InvalidTaskNumberException extends Exception {
        public InvalidTaskNumberException(String message) {
            super(message);
        }
    }

    // Exception for empty input
    public static class EmptyInputException extends Exception {
        public EmptyInputException(String message) {
            super(message);
        }
    }

    //Exception for invalid task description
    public static class EmptyTaskDescriptionException extends Exception {
        public EmptyTaskDescriptionException(String message) {
            super(message);
        }
    }
}