package fall2018.csc2017.GameCentre.MineSweeper;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.GameCentre.Board;

public class SweeperTilesBoard extends Board<SweeperTile> {

    public SweeperTilesBoard(int dimension, List<SweeperTile> tiles) {
        super(dimension, tiles);
    }
    public void setBombToExploded(int row, int col) {
        int[] locationOfTile = {row, col};
        this.getTile(row, col).setBombExploded();
        setChanged();
        notifyObservers(locationOfTile);
    }

    public void setTileToNotFlaged(int row, int col) {
        this.getTile(row, col).setTileToNotFlaged();
        int[] locationOfTile = {row, col};
        setChanged();
        notifyObservers(locationOfTile);
    }

    public void setTileToFlaged(int row, int col) {
        this.getTile(row,col).setTileToFlaged();
        int[] locationOfTile = {row, col};
        setChanged();
        notifyObservers(locationOfTile);
    }
    public void setBombsAround(int row, int col, int numberOfBombs) {
        this.getTile(row, col).setBombsAround(numberOfBombs);
        int[] locationOfTile = {row, col};
        setChanged();
        notifyObservers(locationOfTile);
    }


}
