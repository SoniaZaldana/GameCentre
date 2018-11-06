package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * Top scores of each user in the Sliding Tiles Game
 */
public class SlidingScoreboardActivity extends AppCompatActivity implements View.OnClickListener {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_scoreboard);
        HashMap<String, String> usernamesAndScores  = getValue("SlidingTiles.txt");
        back = findViewById(R.id.GoBack);
        back.setOnClickListener(this);
        ListView listView = findViewById(R.id.listView);
        ScoreBoardArrayAdapter my_adapter = new ScoreBoardArrayAdapter(this, usernamesAndScores);
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

    /**
     * Returns a hash map containing coupled values for the contents of fileName
     * @param fileName - the file from which we are pulling the contents from
     * @return A hashmap containing coupled values <user><score> or <game><score>
     */
    private HashMap<String, String> getValue(String fileName) {
        HashMap<String, String> usernamesAndScores =  new LinkedHashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.getFilesDir(), fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(",");
                String user = line.substring(1, index);
                String score = line.substring(index + 1, line.length() - 1);
                usernamesAndScores.put(user, score);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usernamesAndScores;
    }
}
