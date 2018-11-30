package fall2018.csc2017.GameCentre.MineSweeper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Timer;

import fall2018.csc2017.GameCentre.MovementControllers.MovementController;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingBoardManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MovementControllerSweeperTest {
    private SweeperBoardManager sweeperBoardManager;
    private SweeperTilesBoard sweeperTilesBoard;
    private MovementControllerSweeper movementControllerSweeper;
    @Before
    public void setup() {
        SweeperTile t = new SweeperTile(false);
        SweeperTile t1 = new SweeperTile(false);
        SweeperTile t2 = new SweeperTile(false);
        SweeperTile t3 = new SweeperTile(true);
        SweeperTile t4 = new SweeperTile(false);
        SweeperTile t5 = new SweeperTile(false);
        SweeperTile t6 = new SweeperTile(true);
        SweeperTile t7 = new SweeperTile(false);
        SweeperTile t8 = new SweeperTile(false);
        ArrayList<SweeperTile> tiles = new ArrayList<>();
        tiles.add(t);
        tiles.add(t1);
        tiles.add(t2);
        tiles.add(t3);
        tiles.add(t4);
        tiles.add(t5);
        tiles.add(t6);
        tiles.add(t7);
        tiles.add(t8);
        sweeperTilesBoard = new SweeperTilesBoard(3, tiles);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        movementControllerSweeper = new MovementControllerSweeper(sweeperBoardManager);

    }
    @Test
    public void checkAroundTest() {
        movementControllerSweeper.checkAround(2, 2, sweeperTilesBoard.getTile(2,
                2));
        assertEquals(0, sweeperTilesBoard.getTile(2,
                2).getBombsAround());
    }
    @Test
    public void isGameFinishedTest() {
     assertFalse(movementControllerSweeper.isGameFinished());
    }
    @Test
    public void getTimerTest() {
        Timer timer = new Timer();
        movementControllerSweeper = new MovementControllerSweeper(sweeperBoardManager, timer);
        assertEquals(timer, movementControllerSweeper.getTimer());
    }
}
