package fall2018.csc2017.GameCentre.MineSweeper;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.BoardManager;

public class SweeperBoardManager extends BoardManager<SweeperTilesBoard> implements Serializable {

    /**
     * A timer that increments the time by 1 every second (a game clock)
     */
    private static Timer timer = new SerializableTimer();

    /**
     * Whether or not there's a timer bomb active
     */
    private boolean bombActive = false;

    public SweeperBoardManager(SweeperTilesBoard b){
        super(b);
    }

    @Override
    public int calculateScore(int mines) {
        return mines*(10000 - getBoard().getTime());
    }

    public void startTimer(){
        ScoreTask task = new ScoreTask(this);
        SweeperBoardManager.timer.schedule(task, 1000, 1000);
    }

    public void stopTimer(){
        SweeperBoardManager.timer.cancel();
    }


    public boolean isBombActive() {
        return bombActive;
    }


    public void setBombActive(boolean active){
        this.bombActive = active;
    }

    /**
     * A timer task that increments the timer.
     */
    private class ScoreTask extends TimerTask implements Serializable {

        /**
         * The boardmanager that uses this timer.
         */
        private SweeperBoardManager manager;

        /**
         * An task that increments the time.
         *
         * @param manager The board manager this is acting on
         */
        public ScoreTask(SweeperBoardManager manager){
            super();
            this.manager = manager;
        }

        /**
         * The task this timer does.
         */
        public void run(){
            this.manager.getBoard().timeIncrement();
        }

    }
    public void setBombToExploded(int row, int col) {
        this.getBoard().setBombToExploded(row, col);
    }
    public void setTileToNotFlaged(int row, int col) {
        this.getBoard().setTileToNotFlaged(row, col);
    }

    public void setTileToFlaged(int row, int col) {
        this.getBoard().setTileToFlaged(row, col);
    }

    public void setsBombdAround(int row, int col, int numberOfBombs) {
        this.getBoard().setBombsAround(row, col, numberOfBombs);
    }
}
