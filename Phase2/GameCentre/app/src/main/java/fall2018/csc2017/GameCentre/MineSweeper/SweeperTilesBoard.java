package fall2018.csc2017.GameCentre.MineSweeper;

import java.lang.reflect.Array;
import android.util.Log;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.GameCentre.Board;

public class SweeperTilesBoard extends Board<SweeperTile> {
    /**
     * The number of seconds that has passed for this game
     */
    private int time;

    /**
     * The HP of the player
     */
    private int hitPoints;

    public SweeperTilesBoard(int dimension, List<SweeperTile> tiles) {
        super(dimension, tiles);
        this.time = 0;
        this.hitPoints = 3;
        hasChanged();
        notifyObservers();
    }
    /**
     * A getter for the time
     * @return time time that has transpired for the game
     */
    public int getTime() {
        return time;
    }

    /**
     * Increments the time by 1.
     */
    public void timeIncrement(){
        this.time++;
        this.setChanged();
        this.notifyObservers();
        Log.i("time", String.valueOf(this.time));
    }

    /**
     * Decreases HP by 1
     */
    public void takeDamage() {
        hitPoints--;
    }

    /**
     * A getter for the HP
     * @return int hitPoints
     */

    public int getHitPoints() {
        return hitPoints;
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
