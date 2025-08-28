package luna;

import java.util.ArrayList;

import luna.tasks.Task;

public interface Command {
    void execute(TaskList taskList, Ui ui) throws LunaException;
}

class InvalidCommand implements Command {
    public void execute(TaskList tasks, Ui ui) throws LunaException {
        throw new LunaException.InvalidCommandException(" Sorry I don't know what that means :(");
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
            throw new LunaException.InvalidTaskNumberException(" Give me a valid number please!!");
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
            throw new LunaException.InvalidTaskNumberException(" Give me a valid number please!!");
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

class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by listing all tasks whose description contains the keyword.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The UI to display output messages.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }

        if (matches.isEmpty()) {
            System.out.println(" oops! No matching tasks found :(");
        } else {
            System.out.println(" Here are the matching tasks in your list: ");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matches.get(i));
            }
        }
    }
}

