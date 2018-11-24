package fall2018.csc2017.GameCentre.MineSweeper;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.BoardManager;

public class SweeperBoardManager extends BoardManager<SweeperTilesBoard> implements Serializable {
    /**
     * The number of seconds that has passed for this game
     */
    private int time;

    /**
     * The HP of the player
     */
    private int hitPoints;

    /**
     * A timer that increments the time by 1 every second (a game clock)
     */
    private Timer timer = new Timer();

    /**
     * The amount of time a time bomb has before exploding
     */
    private int bombTime;

    /**
     * Whether or not there's a timer bomb active
     */
    private boolean bombActive = false;

    public SweeperBoardManager(){
        this.time = 0;
        this.hitPoints = 3;
        this.bombTime = 10;
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
    }

    public void takeDamage() {
        hitPoints--;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public int calculateScore(int mines) {
        return (mines / time);
    }

    public void startTimer(){
        ScoreTask task = new ScoreTask(this);
        timer.schedule(task, 1000, 1000);
    }

    public void stopTimer(){
        timer.cancel();
    }

    public int getBombTime() {
        return bombTime;
    }

    public boolean isBombActive() {
        return bombActive;
    }

    public void lowerBombTime(){
        this.bombTime--;
    }

    public void setBombActive(boolean active){
        this.bombActive = active;
    }

    /**
     * A timer task that increments the timer.
     */
    private class ScoreTask extends TimerTask {

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
            this.manager.timeIncrement();
        }

    }
}
