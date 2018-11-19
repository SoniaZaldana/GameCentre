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
import java.util.List;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Score.ScoreBoardArrayAdapter;
import fall2018.csc2017.GameCentre.Score.ScoreboardMenuActivity;


/**
 * Top scores of each user in the Sliding Tiles Game
 */
public class GameScoreboardActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private ListView listView;
    private ScoreBoardArrayAdapter myAdapter;
    private HashMap<String, String> usernamesAndScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_scoreboard);
        String gameFile = getIntent().getStringExtra("GameFile");
        usernamesAndScores  = TextFileManager.getValue(this, gameFile);
        back = findViewById(R.id.GoBack);
        listView = findViewById(R.id.listView);
        myAdapter = new ScoreBoardArrayAdapter(this, usernamesAndScores);
        back.setOnClickListener(this);
        listView.setAdapter(myAdapter);
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
