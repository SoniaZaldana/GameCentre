package fall2018.csc2017.GameCentre.MineSweeper;

import java.util.List;

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

    SweeperTilesBoard(int dimension, List<SweeperTile> tiles) {
        super(dimension, tiles);
        this.time = 0;
        this.hitPoints = 3;
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
}
