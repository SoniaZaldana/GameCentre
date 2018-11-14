package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import static java.lang.Integer.valueOf;

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
    protected void onCreate(Bundle savedInstanceState) {
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

        if (isHighScore(gameFile, user, score))
            highScore.setText("New High Score");
        else
            highScore.setText("Not a new high score, but pretty good!");
        saveToFile(gameFile, user, score);
        saveToFile(user + "Score.txt", "SlidingTiles", score);

    }

    /**
     * Saves scores into the corresponding text files (either the userScore.txt or game.txt)
     *
     * @param fileName      - File in which we are saving into
     * @param firstVariable - The corresponding variable to which score is saved
     *                      either user or game
     * @param scoreSave     - The score that will be saved
     */
    private void saveToFile(String fileName, String firstVariable, int scoreSave) {
        String entry = "[" + firstVariable + "," + scoreSave + "]";
        File scoreFile = new File(this.getFilesDir(), fileName);
        FileWriter fr;
        try {
            if (isHighScore(fileName, firstVariable, scoreSave)) {
                deletePreviousHighScore(fileName, firstVariable);
                fr = new FileWriter(scoreFile, true);
                fr.write(entry + "\n");
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether the new score obtained is a high score for the given user
     *
     * @param fileName  - file we are checking for previous high score (if any)
     * @param userScore - the user's new score achieved
     * @return whether it is a new high score or not
     */
    private boolean isHighScore(String fileName, String targetValue, int userScore) {
        boolean highScore = false;
        boolean userExists = false;
        String line;
        BufferedReader reader;
        int index;
        int scoreSaved;
        try {
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            // Checks whether file is empty, because empty file implies automatically a new high score
            if ((line = reader.readLine()) == null) {
                highScore = true;
            }
            reader.close();
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            while ((line = reader.readLine()) != null) {
                index = line.indexOf(",");
                if (line.substring(1, index).equals(targetValue)) {
                    userExists = true;
                    scoreSaved = valueOf(line.substring(index + 1, line.length() - 1));
                    if (userScore > scoreSaved) {
                        highScore = true;
                    }
                }
            }
            reader.close();
            if (!userExists){
                highScore = true;
            }
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
        return highScore;
    }

    /**
     * Deletes the previous high score in the existing files for game and user scores
     *
     * @param fileName    - file from which we are deleting previous high score
     * @param targetValue - the score we are trying to find to delete that entry in the file
     */
    private void deletePreviousHighScore(String fileName, String targetValue) {
        String line;
        int index;
        BufferedReader reader;
        File fixedFile = new File(this.getFilesDir(), fileName + "temp");
        FileWriter fr;
        try {
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            fr = new FileWriter(fixedFile);
            while ((line = reader.readLine()) != null) {
                index = line.indexOf(",");
                if (!line.substring(1, index).equals(targetValue)) {
                    fr.write(line + "\n");
                }
            }
            fr.close();
            reader.close();
            File oldFile = new File(this.getFilesDir(), fileName);
            oldFile.delete();
            fixedFile.renameTo(new File(this.getFilesDir(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








