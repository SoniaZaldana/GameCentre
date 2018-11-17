package fall2018.csc2017.GameCentre.MovementControllers;

import android.content.Context;

import fall2018.csc2017.GameCentre.BoardManager;

public abstract class MovementControllerSimplePress<T extends BoardManager>  extends MovementController<T>{
    public abstract void  processMove(Context context, int position);

}
