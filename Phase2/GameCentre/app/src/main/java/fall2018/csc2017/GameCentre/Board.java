package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.Observable;

class Board extends Observable implements Serializable {
    /**
     * The number of rows.
     */
    private int dimension;
    /**
     * The number of rows.
     */

    Board(int dimension) {
        this.dimension = dimension;

    }



    /**
     * get the dimension
     * @return dimension
     */
    int getDimension() {
        return dimension;
    }


    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return dimension * dimension;
    }
}
