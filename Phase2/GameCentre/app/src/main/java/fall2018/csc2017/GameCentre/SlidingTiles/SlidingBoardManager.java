package fall2018.csc2017.GameCentre.SlidingTiles;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.UndoStack;
import fall2018.csc2017.GameCentre.Undoable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class SlidingBoardManager extends BoardManager<SlidingTilesBoard> implements Serializable, Undoable {

    /**
     * The undoStack which remembers the moves on the board.
     */
    private UndoStack undoStack;
    /**
     * Manage a board that has been pre-populated.
     *
     */
//    SlidingBoardManager(SlidingTilesBoard board) {
//        this.board = board;
//        this.undoStack = new UndoStack(3);
//    }
    public UndoStack getUndoStack(){
        return undoStack;
    }

    /**
     * Manage a new shuffled board.
     */
    public SlidingBoardManager(int undoMax, SlidingTilesBoard board ) {
        super(board);
        this.undoStack = new UndoStack(undoMax);
    }

    /**
     * Undo the most recent move. If undo is pressed again, undo the next most recent move.
     * Continue as long as moves are recorded.
     * @return whether a move has been undone
     */
    public boolean undo() {
        if (this.undoStack.getSize() == 0) {
            return false;
        } else {
            int[] lst = (int[]) this.undoStack.pop();
            getBoard().swapTiles(lst[0], lst[1], lst[2], lst[3]);
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
    public int calculateScore(int moves) {
        int dimensions = getBoard().getDimension();
        return dimensions * 500 - (moves * 5);
    }




}
