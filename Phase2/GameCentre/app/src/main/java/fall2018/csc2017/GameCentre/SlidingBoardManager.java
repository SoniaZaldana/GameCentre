package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class SlidingBoardManager extends BoardManager implements Serializable, Undoable {

    /**
     * The board being managed.
     */
    private SlidingTilesBoard board;
    /**
     * The stack which remembers the moves on the board.
     */
    private UndoStack stack;
    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    SlidingBoardManager(SlidingTilesBoard board) {
        this.board = board;
        this.stack = new UndoStack(3);
    }
    public UndoStack getStack(){
        return stack;
    }

    /**
     * Manage a new shuffled board.
     */

    SlidingBoardManager(int dimension, int undoMax) {
        super(dimension);
        this.stack = new UndoStack(undoMax);
        //Collections.shuffle(getTileList());
        this.board = new SlidingTilesBoard(dimension, getTileList());
    }

    /**
     * Return the current board.
     */
    @Override
    SlidingTilesBoard getBoard() {
        return this.board;
    }


    /**
     * Undo the most recent move. If undo is pressed again, undo the next most recent move.
     * Continue as long as moves are recorded.
     * @return whether a move has been undone
     */
    public boolean undo() {
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
    @Override
    int calculateScore(int moves) {
        int dimensions = board.getDimension();
        return dimensions * 500 - (moves * 5);
    }


    /**
     * @param position of touch on screen
     * @return the row number(Starts at 0).
     */
    // This one is class specific
    public  int getRow(int position) {
        return position / board.getDimension();
    }

    /**
     * @param position of touch on screen
     * @return the column number(Starts at 0).
     */
    // This one is also class specific
    public int getCol(int position) {
        return position % board.getDimension();
    }

}
