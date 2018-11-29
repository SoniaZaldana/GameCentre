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
}