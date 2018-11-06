package fall2018.csc2017.slidingtiles;

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

public class ScoreCalculatorActivity extends AppCompatActivity {
    int score;
    String user;
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

        user = SharedPreferenceManager.getSharedValue(this, "sharedUser", "thisUser");
        gameFile = getIntent().getStringExtra("Game");
        score = getIntent().getIntExtra("Score", 0);
        scoreValue.setText(Integer.toString(score));

        if (isHighScore(gameFile, score))
            highScore.setText("New High Score");
        else
            highScore.setText("Not a new high score, but pretty good!");
        saveToFile(gameFile, user, score);
        saveToFile(user + "Score.txt", "SlidingTiles", score);


        btn = findViewById(R.id.MainMenuButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreCalculatorActivity.this, GameLauncherActivity.class));
            }
        });

    }

    private void saveToFile(String fileName, String firstVariable, int scoreSave) {
        String entry = "[" + firstVariable + "," + scoreSave + "]";
        File scoreFile = new File(this.getFilesDir(), fileName);
        FileWriter fr;
        try {
            if (isHighScore(fileName, scoreSave)) {
                deletePreviousHighScore(fileName, firstVariable);
                fr = new FileWriter(scoreFile, true);
                fr.write(entry+"\n");
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isHighScore(String fileName, int userScore) {
        boolean highScore = false;
        String line;
        BufferedReader reader;
        int index;
        int scoreSaved;
        try {
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            if ((line = reader.readLine()) == null) {
                highScore = true;
            }
            reader.close();
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            while ((line = reader.readLine()) != null) {
                index = line.indexOf(",");
                scoreSaved = valueOf(line.substring(index + 1, line.length() - 1));
                if (userScore > scoreSaved) {
                    highScore = true;
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
        return highScore;
    }
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
                    fr.write(line+"\n");
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








