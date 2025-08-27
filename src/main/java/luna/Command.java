package luna;

import luna.tasks.Task;

public interface Command {
    void execute(TaskList taskList, Ui ui) throws LunaException;
}

class InvalidCommand implements Command {
    public void execute(TaskList tasks, Ui ui) throws LunaException {
        throw new LunaException.InvalidCommandException("Sorry I don't know what that means :(");
    }
}

class ExitCommand implements Command {
    public void execute(TaskList tasks, Ui ui) {
        ui.exit();
    }
}

class ListCommand implements Command {
    public void execute(TaskList tasks, Ui ui) {
        System.out.println(" Here are the tasks in your list!!");
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.getTaskList().get(i));
        }
    }
}

class MarkCommand implements Command {
    private final int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui) throws LunaException {
        if (index < 0 || index > tasks.getTaskList().size()) {
            throw new LunaException.InvalidTaskNumberException("Give me a valid number please!!");
        }
        tasks.markTask(this.index);
    }
}

class UnmarkCommand implements Command {
    private final int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui) throws LunaException {
        if (index < 0 || index > tasks.getTaskList().size()) {
            throw new LunaException.InvalidTaskNumberException("Give me a valid number please!!");
        }
        tasks.unmarkTask(this.index);
    }
}

class DeleteCommand implements Command {
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui) {
        tasks.deleteTask(index);
    }
}

class AddCommand implements Command {
    private final Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
    }
}

