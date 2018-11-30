package fall2018.csc2017.GameCentre.MineSweeper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.ClicksOnBoard;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerComplexPress;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;
import fall2018.csc2017.GameCentre.Score.ScoreScreenActivity;

/**
 * Controls revealing tiles on the board
 */
public class MovementControllerSweeper extends MovementControllerComplexPress<SweeperBoardManager> {
    /**
     * Timer used for decreasing the time on a time bomb.
     */
    private Timer timer = new Timer();
    private int numberOfMove = 0;

    MovementControllerSweeper(SweeperBoardManager boardManager) {
        setBoardManager(boardManager);
    }
    MovementControllerSweeper(SweeperBoardManager boardManager, Timer timer) {
        setBoardManager(boardManager);
        this.timer = timer;
    }

    /**
     * Processing a click on the board
     * @param context from the app.
     * @param position of the click on the board
     * @param click
     */
    @Override
    public void processMove(Context context, int position, Enum<ClicksOnBoard> click) {
        int row = getBoardManager().getRow(position);
        int col = getBoardManager().getCol(position);
        boolean skipSave = false;
        SweeperTile t = getBoardManager().getBoard().getTile(row, col);
        // If single tap and the tile is not flagged, you want to reveal what is under
        if ((click == ClicksOnBoard.SHORT) && !t.isFlagged()) {
            // If the tile has a bomb and it has not yet exploded
            if (t.hasBomb() && !t.isBombExploded() && numberOfMove == 0) {
                getBoardManager().getBoard().swapWithSafeTile(row, col);
                checkAround(row, col, t);
                numberOfMove++;
            } else if (t.hasBomb() && !t.isBombExploded()) {
                processBombType(context, row, col, t);
                numberOfMove++;
            }
            //If it doesn't have a bomb display how many bombs are around
            else if (!t.hasBomb()) {
                checkAround(row, col, t);
                numberOfMove++;
                //Check if game is finished
                if (isGameFinished()) {
                    timer.cancel();
                    int mines = 0;
                    for (SweeperTile tile : getBoardManager().getBoard()) {
                        if (tile.hasBomb()) {
                            mines++;
                        }
                    }
                    Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();

                    //Calculate score and send it to score screen
                    int score = getBoardManager().calculateScore(mines);
                    moveOnToScoreActivity(context, "Minesweeper.txt", ScoreScreenActivity.class, score);
                }
            }
        } else if (click == ClicksOnBoard.LONG) {
            processLongClick(row, col, t);
        }
        if (!skipSave) {
            SaveAndLoadBoardManager.saveToFile(context, SweeperStartingActivity.SWEEPER_SAVE_FILENAME, getBoardManager());
        }
        // Save the game (Autosaving)
        SaveAndLoadBoardManager.saveToFile(context, SweeperStartingActivity.SWEEPER_SAVE_FILENAME, getBoardManager());
    }

    /**
     * Performs the necessary actions when doing a long click, such as flagging.
     * @param row - row the clicked tile is in
     * @param col - col the clicked tile is in
     * @param t - the sweeper tile
     */
    private void processLongClick(int row, int col, SweeperTile t) {
        if (t.isFlagged()) {
            getBoardManager().setTileToFlagged(row, col, false);

        } else if (t.getBombsAround() == -1) {
            getBoardManager().setTileToFlagged(row, col, true);
        }
    }

    /**
     * Performs the necessary action when the tile contains an undetonated bomb
     * @param context - the context of the activity
     * @param row - the row the tile is in
     * @param col - the column the tile is in
     * @param t - the sweeper tile
     */
    private void processBombType(Context context, int row, int col, SweeperTile t) {
        BombTypes bomb = t.getBombType();
        switch (bomb) {
            case SMALL:
                getBoardManager().getBoard().takeDamage();
                getBoardManager().setBombToExploded(row, col);
                if (getBoardManager().getBoard().getHitPoints() == 0) {
                    processLoss(context);
                }
                break;
            case BIG:
                getBoardManager().setBombToExploded(row, col);
                processLoss(context);
                break;
            case TIMED:
                getBoardManager().setBombToExploded(row, col);
                if (!getBoardManager().isBombActive()) {
                    startTimer(row, col, context);
                }
                break;
        }
    }


    /**
     * Start the countdown timer on the bomb
     * @param row the row of the bomb
     * @param col the column the bomb is in
     * @param context from the app
     */
    void startTimer(int row, int col, Context context) {
        BombTask task = new BombTask(this, context, row, col);
        timer.schedule(task, 1000, 1000);
        getBoardManager().setBombActive(true);
        getBoardManager().setActiveBombCol(col);
        getBoardManager().setActiveBombRow(row);
    }


    /**
     * Helper function for when the player loses. Moves on to score activity
     */
    void processLoss(Context context) {
        if (!getBoardManager().isBombActive()) {
            Toast.makeText(context, "YOU LOSE!", Toast.LENGTH_SHORT).show();
        }
        context.deleteFile(SweeperStartingActivity.SWEEPER_SAVE_FILENAME);
        moveOnToScoreActivity(context, "Minesweeper.txt", ScoreScreenActivity.class, 0);
    }

