package fall2018.csc2017.GameCentre.MineSweeper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.GameCentre.Tile;

import static org.junit.Assert.*;

public class SweeperTilesBoardTest {
    private List<SweeperTile> sweeperTileList;
    private SweeperTilesBoard sweeperTilesBoard;
    @Before
    public void setUp(){
        createValidTileList();
    }



    @Test
    public void getTime() {
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        assertEquals(0, sweeperTilesBoard.getTime());
    }

    /**
     * Creates a valid list of sweeper tiles where half the tiles have a bomb and have do not
     */
    private void createValidTileList() {
        sweeperTileList = new ArrayList<>();
        SweeperTile tile1 = new SweeperTile(true);
        SweeperTile tile2 = new SweeperTile(true);
        SweeperTile tile3 = new SweeperTile(false);
        SweeperTile tile4 = new SweeperTile(false);
        sweeperTileList.add(tile1);
        sweeperTileList.add(tile2);
        sweeperTileList.add(tile3);
        sweeperTileList.add(tile4);
    }

    @Test
    public void timeIncrement() {
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.timeIncrement();
        assertEquals(1, sweeperTilesBoard.getTime());

    }

    @Test
    public void takeDamage() {
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.takeDamage();
        assertEquals(2, sweeperTilesBoard.getHitPoints());
    }

    @Test
    public void getHitPoints() {
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        assertEquals(3, sweeperTilesBoard.getHitPoints());
    }

    @Test
    public void getBombTime(){
        sweeperTilesBoard = new SweeperTilesBoard(5, 15);
        assertEquals(10,sweeperTilesBoard.getBombTime());
    }

    @Test
    public void setBombToExploded(){
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.setBombToExploded(0,0);
        assertTrue(sweeperTilesBoard.getTile(0,0).isBombExploded());
    }

    @Test
    public void setTileToFlagged(){
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.setTileToFlagged(0,0, true);
        assertTrue(sweeperTilesBoard.getTile(0,0).isFlagged());
    }

    @Test
    public void setBombsAround(){
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.setBombsAround(0,0,5);
        assertEquals(5, sweeperTilesBoard.getTile(0,0).getBombsAround());
    }

    @Test
    public void lowerBombTime(){
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.lowerBombTime();
        assertEquals(9, sweeperTilesBoard.getBombTime());
    }

    @Test
    public void swapTiles(){
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.swapTiles(0,0, 1,1);
        assertTrue(sweeperTileList.get(0) == sweeperTilesBoard.getTile(1,1));
        assertTrue(sweeperTileList.get(3) == sweeperTilesBoard.getTile(0,0));
    }

    @Test
    public void swipeWithSafeTile(){
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.swipeWithSafeTile(0,0);
        assertTrue(sweeperTileList.get(0) == sweeperTilesBoard.getTile(1,0));
        assertTrue(sweeperTileList.get(2) == sweeperTilesBoard.getTile(0,0));
    }

    @Test
    public void findFirstSafeTile(){
        sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        ArrayList testArray = sweeperTilesBoard.findFirsSafeTile();
        assertTrue((int) testArray.get(0) == 1);
        assertTrue((int) testArray.get(1) == 0);
        sweeperTileList.get(2).setBomb(true);
        sweeperTileList.get(3).setBomb(true);
        testArray = sweeperTilesBoard.findFirsSafeTile();
        assertTrue(testArray.isEmpty());


    }
}