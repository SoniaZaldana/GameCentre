package fall2018.csc2017.GameCentre.SlidingTiles;

import org.junit.Test;

import fall2018.csc2017.GameCentre.UndoStack;

import static org.junit.Assert.*;

public class SlidingBoardManagerTest {
    private SlidingBoardManager slidingBoardManager;

    @Test
    public void getUndoStack() {
        slidingBoardManager = new SlidingBoardManager(3, 4);
        UndoStack undoStack = new UndoStack(4);
        assertEquals(undoStack, slidingBoardManager.getUndoStack());
    }

    //TODO in here we potentially also need to test for when the stack is not empty, but idk how.
    @Test
    public void undo() {
        slidingBoardManager = new SlidingBoardManager(2, 3);
        assertEquals(false, slidingBoardManager.undo());
    }

    @Test
    public void calculateScore() {
        slidingBoardManager = new SlidingBoardManager(2, 3);
        assertEquals(950, slidingBoardManager.calculateScore(10));
    }
}