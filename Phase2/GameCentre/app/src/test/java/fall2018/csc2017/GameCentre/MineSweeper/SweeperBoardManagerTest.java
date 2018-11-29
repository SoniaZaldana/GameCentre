package fall2018.csc2017.GameCentre.MineSweeper;
/**
 * We did not test timer methods because these were all direct applications of the Timer task in
 * java.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SweeperBoardManagerTest {
    private SweeperBoardManager sweeperBoardManager;
    private List<SweeperTile> sweeperTileList;

    @Test
    public void calculateScore() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperTilesBoard.timeIncrement();
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        assertEquals(10, sweeperBoardManager.calculateScore(10));

    }

    /**
     * Creating a valid list of sweeper tiles where half has a bomb and half does not
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
    public void getBombTime() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        assertEquals(10, sweeperBoardManager.getBombTime());
    }

    @Test
    public void isBombActive() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setBombActive(true);
        assertTrue(sweeperBoardManager.isBombActive());
    }

    @Test
    public void lowerBombTime() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.lowerBombTime();
        assertEquals(9, sweeperBoardManager.getBombTime());
    }

    @Test
    public void setBombActive() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setBombActive(false);
        assertFalse(sweeperBoardManager.isBombActive());
    }
}