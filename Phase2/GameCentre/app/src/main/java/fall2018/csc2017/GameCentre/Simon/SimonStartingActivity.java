/**
 * Excluded from tests because it is a view class.
 */
package fall2018.csc2017.GameCentre.Simon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fall2018.csc2017.GameCentre.ChooseDimensionActivity;
import fall2018.csc2017.GameCentre.GameLauncherActivity;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;
import fall2018.csc2017.GameCentre.SharedPreferenceManager;

/**
 * The initial activity for the simon Game
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_starting);

        addLoadButtonListener();
        addNewGameButtonListener();

        String user = SharedPreferenceManager.getSharedValue(this, "sharedUser", "thisUser");
        SAVE_FILENAME = user + "Simon_save_file.ser";
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        simonBoardManager = SaveAndLoadBoardManager.loadFromFile(this, SAVE_FILENAME);
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
                simonBoardManager = SaveAndLoadBoardManager.loadFromFile(getApplicationContext(), SAVE_FILENAME);
                if (simonBoardManager == null) {
                    Toast.makeText(getApplicationContext(), "No previously saved game.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Loaded Game", Toast.LENGTH_SHORT).show();
                    loadGame();
                }

            }
        });
    }

    /**
     * Loads existing saved instance of game
     */
    private void loadGame() {
        Intent intent = new Intent(this, SimonGameActivity.class);
        startActivity(intent);
    }
    /**
     * Starts activity for a new game
     */
    private void newGame() {
        startActivity(new Intent(this, ChooseDimensionActivity.class).putExtra("Game", "simon"));
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SimonStartingActivity.this, GameLauncherActivity.class));
        finish();
    }
}
