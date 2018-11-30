package fall2018.csc2017.GameCentre.Simon;

import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;

import fall2018.csc2017.GameCentre.Tile;

import static org.junit.Assert.*;

public class SimonMovementControllerTest {

    private SimonMovementController simonMovementController;
    private SimonBoardManager simonBoardManager;


    @Test
    public void getCurrPosition() {
        simonBoardManager = new SimonBoardManager(2,3);
        simonMovementController = new SimonMovementController(simonBoardManager);
        assertEquals(-1, simonMovementController.getCurrPosition());
    }

    @Test
    public void isCorrectMove() {
        simonBoardManager = new SimonBoardManager(2,3);
        simonMovementController = new SimonMovementController(simonBoardManager);
        SimonTile simonTile = new SimonTile();
        simonMovementController.getBoardManager().getGameQueue().add(simonTile);
        assertEquals(true, simonMovementController.isCorrectMove(simonTile));

    }

    @Test
    public void getRound() {
        simonBoardManager = new SimonBoardManager(3,1);
        simonMovementController = new SimonMovementController(simonBoardManager);
        assertEquals(1, simonMovementController.getRound());
    }

}