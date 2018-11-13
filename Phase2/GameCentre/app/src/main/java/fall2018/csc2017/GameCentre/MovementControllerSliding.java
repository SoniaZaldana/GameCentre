package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.widget.Toast;

public class MovementControllerSliding extends MovementController {

    /**
     * Moves variable keeps track of number of moves
     */
    private int moves;
    /**
     * Score variable to store calculated score
     */
    private int score = 0;

    MovementControllerSliding() {
        moves = 0;
    }

    @Override
    public void processTapMovement(Context context, int position) {
        if (getBoardManager().isValidTap(position)) {
            getBoardManager().touchMove(position);
            moves++;
            if (getBoardManager().isGameFinished()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                score = getBoardManager().calculateScore(moves);
                moveOnToScoreActivity(context, "SlidingTiles.txt", ScoreCalculatorActivity.class, score);
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
