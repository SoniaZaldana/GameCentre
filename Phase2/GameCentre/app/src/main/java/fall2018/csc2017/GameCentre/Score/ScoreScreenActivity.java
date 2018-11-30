/**
 * Excluded from tests because it is a view class.
 */

package fall2018.csc2017.GameCentre.Score;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fall2018.csc2017.GameCentre.ChooseDimensionActivity;
import fall2018.csc2017.GameCentre.GameLauncherActivity;
import fall2018.csc2017.GameCentre.MineSweeper.SweeperChooseDimensionActivity;
import fall2018.csc2017.GameCentre.MineSweeper.SweeperStartingActivity;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;
import fall2018.csc2017.GameCentre.SharedPreferenceManager;
import fall2018.csc2017.GameCentre.Simon.SimonStartingActivity;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesStartingActivity;

/**
 * The end screen after the puzzle is solved
 */
public class ScoreScreenActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * score for the game
     */
    int score;
    /**
     * user name
     */
    String user;
    /**
     * name of the game
     */
    String gameFile;

    /**
     * View elements
     */
    TextView scoreValue;
    TextView highScore;
    Button menuButton;
    Button newGameButton;
    String currentGame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_game_score_screen);
        scoreValue = findViewById(R.id.ScoreValueLabel);
        highScore = findViewById(R.id.HighScoreLabel);
        addButtonListeners();

        //Get current user
        user = SharedPreferenceManager.getSharedValue(this, "sharedUser", "thisUser");
        // Get game file for this game
        gameFile = getIntent().getStringExtra("Game");
        // Get score
        score = getIntent().getIntExtra("Score", 0);
        scoreValue.setText(Integer.toString(score));

        // Display if the acquired score is a high score
        if (TextFileManager.isHighScore(this, gameFile, user, score))
            highScore.setText("New High Score");
        else
            highScore.setText("Not a new high score, but pretty good!");

        // Saving score in game file
        TextFileManager.saveToFile(this, gameFile, user, score);
        // Saving score in specific user score file
        String gameName = gameFile.substring(0, gameFile.indexOf('.'));
        TextFileManager.saveToFile(this, user + "Score.txt", gameName, score);

        currentGame = gameFile.substring(0, gameFile.indexOf("."));

        switch(currentGame){
            case("Simon"):
                SaveAndLoadBoardManager.saveToFile(this,SimonStartingActivity.SAVE_FILENAME ,null);
                break;
            case("Minesweeper"):
                SaveAndLoadBoardManager.saveToFile(this,SweeperStartingActivity.SAVE_FILENAME,null);
                break;
            case("SlidingTiles"):
                SaveAndLoadBoardManager.saveToFile(this, SlidingTilesStartingActivity.SAVE_FILENAME, null);
                break;
        }
    }

    private void addButtonListeners() {
        menuButton = findViewById(R.id.MainMenuButton);
        newGameButton = findViewById(R.id.NewGameButton);
        newGameButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ScoreScreenActivity.this, GameLauncherActivity.class));
        finish();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.NewGameButton:
                if (currentGame.equals("Simon")){
                    startActivity(new Intent(this, ChooseDimensionActivity.class).
                            putExtra("Game", currentGame));
                    break;
                }
                else if (currentGame.equals("SlidingTiles")){
                    startActivity(new Intent(this, ChooseDimensionActivity.class).
                            putExtra("Game", currentGame));
                    break;
                }
                else if (currentGame.equals("Minesweeper")){
                    startActivity(new Intent(this, SweeperChooseDimensionActivity.class).
                            putExtra("Game", currentGame));
                    break;
                }
            case R.id.MainMenuButton:
                startActivity(new Intent(this,
                        GameLauncherActivity.class));
                break;

        }
    }

}








