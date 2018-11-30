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
        assertEquals(-1, sweeperBoardManager.calculateScore(0));

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
    public void isBombActive() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setBombActive(true);
        assertTrue(sweeperBoardManager.isBombActive());
    }



    @Test
    public void setBombActive() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setBombActive(false);
        assertFalse(sweeperBoardManager.isBombActive());
    }


    @Test
    public void setBombToExploded() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setBombToExploded(0,0);
        assertTrue(sweeperBoardManager.getBoard().getTile(0,0).isBombExploded());
    }

    @Test
    public void setTileToFlagged() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setTileToFlagged(0,0, true);
        sweeperBoardManager.setTileToFlagged(0,1, false);
        assertTrue(sweeperBoardManager.getBoard().getTile(0,0).isFlagged());
        assertFalse(sweeperBoardManager.getBoard().getTile(0,1).isFlagged());
    }

    @Test
    public void setsBombsAround() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setsBombsAround(1,1, 1);
        assertEquals(1, sweeperBoardManager.getBoard().getTile(1,1).getBombsAround());
    }

    @Test
    public void getTileInPosition() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        SweeperTile testTile = sweeperBoardManager.getTileInPosition(0);
        assertTrue(testTile == sweeperTileList.get(0));
    }

    @Test
    public void getActiveBombRow() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setActiveBombRow((0));
        assertEquals(0, sweeperBoardManager.getActiveBombRow());
    }

    @Test
    public void getActiveBombCol() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setActiveBombCol((0));
        assertEquals(0, sweeperBoardManager.getActiveBombCol());
    }

    @Test
    public void setActiveBombRow() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setActiveBombRow((0));
        assertEquals(0, sweeperBoardManager.getActiveBombRow());
    }

    @Test
    public void setActiveBombCol() {
        createValidTileList();
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        sweeperBoardManager.setActiveBombCol((0));
        assertEquals(0, sweeperBoardManager.getActiveBombCol());
    }
}