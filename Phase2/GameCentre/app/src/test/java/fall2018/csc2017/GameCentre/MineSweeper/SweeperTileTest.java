package fall2018.csc2017.GameCentre.MineSweeper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SweeperTileTest {
    private SweeperTile sweeperTile;
    private List<String> bombTypes;


    @Before
    public void setUp() throws Exception {
    }

    /**
     * We test if the tile has a valid bomb type. We check with a list because the bomb type is
     * random.
     */
    @Test
    public void getBombType() {
        // Checking tile without bomb
        sweeperTile = new SweeperTile(false);
        assertEquals("none", sweeperTile.getBombType());

        // Checking tile with bomb
        sweeperTile = new SweeperTile(true);
        createBombList();
        assertEquals(true, bombTypes.contains(sweeperTile.getBombType()));
    }

    private void createBombList() {
        bombTypes = new ArrayList<>();
        bombTypes.add("small");
        bombTypes.add("timed");
        bombTypes.add("big");
    }

    @Test
    public void isFlagged() {
        sweeperTile = new SweeperTile(false);
        sweeperTile.flag(true);
        assertEquals(true, sweeperTile.isFlagged());
    }

    @Test
    public void flag() {
        sweeperTile = new SweeperTile(true);
        sweeperTile.flag(false);
        assertEquals(false, sweeperTile.isFlagged());
        sweeperTile = new SweeperTile(false);
        sweeperTile.flag(true);
        assertEquals(true, sweeperTile.isFlagged());

    }

    @Test
    public void hasBomb() {
        sweeperTile = new SweeperTile(false);
        assertEquals(false, sweeperTile.hasBomb());
        sweeperTile = new SweeperTile(true);
        assertEquals(true, sweeperTile.hasBomb());
    }

    @Test
    public void setBomb() {
        sweeperTile = new SweeperTile(true);
        sweeperTile.setBomb(false);
        assertEquals(false, sweeperTile.hasBomb());
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