package fall2018.csc2017.GameCentre.MineSweeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


//TODO Create a display for the flag counter, and get it from MovementControllerSweeper.
//TODO WIll probably have to implement observable.
public class MineSweeperActivity extends AppCompatActivity {
    private Timer timer = new Timer();

    private SweeperBoardManager sweeperBoardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        sweeperBoardManager.startTimer();
    }

    @Override
    protected void onPause(){
        super.onPause();
        sweeperBoardManager.stopTimer();
    }

}
