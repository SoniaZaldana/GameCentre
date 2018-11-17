package fall2018.csc2017.GameCentre.Simon;

import java.util.List;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Tile;

public class SimonBoard extends Board<Tile> {
    /**
     * Instantiates a board object with a dimension and list of tiles
     *
     * @param dimension - nxn dimension
     * @param tiles     - list of tiles
     */

    public SimonBoard(int dimension, List<Tile> tiles) {
        super(dimension, tiles);
    }

}
