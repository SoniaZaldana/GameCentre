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


    Board(int dimension) {
        this.numRows = dimension;
        this.numCols = dimension;
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
