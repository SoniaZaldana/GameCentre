package fall2018.csc2017.GameCentre.Score;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fall2018.csc2017.GameCentre.GameLauncherActivity;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SharedPreferenceManager;

/**
 * The end screen after the puzzle is solved
 */
public class ScoreScreenActivity extends AppCompatActivity {
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
    TextView scoreValue;
    TextView highScore;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreValue = findViewById(R.id.ScoreValueLabel);
        highScore = findViewById(R.id.HighScoreLabel);
        btn = findViewById(R.id.MainMenuButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreScreenActivity.this, GameLauncherActivity.class));
            }
        });

        user = SharedPreferenceManager.getSharedValue(this, "sharedUser", "thisUser");
        gameFile = getIntent().getStringExtra("Game");
        score = getIntent().getIntExtra("Score", 0);
        scoreValue.setText(Integer.toString(score));

        if (TextFileManager.isHighScore(this, gameFile, user, score))
            highScore.setText("New High Score");
        else
            highScore.setText("Not a new high score, but pretty good!");

        // Saving score in game file
        TextFileManager.saveToFile(this, gameFile, user, score);
        // Saving score in specific user score file
        String gameName = gameFile.substring(0, gameFile.indexOf('.'));
        TextFileManager.saveToFile(this, user + "Score.txt", gameName, score);
    }
}








