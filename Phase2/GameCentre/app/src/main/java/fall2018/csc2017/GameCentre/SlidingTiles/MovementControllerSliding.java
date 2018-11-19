package fall2018.csc2017.GameCentre.SlidingTiles;

import android.content.Context;
import android.widget.Toast;

import java.util.Iterator;

import fall2018.csc2017.GameCentre.MoveTracker;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;
import fall2018.csc2017.GameCentre.Score.ScoreScreenActivity;
import fall2018.csc2017.GameCentre.Tile;

public class MovementControllerSliding extends MovementControllerSimplePress<SlidingBoardManager> {

    /**
     * Moves variable keeps track of number of moves
     */
    private MoveTracker moves;
    //TODO force all the constructors to take a board.
    public MovementControllerSliding(SlidingBoardManager boardManager) {
        moves = new MoveTracker(boardManager.getScore());
        setBoardManager(boardManager);

    }
    @Override
    public void processMove(Context context, int position){
        if (isValidTap(position)) {
            touchMove(position);
            moves.addMoves(1);
            getBoardManager().setScore(moves.getMoves());
            if (isGameFinished()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                int score = getBoardManager().calculateScore(moves.getMoves());
                moveOnToScoreActivity(context, "SlidingTiles.txt", ScoreScreenActivity.class, score);
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    //TODO made touchMove public for testing. Should that be valid?
    public void touchMove(int position) {
        int row = getBoardManager().getRow(position);
        int col = getBoardManager().getCol(position);
        // If any of the neighbouring tiles is the blank tile, swap by calling SlidingTilesBoard's swap method.

        // check if tile above is blank.
        if (isBlankTile(row, 0, row - 1, col)) {
            getBoardManager().getBoard().swapTiles(row, col, row - 1, col);
            int[] lst = {row, col, row - 1, col};
            getBoardManager().getUndoStack().push(lst);
        }
        // check tile below is blank.
        else if (isBlankTile(row, getBoardManager().getBoard().getDimension() - 1, row + 1, col)) {
            getBoardManager().getBoard().swapTiles(row, col, row + 1, col);
            int[] lst = {row, col, row + 1, col};
            getBoardManager().getUndoStack().push(lst);
        }
        // check if tile on right is blank.
        else if (isBlankTile(col, 0, row, col - 1)) {
            getBoardManager().getBoard().swapTiles(row, col, row, col - 1);
            int[] lst = {row, col, row, col - 1};
            getBoardManager().getUndoStack().push(lst);
        }
        // check if tile on the left is blank.
        else if (isBlankTile(col, getBoardManager().getBoard().getDimension() - 1, row, col + 1)) {
            getBoardManager().getBoard().swapTiles(row, col, row, col + 1);
            int[] lst = {row, col, row, col + 1};
            getBoardManager().getUndoStack().push(lst);
        }
    }
    /**
     * @param rowOrCol     is either a row or a column which you want to compare to a boundary
     *                     case depending on if you're checking up/down/left/right of existing tile.
     * @param boundaryCase is the value of the edge case(occurs at row = 0, col = 0,
     * @param rowToCheck   is the row of the Tile to Check
     * @param colToCheck   is the column of the Tile To check
     * @return whether the Tile with row rowToCheck and column colToCheck
     * contains a blank Tile
     */
    private boolean isBlankTile(int rowOrCol, int boundaryCase, int rowToCheck, int colToCheck) {
        int blankId = getBoardManager().getBoard().getNumTiles();
        Tile tile = rowOrCol == boundaryCase ? null : getBoardManager().getBoard().getTile(rowToCheck, colToCheck);
        return tile != null && tile.getId() == blankId;
    }
    //TODO make it private right after. Only made it public for testing
    public boolean isGameFinished() {
        Iterator<Tile> iterator = getBoardManager().getBoard().iterator();
        boolean inOrder = true;
        int prevTileValue = 0;
        while (iterator.hasNext() && inOrder) {
            Tile checkTile = iterator.next();
            int currTileValue = checkTile.getId();
            if (currTileValue != prevTileValue + 1) {
                inOrder = false;
            }
            prevTileValue = currTileValue;
        }

        return inOrder;
    }
    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    //TODO made it public just for testing. See if it should be public.
    public boolean isValidTap(int position) {
        int row = getBoardManager().getRow(position);
        int col = getBoardManager().getCol(position);
        // check if blank tile is tile above, below, to the left, or to the right in this order.
        return isBlankTile(row, 0, row - 1, col)
                || isBlankTile(row, getBoardManager().getBoard().getDimension() - 1, row + 1, col)
                || isBlankTile(col, 0, row, col - 1)
                || isBlankTile(col, getBoardManager().getBoard().getDimension() - 1, row, col + 1);
    }

}
