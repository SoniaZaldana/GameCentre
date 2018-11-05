package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import java.io.File;
import java.io.IOException;
import java.util.function.ToLongBiFunction;

public class Gamelauncheractivity extends AppCompatActivity implements View.OnClickListener {
    Button SignOff;
    Button Scoreboards;
    ImageView TilesGame;
    String UsersInfo;
    SharedPreferences currentUsername;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelauncheractivity);

        UsersInfo = getIntent().getStringExtra("Username");
        currentUsername = getApplicationContext().getSharedPreferences("sharedUser", MODE_PRIVATE);
        editor = currentUsername.edit();
        editor.putString("thisUser", UsersInfo);
        editor.apply();
//        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        TilesGame = findViewById(R.id.TilesGame);
        SignOff = findViewById(R.id.signoff);
        Scoreboards = findViewById(R.id.ScoreboardButton);
        TilesGame.setOnClickListener(this);
        SignOff.setOnClickListener(this);
        Scoreboards.setOnClickListener(this);
        try {
            //TODO: change this when osman's null error is fixed
            ContextWrapper cw = new ContextWrapper(this);
            File directory = cw.getDir("media", MODE_PRIVATE);
            File userScoreFile = new File( "Sonia.txt");
            File slidingFile = new File("SlidingTiles.txt");
            userScoreFile.createNewFile();
            slidingFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
