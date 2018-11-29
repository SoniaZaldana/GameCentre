package fall2018.csc2017.GameCentre.SlidingTiles;

import org.junit.Test;

import fall2018.csc2017.GameCentre.Tile;
import fall2018.csc2017.GameCentre.UndoStack;

import static org.junit.Assert.*;

public class SlidingBoardManagerTest {
    private SlidingBoardManager slidingBoardManager;

    @Test
    public void getUndoStack() {
        boolean equality = false;
        slidingBoardManager = new SlidingBoardManager(3, 4);
        UndoStack undoStack = new UndoStack(4);
        Tile tile = new Tile(2);
        slidingBoardManager.getUndoStack().push(tile);
        undoStack.push(tile);
        equality = equality(undoStack, slidingBoardManager.getUndoStack());
        assertTrue(equality);
    }

    private boolean equality(UndoStack thisStack, UndoStack otherStack) {
        boolean equality = true;
        if (thisStack.getSize() != otherStack.getSize()){
            return false;
        }
        while (thisStack.getSize() != 0) {
            equality = thisStack.pop() != otherStack.pop();
        }
        return equality;
    }


    //TODO in here we potentially also need to test for when the stack is not empty, but idk how.
    @Test
    public void undo() {
        slidingBoardManager = new SlidingBoardManager(2, 3);
        assertFalse(slidingBoardManager.undo());
        Tile pushingTile = new Tile(2);
        slidingBoardManager.getUndoStack().push(pushingTile);
        assertTrue(slidingBoardManager.undo());
    }

    @Test
    public void calculateScore() {
        slidingBoardManager = new SlidingBoardManager(2, 3);
        assertEquals(950, slidingBoardManager.calculateScore(10));
    }
}