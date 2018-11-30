package fall2018.csc2017.GameCentre.SlidingTiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.Tile;
import fall2018.csc2017.GameCentre.UndoStack;
import fall2018.csc2017.GameCentre.Undoable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class SlidingBoardManager extends BoardManager<SlidingTilesBoard> implements Undoable {

    /**
     * The undoStack which remembers the moves on the board.
     */
    private UndoStack undoStack;
    /**
     * Manage a board that has been pre-populated.
     *
     */

    public SlidingBoardManager(int undoMax, SlidingTilesBoard board ) {
        super(board);
        this.undoStack = new UndoStack(undoMax);
    }



    /**
     * Manage a new shuffled board.
     */
    public SlidingBoardManager(int dimension, int undoMax){
        // Create the tiles
        List<Tile> tilesList = new ArrayList<>();
        final int numTiles = dimension * dimension;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tilesList.add(new Tile(tileNum + 1));
        }
        SlidingTilesBoard slidingTilesBoard = new SlidingTilesBoard(dimension, tilesList);
        setBoard(slidingTilesBoard);
        rearrangeBoard();
        this.undoStack = new UndoStack(undoMax);

    }

    /**
     * Returns this board's undostack
     * @return
     */
    UndoStack getUndoStack(){
        return this.undoStack;
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

    /**
     *
     * @param position of the tile, for which we're searching for
     * tiles around it
     * @return a list of valid positions in the grid that are around it
     * (from up, down, left, and right)
     */
    private List<Integer> validPositionsAroundBlank(int position){
        int row = getRow(position);
        int col = getCol(position);
        int dimension = getBoard().getDimension();
        // Create a list of valid row and valid columns around blank
        List<Integer> validPositions = new ArrayList<>();
        if(row+1<dimension){
            validPositions.add(getPosition(row+1,col ));
        }
        if(row-1<dimension && row-1>=0){
            validPositions.add(getPosition(row-1,col ));
        }
        if(col+1<dimension){
            validPositions.add(getPosition(row,col+1 ));
        }
        if(col-1<dimension && col-1>=0){
            validPositions.add(getPosition(row,col-1 ));
        }
        return validPositions;


    }

    /**
     * Rearranges the board to make sure it is shuffled, but solvable
     */
    public void rearrangeBoard(){
        //initial position of blank tile is at dimension -1
        int blankPosition = getBoard().getNumTiles() -1;
        // paste in an invalid position as the old position
        helperArrangeBoard(blankPosition, getBoard().getNumTiles(), 1000);
    }
    /**
     *
     * @param position of the blank tile
     * @param oldPosition of the blank tile
     * @param numRepeats number of times to repeat the function
     */
    private void helperArrangeBoard(int position, int oldPosition, int numRepeats){
        if(numRepeats>0){
            // get valid positions around tile
            List<Integer> validPositions = validPositionsAroundBlank(position);
            // remove the old position. This is because we do not want to swap back to old state
            validPositions.remove(new Integer(oldPosition));
            // get a random valid tile to switch with
            Random rand = new Random();
            int indexToGet = rand.nextInt(validPositions.size());
            int newBlankPosition = validPositions.get(indexToGet);
            // swap the tiles
            getBoard().swapTiles(getRow(position),getCol(position) ,getRow(newBlankPosition), getCol(newBlankPosition));
            // blank tile is now at new position

            helperArrangeBoard(newBlankPosition, position, numRepeats-1);
        }

    }


}
