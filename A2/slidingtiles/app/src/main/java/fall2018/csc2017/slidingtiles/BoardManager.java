package fall2018.csc2017.slidingtiles;

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
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
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
    BoardManager() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }

        //Collections.shuffle(tiles);
        this.board = new Board(tiles);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        // TODO Check the logic. Make sure it's sound.
        // blankId has the highest Id, therefore it will be last.
        Iterator<Tile> iterator = board.iterator();
        boolean inOrder = true;
        int prevTileValue = 0;
        while(iterator.hasNext() && inOrder){
            Tile checkTile = iterator.next();
            int currTileValue = checkTile.getId();
            // If current value is not 1 + the last one, then it's not in order
            if(currTileValue != prevTileValue +1 ){
                inOrder = false;
            }
            if(currTileValue == board.numTiles()-1){

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
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */

    void touchMove(int position) {
        //TODO touchMove stealscode from isValidTap. Put in same method.
        int row = getRow(position);
        int col = getCol(position);
        int blankId = board.numTiles();
        // If any of the neighbouring
        // tiles is the blank tile, swap by calling Board's swap method.
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        if(above != null && above.getId() == blankId){
            board.swapTiles(row, col, row-1, col);
        }
        else if(below != null && below.getId() == blankId){
            board.swapTiles(row, col, row+1, col);
        }
        else if(left != null && left.getId() == blankId){
            board.swapTiles(row, col, row, col-1);
        }
        else if(right != null && right.getId() == blankId){
            board.swapTiles(row, col, row, col+1);
        }
        // TODO: figure out when to call board.swapTiles. S
    }
    int getRow(int position){
        return position / Board.NUM_ROWS;
    }
    int getCol(int position){
        return position % Board.NUM_COLS;
    }


}