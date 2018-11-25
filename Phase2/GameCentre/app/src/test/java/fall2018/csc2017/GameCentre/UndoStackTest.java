package fall2018.csc2017.GameCentre;

import org.junit.Test;
import static org.junit.Assert.*;

public class UndoStackTest {
    UndoStack stack;

    @Test
    public void push() {
        stack = new UndoStack(3);
        // Checking pushing one item
        stack.push(10);
        assertEquals(10, stack.pop());

        // Checking pushing more than the stack max size
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        assertEquals(40,stack.pop());
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(null, stack.pop());
    }

    @Test
    public void pop() {
        stack = new UndoStack(2);
        stack.push("Sonia");
        assertEquals("Sonia", stack.pop());

    }

    @Test
    public void getSize() {
        stack = new UndoStack(3);
        stack.push(10);
        stack.push(2);
        assertEquals(2, stack.getSize());
    }
}