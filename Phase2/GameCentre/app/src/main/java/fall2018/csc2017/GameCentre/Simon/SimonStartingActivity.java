package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fall2018.csc2017.GameCentre.R;

/**
 * The initial activity for the Simon Game
 */
public class SimonStartingActivity extends AppCompatActivity {
    /**
     * The main save file
     */
    public static String SAVE_FILENAME;
    /**
     * The board Manager
     */
    private SimonBoardManager simonBoardManager;

    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_starting);
    }
}
