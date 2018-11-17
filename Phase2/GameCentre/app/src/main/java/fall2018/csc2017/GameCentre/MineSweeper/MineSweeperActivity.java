package fall2018.csc2017.GameCentre.MineSweeper;

import java.util.TimerTask;

//TODO Create a display for the flag counter, and get it from MovementControllerSweeper.
//TODO WIll probably have to implement observable.
public class MineSweeperActivity {





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
