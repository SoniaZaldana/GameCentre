package fall2018.csc2017.GameCentre.Simon;

import android.os.Handler;

import org.junit.Test;

import java.util.ListIterator;

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
    public void size(){
        GameQueue<SimonTile> queue = new GameQueue<>();
        for(int i = 0; i<4; i++){
            queue.add(new SimonTile());
        }
        assertEquals(4,queue.getSize() );
    }
    @Test
    public void get(){
        GameQueue<SimonTile> queue = new GameQueue<>();
        SimonTile t1 = new SimonTile(0);
        SimonTile t2 = new SimonTile(1);
        queue.add(t1);
        queue.add(t2);
        assertEquals(1, queue.get(1).getId());
        final SimonBoardManager sbm = new SimonBoardManager(3,2 );
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimonTile randomTile = sbm.randomizer();
                sbm.getGameQueue().add(randomTile);
            }
        }, 1000);
    }

    @Test
    public void remove() {
        GameQueue<SimonTile> queue = new GameQueue<>();
        SimonTile tile = new SimonTile();
        queue.add(tile);
        assertEquals(tile, queue.remove());
        assertEquals(true, queue.isEmpty());
    }
    @Test
    public void testIterator(){
        GameQueue<SimonTile> queue = new GameQueue<>();
        ListIterator<SimonTile> it = queue.iterator();
        //text hasPrevious and hasNext
        assertFalse(it.hasPrevious());
        assertFalse(it.hasNext());
        SimonTile tile1 = new SimonTile(1);
        queue.add(tile1);
        assertTrue(it.hasNext());
        assertEquals(it.next(), tile1);
        assertEquals(it.previous(), tile1);
        SimonTile tile2 = new SimonTile(2);
        queue.add(tile2);
        SimonTile tile3 = new SimonTile(3);
        queue.add(tile3);
        //text hasNext on a 3rd item
        it = queue.iterator();
        it.next();
        it.next();
        assertEquals(tile3, it.next());
        assertEquals(2, it.previousIndex());
        assertEquals(4, it.nextIndex());



    }
}