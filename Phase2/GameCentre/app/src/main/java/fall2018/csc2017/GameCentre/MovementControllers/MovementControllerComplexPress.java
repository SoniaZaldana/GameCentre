package fall2018.csc2017.GameCentre.MovementControllers;

import android.content.Context;

import fall2018.csc2017.GameCentre.BoardManager;

public abstract class MovementControllerComplexPress<T extends BoardManager> extends MovementController<T>{
    /**
     * Calls for processMove, specifying event in String.
     * @param context
     * @param position
     * @param e
     */
    public abstract void  processMove(Context context, int position, int e);

}
