package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import fall2018.csc2017.GameCentre.Score.ScoreboardMenuActivity;
import fall2018.csc2017.GameCentre.Simon.SimonStartingActivity;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesStartingActivity;

//TODO        Order HighScore
//TODO        Possibly get rid of backbutton
//TODO        change colour on Scoreboard(to constant shade)
//TODO        Change layouts on scoreboard so it doesn't shift.
//TODO        Once User wins, remove game from saved, as user should not be able to load it again.
/**
 * The Game Launcher Screen
 */
public class GameLauncherActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Scoreboards, TilesGame, SimonGame;
    /**
     * User name
     */
    private String user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelauncheractivity);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.toolbar);
        myToolbar.setLogo(R.drawable.ic_launcher_foreground);
        setSupportActionBar(myToolbar);
        TilesGame = findViewById(R.id.TilesGame);
        Scoreboards = findViewById(R.id.ScoreboardButton);
        SimonGame = findViewById(R.id.SimonGame);
        TilesGame.setOnClickListener(this);
        Scoreboards.setOnClickListener(this);
        SimonGame.setOnClickListener(this);
        user = SharedPreferenceManager.getSharedValue(this, "sharedUser", "thisUser");
        createFiles(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_signoff:
                startActivity(new Intent(this, LoginActivity.class));
                SharedPreferenceManager.deleteSharedValue(this, "sharedUser", "thisUser");
                return true;
        }
        return true;

    }

    /**
     * Creates a file which stores all high-scores for each game for user.
     * Creates another file which stores the high-scores from all users per game.
     * @param userFile represents the code to be added to a filename to differentiate
     * between user high score files.
     */
    //TODO: Add files for minesweeper because Simon was already added
    public void createFiles(String userFile) {
        File userScoreFile = new File(this.getFilesDir(), userFile + "Score.txt");
        File slidingFile = new File(this.getFilesDir(), "SlidingTiles.txt");
        File simonFile = new File(this.getFilesDir(), "Simon.txt");
        try {
            userScoreFile.createNewFile();
            slidingFile.createNewFile();
            simonFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TilesGame:
                startActivity(new Intent(this,
                        SlidingTilesStartingActivity.class));
                break;
            case R.id.ScoreboardButton:
                startActivity(new Intent(this,
                        ScoreboardMenuActivity.class));
                break;
            case R.id.SimonGame:
                startActivity(new Intent(this, SimonStartingActivity.class));
                break;
        }
    }
}
