package fall2018.csc2017.GameCentre.MineSweeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fall2018.csc2017.GameCentre.GameLauncherActivity;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;
import fall2018.csc2017.GameCentre.SharedPreferenceManager;


public class SweeperStartingActivity extends AppCompatActivity {    /**
 * The main save file
 */
public static String SWEEPER_SAVE_FILENAME;
    /**
     * The board Manager
     */
    private SweeperBoardManager sweeperBoardManager;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_sweeper_starting);

        addLoadButtonListener();
        addNewGameButtonListener();

        String user = SharedPreferenceManager.getSharedValue(this, "sharedUser", "thisUser");
        SWEEPER_SAVE_FILENAME = user + "sweeper_save_file.ser";
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Activate the start button.
     */
    private void addNewGameButtonListener() {
        Button startButton = findViewById(R.id.NewGameButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sweeperBoardManager = SaveAndLoadBoardManager.loadFromFile(context, SWEEPER_SAVE_FILENAME);
                if (sweeperBoardManager == null) {
                    Toast.makeText(getApplicationContext(), "No previously saved game.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Loaded Game", Toast.LENGTH_SHORT).show();
                    loadGame();
                }

            }
        });
    }

    private void loadGame() {
        Intent intent = new Intent(this, MineSweeperActivity.class);
        startActivity(intent);
    }
    /**
     * Starts activity for a new game
     */
    private void newGame() {
        Intent tmp = new Intent(this, SweeperChooseDimensionActivity.class);
        startActivity(tmp);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SweeperStartingActivity.this, GameLauncherActivity.class));
        finish();

    }
}
