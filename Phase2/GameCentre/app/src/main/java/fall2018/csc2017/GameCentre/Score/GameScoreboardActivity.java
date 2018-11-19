package fall2018.csc2017.GameCentre.Score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;

import fall2018.csc2017.GameCentre.R;


/**
 * Top scores of each user in each of the 3 games
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
                startActivity(new Intent(this, MenuScoreboardsActivity.class));
                break;
        }
    }

}
