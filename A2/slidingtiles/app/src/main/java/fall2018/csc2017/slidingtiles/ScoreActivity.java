package fall2018.csc2017.slidingtiles;

import android.content.Intent;
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

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView scoreValue = findViewById(R.id.ScoreValueLabel);
        TextView highScore = findViewById(R.id.HighScoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0);
        String user = getIntent().getStringExtra("USERNAME");
        String gameID = getIntent().getStringExtra("GAME_ID");
        scoreValue.setText(score);

        if (isHighScore(gameID, score))
            highScore.setText("New High Score");
        saveToFile(gameID, user, score);
        Button btn = findViewById(R.id.MainMenuButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreActivity.this, Gamelauncheractivity.class));
            }
        });

    }

    private void saveToFile(String fileName, String user, int score) {
        try {
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(fileName));
            if (isHighScore(fileName, score)) {
                deletePreviousHighScore(fileName, user);
                String entry = "[" + user + "," + score + "]";
                outputWriter.write(entry);
                outputWriter.newLine();
                outputWriter.flush();
                outputWriter.close();
            }
            outputWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private boolean isHighScore(String fileName, int userScore) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean highScore = false;
            while ((line = bufferedReader.readLine()) != null) {
                int index = line.indexOf(",");
                int score = valueOf(line.substring(index + 1, line.length()-1));
                if (userScore > score) {
                    highScore = true;
                }
            } bufferedReader.close();
            return highScore;
        } catch (FileNotFoundException e) {
            Log.e("Exception", "Unable to open file: " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static void deletePreviousHighScore(String fileName, String targetUser) {
        try {
            File fixedFile = new File("fixed.txt");
            try {
                fixedFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter("fixed.txt"));
            BufferedReader bufferedReader = new BufferedReader(new BufferedReader(new FileReader(fileName)));
            String currentUser;
            while ((currentUser = bufferedReader.readLine()) != null) {
                int index = currentUser.indexOf(",");
                if (!currentUser.substring(0, index - 1).equals(targetUser)) {
                    outputWriter.write(currentUser.substring(0, index - 1));
                    outputWriter.newLine();
                }
                outputWriter.flush();
                outputWriter.close();
            }
            outputWriter.close();
            bufferedReader.close();
            File oldFile = new File(fileName);
            oldFile.delete();
            fixedFile.renameTo(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







