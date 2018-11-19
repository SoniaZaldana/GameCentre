package fall2018.csc2017.GameCentre.Score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Score.ScoreBoardArrayAdapter;
import fall2018.csc2017.GameCentre.Score.ScoreboardMenuActivity;


/**
 * Top scores of each user in the Sliding Tiles Game
 */
public class SlidingScoreboardActivity extends GameScoreboardActivity implements View.OnClickListener {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_scoreboard);
        back = findViewById(R.id.GoBack);
        back.setOnClickListener(this);
        setGrid("SlidingTiles.txt");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GoBack:
                startActivity(new Intent(this, ScoreboardMenuActivity.class));
                break;
        }
    }

}