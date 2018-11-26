package fall2018.csc2017.GameCentre.MineSweeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridViewLongPress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;

//TODO Create a display for the flag counter, and get it from MovementControllerSweeper.
//TODO WIll probably have to implement observable.
public class MineSweeperActivity extends AppCompatActivity implements Observer {
    private GestureDetectGridViewLongPress gridView;
    private TextView timerText;
    private TextView healthNumber;
    private MovementControllerSweeper movementControllerSweeper;
    private static int columnWidth, columnHeight;

    private SweeperBoardManager sweeperBoardManager;

    private Timer timer = new SerializableTimer();

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTime();
        updateHealth();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweeper);
        List<SweeperTile> tilesList = new ArrayList<>();
        final int numTiles = 5 * 5;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if (tileNum == 2 || tileNum == 5 || tileNum == 18 || tileNum == 3 || tileNum == 25) {
                tilesList.add(new SweeperTile(true));
            } else { tilesList.add(new SweeperTile(false));}
        }
        SweeperTilesBoard sweeperTilesBoard = new SweeperTilesBoard(5, tilesList);
        SweeperBoardManager sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        SaveAndLoadBoardManager.saveToFile(this, SweeperStartingActivity.SWEEPER_SAVE_FILENAME, sweeperBoardManager);



        this.sweeperBoardManager = SaveAndLoadBoardManager.loadFromFile(this, SweeperStartingActivity.SWEEPER_SAVE_FILENAME);

        sweeperBoardManager.getBoard().addObserver(this);
        sweeperBoardManager.startTimer();
        timerText = findViewById(R.id.timer);
        healthNumber = findViewById(R.id.HP);

        startTimer();
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(sweeperBoardManager.getBoard().getDimension());
        movementControllerSweeper = new MovementControllerSweeper(sweeperBoardManager);
        gridView.setMovementController(movementControllerSweeper);
        sweeperBoardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        display();
                    }
                });
    }


    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    private void updateTime(){
        timerText.setText(String.valueOf(sweeperBoardManager.getBoard().getTime()));
    }

    private void updateHealth(){
        healthNumber.setText(String.valueOf(sweeperBoardManager.getBoard().getHitPoints()));
    }

    @Override
    protected void onPause(){
        super.onPause();
        sweeperBoardManager.stopTimer();

        this.timer.cancel();
    }

    public void startTimer(){
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
            this.manager.getBoard().timeIncrement();
        }

    }

}
