package fall2018.csc2017.GameCentre.MineSweeper;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.BoardManager;

public class SweeperBoardManager extends BoardManager<SweeperTilesBoard> implements Serializable {
    /**
     * The number of seconds that has passed for this game
     */
    private int time;

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
    }

    @Override
    public int calculateScore(int mines) {
        return time;
    }
}
