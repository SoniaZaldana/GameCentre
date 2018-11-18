package fall2018.csc2017.GameCentre.Score;

import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

class GameScoreboardActivity extends AppCompatActivity {

    /**
     * Returns a hash map containing coupled values for the contents of fileName
     * @param fileName - the file from which we are pulling the contents from
     * @return A hashmap containing coupled values <user><score> or <game><score>
     */
    HashMap<String, String> getValue(String fileName) {
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
