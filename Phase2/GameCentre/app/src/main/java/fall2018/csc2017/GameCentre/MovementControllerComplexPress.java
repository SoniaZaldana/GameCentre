package fall2018.csc2017.GameCentre;

import android.content.Context;

public abstract class MovementControllerComplexPress<T extends BoardManager> extends MovementController<T>{
    /**
     * Calls for processMove, specifying event in String.
     * @param context
     * @param position
     * @param e
     */
    abstract void  processMove(Context context, int position, int e);

}
