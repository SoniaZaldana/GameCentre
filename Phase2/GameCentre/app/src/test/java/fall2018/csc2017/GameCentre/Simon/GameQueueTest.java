package fall2018.csc2017.GameCentre.Simon;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameQueueTest {

    @Test
    public void add() {
        GameQueue<SimonTile> queue = new GameQueue<>();
        SimonTile tile = new SimonTile();
        queue.add(tile);
        assertEquals(false, queue.isEmpty());
        assertEquals(tile, queue.remove());

    }

    @Test
    public void isEmpty() {
        GameQueue<SimonTile> queue = new GameQueue<>();
        assertEquals(true, queue.isEmpty());
    }

    @Test
    public void remove() {
        GameQueue<SimonTile> queue = new GameQueue<>();
        SimonTile tile = new SimonTile();
        queue.add(tile);
        assertEquals(tile, queue.remove());
        assertEquals(true, queue.isEmpty());
    }
}