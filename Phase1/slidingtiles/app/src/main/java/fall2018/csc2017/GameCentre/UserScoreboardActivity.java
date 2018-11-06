package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * User specific Scoreboard
 */
public class UserScoreboardActivity extends AppCompatActivity {
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_scoreboard);
        TextView gameName = findViewById(R.id.GameNameLabel);
        TextView gameScore = findViewById(R.id.ScoreLabel);
        user = SharedPreferenceManager.getSharedValue(this, "sharedUser", "thisUser");
        gameName.setText(R.string.sliding_tiles);
        gameScore.setText(getScorePerGame(user + "Score.txt", "SlidingTiles"));
    }

    /**
     * Starts activity ScoreboardMenuActivity
     * @param view
     */
    public void goToScoreboardMenu(View view) {
        Intent intent = new Intent(this, ScoreboardMenuActivity.class);
        startActivity(intent);
    }

    /**
     * Returns the score for a specific target game in a given file
     * @param fileName - the given file to search the score in
     * @param targetGame - the game we are looking for
     * @return score saved for the game targetGame in fileName
     */
    public String getScorePerGame(String fileName, String targetGame) {
        String score = "0";
        String line;
        BufferedReader reader;
        int index;
        try {
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            while ((line = reader.readLine()) != null) {
                // Splitting the line entry to find the first half (the game name)
                index = line.indexOf(",");
                String game = line.substring(1, index);
                if (game.equals(targetGame)) {
                    score = line.substring(index + 1, line.length() - 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
        return score;
    }
}






