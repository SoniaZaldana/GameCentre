package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ScoreboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
    }

    // TODO: fix the activity so that it is redirected to game launcher activity
    public void goToMainMenu (View view){
        Intent intent = new Intent (this, GameActivity.class);
        startActivity(intent);
    }
    public void goToUserScoreboard (View view){
        Intent intent = new Intent (this, UserScoreboardActivity.class);
        startActivity(intent);
    }
    //TODO: implement the slidingscoreboardactivity
    public void goToSlidingTilesScoreboard (View view){
        Intent intent = new Intent (this, SlidingScoreboardActivity.class);
        startActivity(intent);
    }




}
