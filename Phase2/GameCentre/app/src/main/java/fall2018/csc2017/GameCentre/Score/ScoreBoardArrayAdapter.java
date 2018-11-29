/**
 * Excluded from tests because it is a view class.
 */
package fall2018.csc2017.GameCentre.Score;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fall2018.csc2017.GameCentre.R;

public class ScoreBoardArrayAdapter extends ArrayAdapter {

    private Context context;
    /**
     * HashMap which contains the users as keys and their HighScores as the values
     */
    private Map<String, Double> usernameAndScores;
    /**
     * List of userNames
     */
    private List<String> userNames;

    /**
     * ArrayAdapter which fills a row with 2 elements: 1 for the username and 1 for the HighScore
     * @param context current context
     * @param usernameAndScores HashMap which contains the users as keys and their HighScores as the values
     */
    public ScoreBoardArrayAdapter(Context context, Map<String, Double> usernameAndScores)
    {
        super(context, android.R.layout.simple_list_item_1, new ArrayList<>(usernameAndScores.keySet()));
        this.context = context;
        this.usernameAndScores = usernameAndScores;
        this.userNames = new ArrayList<>(usernameAndScores.keySet());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_sliding_scoreboard_row, parent, false);

        TextView your_first_text_view =  rowView.findViewById(R.id.username);
        TextView your_second_text_view = rowView.findViewById(R.id.score);
        String currUser = userNames.get(position);
        your_first_text_view.setText(currUser);
        your_second_text_view.setText(String.valueOf(usernameAndScores.get(currUser)));

        return rowView;
    }
}

