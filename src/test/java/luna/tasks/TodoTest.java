package luna.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testMark() {
        Todo t = new Todo("Read book", false);
        assertFalse(t.isDone()); // initially not done
        t.mark();
        assertTrue(t.isDone());  // should now be done
    }

    @Test
    void testToFileString() {
        Todo todo = new Todo("Exercise", false);
        todo.mark();
        assertEquals("T | 1 | Exercise", todo.toFileString());
    }

    @Test
    void testToString() {
        Todo todo = new Todo("Homework", false);
        assertEquals("[T][ ] Homework", todo.toString());
    }

}
