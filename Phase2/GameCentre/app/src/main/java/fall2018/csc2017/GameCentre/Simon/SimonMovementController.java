package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;
import android.os.Handler;

import java.util.ListIterator;
import java.util.Observable;
import java.util.Queue;
import fall2018.csc2017.GameCentre.MoveTracker;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;
import fall2018.csc2017.GameCentre.Score.ScoreScreenActivity;

public class SimonMovementController extends MovementControllerSimplePress<SimonBoardManager> {
    /**
     * Tracks the round the user is currently in
     */
    private int round;

    /**
     * iterator for list of simon tiles
     */
    private ListIterator<SimonTile> iterator;

    /**
     * represents the current position of a tile
     */
    private int currPosition = -1;

    /**
     * Instantiates a SimonMovementController object
     * @param boardManager the board manager for this instance of the game
     */
    SimonMovementController(SimonBoardManager boardManager){
        this.round = 1;
        setBoardManager(boardManager);
        GameQueue<SimonTile> queue = getBoardManager().getGameQueue();
        iterator = queue.iterator();

    }

    @Override
    public void processMove(Context context, int position) {
        currPosition = position;
        setChanged();
        notifyObservers();
        SimonTile tile = getBoardManager().getTileInPosition(position);
        if (isCorrectMove(tile)){
            if (isRoundFinished(getBoardManager().getGameQueue())) {
                //restart the iterator
                iterator = getBoardManager().getGameQueue().iterator();
                this.round++;
                // add a new tile to the gameQueue
                // give the user some time between his last input, and the
                // next round
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SimonTile randomTile = getBoardManager().randomizer();
                        getBoardManager().getGameQueue().add(randomTile);

                    }
                }, 1000-round*20);
            }
        }
        else{
            //This means the user lost
                int score = getBoardManager().calculateScore(this.round);
                moveOnToScoreActivity(context, "Simon.txt", ScoreScreenActivity.class, score);
        }

    }

    /**
     * Returns the current position attribute
     * @return integer
     */
    public int getCurrPosition() {
        return currPosition;
    }

    /**
     * Returns whether the tile the user clicked matches the tile in the queue
     * @param userTile - the tile the user has clicked
     * @return
     */
    public boolean isCorrectMove(SimonTile userTile){
        if(iterator.hasNext()){
            SimonTile tileAtFront = iterator.next();
            return tileAtFront.getId() == userTile.getId();
        }
        else{
            return false;
        }
    }

    /**
     *
     * @return the round the user is in
     */
    public int getRound() {
        return round;
    }

    /**
     * Determines whether a round is finished. By our desing, this means the queue has been emptied.
     * @param gameQueue - the game queue
     * @return boolean round is finished
     */
    public boolean isRoundFinished(GameQueue gameQueue){
        return !iterator.hasNext();
    }


    /**
     * Returns the SimonTile object in that position
     * @param position in board
     * @return SimonTile
     */

}
