package fall2018.csc2017.GameCentre;

import java.util.List;

/**
 * The sliding tiles board. Child class of Board.
 */
public class SlidingTilesBoard extends Board  {

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
        super(dimension, tiles);

    }


    /**
     * @return the id of the blank tile
     */
    int getBlankId() {
        return getNumTiles();
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
        Tile tempValue = getTile(row1, col1);
        setTile(row1,col1, getTile(row2, col2));
        setTile(row2,col2, tempValue);
        setChanged();
        notifyObservers();
    }

}
