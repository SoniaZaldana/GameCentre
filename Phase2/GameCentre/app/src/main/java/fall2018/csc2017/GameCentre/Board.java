package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

class Board extends Observable implements Serializable, Iterable<Tile> {
    /**
     * The number of rows.
     */
    private int dimension;
    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * Instantiates a board object with a dimension and list of tiles
     * @param dimension - nxn dimension
     * @param tiles - list of tiles
     */
    Board(int dimension, List<Tile> tiles) {
        this.dimension = dimension;
        Iterator<Tile> iter = tiles.iterator();
        this.tiles = new Tile[getDimension()][getDimension()];
        for (int row = 0; row != this.getDimension(); row++) {
            for (int col = 0; col != this.getDimension(); col++) {
                this.tiles[row][col] = iter.next();
            }
        }

    }

    /**
     * Returns a list containing all tiles in the board
     * @return list of tiles
     */
    Tile[][] getAllTiles() {
        return this.tiles;
    }

    /**
     * Get the dimension of the File
     * @return dimension
     */
    int getDimension() {
        return dimension;
    }

    /**
     * Return the tile at (row, col)
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return this.tiles[row][col];
    }

    /**
     * Sets a specific tile on the list to a specific value
     * @param row - target row
     * @param col - target column
     * @param tile - tile object to replace it
     */
    void setTile(int row, int col, Tile tile) {
        this.tiles[row][col] = tile;
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int getNumTiles() {
        return dimension * dimension;
    }

    @Override
    public String toString() {
        return "SlidingTilesBoard{" +
                "tiles=" + Arrays.toString(getAllTiles()) +
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
