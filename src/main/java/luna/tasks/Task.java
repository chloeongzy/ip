package luna.tasks;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a general task with a description and completion status.
 * This is the base class for more specific task types.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a Task with the given description and status.
     *
     * @param description The task description.
     * @param isDone True if the task is marked as done; false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new HashSet<>();
    };

    public boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() { this.isDone = true; }

    public void unmark() {
        this.isDone = false;
    }

    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    public void removeTag(String tag) {
        tags.remove(tag.toLowerCase());
    }

    public Set<String> getTags() {
        return this.tags;
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return The string to be written to the save file.
     */
    public String toFileString() {
        String tagString = tags.isEmpty()
                ? ""
                : " " + tags.stream().map(tag -> "#" + tag).reduce((a, b) -> a + " " + b).get();
        return " | " + (isDone ? "1" : "0") + " | " + this.description + tagString;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        String tagString = tags.isEmpty()
                ? ""
                : " " + tags.stream().map(tag -> "#" + tag).reduce((a, b) -> a + " " + b).get();
        return "[" + getStatusIcon() + "] " + this.description + tagString;
    }

}
