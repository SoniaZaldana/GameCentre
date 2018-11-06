package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.valueOf;

public class SlidingScoreboardActivity extends AppCompatActivity implements View.OnClickListener {
    Button back;
    boolean USERS = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_scoreboard);
        TextView userScore = findViewById(R.id.UserScore);
        ArrayList<String> usernames = getValue("SlidingTiles.txt", USERS);
        ArrayList<String> scores = getValue("SlidingTiles.txt", !USERS);
        back = findViewById(R.id.GoBack);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GoBack:
                startActivity(new Intent(this, ScoreboardMenuActivity.class));
                break;
        }
    }

    private ArrayList<String> getValue(String fileName, boolean wantUsers) {
        ArrayList<String> usernames = new ArrayList<String>();
        ArrayList<String> scores = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(",");
                String user = line.substring(1, index);
                String score = line.substring(index + 1, line.length() - 1);
                usernames.add(user);
                scores.add(score);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (wantUsers)
            return usernames;
        return scores;
    }


}
