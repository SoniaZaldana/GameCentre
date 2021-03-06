
package fall2018.csc2017.GameCentre.MovementControllers;

import android.content.Context;
import android.content.Intent;

import java.util.Observable;

import fall2018.csc2017.GameCentre.BoardManager;

/**
 * Movement controller that defines how the boardManager behaves upon the user interracting with the board, i.e physical clicks.
 * @param <T> is the type of boardManager that uses the MovementModel.
 */
public abstract class MovementModel<T extends BoardManager> extends Observable {
    /**
     * type of boardManager used by MovementModel
     */
    private T boardManager;

    /**
     *
     * @param boardManager used by MovementModel
     */
    public void setBoardManager(T boardManager){
        this.boardManager = boardManager;
    }

    /**
     *
     * @return boardManager used by MovementModel.
     */
    public T getBoardManager(){
        return boardManager;
    }

    /**
     *
     * @param context of app
     * @param gameFile name of file where scores for this game are stored
     * @param scoreClass scoreActivity which will display the score.
     * @param score score for this game.
     */
    public void moveOnToScoreActivity(Context context, String gameFile, Class scoreClass, int score) {
        Intent intent = new Intent(context, scoreClass);
        intent.putExtra("Score", score);
        intent.putExtra("Game", gameFile);
        context.startActivity(intent);
    }
}
