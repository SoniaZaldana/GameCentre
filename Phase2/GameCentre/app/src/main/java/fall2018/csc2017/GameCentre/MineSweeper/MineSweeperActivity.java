package fall2018.csc2017.GameCentre.MineSweeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridViewLongPress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SlidingTiles.MovementControllerSliding;


//TODO Create a display for the flag counter, and get it from MovementControllerSweeper.
//TODO WIll probably have to implement observable.
public class MineSweeperActivity extends AppCompatActivity implements Observer {
    private GestureDetectGridViewLongPress gridView;
    private TextView timerText;
    private TextView healthNumber;
    private Timer timer = new Timer();
    private MovementControllerSweeper movementControllerSweeper;
    private static int columnWidth, columnHeight;

    private SweeperBoardManager sweeperBoardManager;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTime();
        updateHealth();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        sweeperBoardManager.getBoard().addObserver(this);
        sweeperBoardManager.startTimer();
        timerText.findViewById(R.id.timer);
        healthNumber.findViewById(R.id.HP);

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

                        columnWidth = displayWidth / sweeperBoardManager.getBoard().getDimension();
                        columnHeight = displayHeight / sweeperBoardManager.getBoard().getDimension();

                        display();
                    }
                });
    }


    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    private void updateTime(){
        timerText.setText(sweeperBoardManager.getBoard().getTime());
    }

    private void updateHealth(){
        healthNumber.setText(sweeperBoardManager.getBoard().getHitPoints());
    }

    @Override
    protected void onPause(){
        super.onPause();
        sweeperBoardManager.stopTimer();
    }

}
