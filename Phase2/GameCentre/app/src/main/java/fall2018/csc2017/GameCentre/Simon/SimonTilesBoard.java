package fall2018.csc2017.GameCentre.Simon;

import java.util.List;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Tile;

public class SimonTilesBoard extends Board<SimonTile> {
    /**
     * Instantiates a SimonTilesBoard object with a dimension and list of SimonTiles
     *
     * @param dimension - nxn dimension
     * @param tiles     - list of SimonTiles
     */

    public SimonTilesBoard(int dimension, List<SimonTile> tiles) {
        super(dimension, tiles);
    }

}
