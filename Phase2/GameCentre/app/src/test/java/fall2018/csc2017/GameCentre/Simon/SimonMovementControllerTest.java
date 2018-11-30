package fall2018.csc2017.GameCentre.Simon;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimonMovementControllerTest {

    private MovementModelSimon simonMovementController;
    private SimonBoardManager simonBoardManager;


    @Test
    public void getCurrPosition() {
        simonBoardManager = new SimonBoardManager(2,3);
        simonMovementController = new MovementModelSimon(simonBoardManager);
        assertEquals(-1, simonMovementController.getCurrPosition());
    }

    @Test
    public void isCorrectMove() {
        simonBoardManager = new SimonBoardManager(2,3);
        simonMovementController = new MovementModelSimon(simonBoardManager);
        SimonTile simonTile = new SimonTile();
        simonMovementController.getBoardManager().getGameQueue().add(simonTile);
        assertEquals(true, simonMovementController.isCorrectMove(simonTile));

    }

    @Test
    public void getRound() {
        simonBoardManager = new SimonBoardManager(3,1);
        simonMovementController = new MovementModelSimon(simonBoardManager);
        assertEquals(1, simonMovementController.getRound());
    }

}