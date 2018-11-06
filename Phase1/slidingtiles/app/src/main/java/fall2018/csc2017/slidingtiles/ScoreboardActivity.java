package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoreboardActivity extends AppCompatActivity implements View.OnClickListener {
    Button menu;
    Button myScoreboard;
    Button sliding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        setUpButtonsAndLabels();

    }

    /**
     * Performs the actions to set up buttons and labels
     */
    private void setUpButtonsAndLabels() {
        menu = findViewById(R.id.MainMenuButton);
        myScoreboard = findViewById(R.id.MyScoreboardLabel);
        sliding = findViewById(R.id.SlidingTilesScoreboardLabel);
        menu.setOnClickListener(this);
        myScoreboard.setOnClickListener(this);
        sliding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MainMenuButton:
                startActivity(new Intent(this, GameLauncherActivity.class));
                break;
            case R.id.MyScoreboardLabel:
                startActivity(new Intent(this, UserScoreboardActivity.class));
                break;
            case R.id.SlidingTilesScoreboardLabel:
                startActivity(new Intent(this, SlidingScoreboardActivity.class));
        }
    }
}