    /**
     * currTile does not have a bomb. Method checks all tiles around and
     * displays on the tile how many bombs there are around.
     * If 0 bombs around, runs checkAround() on every tile around it.
     * around.
     * @param row Row of the initial tile
     * @param col Column of the initial tile
     * @param currTile The tile itself
     */
    public void checkAround(int row, int col, SweeperTile currTile) {
        // Check if the current tile might have already been set.
        if (currTile.getBombsAround() == -1) {
            //Doesn't have bomb. Display how many bombs are around.
            int bombCounter = 0;
            int[] rowValues = {row - 1, row, row + 1};
            int[] colValues = {col - 1, col, col + 1};
            // get the map of all valid tiles around, with the tile as the key and their row and col
            // as the value.
            Map<SweeperTile, List<Integer>> tilesToCheck = getSweeperTileListMap(row, col, rowValues, colValues);
            // Check every valid tile for a bomb and add it to the counter if it exists.
            for (SweeperTile tile : tilesToCheck.keySet()) {
                if (tile.hasBomb()) {
                    bombCounter += 1;
                }
            }
            if (bombCounter == 0) {
                getBoardManager().setsBombsAround(row, col, 0);
                //Check all the tiles around this one, per method requirements.
                for (SweeperTile tile : tilesToCheck.keySet()) {
                    // if this tile has not been checked
                    if (tile.getBombsAround() == -1) {
                        checkAround(tilesToCheck.get(tile).get(0), tilesToCheck.get(tile).get(1), tile);
                    }
                }
            } else {
                getBoardManager().setsBombsAround(row, col, bombCounter);
            }
        }
    }

    /**
     * Get the map of all valid tiles around given tile, with the tile as the key
     * and its row and col as the value.
     *
     * @param row - the row the tile is in
     * @param col - the column the tile is in
     * @param rowValues - the values in the rows
     * @param colValues - the values in the columns
     * @return
     */
    @NonNull
    private Map<SweeperTile, List<Integer>> getSweeperTileListMap(int row, int col, int[] rowValues, int[] colValues) {
        boolean rInBounds;
        boolean cInBounds;
        SweeperTile t;
        ArrayList<Integer> rowColPair;
        Map<SweeperTile, List<Integer>> tilesToCheck = new HashMap<>();
        // creates a map of all valid tiles around, with the tile as the key
        // and the row-col pair as the value.
        for (int r : rowValues) {
            for (int c : colValues) {
                // don't consider the original tile.
                if (r == row && c == col) {
                    // check if row and col are within the correct bounds.
                } else {
                    rInBounds = r < getBoardManager().getBoard().getDimension() &&
                            r >= 0;
                    cInBounds = c < getBoardManager().getBoard().getDimension() &&
                            c >= 0;
                    if (rInBounds && cInBounds) {
                        t = getBoardManager().getBoard().getTile(r, c);
                        rowColPair = new ArrayList<>();
                        rowColPair.add(r);
                        rowColPair.add(c);
                        tilesToCheck.put(t, rowColPair);
                    }
                }
            }
        }
        return tilesToCheck;
    }

    /**
     * Checks if the game is finished (and not a loss)
     *
     * @return boolean True if game is finished
     */
    boolean isGameFinished() {
        boolean gameFinished = true;
        for (SweeperTile tile : getBoardManager().getBoard()) {
            if (!tile.hasBomb() && tile.getBombsAround() == -1) {
                gameFinished = false;
            }
        }
        return gameFinished;
    }

    /**
     * Returns the timer
     * @return timer
     */
    public Timer getTimer(){
        return timer;
    }

    /**
     * A timer task that starts the bomb.
     */
    private class BombTask extends TimerTask {
        /**
         * The Movement Controller this task acts on
         */
        private MovementControllerSweeper movementControllerSweeper;

        /**
         * The context this timer is in
         */
        private Context context;
        private int row;
        private int col;

        /**
         * A timer that starts the bomb
         */
        BombTask(MovementControllerSweeper movementControllerSweeper, Context context, int row, int col) {
            super();
            this.movementControllerSweeper = movementControllerSweeper;
            this.context = context;
            this.row = row;
            this.col = col;
        }

        /**
         * A timer that starts the bomb with a given movement controller and context
         * @param movementControllerSweeper
         * @param context
         */
        public BombTask(MovementControllerSweeper movementControllerSweeper, Context context) {
            super();
            this.movementControllerSweeper = movementControllerSweeper;
            this.context = context;
        }

        /**
         * Makes the bomb explode
         */
        public void run() {
            if (movementControllerSweeper.getBoardManager().getBoard().getBombTime() == 0) {
                movementControllerSweeper.getBoardManager().setBombToExploded(row, col);
                this.movementControllerSweeper.processLoss(context);
            } else {
                movementControllerSweeper.getBoardManager().getBoard().lowerBombTime();
            }
        }
    }
}
