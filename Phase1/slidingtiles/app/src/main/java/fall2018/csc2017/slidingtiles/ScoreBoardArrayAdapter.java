package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardArrayAdapter extends ArrayAdapter {

    private Context context;
    private List<String> usernames;
    private List<String> score;

    public ScoreBoardArrayAdapter(Context context, List<String> usernames, List<String> score)
    {
        super(context, android.R.layout.simple_list_item_1, usernames);
        this.context = context;
        this.usernames = usernames;
        this.score = score;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_sliding_scoreboard_row, parent, false);

        TextView your_first_text_view = (TextView) rowView.findViewById(R.id.username);
        TextView your_second_text_view = (TextView) rowView.findViewById(R.id.score);

        your_first_text_view.setText(usernames.get(position));
        your_second_text_view.setText(score.get(position)); //Instead of the same value use position + 1, or something appropriate

        return rowView;
    }
}

