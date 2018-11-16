package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

abstract class MovementController<T extends BoardManager> {
    private T boardManager;

    public void setBoardManager(T boardManager){
        this.boardManager = boardManager;
    }

    public T getBoardManager(){
        return boardManager;
    }

    public void moveOnToScoreActivity(Context context, String gameFile, Class scoreClass, int score) {
        Intent intent = new Intent(context, scoreClass);
        intent.putExtra("Score", score);
        intent.putExtra("Game", gameFile);
        context.startActivity(intent);
    }
}
