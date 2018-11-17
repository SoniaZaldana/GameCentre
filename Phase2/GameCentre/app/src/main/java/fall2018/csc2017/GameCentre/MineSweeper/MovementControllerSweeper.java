package fall2018.csc2017.GameCentre.MineSweeper;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fall2018.csc2017.GameCentre.ClicksOnBoard;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerComplexPress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Tile;

public class MovementControllerSweeper extends MovementControllerComplexPress<SweeperBoardManager> {
    private int flagCounter;

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
                    //TODO set background to bomb
                    //TODO END GAME
                } else {// display how many bombs are around
                    checkAround(row, col, t);
                }
            }
        }
        // If long tap, change the flag.
        if (click == ClicksOnBoard.LONG) {
            if (t.isFlagged()) {
                t.flag(false);
                flagCounter -= 1;
                //TODO Disable the flag background
                //TODO Display new flag counter on screen

            } else {
                t.flag(true);
                flagCounter += 1;
                //TODO Apply the flag background
                //TODO Display new flag counter on screen
            }
        }
    }

    /**
     * currTile does not have a bomb. Method checks all tiles around and
     * displays on the tile how many bombs there are around.
     * If 0 bombs around, runs checkAround() on every tile around it.
     * around.
     *
     * @param row
     * @param col
     * @param currTile
     */
    public void checkAround(int row, int col, SweeperTile currTile) {
        // Check if the current tile might have already been set.
        if (currTile.getBombsAround() == -1) {
            //Doesn't have bomb. Display how many bombs are around.
            SweeperTile t;
            ArrayList<Integer> rowColPair;
            boolean rInBounds;
            boolean cInBounds;
            int bombCounter = 0;
            int[] rowValues = {row - 1, row, row + 1};
            int[] colValues = {col - 1, col, col + 1};
            Map<SweeperTile, List<Integer>> tilesToCheck = new HashMap<>();
            // creates a map of all valid tiles around, with the tile as the key
            // and the row-col pair as the value.
            for (int r : rowValues) {
                for (int c : colValues) {
                    // don't consider the original tile.
                    if (r != row && c != col) {
                        // check if row and col are within the correct bounds.
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
            // Check every valid tile for a bomb and add it to the counter if it exists.
            for (SweeperTile tile : tilesToCheck.keySet()) {
                if (tile.hasBomb()) {
                    bombCounter += 1;
                }
            }
            if (bombCounter == 0) {
                currTile.setBombsAround(0);
                //TODO display visually
                //Check all the tiles around this one, per method requirements.
                for (SweeperTile tile : tilesToCheck.keySet()) {
                    // if this tile has not been checked
                    if (tile.getBombsAround() == -1) {
                        checkAround(tilesToCheck.get(tile).get(0), tilesToCheck.get(tile).get(1), tile);
                    }
                }
            } else {
                currTile.setBombsAround(bombCounter);
                //TODO display visually
            }
        }

    }
}
