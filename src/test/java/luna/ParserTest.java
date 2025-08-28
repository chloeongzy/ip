package luna;

import org.junit.jupiter.api.Test;
import luna.tasks.*;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseTodo() throws LunaException {
        Command cmd = Parser.parse("todo Read book");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void testParseMarkCommand() throws LunaException {
        TaskList taskList = new TaskList(new java.util.ArrayList<>(), null);
        taskList.addTask(new Todo("Test task", false));

        Command cmd = Parser.parse("mark 1");
        cmd.execute(taskList, new Ui());

        assertTrue(taskList.getTaskList().get(0).isDone());
    }

    @Test
    void testParseInvalidCommand() {
        assertThrows(LunaException.InvalidCommandException.class, () -> {
            Parser.parse("invalidcommand");
        });
    }
}
