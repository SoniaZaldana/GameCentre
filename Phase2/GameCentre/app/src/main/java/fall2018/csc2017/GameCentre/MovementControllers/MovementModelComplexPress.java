package fall2018.csc2017.GameCentre.MovementControllers;

import android.content.Context;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.ClicksOnBoard;

/**
 * Movement controller that defines how the boardManager behaves upon the user interracting with the board, in a complex manner(long press, short press, double click, etc).
 * @param <T>
 */
public abstract class MovementModelComplexPress<T extends BoardManager> extends MovementModel<T> {
    /**
     * Method is called when user interacts with the board and defines behaviour of BoardManager on interaction.
     * @param context from the app.
     * @param position of the click on the board
     * @param e contains what type of move this is. All of them can be found in the enum MovementTypes.
     */
    public abstract void  processMove(Context context, int position, Enum<ClicksOnBoard>  e);

}
