package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;

import java.util.ListIterator;
import java.util.Queue;
import fall2018.csc2017.GameCentre.MoveTracker;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;
import fall2018.csc2017.GameCentre.Score.ScoreScreenActivity;

public class SimonMovementController extends MovementControllerSimplePress<SimonBoardManager> {
    /**
     * Tracks the number of moves
     */
    private MoveTracker moves;
    /**
     * Tracks the round the user is currently in
     */
    private int round;

    private ListIterator<SimonTile> iterator;

    /**
     * Instantiates a SimonMovementController object
     * @param boardManager the board manager for this instance of the game
     */
    SimonMovementController(SimonBoardManager boardManager){
        this.moves = new MoveTracker(boardManager.getScore());
        this.round = 1;
        setBoardManager(boardManager);
        iterator = getBoardManager().getGameQueue().iterator();

    }

    @Override
    public void processMove(Context context, int position) {
        SimonTile tile = getTileInPosition(position);
        if (isCorrectMove(tile)){
            if (isRoundFinished(getBoardManager().getGameQueue())) {
                //restart the iterator
                iterator = getBoardManager().getGameQueue().iterator();
                // add a new tile to the gameQueue
                for (int i = 0; i != this.round * 2; i++) {
                    SimonTile randomTile = getBoardManager().randomizer();
                    getBoardManager().getGameQueue().add(randomTile);
                }
                this.round++;
            }
        }
        else{
            //This means user lost.
            int score = getBoardManager().calculateScore(moves.getMoves());
            moveOnToScoreActivity(context, "Simon.txt", ScoreScreenActivity.class, score);
        }

    }

    //I think I have implemented this correctly

    /**
     * Returns whether the tile the user clicked matches the tile in the queue
     * @param userTile - the tile the user has clicked
     * @return
     */
    boolean isCorrectMove(SimonTile userTile){
        if(iterator.hasNext()){
            SimonTile tileAtFront = iterator.next();
            return tileAtFront.getId() == userTile.getId();
        }
        else{
            return false;
        }

//        GameQueue<SimonTile> gameQueue = getBoardManager().getGameQueue();
//        SimonTile tileAtFront = gameQueue.remove();

//        return tileAtFront.compareTo(userTile) == 0;
    }

    //I think I have also successfully implemented. Have to test.

    /**
     * Determines whether a round is finished. By our desing, this means the queue has been emptied.
     * @param gameQueue - the game queue
     * @return boolean round is finished
     */
    boolean isRoundFinished(GameQueue gameQueue){
//        return gameQueue.isEmpty();
        return !iterator.hasNext();
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
