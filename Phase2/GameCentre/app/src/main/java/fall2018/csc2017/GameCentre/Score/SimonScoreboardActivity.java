package fall2018.csc2017.GameCentre.Score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;

import fall2018.csc2017.GameCentre.R;

public class SimonScoreboardActivity extends GameScoreboardActivity implements View.OnClickListener {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_scoreboard);
        HashMap<String, String> usernamesAndScores  = getValue("Simon.txt");
        ScoreBoardArrayAdapter my_adapter = new ScoreBoardArrayAdapter(this, usernamesAndScores);
        back = findViewById(R.id.GoBack);
        back.setOnClickListener(this);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(my_adapter);
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
