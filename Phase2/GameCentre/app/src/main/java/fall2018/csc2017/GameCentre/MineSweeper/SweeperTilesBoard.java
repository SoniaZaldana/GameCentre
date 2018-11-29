package fall2018.csc2017.GameCentre.MineSweeper;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Tile;

public class SweeperTilesBoard extends Board<SweeperTile> {
    /**
     * The number of seconds that has passed for this game
     */
    private int time;

    /**
     * The HP of the player
     */
    private int hitPoints;

    /**
     * The amount of time a time bomb has before exploding
     */
    private int bombTime;

    public SweeperTilesBoard(int dimension, List<SweeperTile> tiles) {
        super(dimension, tiles);
        this.time = 0;
        this.hitPoints = 3;
        this.bombTime = 10;
        hasChanged();
        notifyObservers();
    }

    public SweeperTilesBoard(int dimension, int complexity) {
        super();
        setDimension(dimension);
        ArrayList<ArrayList<SweeperTile>> tiles = new ArrayList<>();
        ArrayList<SweeperTile> mineTiles = getMinesTiles(complexity);
        Iterator iter = mineTiles.iterator();
        for (int row = 0; row != dimension; row++) {
            ArrayList rowTile = new ArrayList<>();
            for (int col = 0; col != dimension; col++) {
                rowTile.add(iter.next());
            }
            tiles.add(rowTile);
        }
        setTiles(tiles);
        this.time = 0;
        this.hitPoints = 3;
        this.bombTime = 10;
        hasChanged();
        notifyObservers();
    }
    /**
     * A getter for the time
     * @return time time that has transpired for the game
     */
    int getTime() {
        return this.time;
    }

    /**
     * Increments the time by 1.
     */
    void timeIncrement(){
        this.time++;
        this.setChanged();
        this.notifyObservers();
    }
    private ArrayList<SweeperTile> getMinesTiles(int complexity) {
        ArrayList<SweeperTile> tilesList = new ArrayList<>();
        int numOfMines = getDimension() * getDimension() * complexity / 100;
        ArrayList locationOfTilesWithMine = randomlyChoose(numOfMines);
        for (int i = 0; i < getDimension() * getDimension(); i++) {
            if (locationOfTilesWithMine.contains(i)) {
                tilesList.add(new SweeperTile(true));
            } else {
                tilesList.add(new SweeperTile(false));
            }
        }
        return tilesList;
    }

    private ArrayList randomlyChoose(int numOfMines) {
        int i = 0;
        Random myRandom = new Random();
        ArrayList locationOfTilesWithMine = new ArrayList<>();
        while (i != numOfMines) {
            int location = myRandom.nextInt(getDimension() * getDimension());
            if (!locationOfTilesWithMine.contains(location)) {
                locationOfTilesWithMine.add(location);
                i++;
            }
        }
        return locationOfTilesWithMine;

    }

    public int getBombTime() {
        return bombTime;
    }

    /**
     * Decreases HP by 1
     */
    void takeDamage() {
        this.hitPoints--;
    }

    /**
     * A getter for the HP
     * @return int hitPoints
     */

    int getHitPoints() {
        return this.hitPoints;
    }
    public void setBombToExploded(int row, int col) {
        int[] locationOfTile = {row, col};
        this.getTile(row, col).setBombExploded();
        setChanged();
        notifyObservers(locationOfTile);
    }

    public void setTileToNotFlagged(int row, int col) {
        this.getTile(row, col).setTileToNotFlaged();
        int[] locationOfTile = {row, col};
        setChanged();
        notifyObservers(locationOfTile);
    }

    public void setTileToFlagged(int row, int col) {
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

    public void lowerBombTime() {
        bombTime--;
        setChanged();
        notifyObservers();

    }

}
