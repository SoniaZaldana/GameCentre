package fall2018.csc2017.GameCentre.Simon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimonMovementControllerTest {
    //TODO: finish this class

    private SimonMovementController simonMovementController;
    private SimonBoardManager simonBoardManager;


    @Before
    public void setUp(){
        simonBoardManager = new SimonBoardManager(2,3);
        simonMovementController = new SimonMovementController(simonBoardManager);

    }

    @Test
    public void processMove() {
    }

    @Test
    public void getCurrPosition() {
        simonBoardManager = new SimonBoardManager(2,3);
        simonMovementController = new SimonMovementController(simonBoardManager);
        assertEquals(-1, simonMovementController.getCurrPosition());
    }

    @Test
    public void isCorrectMove() {
    }

    @Test
    public void getRound() {
        simonBoardManager = new SimonBoardManager(3,1);
        simonMovementController = new SimonMovementController(simonBoardManager);
        assertEquals(1, simonMovementController.getRound());
    }

    @Test
    public void isRoundFinished() {
    }
}