package fall2018.csc2017.GameCentre.MineSweeper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

    public void createValidTileList() {
        sweeperTileList = new ArrayList<>();
        SweeperTile tile1 = new SweeperTile(true);
        SweeperTile tile2 = new SweeperTile(false);
        SweeperTile tile3 = new SweeperTile(false);
        SweeperTile tile4 = new SweeperTile(false);
        sweeperTileList.add(tile1);
        sweeperTileList.add(tile2);
        sweeperTileList.add(tile3);
        sweeperTileList.add(tile4);
    }

    //TODO not sure how to test this one.
//    @Test
//    public void startTimer() {
//        createValidTileList();
//        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(2, sweeperTileList);
//        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
//    }

    //TODO not sure how to test this one
    @Test
    public void stopTimer() {
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
        assertEquals(true, sweeperBoardManager.isBombActive());
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
        assertEquals(false, sweeperBoardManager.isBombActive());
    }
}