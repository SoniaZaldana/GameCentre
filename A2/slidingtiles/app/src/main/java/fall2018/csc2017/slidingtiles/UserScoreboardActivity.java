package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserScoreboardActivity extends AppCompatActivity {
    String user;
    SharedPreferences currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_scoreboard);
        TextView gameName = findViewById(R.id.GameNameLabel);
        TextView gameScore = findViewById(R.id.ScoreLabel);
        currentUsername = getApplicationContext().getSharedPreferences("sharedUser", MODE_PRIVATE);
        user = currentUsername.getString("thisUser", "User");
        gameName.setText("Sliding Tiles");
        gameScore.setText(getScorePerGame("SlidingTiles.txt", user));

    }

    public void goToScoreboardMenu (View view){
        Intent intent = new Intent (this, ScoreboardActivity.class);
        startActivity(intent);
    }

    public String getScorePerGame(String fileName, String targetUser) {
        String score = "0";
        String line;
        BufferedReader reader;
        int index;
        try {
            reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            while ((line = reader.readLine()) != null) {
                index = line.indexOf(",");
                String user = line.substring(0, index - 1);
                if (user.equals(targetUser)) {
                    score = line.substring(index + 1, line.length() - 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        } return score;

        }
    }






