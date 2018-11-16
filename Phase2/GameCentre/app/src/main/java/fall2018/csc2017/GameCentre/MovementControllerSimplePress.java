package fall2018.csc2017.GameCentre;

import android.content.Context;

public abstract class MovementControllerSimplePress<T extends BoardManager>  extends MovementController<T>{
    public abstract void  processMove(Context context, int position);

}
