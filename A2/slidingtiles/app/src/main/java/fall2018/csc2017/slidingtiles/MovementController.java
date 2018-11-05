package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;


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
                int score = boardManager.calculateScore(moves);
                Intent intent = new Intent(context, ScoreCalculatorActivity.class);
                intent.putExtra("Score", score);
                intent.putExtra("Game", "SlidingTiles.txt");
                context.startActivity(intent);

            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
