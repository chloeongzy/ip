package luna.tasks;

/**
 * Represents a task with no associated date or time.
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {

        super(description, isDone);
    }

    /**
     * Returns a string representation of the task for display.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}

