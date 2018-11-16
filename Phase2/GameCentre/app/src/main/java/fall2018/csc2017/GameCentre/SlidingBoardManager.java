package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class SlidingBoardManager extends BoardManager<SlidingTilesBoard> implements Serializable, Undoable {

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
    //TODO Notice how  the board is created from the tiles created in Super.
    SlidingBoardManager(int dimension, int undoMax) {
        super(dimension);
        this.stack = new UndoStack(undoMax);
        //Collections.shuffle(getTileList());
        setBoard(new SlidingTilesBoard(dimension, getTileList()));
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




}
