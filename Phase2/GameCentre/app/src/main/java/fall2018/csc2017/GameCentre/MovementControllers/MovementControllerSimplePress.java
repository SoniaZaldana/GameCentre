package fall2018.csc2017.GameCentre.MovementControllers;

import android.content.Context;

import fall2018.csc2017.GameCentre.BoardManager;

/**
 * A movement controller for a Boardmanager which only accepts normal short clicks.
 * @param <T>
 */
public abstract class MovementControllerSimplePress<T extends BoardManager>  extends MovementController<T>{
    /**
     * Method is called when user interracts with the board and defines behaviour of BoardManager
     * on interaction.
     * @param context the context from the app
     * @param position the position of the click on the board.
     */
    public abstract void  processMove(Context context, int position);

}
