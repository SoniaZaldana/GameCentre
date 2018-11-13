package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * The sliding tiles board.
 */
public class SlidingTilesBoard extends Board implements Iterable<Tile> {


    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * path of the board Picture.
     */
    private String picturePath;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == numRows * numCols
     *
     * @param tiles the tiles for the board
     */
    SlidingTilesBoard(int dimension, List<Tile> tiles) {
        super(dimension);
        Iterator<Tile> iter = tiles.iterator();
        this.tiles = new Tile[getDimension()][getDimension()];
        for (int row = 0; row != this.getDimension(); row++) {
            for (int col = 0; col != this.getDimension(); col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * @return the id of the blank tile
     */
    int getBlankId() {
        return numTiles();
    }
    /**
     * set picturePath
     * @param picturePath path of the board Picture
     */
    void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * get the picturePath
     * @return picturePath path of the board Picture
     */
    String getPicturePath() {
        return picturePath;
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tempValue1 = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = tempValue1;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "SlidingTilesBoard{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * @return an iterator of class TileIterator.
     */
    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    /**
     * Represents the iterator for class SlidingTilesBoard.
     */
    private class TileIterator implements Iterator<Tile> {
        /**
         * current row index
         */
        private int rowIndex = 0;
        /**
         * current column Index
         */
        private int columnIndex = 0;


        @Override
        public boolean hasNext() {
            return rowIndex < getDimension() && columnIndex < getDimension();
        }

        @Override
        public Tile next() {
            // get the data for the current tile Tile, then move the index to the next Tile.
            Tile nextTile = tiles[rowIndex][columnIndex];
            columnIndex += 1;
            // If there is no more columns, move onto first column in next row.
            if (!(columnIndex < getDimension())) {
                // If it's on the last row, rowIndex will move to a nonexistent one.
                rowIndex += 1;
                columnIndex = 0;
            }
            return nextTile;
        }

    }


}
