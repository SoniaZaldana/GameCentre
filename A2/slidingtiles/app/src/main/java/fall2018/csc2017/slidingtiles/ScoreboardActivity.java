package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoreboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        //TODO: fix so that it goes to Game Launcher activity instead of Game Activity
        Button menu = findViewById(R.id.MainMenuButton);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreboardActivity.this, GameActivity.class));
            }
        });

        Button myScoreboard = findViewById(R.id.MyScoreboardLabel);
        myScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreboardActivity.this, UserScoreboardActivity.class));
            }
        });

        Button sliding = findViewById(R.id.SlidingTilesScoreboardLabel);
        sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreboardActivity.this, SlidingScoreboardActivity.class));
            }
        });
    }






}
