package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
    SharedPreferences currentUsername;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreValue = findViewById(R.id.ScoreValueLabel);
        highScore = findViewById(R.id.HighScoreLabel);

        currentUsername = getApplicationContext().getSharedPreferences("sharedUser", MODE_PRIVATE);
        user = currentUsername.getString("thisUser", "User");

        gameFile = getIntent().getStringExtra("Game");
        score = getIntent().getIntExtra("Score", 0);
        scoreValue.setText(Integer.toString(score));

        if (isHighScore(gameFile, score))
            highScore.setText("New High Score");
        saveToFile(gameFile, user, score);
        btn = findViewById(R.id.MainMenuButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreCalculatorActivity.this, Gamelauncheractivity.class));
            }
        });

    }

    private void saveToFile(String fileName, String user, int score) {
        String entry = "[" + user + "," + score + "]";
        File scoreFile = new File(this.getFilesDir(), fileName);
        FileWriter fr = null;
        try {
            fr = new FileWriter(scoreFile);
            if (isHighScore(fileName, score)) {
                deletePreviousHighScore(fileName, user);
                fr.write(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean isHighScore(String fileName, int userScore) {
        boolean highScore = false;
        String line;
        BufferedReader reader;
        int index;
        int score;
        try {
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            if ((line = reader.readLine()) == null) {
                return true;
            }
            while ((line = reader.readLine()) != null) {
                index = line.indexOf(",");
                score = valueOf(line.substring(index + 1, line.length()-1));
                if (userScore > score) {
                    highScore = true;
                }
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
        return highScore;
    }




    private void deletePreviousHighScore(String fileName, String targetUser) {
        String line;
        int index;
        BufferedReader reader;
        File fixedFile = new File(this.getFilesDir(), fileName);
        FileWriter fr = null;
        try {
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            fr = new FileWriter(fixedFile);
            while ((line = reader.readLine()) != null) {
                index = line.indexOf(",");
                if (!line.substring(0, index - 1).equals(targetUser)) {
                    fr.write(line);
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







