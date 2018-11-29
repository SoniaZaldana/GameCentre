package fall2018.csc2017.GameCentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTrackerTest {
    private MoveTracker moveTracker;

    @Test
    public void setMoves() {
        moveTracker = new MoveTracker();
        moveTracker.setMoves(10);
        assertEquals(10, moveTracker.getMoves());
    }

    @Test
    public void getMoves() {
        moveTracker = new MoveTracker();
        assertEquals(0, moveTracker.getMoves());
        moveTracker.addMoves(10);
        assertEquals(10, moveTracker.getMoves());
    }

    @Test
    public void addMoves() {
        moveTracker = new MoveTracker();
        moveTracker.addMoves(10);
        moveTracker.addMoves(5);
        assertEquals(15, moveTracker.getMoves());
    }
}