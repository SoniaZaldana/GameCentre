package fall2018.csc2017.GameCentre.MineSweeper;

import java.util.Timer;
import java.util.TimerTask;


//TODO Create a display for the flag counter, and get it from MovementControllerSweeper.
//TODO WIll probably have to implement observable.
public class MineSweeperActivity {
    private Timer timer = new Timer();

    private SweeperBoardManager sweeperBoardManager;

    @Override
    protected void onCreate(){
        super.onCreate();
        addSecond();

    }

    @Override
    protected void onPause(){
        super.onPause();
        this.timer.cancel();
    }

    private void addSecond(){
        ScoreTask task = new ScoreTask(sweeperBoardManager);
        timer.schedule(task, 1000, 1000);
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
