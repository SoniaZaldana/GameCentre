package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MovementController {

    private BoardManager boardManager = null;
    // Added move variable to keep track of how many moves are made
    public static int moves;

    public MovementController() {
        moves = 0;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            moves++;
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                moveOnToScoreActivity(context);
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Performs actions in order to calculate score and move on to score activity
     *
     * @param context
     */
    private void moveOnToScoreActivity(Context context) {
        int score = boardManager.calculateScore(moves);
        Intent intent = new Intent(context, ScoreCalculatorActivity.class);
        intent.putExtra("Score", score);
        intent.putExtra("Game", "SlidingTiles.txt");
        context.startActivity(intent);
    }
}
