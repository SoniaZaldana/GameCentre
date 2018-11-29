package fall2018.csc2017.GameCentre.MineSweeper;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SweeperTileTest {
    private SweeperTile sweeperTile;
    private List<String> bombTypes;

    /**
     * We test if the tile has a valid bomb type. To do this, wee check with a list because the
     * bomb type is random, so we just check the bomb type is one of the elements of the list.
     */
    @Test
    public void getBombType() {
        // Checking tile without bomb
        sweeperTile = new SweeperTile(false);
        assertEquals("none", sweeperTile.getBombType());

        // Checking tile with bomb
        sweeperTile = new SweeperTile(true);
        createBombList();
        assertTrue(bombTypes.contains(sweeperTile.getBombType()));
    }

    /**
     * Creates a list containing the 3 types of bombs
     */
    private void createBombList() {
        bombTypes = new ArrayList<>();
        bombTypes.add("small");
        bombTypes.add("timed");
        bombTypes.add("big");
    }

    @Test
    public void isFlagged() {
        // Checking a new tile that hasn't been flagged yet
        sweeperTile = new SweeperTile(false);
        assertFalse(sweeperTile.isFlagged());

        //Checking flagging the tile
        sweeperTile.flag(true);
        assertTrue(sweeperTile.isFlagged());

        //Checking unflagging the tile
        sweeperTile.flag(false);
        assertFalse(sweeperTile.isFlagged());
    }

    @Test
    public void flag() {
        sweeperTile = new SweeperTile(true);
        sweeperTile.flag(false);
        assertFalse(sweeperTile.isFlagged());
        sweeperTile = new SweeperTile(false);
        sweeperTile.flag(true);
        assertTrue(sweeperTile.isFlagged());

    }

    @Test
    public void hasBomb() {
        // Instantiating tile without bomb
        sweeperTile = new SweeperTile(false);
        assertFalse(sweeperTile.hasBomb());

        // Instantiating tile with bomb
        sweeperTile = new SweeperTile(true);
        assertTrue(sweeperTile.hasBomb());
    }

    @Test
    public void setBomb() {
        sweeperTile = new SweeperTile(true);
        sweeperTile.setBomb(false);
        assertFalse(sweeperTile.hasBomb());
    }

    @Test
    public void getBombsAround() {
        sweeperTile = new SweeperTile(false);
        sweeperTile.setBombsAround(2);
        assertEquals(2, sweeperTile.getBombsAround());
    }

    @Test
    public void setBombsAround() {
        sweeperTile = new SweeperTile(true);
        sweeperTile.setBombsAround(3);
        assertEquals(3, sweeperTile.getBombsAround());
    }
}