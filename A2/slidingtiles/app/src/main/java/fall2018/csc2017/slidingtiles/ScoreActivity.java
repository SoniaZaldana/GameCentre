package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView scoreValue = findViewById(R.id.ScoreValueLabel);

        int score = getIntent().getIntExtra("SCORE", 0);
        String user = getIntent().getStringExtra("USERNAME");
        String gameID = getIntent().getStringExtra("GAME_ID");
        scoreValue.setText(score);

        SharedPreferences spGame = getSharedPreferences("SlidingScore", Context.MODE_PRIVATE);
        long highScoreGame = spGame.getLong(user, 0);
        if (score > highScoreGame) {
            SharedPreferences.Editor editor = spGame.edit();
            editor.putInt(user, score);
            editor.apply();
        }
        SharedPreferences spUser = getSharedPreferences("UserScore", Context.MODE_PRIVATE);
        long highScoreUser = spUser.getLong(gameID, 0);
        if (score > highScoreUser) {
            SharedPreferences.Editor editor = spGame.edit();
            editor.putInt(gameID, score);
            editor.apply();

        }
    }
}
