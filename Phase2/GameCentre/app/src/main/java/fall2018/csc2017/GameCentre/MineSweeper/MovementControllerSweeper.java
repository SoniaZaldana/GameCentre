package fall2018.csc2017.GameCentre.MineSweeper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.ClicksOnBoard;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerComplexPress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Score.ScoreScreenActivity;
import fall2018.csc2017.GameCentre.Tile;

public class MovementControllerSweeper extends MovementControllerComplexPress<SweeperBoardManager>{
    private int flagCounter;

    private Timer timer = new Timer();

    public MovementControllerSweeper(SweeperBoardManager boardManager) {
        setBoardManager(boardManager);

    }

    @Override
    public void processMove(Context context, int position, Enum<ClicksOnBoard> click) {
        int row = getBoardManager().getRow(position);
        int col = getBoardManager().getCol(position);
        SweeperTile t = getBoardManager().getBoard().getTile(row, col);
        // If single  tap, then reveal what's under
        if (click == ClicksOnBoard.SHORT) {
            // should be able to press only if the tile is not flagged.
            if (!t.isFlagged()) {
                if (t.hasBomb()) {// if there's a bomb, finish game
                    getBoardManager().setBombToExploded(row, col);
                    //TODO END GAME
                } else {// display how many bombs are around
                    checkAround(row, col, t);
                }
            }
        }
        // If long tap, change the flag.
        if (click == ClicksOnBoard.LONG) {
            if (t.isFlagged()) {
                getBoardManager().setTileToNotFlaged(row, col);
                flagCounter -= 1;
                //TODO Disable the flag background
                //TODO Display new flag counter on screen
                    if (t.hasBomb()) {// if there's a bomb check what bomb
                        if (t.getBombType().equals("small")) { // Takes damage if it's a small bomb
                            //TODO set background to small bomb
                            //TODO change HP display
                            getBoardManager().getBoard().takeDamage();
                            if (getBoardManager().getBoard().getHitPoints() == 0) {
                                sweeperTilesBoardManager.setBombToExploded(row, col);
                                processLoss(context);
                            }
                        }
                        if (t.getBombType().equals("big")) {// End the game if it's a big bomb
                            //TODO set background to big bomb
                            sweeperTilesBoardManager.setBombToExploded(row, col);
                            processLoss(context);
                        }
                        if (t.getBombType().equals("timed")) {
                            // Start a timer, game ends after 10 seconds.
                            //TODO set background to CLOCK
                            if (!getBoardManager().isBombActive()) {
                                BombTask task = new BombTask(this, context, row, col);
                                timer.schedule(task, 1000, 1000);
                                getBoardManager().setBombActive(true);
                            }
                        }

                    } else {// display how many bombs are around
                        checkAround(row, col, t);
                        if (isGameFinished()) {
                            timer.cancel();
                            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                            int score = getBoardManager().calculateScore(0);
                            moveOnToScoreActivity(context, "Minsweeper.txt", ScoreScreenActivity.class, score);
                        }
                    }
                }
            }
            // If long tap, change the flag.
            if (click == ClicksOnBoard.LONG) {
                if (t.isFlagged()) {
                    sweeperTilesBoardManager.setTileToNotFlaged(row, col);
                    flagCounter -= 1;
                    //TODO Disable the flag background
                    //TODO Display new flag counter on screen

            } else {
                getBoardManager().setTileToFlaged(row, col);
                flagCounter += 1;
                //TODO Apply the flag background
                //TODO Display new flag counter on screen
            }
        }
    }
                } else {
                    sweeperTilesBoardManager.setTileToFlaged(row, col);
                    flagCounter += 1;
                    //TODO Apply the flag background
                    //TODO Display new flag counter on screen
                }
            }
        }

    /**
     * Helper function for when the player loses.
     */
    public void processLoss(Context context){
        Toast.makeText(context, "YOU LOSE!", Toast.LENGTH_SHORT).show();
        moveOnToScoreActivity(context, "Minesweeper.txt", ScoreScreenActivity.class, 0);
    }

    /**
     * currTile does not have a bomb. Method checks all tiles around and
     * displays on the tile how many bombs there are around.
     * If 0 bombs around, runs checkAround() on every tile around it.
     * around.
     *
     * @param row
     *
     * @param col
     * @param currTile
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
                getBoardManager().setsBombdAround(row, col, 0);
                //Check all the tiles around this one, per method requirements.
                for (SweeperTile tile : tilesToCheck.keySet()) {
                    // if this tile has not been checked
                    if (tile.getBombsAround() == -1) {
                        checkAround(tilesToCheck.get(tile).get(0), tilesToCheck.get(tile).get(1), tile);
                    }
                }
            } else {
                getBoardManager().setsBombdAround(row, col, bombCounter);
                //TODO display visually
            }
        }
    }

    /**
     * Get the map of all valid tiles around given tile, with the tile as the key
     * and its row and col as the value.
     * @param row
     * @param col
     * @param rowValues
     * @param colValues
     * @return
     */
    @NonNull
    private Map<SweeperTile, List<Integer>> getSweeperTileListMap(int row, int col, int[] rowValues, int[] colValues) {
        boolean rInBounds; boolean cInBounds; SweeperTile t; ArrayList<Integer> rowColPair;
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
                        //TODO verify that it sets map correctly.
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
     * @return boolean True if game is finished
     */
    public boolean isGameFinished(){
        boolean gameFinished = true;
        for (SweeperTile tile:getBoardManager().getBoard()) {
            if (!tile.hasBomb() && tile.getBombsAround() == -1){
                gameFinished = false;
            }
        }
        return gameFinished;
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
         *A timer that starts the bomb
         */
         public BombTask(MovementControllerSweeper movementControllerSweeper, Context context, int row, int col){
            super();
            this.movementControllerSweeper = movementControllerSweeper;
            this.context = context;
            this.row = row;
            this.col = col;
        }

        /**
         * Makes the bomb explode
         */
        public void run(){
            if (movementControllerSweeper.getBoardManager().getBombTime() == 0) {
                movementControllerSweeper.getBoardManager().setBombToExploded(row, col);
                this.movementControllerSweeper.processLoss(context);
            } else {
                movementControllerSweeper.getBoardManager().lowerBombTime();
            }
        }

    }}
