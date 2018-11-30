package fall2018.csc2017.GameCentre.MineSweeper;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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

    /**
     * The amount of time a time bomb has before exploding
     */
    private int bombTime;

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
     *
     * @return time time that has transpired for the game
     */
    int getTime() {
        return this.time;
    }

    /**
     * Increments the time by 1 and notifies the observers.
     */
    void timeIncrement() {
        this.time++;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Builds up the list of SweeperTiles
     *
     * @param complexity the percentage of tiles with bombs
     * @return
     */
    private ArrayList<SweeperTile> getMinesTiles(int complexity) {
        ArrayList<SweeperTile> tilesList = new ArrayList<>();
        int numOfMines = getDimension() * getDimension() * complexity / 100;
        ArrayList locationOfTilesWithMine = buildLocationsOfMines(numOfMines);
        for (int i = 0; i < getDimension() * getDimension(); i++) {
            if (locationOfTilesWithMine.contains(i)) {
                tilesList.add(new SweeperTile(true));
            } else {
                tilesList.add(new SweeperTile(false));
            }
        }
        return tilesList;
    }

    /**
     * Builds an array of ints which represent the location of mines on the board.
     *
     * @param numOfMines
     * @return
     */
    private ArrayList buildLocationsOfMines(int numOfMines) {
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

    /**
     * A getter for bombTime
     *
     * @return int bombTime
     */
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
     *
     * @return int hitPoints
     */
    int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Sets the tile located in a row row and column col to exploded
     *
     * @param row represents on which row tile is located
     * @param col represents on which column tile is located
     */
    public void setBombToExploded(int row, int col) {
        int[] locationOfTile = {row, col};
        this.getTile(row, col).setBombExploded();
        setChanged();
        notifyObservers(locationOfTile);
    }

    /**
     * Sets the tile located in a row row and column col to not flagged
     *
     * @param row represents on which row tile is located
     * @param col represents on which column tile is located
     */
    public void setTileToNotFlagged(int row, int col) {
        this.getTile(row, col).setTileToNotFlaged();
        int[] locationOfTile = {row, col};
        setChanged();
        notifyObservers(locationOfTile);
    }

    /**
     * Sets the tile located in a row row and column col to flagged
     *
     * @param row represents on which row tile is located
     * @param col represents on which column tile is located
     */
    public void setTileToFlagged(int row, int col) {
        this.getTile(row, col).setTileToFlaged();
        int[] locationOfTile = {row, col};
        setChanged();
        notifyObservers(locationOfTile);
    }

    /**
     * Sets the how many adjacent bombs the tile located in a row row and column col has
     *
     * @param row           represents on which row tile is located
     * @param col           represents on which column tile is located
     * @param numberOfBombs represent how many adjacent tiles contains bombs
     */
    public void setBombsAround(int row, int col, int numberOfBombs) {
        this.getTile(row, col).setBombsAround(numberOfBombs);
        int[] locationOfTile = {row, col};
        setChanged();
        notifyObservers(locationOfTile);
    }

    /**
     * Lowers the bombTime by one
     */
    public void lowerBombTime() {
        bombTime--;
        setChanged();
        notifyObservers();

    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {
        SweeperTile tempValue = getTile(row1, col1);
        setTile(row1, col1, getTile(row2, col2));
        setTile(row2, col2, tempValue);
        setChanged();
        notifyObservers();
    }

    public void swipeWithSafeTile(int row, int col) {
        ArrayList location = findFirsSafeTile();
        swapTiles(row, col, (int) location.get(0), (int) location.get(1));
    }

    public ArrayList findFirsSafeTile() {
        ArrayList<ArrayList<SweeperTile>> tiles = getAllTiles();
        for (int i = 0; i != tiles.size(); i++) {
            for (int b = 0; b != tiles.get(i).size(); b++) {
                if (!getTile(i, b).hasBomb()) {
                    ArrayList location = new ArrayList();
                    location.add(i);
                    location.add(b);
                    return location;
                }
            }
        }
        return new ArrayList();
    }
}





