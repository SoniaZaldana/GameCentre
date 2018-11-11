package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.Observable;

class Board extends Observable implements Serializable {
    /**
     * The number of rows.
     */
    private int numRows;
    /**
     * The number of rows.
     */
    private int numCols;
    /**
     * path of the board Picture.
     */
    private String picturePath;

    Board(int dimension) {
        this.numRows = dimension;
        this.numCols = dimension;
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
     * get the number of columns
     * @return numCols
     */
    int getNumCols() {
        return numCols;
    }

    /**
     * get the number of rows
     * @return numRows
     */
    int getNumRows() {
        return numRows;
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return numRows * numCols;
    }
}
