package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;
    /**
     * The stack which remembers the moves on the board.
     */
    private UndoStack stack;

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
        this.stack = new UndoStack(3);
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled board.
     */

    BoardManager(int dimension, int undoMax) {
        this.stack = new UndoStack(undoMax);
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = dimension * dimension;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1));
        }
        Collections.shuffle(tiles);
        this.board = new Board(dimension, tiles);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        Iterator<Tile> iterator = board.iterator();
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
    boolean isValidTap(int position) {
        int row = getRow(position);
        int col = getCol(position);
        // check if blank tile is tile above, below, to the left, or to the right in this order.
        return isBlankTile(row, 0, row - 1, col)
                || isBlankTile(row, board.getNumRows() - 1, row + 1, col)
                || isBlankTile(col, 0, row, col - 1)
                || isBlankTile(col, board.getNumCols() - 1, row, col + 1);
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
        int blankId = board.numTiles();
        Tile tile = rowOrCol == boundaryCase ? null : board.getTile(rowToCheck, colToCheck);
        return tile != null && tile.getId() == blankId;
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {
        int row = getRow(position);
        int col = getCol(position);
        // If any of the neighbouring tiles is the blank tile, swap by calling Board's swap method.

        // check if tile above is blank.
        if (isBlankTile(row, 0, row - 1, col)) {
            board.swapTiles(row, col, row - 1, col);
            int[] lst = {row, col, row - 1, col};
            this.stack.push(lst);
        }
        // check tile below is blank.
        else if (isBlankTile(row, board.getNumRows() - 1, row + 1, col)) {
            board.swapTiles(row, col, row + 1, col);
            int[] lst = {row, col, row + 1, col};
            this.stack.push(lst);
        }
        // check if tile on right is blank.
        else if (isBlankTile(col, 0, row, col - 1)) {
            board.swapTiles(row, col, row, col - 1);
            int[] lst = {row, col, row, col - 1};
            this.stack.push(lst);
        }
        // check if tile on the left is blank.
        else if (isBlankTile(col, board.getNumCols() - 1, row, col + 1)) {
            board.swapTiles(row, col, row, col + 1);
            int[] lst = {row, col, row, col + 1};
            this.stack.push(lst);
        }
    }

    /**
     * Undo the most recent move. If undo is pressed again, undo the next most recent move.
     * Continue as long as moves are recorded.
     * @return whether a move has been undone
     */
    boolean undo() {
        if (this.stack.getSize() == 0) {
            return false;
        } else {
            int[] lst = (int[]) this.stack.pop();
            board.swapTiles(lst[0], lst[1], lst[2], lst[3]);
            return true;
        }
    }


    /**
     * Returns score based on dimensions of puzzle
     *
     * @param moves number of moves
     * @return total score
     */
    int calculateScore(int moves) {
        int dimensions = board.getNumRows();
        return dimensions * 500 - (moves * 5);
    }


    /**
     * @param position of touch on screen
     * @return the row number(Starts at 0).
     */
    private int getRow(int position) {
        return position / board.getNumRows();
    }

    /**
     * @param position of touch on screen
     * @return the column number(Starts at 0).
     */
    private int getCol(int position) {
        return position % board.getNumCols();
    }


}