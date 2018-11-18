package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;
import fall2018.csc2017.GameCentre.MoveTracker;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;
import fall2018.csc2017.GameCentre.Score.ScoreScreenActivity;
import fall2018.csc2017.GameCentre.Stack;

public class SimonMovementController extends MovementControllerSimplePress {

    private MoveTracker moves;

    SimonMovementController(SimonBoardManager boardManager){
        moves = new MoveTracker(boardManager.getScore());
        setBoardManager(boardManager);
    }

    @Override
    public void processMove(Context context, int position) {
        SimonTile tile = getTileInPosition(position);

        if (isCorrectMove(tile)){
            SimonBoardManager simonBoardManager = (SimonBoardManager) getBoardManager();
            if (isRoundFinished(simonBoardManager.getGameStack())) {
                // TODO add instructions to repopulate the stack with more elements than previous round
            }
        }
        else{
            //This means user lost.
            int score = getBoardManager().calculateScore(moves.getMoves());
            moveOnToScoreActivity(context, "Simon.txt", ScoreScreenActivity.class, score);
        }

    }



    //TODO implement what to do when a user touches a tile.
    // TODO potentially this method would pop the stack and compare the user picked tile and the popped one
    boolean isCorrectMove(SimonTile tile){
        return false;
    }

    //TODO implement a method to see if a round is finished i.e. if the stack is empty
    boolean isRoundFinished(Stack gameStack){
        return false;
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
