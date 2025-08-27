package luna.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, inputFormat);
        this.to = LocalDateTime.parse(to, inputFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " +
                this.from.format(outputFormat) + " to: " + this.to.format(outputFormat) + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E" + super.toFileString() + " | " +
                from.format(outputFormat) + " | " + to.format(outputFormat);
    }
}
