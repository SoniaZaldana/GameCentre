package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoreboardActivity extends AppCompatActivity implements View.OnClickListener {
    String user = getIntent().getStringExtra("Username");
    Button menu = findViewById(R.id.MainMenuButton);
    Button myScoreboard = findViewById(R.id.MyScoreboardLabel);
    Button sliding = findViewById(R.id.SlidingTilesScoreboardLabel);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        menu.setOnClickListener(this);
        myScoreboard.setOnClickListener(this);
        sliding.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MainMenuButton:
                startActivity(new Intent(this, Gamelauncheractivity.class));
                break;
            case R.id.MyScoreboardLabel:
                startActivity(new Intent(this, UserScoreboardActivity.class).putExtra("Username", user));
                break;
            case R.id.SlidingTilesScoreboardLabel:
                startActivity(new Intent(this, SlidingScoreboardActivity.class).putExtra("Username", user));
        }
    }
}
