package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.valueOf;

public class SlidingScoreboardActivity extends AppCompatActivity implements View.OnClickListener {
Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_scoreboard);
        TextView userScore = findViewById(R.id.UserScore);
        StringBuilder text = getScore("SlidingTiles.txt");
        userScore.setText(text);
        back = findViewById(R.id.GoBack);
        back.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GoBack:
                startActivity(new Intent(this, ScoreboardActivity.class));
                break;
        }
    }

    private StringBuilder getScore(String fileName) {
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int index = line.indexOf(",");
                String score = line.substring(index + 1, line.length()-1);
                String user = line.substring(0, index);
                text.append(user + score);
                text.append('\n');
        }
        bufferedReader.close();
    } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }


}
