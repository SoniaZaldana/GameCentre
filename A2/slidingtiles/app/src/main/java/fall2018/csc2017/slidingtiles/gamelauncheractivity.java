package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class gamelauncheractivity extends AppCompatActivity implements View.OnClickListener {
    ImageView TilesGame;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelauncheractivity);
        TilesGame = findViewById(R.id.TilesGame);
        TilesGame.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.TilesGame:
                startActivity(new Intent(this, StartingActivity.class));
                break;

        }
    }
}
