package fall2018.csc2017.GameCentre.SlidingTiles;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.Stack;
import fall2018.csc2017.GameCentre.Undoable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class SlidingBoardManager extends BoardManager<SlidingTilesBoard> implements Serializable, Undoable {

    /**
     * The stack which remembers the moves on the board.
     */
    private Stack stack;
    /**
     * Manage a board that has been pre-populated.
     *
     */
//    SlidingBoardManager(SlidingTilesBoard board) {
//        this.board = board;
//        this.stack = new Stack(3);
//    }
    public Stack getStack(){
        return stack;
    }

    /**
     * Manage a new shuffled board.
     */
    public SlidingBoardManager(int undoMax, SlidingTilesBoard board ) {
        super(board);
        this.stack = new Stack(undoMax);
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
