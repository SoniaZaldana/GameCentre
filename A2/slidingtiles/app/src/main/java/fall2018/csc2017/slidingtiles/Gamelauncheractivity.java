package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Gamelauncheractivity extends AppCompatActivity implements View.OnClickListener {
    Button SignOff;
    ImageView TilesGame;
    String UsersInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelauncheractivity);
        UsersInfo = getIntent().getStringExtra("Username");
        TilesGame = findViewById(R.id.TilesGame);
        SignOff = findViewById(R.id.signoff);
        TilesGame.setOnClickListener(this);
        SignOff.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.TilesGame:
                startActivity(new Intent(this,
                        StartingActivity.class).putExtra("Username", UsersInfo));
                break;
            case R.id.signoff:
                startActivity(new Intent(this, Loginactivity.class));
                break;

        }
    }
}
