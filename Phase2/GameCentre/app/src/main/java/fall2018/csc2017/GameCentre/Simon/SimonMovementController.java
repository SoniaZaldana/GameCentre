package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.GameCentre.MoveTracker;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;
import fall2018.csc2017.GameCentre.Score.ScoreScreenActivity;

public class SimonMovementController extends MovementControllerSimplePress {

    private MoveTracker moves;

    SimonMovementController(SimonBoardManager boardManager){
        moves = new MoveTracker(boardManager.getScore());
        setBoardManager(boardManager);
    }

    @Override
    public void processMove(Context context, int position) {
        SimonTile tile = getTileInPosition(position);
        // pop stack in board manager
        // compare to the tile popped
        // todo use compareTO with another tile
//        if (tile == other_tile) {
//
//        } else {
//            int score = getBoardManager().calculateScore(moves.getMoves());
//            //TODO evaluate this score screen activity class to see this will still work with simon
//            moveOnToScoreActivity(context, "Simon.txt", ScoreScreenActivity.class, score);
//        }

    }

    void touchMove(){
        //
    }






    /**
     * Returns the SimonTile object in that position
     * @param position in board
     * @return SimonTile
     */
    private SimonTile getTileInPosition(int position) {
        int row = getBoardManager().getRow(position);
        int col = getBoardManager().getCol(position);
        SimonTile tile = (SimonTile) getBoardManager().getBoard().getTile(row,col);
        return tile;
    }
}
