package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.content.Intent;

abstract class MovementController {
    private SlidingBoardManager slidingBoardManager = null;

    /**
     * Setter for board Manager
     * @param slidingBoardManager - board manager we want
     */ // TODO: This needs to be generalized, not in terms of sliding board manager
    void setSlidingBoardManager(SlidingBoardManager slidingBoardManager) {
        this.slidingBoardManager = slidingBoardManager;
    }

    /**
     * Getter for board manager
     * @return the board manager
     */ // TODO: this also needs to be generalized, Sonia.
    SlidingBoardManager getSlidingBoardManager(){
        return this.slidingBoardManager;
    }
    /**
     * Performs actions in order to calculate score and move on to score activity
     * @param context - the activity's context
     * @param gameFile - the name of the game file
     * @param scoreClass - the score activity to be opened
     */
    void moveOnToScoreActivity(Context context, String gameFile, Class scoreClass, int score) {
        Intent intent = new Intent(context, scoreClass);
        intent.putExtra("Score", score);
        intent.putExtra("Game", gameFile);
        context.startActivity(intent);
    }

    abstract void processTapMovement(Context context, int position);
}
