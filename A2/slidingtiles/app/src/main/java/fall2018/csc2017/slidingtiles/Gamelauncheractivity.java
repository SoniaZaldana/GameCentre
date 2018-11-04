package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class Gamelauncheractivity extends AppCompatActivity implements View.OnClickListener {
    Button SignOff;
    Button Scoreboards;
    ImageView TilesGame;
    String UsersInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelauncheractivity);

        UsersInfo = getIntent().getStringExtra("Username");
        SharedPreferences currentUsername = getApplicationContext().getSharedPreferences("sharedUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = currentUsername.edit();
        editor.putString("thisUser", UsersInfo);
        editor.apply();

        TilesGame = findViewById(R.id.TilesGame);
        SignOff = findViewById(R.id.signoff);
        Scoreboards = findViewById(R.id.ScoreboardButton);
        TilesGame.setOnClickListener(this);
        SignOff.setOnClickListener(this);
        Scoreboards.setOnClickListener(this);
        try {
            createFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFiles() throws IOException {
        File userScoreFile = new File(UsersInfo + ".txt");
        File slidingFile = new File("SlidingTiles.txt");
        userScoreFile.createNewFile();
        slidingFile.createNewFile();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.TilesGame:
                startActivity(new Intent(this,
                        StartingActivity.class));
                break;
            case R.id.signoff:
                startActivity(new Intent(this, Loginactivity.class));
                break;
            case R.id.ScoreboardButton:
                startActivity(new Intent(this,
                        ScoreboardActivity.class));
                break;
        }
    }
}
