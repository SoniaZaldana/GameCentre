package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Board<U extends Tile> extends Observable implements Serializable, Iterable<U> {
    /**
     * The number of rows.
     */
    private int dimension;
    /**
     * The tiles on the board in row-major order.
     */
    private ArrayList<ArrayList<U>> tiles;

    /**
     * Instantiates a board object with a dimension and list of tiles
     * @param dimension - nxn dimension
     */

    /**
     * Returns a list containing all tiles in the board
     * @return list of tiles
     */

    public Board(int dimension, List<U> listOfTiles) {
        this.dimension = dimension;
        tiles = new ArrayList<>();
        // Create the board
        Iterator<U> iter = listOfTiles.iterator();
        for (int row = 0; row != dimension; row++) {
            ArrayList<U> rowTile = new ArrayList<>();
            for (int col = 0; col != dimension; col++) {
                rowTile.add(iter.next());
            }
            tiles.add(rowTile);
        }
    }
    public ArrayList<ArrayList<U>> getAllTiles() {
        return this.tiles;
    }


    /**
     * Get the dimension of the File
     * @return dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Return the tile at (row, col)
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public U getTile(int row, int col) {
        return this.tiles.get(row).get(col);
    }

    /**
     * Sets a specific tile on the list to a specific value
     * @param row - target row
     * @param col - target column
     * @param tile - tile object to replace it
     */
    public void setTile(int row, int col, U tile) {
        this.tiles.get(row).set(col, tile);

    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int getNumTiles() {
        return dimension * dimension;
    }

    @Override
    public String toString() {
        List<ArrayList<U>> allTiles = getAllTiles();
        List<Integer> tilesId = new ArrayList<>();
        for(ArrayList<U> listOfTiles: allTiles){
            for(U t: listOfTiles){
                tilesId.add(t.getId());
            }
        }
        return "tiles = " + tilesId.toString();
    }
    /**
     * @return an iterator of class TileIterator.
     */
    @NonNull
    @Override
    public Iterator<U> iterator() {
        return new TileIterator();
    }

    /**
     * Represents the iterator for class SlidingTilesBoard.
     */
    private class TileIterator implements Iterator<U> {
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
        public U next() {
            // get the data for the current tile Tile, then move the index to the next Tile.
            U nextTile = tiles.get(rowIndex).get(columnIndex);
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
