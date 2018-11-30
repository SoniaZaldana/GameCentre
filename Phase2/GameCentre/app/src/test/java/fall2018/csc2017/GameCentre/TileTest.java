package fall2018.csc2017.GameCentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {

    @Test
    public void getId() {
        Tile tile = new Tile(2);
        assertEquals(2, tile.getId());
    }

    @Test
    public void setId() {
        Tile tile = new Tile(3);
        tile.setId(4);
        Tile tile1 = new Tile();
        assertEquals(4, tile.getId());
    }

    @Test
    public void compareTo() {
        Tile tile1 = new Tile(5);
        Tile tile2 = new Tile(5);
        assertTrue(tile1.compareTo(tile2) == 0);
        Tile tile3 = new Tile(4);
        assertFalse(tile1.compareTo(tile3) == 0);
    }
}